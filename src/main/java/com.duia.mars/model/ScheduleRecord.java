package com.duia.mars.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "schedule_record")
public class ScheduleRecord {


    /** 调度状态-开始 */
    public static final int STATUS_STARTED  = 0;
    /** 调度状态-成功 */
    public static final int STATUS_SUCCESS  = 1;
    /** 调度状态-失败 */
    public static final int STATUS_FAILED   = -1;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 调度类型
     */
    private Integer type;

    /**
     * 调度数据日期
     */
    @Column(name = "schedule_date")
    private Date scheduleDate;

    /**
     * 调度类
     */
    @Column(name = "class_type")
    private String classType;

    /**
     * 调度时间
     */
    @Column(name = "schedule_time")
    private Date scheduleTime;

    /**
     * 调度状态 0 调度开始 1 调度成功  -1 调度失败
     */
    private Integer status;

    /**
     * 执行耗时,毫秒
     */
    @Column(name = "execute_time")
    private String executeTime;

    /**
     * 日志备注
     */
    private String remark;

    /**
     * 区分调度任务归属项目[sale:营销后台, null:其他项目]
     */
    @Column(name = "project_key")
    private String projectKey;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取调度类型
     *
     * @return type - 调度类型
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置调度类型
     *
     * @param type 调度类型
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取调度数据日期
     *
     * @return schedule_date - 调度数据日期
     */
    public Date getScheduleDate() {
        return scheduleDate;
    }

    /**
     * 设置调度数据日期
     *
     * @param scheduleDate 调度数据日期
     */
    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    /**
     * 获取调度类
     *
     * @return class_type - 调度类
     */
    public String getClassType() {
        return classType;
    }

    /**
     * 设置调度类
     *
     * @param classType 调度类
     */
    public void setClassType(String classType) {
        this.classType = classType;
    }

    /**
     * 获取调度时间
     *
     * @return schedule_time - 调度时间
     */
    public Date getScheduleTime() {
        return scheduleTime;
    }

    /**
     * 设置调度时间
     *
     * @param scheduleTime 调度时间
     */
    public void setScheduleTime(Date scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    /**
     * 获取调度状态 0 调度开始 1 调度成功  -1 调度失败
     *
     * @return status - 调度状态 0 调度开始 1 调度成功  -1 调度失败
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置调度状态 0 调度开始 1 调度成功  -1 调度失败
     *
     * @param status 调度状态 0 调度开始 1 调度成功  -1 调度失败
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取执行耗时,毫秒
     *
     * @return execute_time - 执行耗时,毫秒
     */
    public String getExecuteTime() {
        return executeTime;
    }

    /**
     * 设置执行耗时,毫秒
     *
     * @param executeTime 执行耗时,毫秒
     */
    public void setExecuteTime(String executeTime) {
        this.executeTime = executeTime;
    }

    /**
     * 获取日志备注
     *
     * @return remark - 日志备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置日志备注
     *
     * @param remark 日志备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取区分调度任务归属项目[sale:营销后台, null:其他项目]
     *
     * @return project_key - 区分调度任务归属项目[sale:营销后台, null:其他项目]
     */
    public String getProjectKey() {
        return projectKey;
    }

    /**
     * 设置区分调度任务归属项目[sale:营销后台, null:其他项目]
     *
     * @param projectKey 区分调度任务归属项目[sale:营销后台, null:其他项目]
     */
    public void setProjectKey(String projectKey) {
        this.projectKey = projectKey;
    }

    public ScheduleRecord() {
        this.projectKey = "sale";
    }

    public ScheduleRecord(Integer type, Date scheduleDate, String classType) {
        this.type = type;
        this.scheduleDate = scheduleDate;
        this.classType = classType;
        this.projectKey = "sale";
    }

    public ScheduleRecord(Integer id, Integer type, Date scheduleDate,
                          String classType, Date scheduleTime,
                          Integer status, String executeTime,
                          String remark, String projectKey) {
        this.id = id;
        this.type = type;
        this.scheduleDate = scheduleDate;
        this.classType = classType;
        this.scheduleTime = scheduleTime;
        this.status = status;
        this.executeTime = executeTime;
        this.remark = remark;
        this.projectKey = projectKey;
    }
}