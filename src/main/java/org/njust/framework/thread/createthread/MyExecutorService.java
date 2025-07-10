package org.njust.framework.thread.createthread;

import java.util.concurrent.*;

/**
 * 创建线程方式四：使用ExecutorService创建线程池，也可以自定义线程池
 */
public class MyExecutorService {
    public static void main(String[] args) {
        ExecutorService poolA = Executors.newFixedThreadPool(2);
        poolA.execute(() -> {
            System.out.println("使用ExecutorService创建线程池...");
        });
        // 关闭线程池
        poolA.shutdown();
        // 自定义线程池
        ThreadPoolExecutor poolB = new ThreadPoolExecutor(2, 3, 0, TimeUnit.MINUTES,
                new LinkedBlockingDeque<Runnable>(3), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        poolB.execute(() -> {
            System.out.println("自定义线程池...");
        });
        poolB.shutdown();
    }
}
