package com.duia.mars.http;

/**
 * 响应体
 * @author xingshaofei
 * @date 2018-01-23 下午 4:41
 */
public class HttpResponseEntity {

    private int statusCode;
    private String entityString;

    public HttpResponseEntity() {}

    public HttpResponseEntity(int statusCode, String entity) {
        this.statusCode = statusCode;
        this.entityString = entity;
    }

    public int getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getEntityString() {
        return entityString;
    }
    public void setEntityString(String entityString) {
        this.entityString = entityString;
    }
}
