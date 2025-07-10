package org.njust.framework.coding;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 自定义一个Cache：
 *  每秒读入百万条数据：key,value,过期时间
 *  key是50字节的字符串
 *  value是10k字节的json字符串
 *  过期时间为600-3600s
 *  要求设计数据结构对数据进行存储和读取，并且能够及时删除过期数据
 */
public class Cache {
    // 缓存实体类
    private static class CacheEntry {
        private String key;
        private String value;
        private long expireAt;

        public CacheEntry(String key, String value, long expireAt) {
            this.key = key;
            this.value = value;
            this.expireAt = expireAt;
        }
    }

    // 主缓存映射
    private final ConcurrentHashMap<String, CacheEntry> cache = new ConcurrentHashMap<>();
    // 优先队列，按照过期时间排序
    private final PriorityQueue<CacheEntry> expireQueue = new PriorityQueue<>(new Comparator<CacheEntry>() {
        @Override
        public int compare(CacheEntry o1, CacheEntry o2) {
            return (int) (o1.expireAt - o2.expireAt); // 过期时间升序排列
        }
    });
    // 是否为自动清理
    private volatile boolean running = true;
    // 自动清理线程
    private final Thread cleanerThread = new Thread(() -> {
        while (running) {
            try {
                Thread.sleep(1000); // 每秒清理一次
                long now = System.currentTimeMillis();
                synchronized (this) {
                    while (!expireQueue.isEmpty() && expireQueue.peek().expireAt <= now) {
                        CacheEntry entry = expireQueue.poll();
                        cache.remove(entry.key);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });
    public Cache() {
        cleanerThread.setDaemon(true); // 设置为守护线程
        cleanerThread.start(); // 启动自动清理线程
    }

    public void put(String key, String value, long expire) {
        // 先把旧缓存删除
        if (cache.containsKey(key)) {
            remove(key);
        }
        CacheEntry entry = new CacheEntry(key, value, System.currentTimeMillis() + expire * 1000);
        cache.put(key, entry);
        synchronized (this) {
            // 空间不够不会抛异常
            expireQueue.offer(entry);
        }
    }

    public String get(String key) {
        CacheEntry entry = cache.get(key);
        if (entry != null && entry.expireAt >= System.currentTimeMillis()) {
            return entry.value;
        } else if (entry != null) {
            remove(key);
        }
        return null;
    }

    public void remove(String key) {
        cache.remove(key);
        synchronized (this) {
            expireQueue.removeIf(entry -> entry.key.equals(key));
        }
    }

    // 关闭线程
    public void shutdown() {
        running = false;
    }

    // main 方法测试用例
    public static void main(String[] args) throws InterruptedException {
        Cache cache = new Cache();

        cache.put("k1", "v1", 5); // 5秒过期
        cache.put("k2", "v2", 10); // 10秒过期

        System.out.println("k1: " + cache.get("k1")); // v1
        System.out.println("k2: " + cache.get("k2")); // v2

        Thread.sleep(6000); // 等待 k1 过期
        System.out.println("k1 after 6s: " + cache.get("k1")); // null
        System.out.println("k2 after 6s: " + cache.get("k2")); // v2

        cache.shutdown();
    }
}
