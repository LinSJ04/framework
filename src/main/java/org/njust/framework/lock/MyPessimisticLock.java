package org.njust.framework.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyPessimisticLock {
    public void PerformSynchronizedTask() {
        synchronized (this) {
            // 同步操作
        }
    }

    public static void main(String[] args) {
        Lock lock =new ReentrantLock();
        lock.lock();
        try {
            // 同步操作
        } finally {
            lock.unlock();
        }
    }
}
