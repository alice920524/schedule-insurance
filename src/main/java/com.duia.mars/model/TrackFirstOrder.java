package com.duia.mars.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "track_first_order")
public class TrackFirstOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 代理渠道ID
     */
    @Column(name = "agent_channel_id")
    private Integer agentChannelId;

    /**
     * 父订单号
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 订单金额
     */
    private Double money;

    /**
     * 财务确定时间
     */
    @Column(name = "finance_time")
    private Date financeTime;

    /**
     * 订单关闭时间
     */
    @Column(name = "colse_time")
    private Date colseTime;

    /**
     * 订单状态 1 正常订单 -1 取消订单
     */
    private Integer status;

    /**
     * skuId
     */
    @Column(name = "sku_id")
    private Integer skuId;

    /**
     * sku名
     */
    @Column(name = "sku_name")
    private String skuName;

    /**
     * 支付时间
     */
    @Column(name = "pay_time")
    private Date payTime;

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
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取代理渠道ID
     *
     * @return agent_channel_id - 代理渠道ID
     */
    public Integer getAgentChannelId() {
        return agentChannelId;
    }

    /**
     * 设置代理渠道ID
     *
     * @param agentChannelId 代理渠道ID
     */
    public void setAgentChannelId(Integer agentChannelId) {
        this.agentChannelId = agentChannelId;
    }

    /**
     * 获取父订单号
     *
     * @return order_id - 父订单号
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 设置父订单号
     *
     * @param orderId 父订单号
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取订单金额
     *
     * @return money - 订单金额
     */
    public Double getMoney() {
        return money;
    }

    /**
     * 设置订单金额
     *
     * @param money 订单金额
     */
    public void setMoney(Double money) {
        this.money = money;
    }

    /**
     * 获取财务确定时间
     *
     * @return finance_time - 财务确定时间
     */
    public Date getFinanceTime() {
        return financeTime;
    }

    /**
     * 设置财务确定时间
     *
     * @param financeTime 财务确定时间
     */
    public void setFinanceTime(Date financeTime) {
        this.financeTime = financeTime;
    }

    /**
     * 获取订单关闭时间
     *
     * @return colse_time - 订单关闭时间
     */
    public Date getColseTime() {
        return colseTime;
    }

    /**
     * 设置订单关闭时间
     *
     * @param colseTime 订单关闭时间
     */
    public void setColseTime(Date colseTime) {
        this.colseTime = colseTime;
    }

    /**
     * 获取订单状态 1 正常订单 -1 取消订单
     *
     * @return status - 订单状态 1 正常订单 -1 取消订单
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置订单状态 1 正常订单 -1 取消订单
     *
     * @param status 订单状态 1 正常订单 -1 取消订单
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取skuId
     *
     * @return sku_id - skuId
     */
    public Integer getSkuId() {
        return skuId;
    }

    /**
     * 设置skuId
     *
     * @param skuId skuId
     */
    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    /**
     * 获取sku名
     *
     * @return sku_name - sku名
     */
    public String getSkuName() {
        return skuName;
    }

    /**
     * 设置sku名
     *
     * @param skuName sku名
     */
    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    /**
     * 获取支付时间
     *
     * @return pay_time - 支付时间
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     * 设置支付时间
     *
     * @param payTime 支付时间
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }
}