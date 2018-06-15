package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * class_name: DepositDetail
 * package: com.wintop.ms.carauction.entity
 * describe: 保证金明细实体类
 * creat_user: lizhaoyang
 * creat_date-time: 2018/3/26/17:46
 **/
public class DepositDetail implements Serializable{
    private static final long serialVersionUID = -5982698522400831245L;
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
     * 用户姓名
     */
    private String username;
    /**
     * 联系电话
     */
    private String mobile;
    /**
     * 会员所在店铺名称
     */
    private String customerStoreName;
    /**
     * 支付时间
     */
    private Date payTime;
    /**
     *支付方式
     */
    private String payType;
    /**
     * 支付流水号
     */
    private String logNo;
    /**
     * 支付金额
     */
    private BigDecimal payFee;

    public DepositDetail() {
    }

    public DepositDetail(Long id, String userCode, String userNum, String username, String mobile, String customerStoreName, Date payTime, String payType, String logNo, BigDecimal payFee) {
        this.id = id;
        this.userCode = userCode;
        this.userNum = userNum;
        this.username = username;
        this.mobile = mobile;
        this.customerStoreName = customerStoreName;
        this.payTime = payTime;
        this.payType = payType;
        this.logNo = logNo;
        this.payFee = payFee;
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

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getLogNo() {
        return logNo;
    }

    public void setLogNo(String logNo) {
        this.logNo = logNo;
    }

    public BigDecimal getPayFee() {
        return payFee;
    }

    public void setPayFee(BigDecimal payFee) {
        this.payFee = payFee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DepositDetail)) return false;

        DepositDetail that = (DepositDetail) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (userCode != null ? !userCode.equals(that.userCode) : that.userCode != null) return false;
        if (userNum != null ? !userNum.equals(that.userNum) : that.userNum != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) return false;
        if (customerStoreName != null ? !customerStoreName.equals(that.customerStoreName) : that.customerStoreName != null)
            return false;
        if (payTime != null ? !payTime.equals(that.payTime) : that.payTime != null) return false;
        if (payType != null ? !payType.equals(that.payType) : that.payType != null) return false;
        if (logNo != null ? !logNo.equals(that.logNo) : that.logNo != null) return false;
        return payFee != null ? payFee.equals(that.payFee) : that.payFee == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (userNum != null ? userNum.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (customerStoreName != null ? customerStoreName.hashCode() : 0);
        result = 31 * result + (payTime != null ? payTime.hashCode() : 0);
        result = 31 * result + (payType != null ? payType.hashCode() : 0);
        result = 31 * result + (logNo != null ? logNo.hashCode() : 0);
        result = 31 * result + (payFee != null ? payFee.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DepositDetail{" +
                "id=" + id +
                ", userCode='" + userCode + '\'' +
                ", userNum='" + userNum + '\'' +
                ", username='" + username + '\'' +
                ", mobile='" + mobile + '\'' +
                ", customerStoreName='" + customerStoreName + '\'' +
                ", payTime=" + payTime +
                ", payType='" + payType + '\'' +
                ", logNo='" + logNo + '\'' +
                ", payFee=" + payFee +
                '}';
    }
}
