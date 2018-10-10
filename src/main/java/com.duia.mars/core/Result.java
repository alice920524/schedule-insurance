package com.duia.mars.core;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;

import java.util.Collection;

/**
 * Created by 李恒名 on 2017/6/13.
 * 统一API响应结果封装
 */
public class Result {
    private int code;
    private String message;
    private Object data;

    public Result() {
        this.code = ResultCode.NO_DATA.code;
        this.message = ResultCode.NO_DATA.msg;
    }

    public Result setCode(ResultCode resultCode) {
        this.code = resultCode.code;
        return this;
    }

    public int getCode() {
        return code;
    }

    public Result setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        /*if ((null == data) ||
                ((data instanceof Collection) && ((Collection) data).size() == 0) ||
                (data instanceof PageInfo) && (null == ((PageInfo) data).getList()) ||
                (data instanceof PageInfo) && (0 == ((PageInfo) data).getSize())) {
            this.code = ResultCode.NO_DATA.code;
            this.message = ResultCode.NO_DATA.msg;
            this.data = null;
        } else {
            this.data = data;
        }*/
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
