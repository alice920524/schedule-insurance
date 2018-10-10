package com.duia.mars.dto;

/**
 * 发送传输数据结果
 * @author xingshaofei
 * @date 2017-12-28 下午 3:57
 */
public class SendTransmitResult {

    public SendTransmitResult(){}

    public SendTransmitResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
