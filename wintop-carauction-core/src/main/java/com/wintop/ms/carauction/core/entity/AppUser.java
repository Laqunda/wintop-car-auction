package com.wintop.ms.carauction.core.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 客户端用户表
 * Created by liangtingsen on 2018/2/3.
 */
public class AppUser implements Serializable{
    private Long id;
    private String userName;//用户名
    private String password;//密码
    private String name;//姓名
    private String address;//同学地址
    private String city;//所在城市
    private String headImg;//头像
    private String sex;//性别男女
    private String mobile;//手机号
    private String loginIp;//最近登陆ip
    private Timestamp registTime;//注册时间
    private Timestamp loginTime;//最近登陆时间
    private String status;//状态
    private String userNum;//车商号
    private String isAuth;//是否已认证
    private String isSign;//是否已签约
    private Long userRankId;//用户等级

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Timestamp getRegistTime() {
        return registTime;
    }

    public void setRegistTime(Timestamp registTime) {
        this.registTime = registTime;
    }

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getIsAuth() {
        return isAuth;
    }

    public void setIsAuth(String isAuth) {
        this.isAuth = isAuth;
    }

    public String getIsSign() {
        return isSign;
    }

    public void setIsSign(String isSign) {
        this.isSign = isSign;
    }

    public Long getUserRankId() {
        return userRankId;
    }

    public void setUserRankId(Long userRankId) {
        this.userRankId = userRankId;
    }
}
