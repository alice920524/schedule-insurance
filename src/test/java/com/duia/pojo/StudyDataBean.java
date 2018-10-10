package com.duia.pojo;

import com.duia.mars.common.util.KeyBean;

import java.util.Date;

/**
 * 学习数据-正课直播数据
 * @author xingshaofei
 * @date 2017-12-27 下午 6:37
 */
public class StudyDataBean implements KeyBean<Integer> {

    private Integer id;

    private Integer studentId;

    /*private String  insuranceUserName;
    private String  idCard;
    private Date    payTime;
    private String  insurancePeriod;
    private Date    signDate;*/

    private String  classNo;
    private Integer classCourseCount;

    private Integer classTypeId;
    private String  classTypeTitle;

    private String  scheduleName;
    private String  chapterName;

    private Integer courseId;
    private String  courseName;
    private String  courseStartTime;

    private String  source;

    private Date    enterTime;
    private Date    updateTime;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getStudentId() {
        return studentId;
    }
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
    public String getClassNo() {
        return classNo;
    }
    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }
    public Integer getClassCourseCount() {
        return classCourseCount;
    }
    public void setClassCourseCount(Integer classCourseCount) {
        this.classCourseCount = classCourseCount;
    }
    public Integer getClassTypeId() {
        return classTypeId;
    }
    public void setClassTypeId(Integer classTypeId) {
        this.classTypeId = classTypeId;
    }
    public String getClassTypeTitle() {
        return classTypeTitle;
    }
    public void setClassTypeTitle(String classTypeTitle) {
        this.classTypeTitle = classTypeTitle;
    }
    public String getScheduleName() {
        return scheduleName;
    }
    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }
    public String getChapterName() {
        return chapterName;
    }
    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }
    public Integer getCourseId() {
        return courseId;
    }
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public String getCourseStartTime() {
        return courseStartTime;
    }
    public void setCourseStartTime(String courseStartTime) {
        this.courseStartTime = courseStartTime;
    }
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public Date getEnterTime() {
        return enterTime;
    }
    public void setEnterTime(Date enterTime) {
        this.enterTime = enterTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public Integer getKey() {
        return id;
    }

    @Override
    public String toString() {

        return "StudyDataBean{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", classNo='" + classNo + '\'' +
                ", classCourseCount=" + classCourseCount +
                ", classTypeId=" + classTypeId +
                ", classTypeTitle='" + classTypeTitle + '\'' +
                ", scheduleName='" + scheduleName + '\'' +
                ", chapterName='" + chapterName + '\'' +
                ", courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", courseStartTime='" + courseStartTime + '\'' +
                ", source='" + source + '\'' +
                ", enterTime=" + enterTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
