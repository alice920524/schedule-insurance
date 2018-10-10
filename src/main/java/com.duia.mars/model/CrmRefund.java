package com.duia.mars.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "crm_refund")
public class CrmRefund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 订单ID
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 普通退款：1，转订单退款：2
     */
    @Column(name = "refund_type")
    private Integer refundType;

    /**
     * 转订单记录表ID
     */
    @Column(name = "turn_order_record_id")
    private Integer turnOrderRecordId;

    /**
     * 退款状态(提交1，主管审核通过2，主管审核不通过3）
     */
    @Column(name = "refund_teach_status")
    private Integer refundTeachStatus;

    /**
     * 退款金额
     */
    @Column(name = "refund_money")
    private Double refundMoney;

    /**
     * 退款方式:转账银行1，天猫2 ，京东 3
     */
    @Column(name = "refund_way")
    private Integer refundWay;

    /**
     * 银行卡号
     */
    @Column(name = "bank_card")
    private String bankCard;

    /**
     * 省名称
     */
    private String province;

    /**
     * 市名称
     */
    private String city;

    /**
     * 支行名称
     */
    @Column(name = "bank_sub_branch_name")
    private String bankSubBranchName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 审批意见
     */
    @Column(name = "approval_opinion")
    private String approvalOpinion;

    /**
     * 退款账户（天猫，京东）
     */
    private String account;

    /**
     * 订单号（天猫，京东）
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 退款理由
     */
    @Column(name = "refund_reason")
    private String refundReason;

    /**
     * 财务审核：待审核1（退款中），通过2，审核不通过3
     */
    @Column(name = "refund_finance_status")
    private Integer refundFinanceStatus;

    /**
     * 银行名称
     */
    @Column(name = "bank_name")
    private String bankName;

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
     * 退款单号
     */
    @Column(name = "refund_no")
    private String refundNo;

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
     * 退款类型，导数据用,其他用不到
     */
    @Column(name = "back_type")
    private String backType;

    /**
     * 财务审核退款时间
     */
    @Column(name = "finance_time")
    private Date financeTime;

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
     * 获取订单ID
     *
     * @return order_id - 订单ID
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 设置订单ID
     *
     * @param orderId 订单ID
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取普通退款：1，转订单退款：2
     *
     * @return refund_type - 普通退款：1，转订单退款：2
     */
    public Integer getRefundType() {
        return refundType;
    }

    /**
     * 设置普通退款：1，转订单退款：2
     *
     * @param refundType 普通退款：1，转订单退款：2
     */
    public void setRefundType(Integer refundType) {
        this.refundType = refundType;
    }

    /**
     * 获取转订单记录表ID
     *
     * @return turn_order_record_id - 转订单记录表ID
     */
    public Integer getTurnOrderRecordId() {
        return turnOrderRecordId;
    }

    /**
     * 设置转订单记录表ID
     *
     * @param turnOrderRecordId 转订单记录表ID
     */
    public void setTurnOrderRecordId(Integer turnOrderRecordId) {
        this.turnOrderRecordId = turnOrderRecordId;
    }

    /**
     * 获取退款状态(提交1，主管审核通过2，主管审核不通过3）
     *
     * @return refund_teach_status - 退款状态(提交1，主管审核通过2，主管审核不通过3）
     */
    public Integer getRefundTeachStatus() {
        return refundTeachStatus;
    }

    /**
     * 设置退款状态(提交1，主管审核通过2，主管审核不通过3）
     *
     * @param refundTeachStatus 退款状态(提交1，主管审核通过2，主管审核不通过3）
     */
    public void setRefundTeachStatus(Integer refundTeachStatus) {
        this.refundTeachStatus = refundTeachStatus;
    }

    /**
     * 获取退款金额
     *
     * @return refund_money - 退款金额
     */
    public Double getRefundMoney() {
        return refundMoney;
    }

    /**
     * 设置退款金额
     *
     * @param refundMoney 退款金额
     */
    public void setRefundMoney(Double refundMoney) {
        this.refundMoney = refundMoney;
    }

    /**
     * 获取退款方式:转账银行1，天猫2 ，京东 3
     *
     * @return refund_way - 退款方式:转账银行1，天猫2 ，京东 3
     */
    public Integer getRefundWay() {
        return refundWay;
    }

    /**
     * 设置退款方式:转账银行1，天猫2 ，京东 3
     *
     * @param refundWay 退款方式:转账银行1，天猫2 ，京东 3
     */
    public void setRefundWay(Integer refundWay) {
        this.refundWay = refundWay;
    }

    /**
     * 获取银行卡号
     *
     * @return bank_card - 银行卡号
     */
    public String getBankCard() {
        return bankCard;
    }

    /**
     * 设置银行卡号
     *
     * @param bankCard 银行卡号
     */
    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    /**
     * 获取省名称
     *
     * @return province - 省名称
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省名称
     *
     * @param province 省名称
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取市名称
     *
     * @return city - 市名称
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置市名称
     *
     * @param city 市名称
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取支行名称
     *
     * @return bank_sub_branch_name - 支行名称
     */
    public String getBankSubBranchName() {
        return bankSubBranchName;
    }

    /**
     * 设置支行名称
     *
     * @param bankSubBranchName 支行名称
     */
    public void setBankSubBranchName(String bankSubBranchName) {
        this.bankSubBranchName = bankSubBranchName;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取审批意见
     *
     * @return approval_opinion - 审批意见
     */
    public String getApprovalOpinion() {
        return approvalOpinion;
    }

    /**
     * 设置审批意见
     *
     * @param approvalOpinion 审批意见
     */
    public void setApprovalOpinion(String approvalOpinion) {
        this.approvalOpinion = approvalOpinion;
    }

    /**
     * 获取退款账户（天猫，京东）
     *
     * @return account - 退款账户（天猫，京东）
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置退款账户（天猫，京东）
     *
     * @param account 退款账户（天猫，京东）
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取订单号（天猫，京东）
     *
     * @return order_no - 订单号（天猫，京东）
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置订单号（天猫，京东）
     *
     * @param orderNo 订单号（天猫，京东）
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取退款理由
     *
     * @return refund_reason - 退款理由
     */
    public String getRefundReason() {
        return refundReason;
    }

    /**
     * 设置退款理由
     *
     * @param refundReason 退款理由
     */
    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }

    /**
     * 获取财务审核：待审核1（退款中），通过2，审核不通过3
     *
     * @return refund_finance_status - 财务审核：待审核1（退款中），通过2，审核不通过3
     */
    public Integer getRefundFinanceStatus() {
        return refundFinanceStatus;
    }

    /**
     * 设置财务审核：待审核1（退款中），通过2，审核不通过3
     *
     * @param refundFinanceStatus 财务审核：待审核1（退款中），通过2，审核不通过3
     */
    public void setRefundFinanceStatus(Integer refundFinanceStatus) {
        this.refundFinanceStatus = refundFinanceStatus;
    }

    /**
     * 获取银行名称
     *
     * @return bank_name - 银行名称
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * 设置银行名称
     *
     * @param bankName 银行名称
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
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
     * 获取退款单号
     *
     * @return refund_no - 退款单号
     */
    public String getRefundNo() {
        return refundNo;
    }

    /**
     * 设置退款单号
     *
     * @param refundNo 退款单号
     */
    public void setRefundNo(String refundNo) {
        this.refundNo = refundNo;
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
     * 获取退款类型，导数据用,其他用不到
     *
     * @return back_type - 退款类型，导数据用,其他用不到
     */
    public String getBackType() {
        return backType;
    }

    /**
     * 设置退款类型，导数据用,其他用不到
     *
     * @param backType 退款类型，导数据用,其他用不到
     */
    public void setBackType(String backType) {
        this.backType = backType;
    }

    /**
     * 获取财务审核退款时间
     *
     * @return finance_time - 财务审核退款时间
     */
    public Date getFinanceTime() {
        return financeTime;
    }

    /**
     * 设置财务审核退款时间
     *
     * @param financeTime 财务审核退款时间
     */
    public void setFinanceTime(Date financeTime) {
        this.financeTime = financeTime;
    }
}