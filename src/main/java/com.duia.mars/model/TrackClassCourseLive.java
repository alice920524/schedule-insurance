package com.duia.mars.model;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 学习记录
 */
//@Table(name = "track_class_course_live")
//@Table(name = "track_class_course_live_bak20180306")// todo for 03-06 12:00前数据
@Table(name = "track_class_course_live")// todo for 03-06 12:00后数据
public class TrackClassCourseLive {

	@Id
	private Integer id;

	private Integer	userId;					/* 用户id */
	private Integer	studentId;				/* 学员id */
	private Integer	classId;				/* 班级id */
	private Integer	courseId;				/* 课表节id */
	private String	source;					/* 数据来源 */
	private Date	inTime;
	private String	durationSecond;			/* 持续时长，秒 */
	private Integer	isAttend;
	private Integer	appType;				/* 应用类型 */
	private Integer	platform;				/* 平台：1：android,2:IOS */
	private String	recordId;				/* 每进入一次的记录id */
	private Date	enterTime;				/* 进入时间 */
	private Date	updateTime;				/* 更新时间（作为最后离开时间） */
	private Integer	attendanceDuration;		/* 出勤时长 */

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public Integer getClassId() {
		return classId;
	}
	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Date getInTime() {
		return inTime;
	}
	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}
	public String getDurationSecond() {
		return durationSecond;
	}
	public void setDurationSecond(String durationSecond) {
		this.durationSecond = durationSecond;
	}
	public Integer getIsAttend() {
		return isAttend;
	}
	public void setIsAttend(Integer isAttend) {
		this.isAttend = isAttend;
	}
	public Integer getAppType() {
		return appType;
	}
	public void setAppType(Integer appType) {
		this.appType = appType;
	}
	public Integer getPlatform() {
		return platform;
	}
	public void setPlatform(Integer platform) {
		this.platform = platform;
	}
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
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
	public Integer getAttendanceDuration() {
		return attendanceDuration;
	}
	public void setAttendanceDuration(Integer attendanceDuration) {
		this.attendanceDuration = attendanceDuration;
	}
}
