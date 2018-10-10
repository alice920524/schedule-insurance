package com.duia.mars.common.dto;

import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * @Author: liuxue
 * @Date: 2017/11/14
 */
@SuppressWarnings("serial")
@Alias("PointCleanTime")
public class PointCleanTime {

    /** 创建时间 */
    private Date createDate;
    /** 设置的清零时间 */
    private String cleanTime;
    /** 可用性 */
    private Boolean useable;
    /** cron表达式 */
    private String cronExpression;

    public PointCleanTime() {}

    public PointCleanTime(Date createDate, String cleanTime, Boolean useable, String cronExpression) {
        this.createDate = createDate;
        this.cleanTime = cleanTime;
        this.useable = useable;
        this.cronExpression = cronExpression;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCleanTime() {
        return cleanTime;
    }

    public void setCleanTime(String cleanTime) {
        this.cleanTime = cleanTime;
    }

    public Boolean getUseable() {
        return useable;
    }

    public void setUseable(Boolean useable) {
        this.useable = useable;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }
}
