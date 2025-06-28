package org.njust.framework.exception;

public class BaseException extends RuntimeException {

    public BaseException() {}

    public BaseException(String msg) {
        super(msg);
    }

    public BaseException(String msg, Throwable cause) {
        super(msg, cause);
    }

}