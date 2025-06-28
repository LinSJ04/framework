package org.njust.framework.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class TestAsyncService {

    @Async("taskExecutor") // 使用指定的线程池
    public void doAsyncTask(int taskId) {
        System.out.println("【开始任务 " + taskId + "】当前线程：" + Thread.currentThread().getName());

        try {
            // 模拟耗时操作（例如数据库操作）
            Thread.sleep(2000); // 2秒
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("【完成任务 " + taskId + "】当前线程：" + Thread.currentThread().getName());
    }
}