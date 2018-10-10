package com.duia.mars.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "authority_users")
public class AuthorityUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 员工真实姓名或者花名
     */
    private String username;

    /**
     * md5密码
     */
    private String password;

    /**
     * 员工的手机号
     */
    private String mobile;

    /**
     * 员工的电子邮箱
     */
    private String email;

    /**
     * 性别
     */
    private String sex;

    /**
     * 废弃字段 非必填
     */
    private String job;

    /**
     * 员工工号
     */
    private String num;

    /**
     * 账号是否锁定(0:锁定1:正常)
     */
    @Column(name = "user_lock")
    private Boolean userLock;

    /**
     * 员工状态(0:离职1:在职)
     */
    @Column(name = "user_status")
    private Boolean userStatus;

    /**
     * 员工头像(大图)
     */
    @Column(name = "big_img")
    private String bigImg;

    /**
     * 员工头像(小图)
     */
    @Column(name = "small_img")
    private String smallImg;

    /**
     * 1:超级管理员0:普通用户
     */
    private Boolean administrator;

    /**
     * 员工创建时间
     */
    @Column(name = "create_datetime")
    private Date createDatetime;

    /**
     * 最后一次修改该员工信息的用户
     */
    @Column(name = "last_modify_user_id")
    private Integer lastModifyUserId;

    /**
     * 最后一次修改该员工信息的用户email
     */
    @Column(name = "last_modify_user_email")
    private String lastModifyUserEmail;

    /**
     * 最后一次修改该员工信息的时间
     */
    @Column(name = "last_modify_datetime")
    private Date lastModifyDatetime;

    /**
     * 最后一次登录时间
     */
    @Column(name = "last_login_datetime")
    private Date lastLoginDatetime;

    /**
     * 1:老师，2：教务 3：销售 4：财务 5：技术 6：其他 9999：大总管
     */
    private Integer position;

    /**
     * 令牌
     */
    private String token;

    /**
     * 个人积分
     */
    private Integer point;

    /**
     * 昵称
     */
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 签名
     */
    private String signature;

    /**
     * 个人简介
     */
    private String introduct;

    /**
     * 绑定的用户账号
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 员工类型（1:全职，2:兼职）
     */
    @Column(name = "user_type")
    private Integer userType;

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
     * 获取员工真实姓名或者花名
     *
     * @return username - 员工真实姓名或者花名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置员工真实姓名或者花名
     *
     * @param username 员工真实姓名或者花名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取md5密码
     *
     * @return password - md5密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置md5密码
     *
     * @param password md5密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取员工的手机号
     *
     * @return mobile - 员工的手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置员工的手机号
     *
     * @param mobile 员工的手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取员工的电子邮箱
     *
     * @return email - 员工的电子邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置员工的电子邮箱
     *
     * @param email 员工的电子邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取性别
     *
     * @return sex - 性别
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置性别
     *
     * @param sex 性别
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 获取废弃字段 非必填
     *
     * @return job - 废弃字段 非必填
     */
    public String getJob() {
        return job;
    }

    /**
     * 设置废弃字段 非必填
     *
     * @param job 废弃字段 非必填
     */
    public void setJob(String job) {
        this.job = job;
    }

    /**
     * 获取员工工号
     *
     * @return num - 员工工号
     */
    public String getNum() {
        return num;
    }

    /**
     * 设置员工工号
     *
     * @param num 员工工号
     */
    public void setNum(String num) {
        this.num = num;
    }

    /**
     * 获取账号是否锁定(0:锁定1:正常)
     *
     * @return user_lock - 账号是否锁定(0:锁定1:正常)
     */
    public Boolean getUserLock() {
        return userLock;
    }

    /**
     * 设置账号是否锁定(0:锁定1:正常)
     *
     * @param userLock 账号是否锁定(0:锁定1:正常)
     */
    public void setUserLock(Boolean userLock) {
        this.userLock = userLock;
    }

    /**
     * 获取员工状态(0:离职1:在职)
     *
     * @return user_status - 员工状态(0:离职1:在职)
     */
    public Boolean getUserStatus() {
        return userStatus;
    }

    /**
     * 设置员工状态(0:离职1:在职)
     *
     * @param userStatus 员工状态(0:离职1:在职)
     */
    public void setUserStatus(Boolean userStatus) {
        this.userStatus = userStatus;
    }

    /**
     * 获取员工头像(大图)
     *
     * @return big_img - 员工头像(大图)
     */
    public String getBigImg() {
        return bigImg;
    }

    /**
     * 设置员工头像(大图)
     *
     * @param bigImg 员工头像(大图)
     */
    public void setBigImg(String bigImg) {
        this.bigImg = bigImg;
    }

    /**
     * 获取员工头像(小图)
     *
     * @return small_img - 员工头像(小图)
     */
    public String getSmallImg() {
        return smallImg;
    }

    /**
     * 设置员工头像(小图)
     *
     * @param smallImg 员工头像(小图)
     */
    public void setSmallImg(String smallImg) {
        this.smallImg = smallImg;
    }

    /**
     * 获取1:超级管理员0:普通用户
     *
     * @return administrator - 1:超级管理员0:普通用户
     */
    public Boolean getAdministrator() {
        return administrator;
    }

    /**
     * 设置1:超级管理员0:普通用户
     *
     * @param administrator 1:超级管理员0:普通用户
     */
    public void setAdministrator(Boolean administrator) {
        this.administrator = administrator;
    }

    /**
     * 获取员工创建时间
     *
     * @return create_datetime - 员工创建时间
     */
    public Date getCreateDatetime() {
        return createDatetime;
    }

    /**
     * 设置员工创建时间
     *
     * @param createDatetime 员工创建时间
     */
    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    /**
     * 获取最后一次修改该员工信息的用户
     *
     * @return last_modify_user_id - 最后一次修改该员工信息的用户
     */
    public Integer getLastModifyUserId() {
        return lastModifyUserId;
    }

    /**
     * 设置最后一次修改该员工信息的用户
     *
     * @param lastModifyUserId 最后一次修改该员工信息的用户
     */
    public void setLastModifyUserId(Integer lastModifyUserId) {
        this.lastModifyUserId = lastModifyUserId;
    }

    /**
     * 获取最后一次修改该员工信息的用户email
     *
     * @return last_modify_user_email - 最后一次修改该员工信息的用户email
     */
    public String getLastModifyUserEmail() {
        return lastModifyUserEmail;
    }

    /**
     * 设置最后一次修改该员工信息的用户email
     *
     * @param lastModifyUserEmail 最后一次修改该员工信息的用户email
     */
    public void setLastModifyUserEmail(String lastModifyUserEmail) {
        this.lastModifyUserEmail = lastModifyUserEmail;
    }

    /**
     * 获取最后一次修改该员工信息的时间
     *
     * @return last_modify_datetime - 最后一次修改该员工信息的时间
     */
    public Date getLastModifyDatetime() {
        return lastModifyDatetime;
    }

    /**
     * 设置最后一次修改该员工信息的时间
     *
     * @param lastModifyDatetime 最后一次修改该员工信息的时间
     */
    public void setLastModifyDatetime(Date lastModifyDatetime) {
        this.lastModifyDatetime = lastModifyDatetime;
    }

    /**
     * 获取最后一次登录时间
     *
     * @return last_login_datetime - 最后一次登录时间
     */
    public Date getLastLoginDatetime() {
        return lastLoginDatetime;
    }

    /**
     * 设置最后一次登录时间
     *
     * @param lastLoginDatetime 最后一次登录时间
     */
    public void setLastLoginDatetime(Date lastLoginDatetime) {
        this.lastLoginDatetime = lastLoginDatetime;
    }

    /**
     * 获取1:老师，2：教务 3：销售 4：财务 5：技术 6：其他 9999：大总管
     *
     * @return position - 1:老师，2：教务 3：销售 4：财务 5：技术 6：其他 9999：大总管
     */
    public Integer getPosition() {
        return position;
    }

    /**
     * 设置1:老师，2：教务 3：销售 4：财务 5：技术 6：其他 9999：大总管
     *
     * @param position 1:老师，2：教务 3：销售 4：财务 5：技术 6：其他 9999：大总管
     */
    public void setPosition(Integer position) {
        this.position = position;
    }

    /**
     * 获取令牌
     *
     * @return token - 令牌
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置令牌
     *
     * @param token 令牌
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 获取个人积分
     *
     * @return point - 个人积分
     */
    public Integer getPoint() {
        return point;
    }

    /**
     * 设置个人积分
     *
     * @param point 个人积分
     */
    public void setPoint(Integer point) {
        this.point = point;
    }

    /**
     * 获取昵称
     *
     * @return nick_name - 昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置昵称
     *
     * @param nickName 昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 获取签名
     *
     * @return signature - 签名
     */
    public String getSignature() {
        return signature;
    }

    /**
     * 设置签名
     *
     * @param signature 签名
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }

    /**
     * 获取个人简介
     *
     * @return introduct - 个人简介
     */
    public String getIntroduct() {
        return introduct;
    }

    /**
     * 设置个人简介
     *
     * @param introduct 个人简介
     */
    public void setIntroduct(String introduct) {
        this.introduct = introduct;
    }

    /**
     * 获取绑定的用户账号
     *
     * @return user_id - 绑定的用户账号
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置绑定的用户账号
     *
     * @param userId 绑定的用户账号
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取员工类型（1:全职，2:兼职）
     *
     * @return user_type - 员工类型（1:全职，2:兼职）
     */
    public Integer getUserType() {
        return userType;
    }

    /**
     * 设置员工类型（1:全职，2:兼职）
     *
     * @param userType 员工类型（1:全职，2:兼职）
     */
    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}