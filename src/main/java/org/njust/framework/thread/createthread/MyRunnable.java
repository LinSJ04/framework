package org.njust.framework.thread.createthread;

/**
 * 创建线程方式二：实现Runnable接口，重写run方法
 */
public class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("实现Runnable接口，重写run方法...");
    }

    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();
    }
}
