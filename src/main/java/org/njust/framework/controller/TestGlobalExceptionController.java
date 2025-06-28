package org.njust.framework.controller;

import org.njust.framework.exception.BaseException;
import org.njust.framework.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test-global-exception")
public class TestGlobalExceptionController {

    @GetMapping("/test")
    public Result<String> test(@RequestParam String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("名称不能为空");
        }
        return Result.success("Hello, " + name);
    }

    // Spring Boot 会在 controller 尚未加载完成前就尝试访问 /error 接口，导致冲突或无法正确注册该映射。
//    @GetMapping("/error")
//    public Result<String> errorTest() {
//        String str = null;
//        return Result.success(str.toUpperCase()); // 触发 NullPointerException
//    }

    @GetMapping("/test-error")
    public Result<String> errorTest() {
        String str = null;
        return Result.success(str.toUpperCase()); // 触发 NullPointerException
    }

    @GetMapping("/exception")
    public Result<String> exceptionTest() {
        throw new RuntimeException("这是一个运行时异常");
    }

    @GetMapping("/test-BaseException")
    public Result<String> baseExceptionTest() {
        throw new BaseException("这是一个自定义业务异常");
    }
}