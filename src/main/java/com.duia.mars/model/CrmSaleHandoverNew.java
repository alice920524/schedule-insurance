package com.duia.mars.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "crm_sale_handover_new")
public class CrmSaleHandoverNew {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 订单id，父订单id或者子订单id
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 前台用户邮箱
     */
    private String email;

    /**
     * 交接录入的手机号
     */
    private String mobile;

    /**
     * 交接录入的qq
     */
    @Column(name = "qq_num")
    private String qqNum;

    /**
     * 业绩归属者id后台用户表
     */
    @Column(name = "sale_id")
    private Integer saleId;

    /**
     * 业绩归属者name 后台用户表
     */
    @Column(name = "sale_name")
    private String saleName;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 承接天数1，3（3天内），7（七天内），30（1月内），31(31天以上)
     */
    @Column(name = "accept_day")
    private Integer acceptDay;

    /**
     * 业绩提交状态0未提交1已提交2业务上标记不查询的状态
     */
    @Column(name = "sale_status")
    private Integer saleStatus;

    /**
     * 教务审核状态状态0：未审核1：审核通过2：教务标记未完成
     */
    @Column(name = "teach_status")
    private Integer teachStatus;

    /**
     * 财务审核状态0：未审核1：审核通过2：财务审核不通过
     */
    @Column(name = "finance_status")
    private Integer financeStatus;

    /**
     * 教务订单关闭状态0：未关闭 1：已关闭
     */
    @Column(name = "colse_status")
    private Integer colseStatus;

    /**
     * 交接父id
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 退款状态状态0：未退款1：已退款 2退款中
     */
    @Column(name = "back_status")
    private Integer backStatus;

    /**
     * 退款金额
     */
    @Column(name = "back_num")
    private Double backNum;

    /**
     * 业绩所属人的部门id
     */
    @Column(name = "dep_id")
    private Integer depId;

    /**
     * 业绩所属人小组的id
     */
    @Column(name = "grp_id")
    private Integer grpId;

    /**
     * 业绩所属人邮箱
     */
    @Column(name = "sale_email")
    private String saleEmail;

    /**
     * 学员id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 订单号
     */
    @Column(name = "order_num")
    private String orderNum;

    @Column(name = "pay_num")
    private String payNum;

    /**
     * 支付状态(pay_status_non:未支付;pay_status_failed:支付失败;pay_status_success;支付成功)
     */
    @Column(name = "pay_status")
    private String payStatus;

    /**
     * 支付类型 (分期分批和其他付款类型详见文档对应关系)
     */
    @Column(name = "pay_type")
    private String payType;

    /**
     * 方案名称
     */
    @Column(name = "program_name")
    private String programName;

    /**
     * 方案ID
     */
    @Column(name = "program_id")
    private Integer programId;

    /**
     * 实际支付价格
     */
    @Column(name = "realpay_price")
    private Double realpayPrice;

    /**
     * 支付时间
     */
    @Column(name = "pay_time")
    private String payTime;

    /**
     * 价格
     */
    @Column(name = "cost_price")
    private Double costPrice;

    /**
     * 支付商品类型 c,s,r,u   班级，优惠套餐，组合，升级
     */
    private String type;

    /**
     * 退款来源：1退余额2转订单
     */
    @Column(name = "back_source")
    private Integer backSource;

    /**
     * 分批支付 第几个子订单
     */
    @Column(name = "order_batch")
    private Integer orderBatch;

    /**
     * 分期付款当前期数 0：未分期
     */
    @Column(name = "order_instal")
    private Integer orderInstal;

    /**
     * 班型信息(新增的冗余)
     */
    @Column(name = "class_type_info")
    private String classTypeInfo;

    /**
     * 分期完成度1未完成2已完成
     */
    @Column(name = "order_batch_status")
    private Integer orderBatchStatus;

    /**
     * 是否天猫1:是2:否
     */
    @Column(name = "is_tmall")
    private Integer isTmall;

    /**
     * skuid
     */
    @Column(name = "sku_id")
    private Integer skuId;

    /**
     * sku名
     */
    @Column(name = "sku_name")
    private String skuName;

    /**
     * 业绩状态标记业绩是否生效1：是2：否
     */
    private Integer achievement;

    /**
     * 教务标记完成时间
     */
    @Column(name = "teach_finish_time")
    private String teachFinishTime;

    /**
     * 1为未对比,2为对比失败，3对比通过
     */
    @Column(name = "batch_audit_status")
    private Integer batchAuditStatus;

    /**
     * 财务审核时间(财务标记审核通过或者不通过时记录时间，财务审核状态为0要置空)
     */
    @Column(name = "finance_time")
    private String financeTime;

    /**
     * 原单余额
     */
    @Column(name = "old_balance")
    private Double oldBalance;

    /**
     * 学习包总价格(默认值是0)重点
     */
    @Column(name = "study_package_price")
    private Double studyPackagePrice;

    /**
     * 是否邮寄教材（或者说学习包）是否邮寄教材 0:学员不需要 1:需要 2:是班型不需要
     */
    @Column(name = "is_mail")
    private Integer isMail;

    /**
     * qq验证信息
     */
    @Column(name = "qq_validate")
    private String qqValidate;

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
     * 获取订单id，父订单id或者子订单id
     *
     * @return order_id - 订单id，父订单id或者子订单id
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 设置订单id，父订单id或者子订单id
     *
     * @param orderId 订单id，父订单id或者子订单id
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取前台用户邮箱
     *
     * @return email - 前台用户邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置前台用户邮箱
     *
     * @param email 前台用户邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取交接录入的手机号
     *
     * @return mobile - 交接录入的手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置交接录入的手机号
     *
     * @param mobile 交接录入的手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取交接录入的qq
     *
     * @return qq_num - 交接录入的qq
     */
    public String getQqNum() {
        return qqNum;
    }

    /**
     * 设置交接录入的qq
     *
     * @param qqNum 交接录入的qq
     */
    public void setQqNum(String qqNum) {
        this.qqNum = qqNum;
    }

    /**
     * 获取业绩归属者id后台用户表
     *
     * @return sale_id - 业绩归属者id后台用户表
     */
    public Integer getSaleId() {
        return saleId;
    }

    /**
     * 设置业绩归属者id后台用户表
     *
     * @param saleId 业绩归属者id后台用户表
     */
    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
    }

    /**
     * 获取业绩归属者name 后台用户表
     *
     * @return sale_name - 业绩归属者name 后台用户表
     */
    public String getSaleName() {
        return saleName;
    }

    /**
     * 设置业绩归属者name 后台用户表
     *
     * @param saleName 业绩归属者name 后台用户表
     */
    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取承接天数1，3（3天内），7（七天内），30（1月内），31(31天以上)
     *
     * @return accept_day - 承接天数1，3（3天内），7（七天内），30（1月内），31(31天以上)
     */
    public Integer getAcceptDay() {
        return acceptDay;
    }

    /**
     * 设置承接天数1，3（3天内），7（七天内），30（1月内），31(31天以上)
     *
     * @param acceptDay 承接天数1，3（3天内），7（七天内），30（1月内），31(31天以上)
     */
    public void setAcceptDay(Integer acceptDay) {
        this.acceptDay = acceptDay;
    }

    /**
     * 获取业绩提交状态0未提交1已提交2业务上标记不查询的状态
     *
     * @return sale_status - 业绩提交状态0未提交1已提交2业务上标记不查询的状态
     */
    public Integer getSaleStatus() {
        return saleStatus;
    }

    /**
     * 设置业绩提交状态0未提交1已提交2业务上标记不查询的状态
     *
     * @param saleStatus 业绩提交状态0未提交1已提交2业务上标记不查询的状态
     */
    public void setSaleStatus(Integer saleStatus) {
        this.saleStatus = saleStatus;
    }

    /**
     * 获取教务审核状态状态0：未审核1：审核通过2：教务标记未完成
     *
     * @return teach_status - 教务审核状态状态0：未审核1：审核通过2：教务标记未完成
     */
    public Integer getTeachStatus() {
        return teachStatus;
    }

    /**
     * 设置教务审核状态状态0：未审核1：审核通过2：教务标记未完成
     *
     * @param teachStatus 教务审核状态状态0：未审核1：审核通过2：教务标记未完成
     */
    public void setTeachStatus(Integer teachStatus) {
        this.teachStatus = teachStatus;
    }

    /**
     * 获取财务审核状态0：未审核1：审核通过2：财务审核不通过
     *
     * @return finance_status - 财务审核状态0：未审核1：审核通过2：财务审核不通过
     */
    public Integer getFinanceStatus() {
        return financeStatus;
    }

    /**
     * 设置财务审核状态0：未审核1：审核通过2：财务审核不通过
     *
     * @param financeStatus 财务审核状态0：未审核1：审核通过2：财务审核不通过
     */
    public void setFinanceStatus(Integer financeStatus) {
        this.financeStatus = financeStatus;
    }

    /**
     * 获取教务订单关闭状态0：未关闭 1：已关闭
     *
     * @return colse_status - 教务订单关闭状态0：未关闭 1：已关闭
     */
    public Integer getColseStatus() {
        return colseStatus;
    }

    /**
     * 设置教务订单关闭状态0：未关闭 1：已关闭
     *
     * @param colseStatus 教务订单关闭状态0：未关闭 1：已关闭
     */
    public void setColseStatus(Integer colseStatus) {
        this.colseStatus = colseStatus;
    }

    /**
     * 获取交接父id
     *
     * @return parent_id - 交接父id
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置交接父id
     *
     * @param parentId 交接父id
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取退款状态状态0：未退款1：已退款 2退款中
     *
     * @return back_status - 退款状态状态0：未退款1：已退款 2退款中
     */
    public Integer getBackStatus() {
        return backStatus;
    }

    /**
     * 设置退款状态状态0：未退款1：已退款 2退款中
     *
     * @param backStatus 退款状态状态0：未退款1：已退款 2退款中
     */
    public void setBackStatus(Integer backStatus) {
        this.backStatus = backStatus;
    }

    /**
     * 获取退款金额
     *
     * @return back_num - 退款金额
     */
    public Double getBackNum() {
        return backNum;
    }

    /**
     * 设置退款金额
     *
     * @param backNum 退款金额
     */
    public void setBackNum(Double backNum) {
        this.backNum = backNum;
    }

    /**
     * 获取业绩所属人的部门id
     *
     * @return dep_id - 业绩所属人的部门id
     */
    public Integer getDepId() {
        return depId;
    }

    /**
     * 设置业绩所属人的部门id
     *
     * @param depId 业绩所属人的部门id
     */
    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    /**
     * 获取业绩所属人小组的id
     *
     * @return grp_id - 业绩所属人小组的id
     */
    public Integer getGrpId() {
        return grpId;
    }

    /**
     * 设置业绩所属人小组的id
     *
     * @param grpId 业绩所属人小组的id
     */
    public void setGrpId(Integer grpId) {
        this.grpId = grpId;
    }

    /**
     * 获取业绩所属人邮箱
     *
     * @return sale_email - 业绩所属人邮箱
     */
    public String getSaleEmail() {
        return saleEmail;
    }

    /**
     * 设置业绩所属人邮箱
     *
     * @param saleEmail 业绩所属人邮箱
     */
    public void setSaleEmail(String saleEmail) {
        this.saleEmail = saleEmail;
    }

    /**
     * 获取学员id
     *
     * @return user_id - 学员id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置学员id
     *
     * @param userId 学员id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取订单号
     *
     * @return order_num - 订单号
     */
    public String getOrderNum() {
        return orderNum;
    }

    /**
     * 设置订单号
     *
     * @param orderNum 订单号
     */
    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
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
     * 获取支付状态(pay_status_non:未支付;pay_status_failed:支付失败;pay_status_success;支付成功)
     *
     * @return pay_status - 支付状态(pay_status_non:未支付;pay_status_failed:支付失败;pay_status_success;支付成功)
     */
    public String getPayStatus() {
        return payStatus;
    }

    /**
     * 设置支付状态(pay_status_non:未支付;pay_status_failed:支付失败;pay_status_success;支付成功)
     *
     * @param payStatus 支付状态(pay_status_non:未支付;pay_status_failed:支付失败;pay_status_success;支付成功)
     */
    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * 获取支付类型 (分期分批和其他付款类型详见文档对应关系)
     *
     * @return pay_type - 支付类型 (分期分批和其他付款类型详见文档对应关系)
     */
    public String getPayType() {
        return payType;
    }

    /**
     * 设置支付类型 (分期分批和其他付款类型详见文档对应关系)
     *
     * @param payType 支付类型 (分期分批和其他付款类型详见文档对应关系)
     */
    public void setPayType(String payType) {
        this.payType = payType;
    }

    /**
     * 获取方案名称
     *
     * @return program_name - 方案名称
     */
    public String getProgramName() {
        return programName;
    }

    /**
     * 设置方案名称
     *
     * @param programName 方案名称
     */
    public void setProgramName(String programName) {
        this.programName = programName;
    }

    /**
     * 获取方案ID
     *
     * @return program_id - 方案ID
     */
    public Integer getProgramId() {
        return programId;
    }

    /**
     * 设置方案ID
     *
     * @param programId 方案ID
     */
    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    /**
     * 获取实际支付价格
     *
     * @return realpay_price - 实际支付价格
     */
    public Double getRealpayPrice() {
        return realpayPrice;
    }

    /**
     * 设置实际支付价格
     *
     * @param realpayPrice 实际支付价格
     */
    public void setRealpayPrice(Double realpayPrice) {
        this.realpayPrice = realpayPrice;
    }

    /**
     * 获取支付时间
     *
     * @return pay_time - 支付时间
     */
    public String getPayTime() {
        return payTime;
    }

    /**
     * 设置支付时间
     *
     * @param payTime 支付时间
     */
    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    /**
     * 获取价格
     *
     * @return cost_price - 价格
     */
    public Double getCostPrice() {
        return costPrice;
    }

    /**
     * 设置价格
     *
     * @param costPrice 价格
     */
    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    /**
     * 获取支付商品类型 c,s,r,u   班级，优惠套餐，组合，升级
     *
     * @return type - 支付商品类型 c,s,r,u   班级，优惠套餐，组合，升级
     */
    public String getType() {
        return type;
    }

    /**
     * 设置支付商品类型 c,s,r,u   班级，优惠套餐，组合，升级
     *
     * @param type 支付商品类型 c,s,r,u   班级，优惠套餐，组合，升级
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取退款来源：1退余额2转订单
     *
     * @return back_source - 退款来源：1退余额2转订单
     */
    public Integer getBackSource() {
        return backSource;
    }

    /**
     * 设置退款来源：1退余额2转订单
     *
     * @param backSource 退款来源：1退余额2转订单
     */
    public void setBackSource(Integer backSource) {
        this.backSource = backSource;
    }

    /**
     * 获取分批支付 第几个子订单
     *
     * @return order_batch - 分批支付 第几个子订单
     */
    public Integer getOrderBatch() {
        return orderBatch;
    }

    /**
     * 设置分批支付 第几个子订单
     *
     * @param orderBatch 分批支付 第几个子订单
     */
    public void setOrderBatch(Integer orderBatch) {
        this.orderBatch = orderBatch;
    }

    /**
     * 获取分期付款当前期数 0：未分期
     *
     * @return order_instal - 分期付款当前期数 0：未分期
     */
    public Integer getOrderInstal() {
        return orderInstal;
    }

    /**
     * 设置分期付款当前期数 0：未分期
     *
     * @param orderInstal 分期付款当前期数 0：未分期
     */
    public void setOrderInstal(Integer orderInstal) {
        this.orderInstal = orderInstal;
    }

    /**
     * 获取班型信息(新增的冗余)
     *
     * @return class_type_info - 班型信息(新增的冗余)
     */
    public String getClassTypeInfo() {
        return classTypeInfo;
    }

    /**
     * 设置班型信息(新增的冗余)
     *
     * @param classTypeInfo 班型信息(新增的冗余)
     */
    public void setClassTypeInfo(String classTypeInfo) {
        this.classTypeInfo = classTypeInfo;
    }

    /**
     * 获取分期完成度1未完成2已完成
     *
     * @return order_batch_status - 分期完成度1未完成2已完成
     */
    public Integer getOrderBatchStatus() {
        return orderBatchStatus;
    }

    /**
     * 设置分期完成度1未完成2已完成
     *
     * @param orderBatchStatus 分期完成度1未完成2已完成
     */
    public void setOrderBatchStatus(Integer orderBatchStatus) {
        this.orderBatchStatus = orderBatchStatus;
    }

    /**
     * 获取是否天猫1:是2:否
     *
     * @return is_tmall - 是否天猫1:是2:否
     */
    public Integer getIsTmall() {
        return isTmall;
    }

    /**
     * 设置是否天猫1:是2:否
     *
     * @param isTmall 是否天猫1:是2:否
     */
    public void setIsTmall(Integer isTmall) {
        this.isTmall = isTmall;
    }

    /**
     * 获取skuid
     *
     * @return sku_id - skuid
     */
    public Integer getSkuId() {
        return skuId;
    }

    /**
     * 设置skuid
     *
     * @param skuId skuid
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
     * 获取业绩状态标记业绩是否生效1：是2：否
     *
     * @return achievement - 业绩状态标记业绩是否生效1：是2：否
     */
    public Integer getAchievement() {
        return achievement;
    }

    /**
     * 设置业绩状态标记业绩是否生效1：是2：否
     *
     * @param achievement 业绩状态标记业绩是否生效1：是2：否
     */
    public void setAchievement(Integer achievement) {
        this.achievement = achievement;
    }

    /**
     * 获取教务标记完成时间
     *
     * @return teach_finish_time - 教务标记完成时间
     */
    public String getTeachFinishTime() {
        return teachFinishTime;
    }

    /**
     * 设置教务标记完成时间
     *
     * @param teachFinishTime 教务标记完成时间
     */
    public void setTeachFinishTime(String teachFinishTime) {
        this.teachFinishTime = teachFinishTime;
    }

    /**
     * 获取1为未对比,2为对比失败，3对比通过
     *
     * @return batch_audit_status - 1为未对比,2为对比失败，3对比通过
     */
    public Integer getBatchAuditStatus() {
        return batchAuditStatus;
    }

    /**
     * 设置1为未对比,2为对比失败，3对比通过
     *
     * @param batchAuditStatus 1为未对比,2为对比失败，3对比通过
     */
    public void setBatchAuditStatus(Integer batchAuditStatus) {
        this.batchAuditStatus = batchAuditStatus;
    }

    /**
     * 获取财务审核时间(财务标记审核通过或者不通过时记录时间，财务审核状态为0要置空)
     *
     * @return finance_time - 财务审核时间(财务标记审核通过或者不通过时记录时间，财务审核状态为0要置空)
     */
    public String getFinanceTime() {
        return financeTime;
    }

    /**
     * 设置财务审核时间(财务标记审核通过或者不通过时记录时间，财务审核状态为0要置空)
     *
     * @param financeTime 财务审核时间(财务标记审核通过或者不通过时记录时间，财务审核状态为0要置空)
     */
    public void setFinanceTime(String financeTime) {
        this.financeTime = financeTime;
    }

    /**
     * 获取原单余额
     *
     * @return old_balance - 原单余额
     */
    public Double getOldBalance() {
        return oldBalance;
    }

    /**
     * 设置原单余额
     *
     * @param oldBalance 原单余额
     */
    public void setOldBalance(Double oldBalance) {
        this.oldBalance = oldBalance;
    }

    /**
     * 获取学习包总价格(默认值是0)重点
     *
     * @return study_package_price - 学习包总价格(默认值是0)重点
     */
    public Double getStudyPackagePrice() {
        return studyPackagePrice;
    }

    /**
     * 设置学习包总价格(默认值是0)重点
     *
     * @param studyPackagePrice 学习包总价格(默认值是0)重点
     */
    public void setStudyPackagePrice(Double studyPackagePrice) {
        this.studyPackagePrice = studyPackagePrice;
    }

    /**
     * 获取是否邮寄教材（或者说学习包）是否邮寄教材 0:学员不需要 1:需要 2:是班型不需要
     *
     * @return is_mail - 是否邮寄教材（或者说学习包）是否邮寄教材 0:学员不需要 1:需要 2:是班型不需要
     */
    public Integer getIsMail() {
        return isMail;
    }

    /**
     * 设置是否邮寄教材（或者说学习包）是否邮寄教材 0:学员不需要 1:需要 2:是班型不需要
     *
     * @param isMail 是否邮寄教材（或者说学习包）是否邮寄教材 0:学员不需要 1:需要 2:是班型不需要
     */
    public void setIsMail(Integer isMail) {
        this.isMail = isMail;
    }

    /**
     * 获取qq验证信息
     *
     * @return qq_validate - qq验证信息
     */
    public String getQqValidate() {
        return qqValidate;
    }

    /**
     * 设置qq验证信息
     *
     * @param qqValidate qq验证信息
     */
    public void setQqValidate(String qqValidate) {
        this.qqValidate = qqValidate;
    }
}