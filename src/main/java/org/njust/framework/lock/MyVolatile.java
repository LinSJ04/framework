package org.njust.framework.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class MyVolatile {
    private volatile static int cnt = 0;


//    public void increase() {
//        cnt++;
//    }

//    public synchronized void increase() { // 解决方法一：使用 synchronized
//        cnt++;
//    }

//    private static AtomicInteger cnt = new AtomicInteger(); // 解决方法二：使用AtomicInteger
//
//    public void increase() {
//        cnt.getAndIncrement();
//    }

    ReentrantLock lock = new ReentrantLock();
    public void increase() {
        // 解决方法三：使用 ReentrantLock
        lock.lock();
        try {
            cnt++;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        MyVolatile myVolatile = new MyVolatile();
        for (int i = 0; i < 5; i++) {
            executorService.execute(() -> {
                for (int j = 0; j < 500; j++) {
                    myVolatile.increase();
                }
            });
        }
        // 休息1.5秒，等待线程执行完毕
        Thread.sleep(1500);
        System.out.println(cnt);
        executorService.shutdown(); // 关闭线程池
    }
}
