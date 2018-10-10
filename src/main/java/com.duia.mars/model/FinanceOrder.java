package com.duia.mars.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "finance_order")
public class FinanceOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "pay_num")
    private String payNum;

    @Column(name = "cost_price")
    private Double costPrice;

    private Double balance;

    /**
     * 记录插入时间
     */
    @Column(name = "schedule_time")
    private String scheduleTime;

    /**
     * 订单创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 财务审核时间
     */
    @Column(name = "finance_time")
    private Date financeTime;

    @Column(name = "pay_time")
    private Date payTime;

    /**
     * 尾差
     */
    @Column(name = "tail_dif")
    private Double tailDif;

    @Column(name = "sku_id")
    private Integer skuId;

    @Column(name = "sku_name")
    private String skuName;

    @Column(name = "com_id")
    private Integer comId;

    @Column(name = "com_name")
    private String comName;

    /**
     * 学员user id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 注册费用
     */
    @Column(name = "regist_fee")
    private Double registFee;

    /**
     * 总课时
     */
    @Column(name = "all_class_time")
    private Integer allClassTime;

    /**
     * 最后一节课
     */
    @Column(name = "last_class_time")
    private Date lastClassTime;

    @Column(name = "pay_type")
    private String payType;

    /**
     * pay_oder的type；[c:班级][s:优惠][r:组合][u:升级][x:重修]
     */
    @Column(name = "com_type")
    private String comType;

    @Column(name = "refund_class_count")
    private Integer refundClassCount;

    @Column(name = "refund_class_time")
    private String refundClassTime;

    @Column(name = "balance_class_count")
    private Integer balanceClassCount;

    @Column(name = "balance_class_time")
    private String balanceClassTime;

    @Column(name = "turn_class_count")
    private Integer turnClassCount;

    @Column(name = "turn_class_time")
    private String turnClassTime;

    /**
     * 是否不再计算0：否 ; 1：停止计算
     */
    @Column(name = "stop_status")
    private Integer stopStatus;

    /**
     * 停止计算的日期
     */
    @Column(name = "stop_date")
    private String stopDate;

    /**
     * 创建月之前确认课时（包含正常确认当月，当月前结课，课表为空：1，班级为空:1）
     */
    @Column(name = "before_count")
    private Integer beforeCount;

    /**
     * 关单课程计数（财务，教务）
     */
    @Column(name = "close_class_count")
    private Integer closeClassCount;

    /**
     * 关单时间（财务、教务）
     */
    @Column(name = "close_class_time")
    private String closeClassTime;

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
     * @return order_id
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * @param orderId
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * @return pay_num
     */
    public String getPayNum() {
        return payNum;
    }

    /**
     * @param payNum
     */
    public void setPayNum(String payNum) {
        this.payNum = payNum;
    }

    /**
     * @return cost_price
     */
    public Double getCostPrice() {
        return costPrice;
    }

    /**
     * @param costPrice
     */
    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    /**
     * @return balance
     */
    public Double getBalance() {
        return balance;
    }

    /**
     * @param balance
     */
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    /**
     * 获取记录插入时间
     *
     * @return schedule_time - 记录插入时间
     */
    public String getScheduleTime() {
        return scheduleTime;
    }

    /**
     * 设置记录插入时间
     *
     * @param scheduleTime 记录插入时间
     */
    public void setScheduleTime(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    /**
     * 获取订单创建时间
     *
     * @return create_time - 订单创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置订单创建时间
     *
     * @param createTime 订单创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取财务审核时间
     *
     * @return finance_time - 财务审核时间
     */
    public Date getFinanceTime() {
        return financeTime;
    }

    /**
     * 设置财务审核时间
     *
     * @param financeTime 财务审核时间
     */
    public void setFinanceTime(Date financeTime) {
        this.financeTime = financeTime;
    }

    /**
     * @return pay_time
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     * @param payTime
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * 获取尾差
     *
     * @return tail_dif - 尾差
     */
    public Double getTailDif() {
        return tailDif;
    }

    /**
     * 设置尾差
     *
     * @param tailDif 尾差
     */
    public void setTailDif(Double tailDif) {
        this.tailDif = tailDif;
    }

    /**
     * @return sku_id
     */
    public Integer getSkuId() {
        return skuId;
    }

    /**
     * @param skuId
     */
    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    /**
     * @return sku_name
     */
    public String getSkuName() {
        return skuName;
    }

    /**
     * @param skuName
     */
    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    /**
     * @return com_id
     */
    public Integer getComId() {
        return comId;
    }

    /**
     * @param comId
     */
    public void setComId(Integer comId) {
        this.comId = comId;
    }

    /**
     * @return com_name
     */
    public String getComName() {
        return comName;
    }

    /**
     * @param comName
     */
    public void setComName(String comName) {
        this.comName = comName;
    }

    /**
     * 获取学员user id
     *
     * @return user_id - 学员user id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置学员user id
     *
     * @param userId 学员user id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取注册费用
     *
     * @return regist_fee - 注册费用
     */
    public Double getRegistFee() {
        return registFee;
    }

    /**
     * 设置注册费用
     *
     * @param registFee 注册费用
     */
    public void setRegistFee(Double registFee) {
        this.registFee = registFee;
    }

    /**
     * 获取总课时
     *
     * @return all_class_time - 总课时
     */
    public Integer getAllClassTime() {
        return allClassTime;
    }

    /**
     * 设置总课时
     *
     * @param allClassTime 总课时
     */
    public void setAllClassTime(Integer allClassTime) {
        this.allClassTime = allClassTime;
    }

    /**
     * 获取最后一节课
     *
     * @return last_class_time - 最后一节课
     */
    public Date getLastClassTime() {
        return lastClassTime;
    }

    /**
     * 设置最后一节课
     *
     * @param lastClassTime 最后一节课
     */
    public void setLastClassTime(Date lastClassTime) {
        this.lastClassTime = lastClassTime;
    }

    /**
     * @return pay_type
     */
    public String getPayType() {
        return payType;
    }

    /**
     * @param payType
     */
    public void setPayType(String payType) {
        this.payType = payType;
    }

    /**
     * 获取pay_oder的type；[c:班级][s:优惠][r:组合][u:升级][x:重修]
     *
     * @return com_type - pay_oder的type；[c:班级][s:优惠][r:组合][u:升级][x:重修]
     */
    public String getComType() {
        return comType;
    }

    /**
     * 设置pay_oder的type；[c:班级][s:优惠][r:组合][u:升级][x:重修]
     *
     * @param comType pay_oder的type；[c:班级][s:优惠][r:组合][u:升级][x:重修]
     */
    public void setComType(String comType) {
        this.comType = comType;
    }

    /**
     * @return refund_class_count
     */
    public Integer getRefundClassCount() {
        return refundClassCount;
    }

    /**
     * @param refundClassCount
     */
    public void setRefundClassCount(Integer refundClassCount) {
        this.refundClassCount = refundClassCount;
    }

    /**
     * @return refund_class_time
     */
    public String getRefundClassTime() {
        return refundClassTime;
    }

    /**
     * @param refundClassTime
     */
    public void setRefundClassTime(String refundClassTime) {
        this.refundClassTime = refundClassTime;
    }

    /**
     * @return balance_class_count
     */
    public Integer getBalanceClassCount() {
        return balanceClassCount;
    }

    /**
     * @param balanceClassCount
     */
    public void setBalanceClassCount(Integer balanceClassCount) {
        this.balanceClassCount = balanceClassCount;
    }

    /**
     * @return balance_class_time
     */
    public String getBalanceClassTime() {
        return balanceClassTime;
    }

    /**
     * @param balanceClassTime
     */
    public void setBalanceClassTime(String balanceClassTime) {
        this.balanceClassTime = balanceClassTime;
    }

    /**
     * @return turn_class_count
     */
    public Integer getTurnClassCount() {
        return turnClassCount;
    }

    /**
     * @param turnClassCount
     */
    public void setTurnClassCount(Integer turnClassCount) {
        this.turnClassCount = turnClassCount;
    }

    /**
     * @return turn_class_time
     */
    public String getTurnClassTime() {
        return turnClassTime;
    }

    /**
     * @param turnClassTime
     */
    public void setTurnClassTime(String turnClassTime) {
        this.turnClassTime = turnClassTime;
    }

    /**
     * 获取是否不再计算0：否 ; 1：停止计算
     *
     * @return stop_status - 是否不再计算0：否 ; 1：停止计算
     */
    public Integer getStopStatus() {
        return stopStatus;
    }

    /**
     * 设置是否不再计算0：否 ; 1：停止计算
     *
     * @param stopStatus 是否不再计算0：否 ; 1：停止计算
     */
    public void setStopStatus(Integer stopStatus) {
        this.stopStatus = stopStatus;
    }

    /**
     * 获取停止计算的日期
     *
     * @return stop_date - 停止计算的日期
     */
    public String getStopDate() {
        return stopDate;
    }

    /**
     * 设置停止计算的日期
     *
     * @param stopDate 停止计算的日期
     */
    public void setStopDate(String stopDate) {
        this.stopDate = stopDate;
    }

    /**
     * 获取创建月之前确认课时（包含正常确认当月，当月前结课，课表为空：1，班级为空:1）
     *
     * @return before_count - 创建月之前确认课时（包含正常确认当月，当月前结课，课表为空：1，班级为空:1）
     */
    public Integer getBeforeCount() {
        return beforeCount;
    }

    /**
     * 设置创建月之前确认课时（包含正常确认当月，当月前结课，课表为空：1，班级为空:1）
     *
     * @param beforeCount 创建月之前确认课时（包含正常确认当月，当月前结课，课表为空：1，班级为空:1）
     */
    public void setBeforeCount(Integer beforeCount) {
        this.beforeCount = beforeCount;
    }

    /**
     * 获取关单课程计数（财务，教务）
     *
     * @return close_class_count - 关单课程计数（财务，教务）
     */
    public Integer getCloseClassCount() {
        return closeClassCount;
    }

    /**
     * 设置关单课程计数（财务，教务）
     *
     * @param closeClassCount 关单课程计数（财务，教务）
     */
    public void setCloseClassCount(Integer closeClassCount) {
        this.closeClassCount = closeClassCount;
    }

    /**
     * 获取关单时间（财务、教务）
     *
     * @return close_class_time - 关单时间（财务、教务）
     */
    public String getCloseClassTime() {
        return closeClassTime;
    }

    /**
     * 设置关单时间（财务、教务）
     *
     * @param closeClassTime 关单时间（财务、教务）
     */
    public void setCloseClassTime(String closeClassTime) {
        this.closeClassTime = closeClassTime;
    }
}