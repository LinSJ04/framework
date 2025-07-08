package org.njust.framework.thread.createthread;

/**
 * 创建线程方式一：继承Thread类，重写run方法
 */
public class MyThread extends Thread{
    @Override
    public void run() {
        System.out.println("继承Thread类，重写run方法...");
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
    }
}
