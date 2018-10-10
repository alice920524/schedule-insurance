package com.duia.mars.model;

import org.apache.ibatis.type.Alias;

/**
 * Created by Administrator on 2017/8/22.
 */
@Alias("FinanceMonthCount")
public class FinanceMonthCount {
    private String monDate;
    private String monCount;

    public String getMonDate() {
        return monDate;
    }

    public void setMonDate(String monDate) {
        this.monDate = monDate;
    }

    public String getMonCount() {
        return monCount;
    }

    public void setMonCount(String monCount) {
        this.monCount = monCount;
    }

    @Override
    public String toString() {
        return "FinanceMonthCount{" +
                "monDate='" + monDate + '\'' +
                ", monCount='" + monCount + '\'' +
                '}';
    }
}

