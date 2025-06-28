package org.njust.framework.enums;

public enum ResultCodeEnum {

    SUCCESS(200, "操作成功"),
    BAD_REQUEST(400, "请求参数错误"),
    INTERNAL_SERVER_ERROR(500, "系统内部异常"),
    NULL_POINTER_EXCEPTION(500, "空指针异常"),
    ILLEGAL_ARGUMENT_EXCEPTION(500, "非法参数异常");


    private final int code;
    private final String message;

    ResultCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}