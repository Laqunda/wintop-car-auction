package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * class_name: DepositFreeze
 * package: com.wintop.ms.carauction.entity
 * describe: 保证金冻结明细实体类
 * creat_user: lizhaoyang
 * creat_date-time: 2018/3/27/16:58
 **/
public class DepositFreeze implements Serializable{
    private static final long serialVersionUID = -6290849540779653253L;
    /**
     * 用户id
     */
    private Long id;
    /**
     * 会员编号
     */
    private String userCode;
    /**
     *车商号
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
     * 用户所属店铺名称
     */
    private String customerStoreName;
    /**
     * 冻结时间
     */
    private Date freezeTime;
    /**
     * 冻结保证金金额
      */
    private BigDecimal depositAmount;
    /**
     * 冻结理由
     */
    private String remark;

    public DepositFreeze() {
    }

    public DepositFreeze(Long id, String userCode, String userNum, String username, String mobile, String customerStoreName, Date freezeTime, BigDecimal depositAmount, String remark) {
        this.id = id;
        this.userCode = userCode;
        this.userNum = userNum;
        this.username = username;
        this.mobile = mobile;
        this.customerStoreName = customerStoreName;
        this.freezeTime = freezeTime;
        this.depositAmount = depositAmount;
        this.remark = remark;
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

    public Date getFreezeTime() {
        return freezeTime;
    }

    public void setFreezeTime(Date freezeTime) {
        this.freezeTime = freezeTime;
    }

    public BigDecimal getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DepositFreeze)) return false;

        DepositFreeze that = (DepositFreeze) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (userCode != null ? !userCode.equals(that.userCode) : that.userCode != null) return false;
        if (userNum != null ? !userNum.equals(that.userNum) : that.userNum != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) return false;
        if (customerStoreName != null ? !customerStoreName.equals(that.customerStoreName) : that.customerStoreName != null)
            return false;
        if (freezeTime != null ? !freezeTime.equals(that.freezeTime) : that.freezeTime != null) return false;
        if (depositAmount != null ? !depositAmount.equals(that.depositAmount) : that.depositAmount != null)
            return false;
        return remark != null ? remark.equals(that.remark) : that.remark == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (userNum != null ? userNum.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (customerStoreName != null ? customerStoreName.hashCode() : 0);
        result = 31 * result + (freezeTime != null ? freezeTime.hashCode() : 0);
        result = 31 * result + (depositAmount != null ? depositAmount.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DepositFreeze{" +
                "id=" + id +
                ", userCode='" + userCode + '\'' +
                ", userNum='" + userNum + '\'' +
                ", username='" + username + '\'' +
                ", mobile='" + mobile + '\'' +
                ", customerStoreName='" + customerStoreName + '\'' +
                ", freezeTime=" + freezeTime +
                ", depositAmount=" + depositAmount +
                ", remark='" + remark + '\'' +
                '}';
    }
}
