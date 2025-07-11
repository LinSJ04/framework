package org.njust.framework.lock;

public class MyDeadLock {
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        // 要记得start线程
        new Thread(() -> {
           synchronized (lock1) {
               System.out.println("Thread 1: Holding lock 1...");
               try {
                   Thread.sleep(6000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               synchronized (lock2) {
                   System.out.println("Thread 1: Holding lock 1 & 2...");
               }
           }
        }).start();
        new Thread(() -> {
            synchronized (lock2) {
                System.out.println("Thread 2: Holding lock 2...");
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println("Thread 2: Holding lock 1 & 2...");
                }
            }
        }).start();
    }
}
