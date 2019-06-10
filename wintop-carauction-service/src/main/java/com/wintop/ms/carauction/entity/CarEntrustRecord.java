package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * class_name: CarEntrustRecord
 * package: com.wintop.ms.carauction.entity
 * describe: 委托价设置记录实体类
 * creat_user: lizhaoyang
 * creat_date-time: 2018/3/23/10:22
 **/
public class CarEntrustRecord implements Serializable{
    private static final long serialVersionUID = 6011512134658292996L;
    /**
     * 用户id
     */
    private Long id;
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
     * 会员所属店铺
     */
    private String userStoreName;
    /**
     * 车辆编号
     */
    private String carAutoNo;
    /**
     * 车辆标题
     */
    private String autoInfoName;
    /**
     * 车辆所属店铺
     */
    private String carStoreName;
    /**
     * 起拍价
     */
    private BigDecimal startingPrice;
    /**
     * 委托时间
     */
    private Date createTime;
    /**
     * 委托价金额
     */
    private BigDecimal entrustFee;

    public CarEntrustRecord() {
    }

    public CarEntrustRecord(Long id, String userCode, String userNum, String username, String mobile, String userStoreName, String carAutoNo, String autoInfoName, String carStoreName, BigDecimal startingPrice, Date createTime, BigDecimal entrustFee) {
        this.id = id;
        this.userCode = userCode;
        this.userNum = userNum;
        this.username = username;
        this.mobile = mobile;
        this.userStoreName = userStoreName;
        this.carAutoNo = carAutoNo;
        this.autoInfoName = autoInfoName;
        this.carStoreName = carStoreName;
        this.startingPrice = startingPrice;
        this.createTime = createTime;
        this.entrustFee = entrustFee;
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

    public String getUserStoreName() {
        return userStoreName;
    }

    public void setUserStoreName(String userStoreName) {
        this.userStoreName = userStoreName;
    }

    public String getCarAutoNo() {
        return carAutoNo;
    }

    public void setCarAutoNo(String carAutoNo) {
        this.carAutoNo = carAutoNo;
    }

    public String getAutoInfoName() {
        return autoInfoName;
    }

    public void setAutoInfoName(String autoInfoName) {
        this.autoInfoName = autoInfoName;
    }

    public String getCarStoreName() {
        return carStoreName;
    }

    public void setCarStoreName(String carStoreName) {
        this.carStoreName = carStoreName;
    }

    public BigDecimal getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(BigDecimal startingPrice) {
        this.startingPrice = startingPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getEntrustFee() {
        return entrustFee;
    }

    public void setEntrustFee(BigDecimal entrustFee) {
        this.entrustFee = entrustFee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarEntrustRecord)) return false;

        CarEntrustRecord record = (CarEntrustRecord) o;

        if (id != null ? !id.equals(record.id) : record.id != null) return false;
        if (userCode != null ? !userCode.equals(record.userCode) : record.userCode != null) return false;
        if (userNum != null ? !userNum.equals(record.userNum) : record.userNum != null) return false;
        if (username != null ? !username.equals(record.username) : record.username != null) return false;
        if (mobile != null ? !mobile.equals(record.mobile) : record.mobile != null) return false;
        if (userStoreName != null ? !userStoreName.equals(record.userStoreName) : record.userStoreName != null)
            return false;
        if (carAutoNo != null ? !carAutoNo.equals(record.carAutoNo) : record.carAutoNo != null) return false;
        if (autoInfoName != null ? !autoInfoName.equals(record.autoInfoName) : record.autoInfoName != null)
            return false;
        if (carStoreName != null ? !carStoreName.equals(record.carStoreName) : record.carStoreName != null)
            return false;
        if (startingPrice != null ? !startingPrice.equals(record.startingPrice) : record.startingPrice != null)
            return false;
        if (createTime != null ? !createTime.equals(record.createTime) : record.createTime != null) return false;
        return entrustFee != null ? entrustFee.equals(record.entrustFee) : record.entrustFee == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (userNum != null ? userNum.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (userStoreName != null ? userStoreName.hashCode() : 0);
        result = 31 * result + (carAutoNo != null ? carAutoNo.hashCode() : 0);
        result = 31 * result + (autoInfoName != null ? autoInfoName.hashCode() : 0);
        result = 31 * result + (carStoreName != null ? carStoreName.hashCode() : 0);
        result = 31 * result + (startingPrice != null ? startingPrice.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (entrustFee != null ? entrustFee.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CarEntrustRecord{" +
                "id=" + id +
                ", userCode='" + userCode + '\'' +
                ", userNum='" + userNum + '\'' +
                ", username='" + username + '\'' +
                ", mobile='" + mobile + '\'' +
                ", userStoreName='" + userStoreName + '\'' +
                ", carAutoNo='" + carAutoNo + '\'' +
                ", autoInfoName='" + autoInfoName + '\'' +
                ", carStoreName='" + carStoreName + '\'' +
                ", startingPrice=" + startingPrice +
                ", createTime=" + createTime +
                ", entrustFee=" + entrustFee +
                '}';
    }
}
