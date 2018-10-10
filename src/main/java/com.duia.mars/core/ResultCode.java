package com.duia.mars.core;

/**
 * Created by 李恒名 on 2017/6/13.
 * 响应码枚举，参考HTTP状态码的语义
 */
public enum ResultCode {
    SUCCESS(200,"请求成功"),//成功
    FAIL(400,"请求失败"),//失败
    UNAUTHORIZED(401,"认证失败"),//未认证（签名错误）
    NO_CLASS_TICKET(402,"未携带班级认证"),//未认证（签名错误）
    NOT_FOUND(404,"不存在的资源"),//接口不存在
    INTERNAL_SERVER_ERROR(500,"服务器内部错误"),//服务器内部错误


    /*******自定义*********/
    NO_DATA(606,"没有数据");


    public int code;
    public String msg;

    ResultCode(int code,String msg) {
        this.code = code;
        this.msg = msg;
    }
}
