package com.duia.mars.model;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Table(name = "class_student_detail")
public class ClassStudentDetail {

	@Id
	private Integer id;
	private Integer	userId;		
	private String	name;		
	private Date	birthdate;		
	private Integer	sex;		
	private String	province;		
	private String	city;		
	private String	degree;		
	private String	profession;		
	private String	level;		
	private String	isexam;		
	private Date	examDate;		
	private String	istrain;		
	private String	trainOrgName;		
	private Double	trainPrice;		
	private String	mobile;		
	private String	qqNo;		
	private String	qqName;		
	private String	email;		
	private String	maritalStatus;		
	private String	child;		
	private String	income;		
	private String	salary;		
	private String	netYear;		
	private String	shopTime;		
	private String	netTime;		
	private String	shopPayment;		
	private String	eduPayment;		
	private String	reason;		
	private String	yxAccount;		
	private String	yxToken;		
	private Integer	yxOpen;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getIsexam() {
		return isexam;
	}
	public void setIsexam(String isexam) {
		this.isexam = isexam;
	}
	public Date getExamDate() {
		return examDate;
	}
	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}
	public String getIstrain() {
		return istrain;
	}
	public void setIstrain(String istrain) {
		this.istrain = istrain;
	}
	public String getTrainOrgName() {
		return trainOrgName;
	}
	public void setTrainOrgName(String trainOrgName) {
		this.trainOrgName = trainOrgName;
	}
	public Double getTrainPrice() {
		return trainPrice;
	}
	public void setTrainPrice(Double trainPrice) {
		this.trainPrice = trainPrice;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getQqNo() {
		return qqNo;
	}
	public void setQqNo(String qqNo) {
		this.qqNo = qqNo;
	}
	public String getQqName() {
		return qqName;
	}
	public void setQqName(String qqName) {
		this.qqName = qqName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getChild() {
		return child;
	}
	public void setChild(String child) {
		this.child = child;
	}
	public String getIncome() {
		return income;
	}
	public void setIncome(String income) {
		this.income = income;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getNetYear() {
		return netYear;
	}
	public void setNetYear(String netYear) {
		this.netYear = netYear;
	}
	public String getShopTime() {
		return shopTime;
	}
	public void setShopTime(String shopTime) {
		this.shopTime = shopTime;
	}
	public String getNetTime() {
		return netTime;
	}
	public void setNetTime(String netTime) {
		this.netTime = netTime;
	}
	public String getShopPayment() {
		return shopPayment;
	}
	public void setShopPayment(String shopPayment) {
		this.shopPayment = shopPayment;
	}
	public String getEduPayment() {
		return eduPayment;
	}
	public void setEduPayment(String eduPayment) {
		this.eduPayment = eduPayment;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getYxAccount() {
		return yxAccount;
	}
	public void setYxAccount(String yxAccount) {
		this.yxAccount = yxAccount;
	}
	public String getYxToken() {
		return yxToken;
	}
	public void setYxToken(String yxToken) {
		this.yxToken = yxToken;
	}
	public Integer getYxOpen() {
		return yxOpen;
	}
	public void setYxOpen(Integer yxOpen) {
		this.yxOpen = yxOpen;
	}
}
