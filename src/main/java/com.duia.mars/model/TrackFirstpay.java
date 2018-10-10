package com.duia.mars.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "track_firstPay")
public class TrackFirstpay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    /**
     * 注册时间
     */
    @Column(name = "regist_time")
    private Date registTime;

    /**
     * 首次支付时间
     */
    @Column(name = "pay_time")
    private Date payTime;

    /**
     * 时间差值
     */
    private String time;

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
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取注册时间
     *
     * @return regist_time - 注册时间
     */
    public Date getRegistTime() {
        return registTime;
    }

    /**
     * 设置注册时间
     *
     * @param registTime 注册时间
     */
    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }

    /**
     * 获取首次支付时间
     *
     * @return pay_time - 首次支付时间
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     * 设置首次支付时间
     *
     * @param payTime 首次支付时间
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * 获取时间差值
     *
     * @return time - 时间差值
     */
    public String getTime() {
        return time;
    }

    /**
     * 设置时间差值
     *
     * @param time 时间差值
     */
    public void setTime(String time) {
        this.time = time;
    }
}