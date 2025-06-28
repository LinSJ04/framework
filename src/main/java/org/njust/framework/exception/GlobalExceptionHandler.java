package org.njust.framework.exception;

import org.njust.framework.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.njust.framework.enums.ResultCodeEnum.*;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    // 处理所有未被捕获的异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<String> handleException(Exception ex) {
        ex.printStackTrace(); // 可以记录日志
        return Result.error(INTERNAL_SERVER_ERROR);
    }

    // 处理所有未被捕获的异常
    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<String> handleBaseException(BaseException ex) {
        ex.printStackTrace(); // 可以记录日志
        return Result.error(500, ex.getMessage());
    }

    // 处理空指针异常
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<String> handleNullPointerException(NullPointerException ex) {
        ex.printStackTrace();
        return Result.error(NULL_POINTER_EXCEPTION);
    }

    // 处理非法参数异常
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        ex.printStackTrace();
        return Result.error(ILLEGAL_ARGUMENT_EXCEPTION);
    }

    // 可扩展更多异常类型...
}