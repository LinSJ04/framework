package org.njust.framework.lock;

import java.util.concurrent.atomic.LongAdder;

public class MyOptimisticLock {
    public static void main(String[] args) {
        // LongAdder在高并发场景中的性能由于AtomicLong和AtomicInteger
        // 代价就是会消耗更多的内存空间(空间换时间)
        LongAdder sum = new LongAdder();
        sum.increment();
    }
}
