package com.duia.mars.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "finance_month_ensure")
public class FinanceMonthEnsure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * finance_order_com表主键
     */
    @Column(name = "fo_id")
    private Integer foId;

    /**
     * 每月确认收入的json：{[{year:2017,month:04,month_class：10},{year:2017,month:05,month_class：10}]}
     */
    @Column(name = "every_month_money")
    private String everyMonthMoney;

    /**
     * 开始确认的年月
     */
    @Column(name = "begin_date")
    private Date beginDate;

    /**
     * 结束确认的年月
     */
    @Column(name = "end_date")
    private Date endDate;

    /**
     * 0未确认完成  1：已全部确认
     */
    private Integer status;

    /**
     * 班级所有节
     */
    @Column(name = "all_course")
    private Integer allCourse;

    /**
     * 班级
     */
    @Column(name = "class_id")
    private Integer classId;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 班号
     */
    @Column(name = "class_no")
    private String classNo;

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
     * 获取finance_order_com表主键
     *
     * @return fo_id - finance_order_com表主键
     */
    public Integer getFoId() {
        return foId;
    }

    /**
     * 设置finance_order_com表主键
     *
     * @param foId finance_order_com表主键
     */
    public void setFoId(Integer foId) {
        this.foId = foId;
    }

    /**
     * 获取每月确认收入的json：{[{year:2017,month:04,month_class：10},{year:2017,month:05,month_class：10}]}
     *
     * @return every_month_money - 每月确认收入的json：{[{year:2017,month:04,month_class：10},{year:2017,month:05,month_class：10}]}
     */
    public String getEveryMonthMoney() {
        return everyMonthMoney;
    }

    /**
     * 设置每月确认收入的json：{[{year:2017,month:04,month_class：10},{year:2017,month:05,month_class：10}]}
     *
     * @param everyMonthMoney 每月确认收入的json：{[{year:2017,month:04,month_class：10},{year:2017,month:05,month_class：10}]}
     */
    public void setEveryMonthMoney(String everyMonthMoney) {
        this.everyMonthMoney = everyMonthMoney;
    }

    /**
     * 获取开始确认的年月
     *
     * @return begin_date - 开始确认的年月
     */
    public Date getBeginDate() {
        return beginDate;
    }

    /**
     * 设置开始确认的年月
     *
     * @param beginDate 开始确认的年月
     */
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * 获取结束确认的年月
     *
     * @return end_date - 结束确认的年月
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置结束确认的年月
     *
     * @param endDate 结束确认的年月
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 获取0未确认完成  1：已全部确认
     *
     * @return status - 0未确认完成  1：已全部确认
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0未确认完成  1：已全部确认
     *
     * @param status 0未确认完成  1：已全部确认
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取班级所有节
     *
     * @return all_course - 班级所有节
     */
    public Integer getAllCourse() {
        return allCourse;
    }

    /**
     * 设置班级所有节
     *
     * @param allCourse 班级所有节
     */
    public void setAllCourse(Integer allCourse) {
        this.allCourse = allCourse;
    }

    /**
     * 获取班级
     *
     * @return class_id - 班级
     */
    public Integer getClassId() {
        return classId;
    }

    /**
     * 设置班级
     *
     * @param classId 班级
     */
    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    /**
     * 获取订单id
     *
     * @return order_id - 订单id
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 设置订单id
     *
     * @param orderId 订单id
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取班号
     *
     * @return class_no - 班号
     */
    public String getClassNo() {
        return classNo;
    }

    /**
     * 设置班号
     *
     * @param classNo 班号
     */
    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }
}