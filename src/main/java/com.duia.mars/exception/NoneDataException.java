package com.duia.mars.exception;

/**
 * 受检异常：没有数据异常
 * @author xingshaofei
 * @date 2018-01-23 上午 10:31
 */
public class NoneDataException extends Exception {

    public NoneDataException() {

        super();
    }

    public NoneDataException(String message) {

        super(message);
    }

    public NoneDataException(String message, Throwable cause) {

        super(message, cause);
    }

    public NoneDataException(Throwable cause) {

        super(cause);
    }

    protected NoneDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {

        super(message, cause, enableSuppression, writableStackTrace);
    }
}
