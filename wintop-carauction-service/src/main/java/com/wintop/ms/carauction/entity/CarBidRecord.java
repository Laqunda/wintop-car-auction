package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * class_name: CarBidRecord
 * package: com.wintop.ms.carauction.entity
 * describe: 车辆出价记录实体类
 * creat_user: lizhaoyang
 * creat_date-time: 2018/3/24/18:36
 **/
public class CarBidRecord implements Serializable{
    private static final long serialVersionUID = -731092582867215610L;
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
     * 竞拍类型
     */
    private String auctionType;
    /**
     * 起拍价
     */
    private BigDecimal startingPrice;
    /**
     * 出价时间
     */
    private Date bidTime;
    /**
     * 出价金额
     */
    private BigDecimal bidFee;
    /**
     * 竞拍状态
     */
    private String auctionStatus;
    public CarBidRecord() {
    }

    public CarBidRecord(Long id, String userCode, String userNum, String username, String mobile, String userStoreName, String carAutoNo, String autoInfoName, String carStoreName, String auctionType, BigDecimal startingPrice, Date bidTime, BigDecimal bidFee) {
        this.id = id;
        this.userCode = userCode;
        this.userNum = userNum;
        this.username = username;
        this.mobile = mobile;
        this.userStoreName = userStoreName;
        this.carAutoNo = carAutoNo;
        this.autoInfoName = autoInfoName;
        this.carStoreName = carStoreName;
        this.auctionType = auctionType;
        this.startingPrice = startingPrice;
        this.bidTime = bidTime;
        this.bidFee = bidFee;
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

    public String getAuctionType() {
        return auctionType;
    }

    public void setAuctionType(String auctionType) {
        this.auctionType = auctionType;
    }

    public BigDecimal getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(BigDecimal startingPrice) {
        this.startingPrice = startingPrice;
    }

    public Date getBidTime() {
        return bidTime;
    }

    public void setBidTime(Date bidTime) {
        this.bidTime = bidTime;
    }

    public BigDecimal getBidFee() {
        return bidFee;
    }

    public void setBidFee(BigDecimal bidFee) {
        this.bidFee = bidFee;
    }

    public String getAuctionStatus() {
        return auctionStatus;
    }

    public void setAuctionStatus(String auctionStatus) {
        this.auctionStatus = auctionStatus;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarBidRecord)) return false;

        CarBidRecord that = (CarBidRecord) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (userCode != null ? !userCode.equals(that.userCode) : that.userCode != null) return false;
        if (userNum != null ? !userNum.equals(that.userNum) : that.userNum != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) return false;
        if (userStoreName != null ? !userStoreName.equals(that.userStoreName) : that.userStoreName != null)
            return false;
        if (carAutoNo != null ? !carAutoNo.equals(that.carAutoNo) : that.carAutoNo != null) return false;
        if (autoInfoName != null ? !autoInfoName.equals(that.autoInfoName) : that.autoInfoName != null) return false;
        if (carStoreName != null ? !carStoreName.equals(that.carStoreName) : that.carStoreName != null) return false;
        if (auctionType != null ? !auctionType.equals(that.auctionType) : that.auctionType != null) return false;
        if (startingPrice != null ? !startingPrice.equals(that.startingPrice) : that.startingPrice != null)
            return false;
        if (bidTime != null ? !bidTime.equals(that.bidTime) : that.bidTime != null) return false;
        return bidFee != null ? bidFee.equals(that.bidFee) : that.bidFee == null;
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
        result = 31 * result + (auctionType != null ? auctionType.hashCode() : 0);
        result = 31 * result + (startingPrice != null ? startingPrice.hashCode() : 0);
        result = 31 * result + (bidTime != null ? bidTime.hashCode() : 0);
        result = 31 * result + (bidFee != null ? bidFee.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CarBidRecord{" +
                "id=" + id +
                ", userCode='" + userCode + '\'' +
                ", userNum='" + userNum + '\'' +
                ", username='" + username + '\'' +
                ", mobile='" + mobile + '\'' +
                ", userStoreName='" + userStoreName + '\'' +
                ", carAutoNo='" + carAutoNo + '\'' +
                ", autoInfoName='" + autoInfoName + '\'' +
                ", carStoreName='" + carStoreName + '\'' +
                ", auctionType='" + auctionType + '\'' +
                ", startingPrice=" + startingPrice +
                ", bidTime=" + bidTime +
                ", bidFee=" + bidFee +
                '}';
    }
}
