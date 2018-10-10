package com.duia.mars.common.dto;

import com.duia.mars.model.FinanceOrder;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * Created by Administrator on 2017/8/29.
 */
@Alias("FinanceOrderDto")
public class FinanceOrderDto extends FinanceOrder {
    private Integer fmId; /*FinanceMonthEnsure 的主键id*/
    private String	everyMonthMoney;		 /* 每月确认收入的json：{[{year:2017,month:04,month_class：10},{year:2017,month:05,month_class：10}]} */
    private Date beginDate;		 /* 开始确认的年月 */
    private Date	endDate;		 /* 结束确认的年月 */
    private Integer	allCourse;		 /* 班级所有节 */

    public Integer getFmId() {
        return fmId;
    }

    public void setFmId(Integer fmId) {
        this.fmId = fmId;
    }

    public String getEveryMonthMoney() {
        return everyMonthMoney;
    }

    public void setEveryMonthMoney(String everyMonthMoney) {
        this.everyMonthMoney = everyMonthMoney;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getAllCourse() {
        return allCourse;
    }

    public void setAllCourse(Integer allCourse) {
        this.allCourse = allCourse;
    }
}
