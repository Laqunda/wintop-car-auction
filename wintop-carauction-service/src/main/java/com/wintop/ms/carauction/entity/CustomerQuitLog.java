package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * class_name: CustomerQuitLog
 * package: com.wintop.ms.carauction.entity
 * describe: 用户退会记录实体类
 * creat_user: lizhaoyang
 * creat_date-time: 2018/3/26/13:16
 **/
public class CustomerQuitLog implements Serializable{
    private static final long serialVersionUID = -6096110899175062499L;
    /**
     * 用户id
     */
    private  Long id;
    /**
     * 会员编号
     */
    private String userCode;
    /**
     * 车商号
     */
    private String userNum;
    /**
     * 会员姓名
     */
    private String username;
    /**
     * 联系电话
     */
    private String mobile;
    /**
     * 会员所属店铺名称
     */
    private String customerStoreName;
    /**
     * 退回保证金
     */
    private BigDecimal payFee;
    /**
     * 退会原因
     */
    private String msg;
    /**
     * 退会时间
     */
    private Date  quitTime;
    /**
     * 操作人
     */
    private String editerName;

    public CustomerQuitLog() {
    }

    public CustomerQuitLog(Long id, String userCode, String userNum, String username, String mobile, String customerStoreName, BigDecimal payFee, String msg, Date quitTime, String editerName) {
        this.id = id;
        this.userCode = userCode;
        this.userNum = userNum;
        this.username = username;
        this.mobile = mobile;
        this.customerStoreName = customerStoreName;
        this.payFee = payFee;
        this.msg = msg;
        this.quitTime = quitTime;
        this.editerName = editerName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCustomerStoreName() {
        return customerStoreName;
    }

    public void setCustomerStoreName(String customerStoreName) {
        this.customerStoreName = customerStoreName;
    }

    public BigDecimal getPayFee() {
        return payFee;
    }

    public void setPayFee(BigDecimal payFee) {
        this.payFee = payFee;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getQuitTime() {
        return quitTime;
    }

    public void setQuitTime(Date quitTime) {
        this.quitTime = quitTime;
    }

    public String getEditerName() {
        return editerName;
    }

    public void setEditerName(String editerName) {
        this.editerName = editerName;
    }



    @Override
    public String toString() {
        return "CustomerQuitLog{" +
                "id=" + id +
                ", userCode='" + userCode + '\'' +
                ", userNum='" + userNum + '\'' +
                ", username='" + username + '\'' +
                ", mobile='" + mobile + '\'' +
                ", customerStoreName='" + customerStoreName + '\'' +
                ", payFee=" + payFee +
                ", msg='" + msg + '\'' +
                ", quitTime=" + quitTime +
                ", editerName='" + editerName + '\'' +
                '}';
    }
}
