package org.njust.framework.thread.createthread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 创建线程方式3：实现Callable接口，重写call()方法，并且可以拿到线程执行完的返回值
 */
public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("实现Callable接口，重写call()方法，并且可以拿到线程执行完的返回值...");
        return "模拟返回值";
    }

    public static void main(String[] args) {
        MyCallable myCallable = new MyCallable();
        // 用FutureTask封装Callable
        FutureTask<String> futureTask = new FutureTask<>(myCallable);
        new Thread(futureTask).start();
        try {
            // 获取返回值
            String result = futureTask.get();
            System.out.println("result = " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
