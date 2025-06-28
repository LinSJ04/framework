package org.njust.framework.result;

import lombok.Data;
import org.njust.framework.enums.ResultCodeEnum;

@Data // Lombok依赖
public class Result<T> {
    private int code;        // 状态码
    private String message;  // 描述信息
    private T data;          // 返回数据

    public static <T> Result<T> success() {
        return new Result<>(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage(), null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage(), data);
    }

    public static <T> Result<T> error(int code, String message) {
        return new Result<>(code, message, null);
    }

    public static <T> Result<T> error(ResultCodeEnum resultCodeEnum) {
        return new Result<>(resultCodeEnum.getCode(), resultCodeEnum.getMessage(), null);
    }

    private Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}