package com.duia.mars.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "crm_turn_order_record")
public class CrmTurnOrderRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 旧订单id
     */
    @Column(name = "old_order_id")
    private Integer oldOrderId;

    /**
     * 新订单id
     */
    @Column(name = "new_order_id")
    private Integer newOrderId;

    /**
     * 操作人id
     */
    @Column(name = "author_user_id")
    private Integer authorUserId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private String createTime;

    /**
     * 是否需要退款1没2有
     */
    @Column(name = "has_refund")
    private Integer hasRefund;

    /**
     * 教务主管审核状态1未审核，2通过，3不通过
     */
    @Column(name = "teach_status")
    private Integer teachStatus;

    /**
     * 旧的交接表id
     */
    @Column(name = "old_handover_id")
    private Integer oldHandoverId;

    /**
     * 新的交接表id
     */
    @Column(name = "new_handover_id")
    private Integer newHandoverId;

    /**
     * 原因
     */
    private String reason;

    /**
     * 退款记录表id
     */
    @Column(name = "crm_refund_id")
    private Integer crmRefundId;

    /**
     * 旧单的余额（留给新单的余额）
     */
    @Column(name = "old_balance")
    private Double oldBalance;

    /**
     * 新单优惠，即优惠券减免的
     */
    @Column(name = "new_discount")
    private Double newDiscount;

    /**
     * 此次转订单退款金额
     */
    @Column(name = "back_num")
    private Double backNum;

    /**
     * 需要补款金额
     */
    @Column(name = "repair_num")
    private Double repairNum;

    /**
     * 审核的备注
     */
    @Column(name = "audit_remark")
    private String auditRemark;

    /**
     * 扣款金额
     */
    @Column(name = "deduct_num")
    private Double deductNum;

    /**
     * 教务审核时间
     */
    @Column(name = "teach_time")
    private Date teachTime;

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
     * 获取旧订单id
     *
     * @return old_order_id - 旧订单id
     */
    public Integer getOldOrderId() {
        return oldOrderId;
    }

    /**
     * 设置旧订单id
     *
     * @param oldOrderId 旧订单id
     */
    public void setOldOrderId(Integer oldOrderId) {
        this.oldOrderId = oldOrderId;
    }

    /**
     * 获取新订单id
     *
     * @return new_order_id - 新订单id
     */
    public Integer getNewOrderId() {
        return newOrderId;
    }

    /**
     * 设置新订单id
     *
     * @param newOrderId 新订单id
     */
    public void setNewOrderId(Integer newOrderId) {
        this.newOrderId = newOrderId;
    }

    /**
     * 获取操作人id
     *
     * @return author_user_id - 操作人id
     */
    public Integer getAuthorUserId() {
        return authorUserId;
    }

    /**
     * 设置操作人id
     *
     * @param authorUserId 操作人id
     */
    public void setAuthorUserId(Integer authorUserId) {
        this.authorUserId = authorUserId;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取是否需要退款1没2有
     *
     * @return has_refund - 是否需要退款1没2有
     */
    public Integer getHasRefund() {
        return hasRefund;
    }

    /**
     * 设置是否需要退款1没2有
     *
     * @param hasRefund 是否需要退款1没2有
     */
    public void setHasRefund(Integer hasRefund) {
        this.hasRefund = hasRefund;
    }

    /**
     * 获取教务主管审核状态1未审核，2通过，3不通过
     *
     * @return teach_status - 教务主管审核状态1未审核，2通过，3不通过
     */
    public Integer getTeachStatus() {
        return teachStatus;
    }

    /**
     * 设置教务主管审核状态1未审核，2通过，3不通过
     *
     * @param teachStatus 教务主管审核状态1未审核，2通过，3不通过
     */
    public void setTeachStatus(Integer teachStatus) {
        this.teachStatus = teachStatus;
    }

    /**
     * 获取旧的交接表id
     *
     * @return old_handover_id - 旧的交接表id
     */
    public Integer getOldHandoverId() {
        return oldHandoverId;
    }

    /**
     * 设置旧的交接表id
     *
     * @param oldHandoverId 旧的交接表id
     */
    public void setOldHandoverId(Integer oldHandoverId) {
        this.oldHandoverId = oldHandoverId;
    }

    /**
     * 获取新的交接表id
     *
     * @return new_handover_id - 新的交接表id
     */
    public Integer getNewHandoverId() {
        return newHandoverId;
    }

    /**
     * 设置新的交接表id
     *
     * @param newHandoverId 新的交接表id
     */
    public void setNewHandoverId(Integer newHandoverId) {
        this.newHandoverId = newHandoverId;
    }

    /**
     * 获取原因
     *
     * @return reason - 原因
     */
    public String getReason() {
        return reason;
    }

    /**
     * 设置原因
     *
     * @param reason 原因
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * 获取退款记录表id
     *
     * @return crm_refund_id - 退款记录表id
     */
    public Integer getCrmRefundId() {
        return crmRefundId;
    }

    /**
     * 设置退款记录表id
     *
     * @param crmRefundId 退款记录表id
     */
    public void setCrmRefundId(Integer crmRefundId) {
        this.crmRefundId = crmRefundId;
    }

    /**
     * 获取旧单的余额（留给新单的余额）
     *
     * @return old_balance - 旧单的余额（留给新单的余额）
     */
    public Double getOldBalance() {
        return oldBalance;
    }

    /**
     * 设置旧单的余额（留给新单的余额）
     *
     * @param oldBalance 旧单的余额（留给新单的余额）
     */
    public void setOldBalance(Double oldBalance) {
        this.oldBalance = oldBalance;
    }

    /**
     * 获取新单优惠，即优惠券减免的
     *
     * @return new_discount - 新单优惠，即优惠券减免的
     */
    public Double getNewDiscount() {
        return newDiscount;
    }

    /**
     * 设置新单优惠，即优惠券减免的
     *
     * @param newDiscount 新单优惠，即优惠券减免的
     */
    public void setNewDiscount(Double newDiscount) {
        this.newDiscount = newDiscount;
    }

    /**
     * 获取此次转订单退款金额
     *
     * @return back_num - 此次转订单退款金额
     */
    public Double getBackNum() {
        return backNum;
    }

    /**
     * 设置此次转订单退款金额
     *
     * @param backNum 此次转订单退款金额
     */
    public void setBackNum(Double backNum) {
        this.backNum = backNum;
    }

    /**
     * 获取需要补款金额
     *
     * @return repair_num - 需要补款金额
     */
    public Double getRepairNum() {
        return repairNum;
    }

    /**
     * 设置需要补款金额
     *
     * @param repairNum 需要补款金额
     */
    public void setRepairNum(Double repairNum) {
        this.repairNum = repairNum;
    }

    /**
     * 获取审核的备注
     *
     * @return audit_remark - 审核的备注
     */
    public String getAuditRemark() {
        return auditRemark;
    }

    /**
     * 设置审核的备注
     *
     * @param auditRemark 审核的备注
     */
    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark;
    }

    /**
     * 获取扣款金额
     *
     * @return deduct_num - 扣款金额
     */
    public Double getDeductNum() {
        return deductNum;
    }

    /**
     * 设置扣款金额
     *
     * @param deductNum 扣款金额
     */
    public void setDeductNum(Double deductNum) {
        this.deductNum = deductNum;
    }

    /**
     * 获取教务审核时间
     *
     * @return teach_time - 教务审核时间
     */
    public Date getTeachTime() {
        return teachTime;
    }

    /**
     * 设置教务审核时间
     *
     * @param teachTime 教务审核时间
     */
    public void setTeachTime(Date teachTime) {
        this.teachTime = teachTime;
    }
}