package com.duia;/**
 * Created by Administrator on 2017/12/25 0025.
 */

import java.util.Date;

/**
 * @author xingshaofei
 * @date 2017-12-25 下午 3:41
 */
public class TestSkipFieldBean {

    private String name;

    private Date createTime;

    private String beNull;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getBeNull() {
        return beNull;
    }

    public void setBeNull(String beNull) {
        this.beNull = beNull;
    }
}
