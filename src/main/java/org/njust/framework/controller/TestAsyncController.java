package org.njust.framework.controller;

import org.njust.framework.service.TestAsyncService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test-async")
public class TestAsyncController {
    private final TestAsyncService testAsyncService;

    public TestAsyncController(TestAsyncService testAsyncService) {
        this.testAsyncService = testAsyncService;
    }

    @GetMapping("/async")
    public String triggerAsyncTasks() {
        System.out.println("主线程开始：" + Thread.currentThread().getName());

        for (int i = 1; i <= 5; i++) {
            testAsyncService.doAsyncTask(i);
        }

        System.out.println("主线程结束，任务已提交");

        return "已触发5个异步任务，请查看控制台输出";
    }
}
