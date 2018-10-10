package com.duia.mars.common.dto;


import com.duia.mars.model.FinanceMonthEnsure;
import org.apache.ibatis.type.Alias;

/**
 * Created by Administrator on 2017/8/16.
 */
@Alias("FinanceMonthEnsureDto")
public class FinanceMonthEnsureDto extends FinanceMonthEnsure {
    private String classBeginTime;
    private String classEndTime;
    private Integer allCoursesCount;

    public String getClassBeginTime() {
        return classBeginTime;
    }

    public void setClassBeginTime(String classBeginTime) {
        this.classBeginTime = classBeginTime;
    }

    public String getClassEndTime() {
        return classEndTime;
    }

    public void setClassEndTime(String classEndTime) {
        this.classEndTime = classEndTime;
    }

    public Integer getAllCoursesCount() {
        return allCoursesCount;
    }

    public void setAllCoursesCount(Integer allCoursesCount) {
        this.allCoursesCount = allCoursesCount;
    }
}
