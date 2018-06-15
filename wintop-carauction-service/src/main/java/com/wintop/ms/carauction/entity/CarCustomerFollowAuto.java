package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * CarCustomerFollowAuto:用户关注车辆
 * @author zhangzijuan
 * @date 2018-02-27
 */
public class CarCustomerFollowAuto implements Serializable {
    private static final long serialVersionUID = -3077096785757134117L;
    /**
     * 关注ID
     */
    private Long id;

    /**
     * 关注车辆ID
     */
    private Long autoId;

    /**
     * 关注人
     */
    private Long userId;

    /**
     * 关注时间
     */
    private Date createTime;

    /**
     * 状态：1关注，2取消
     */
    private String status;

    /**
     * 车辆图片
     */
    private String mainPhoto;
    /**
     * 车辆标题
     */
    private String autoInfoName;
    /**
     * 城市
     */
    private String vehicleAttributionCity;
    /**
     * 级别
     */
    private String reportColligationRanks;
    /**
     * 报告评分
     */
    private String reportScore;
    /**
     * 里程
     */
    private BigDecimal mileage;
    /**
     * 起拍价
     */
    private BigDecimal startingPrice;
    /**
     * 开拍时间
     */
    private Date auctionStartTime;
    /**
     * 结束时间
     */
    private Date auctionEndTime;
    /**
     * 车辆编号
     */
    private String carAutoNo;
    private String reportServicingRanks;
    private String isEntrust;
    private String carStatus;
    private Long carId;
    private String environment;
    private Date beginRegisterDate;
    private String carStoreName;
    private BigDecimal maxBidPrice;
    private String  auctionType;
    private String vehicleAttributionCityCn;
    private String auctionCode;

    private Long autoAuctionId;

    public String getVehicleAttributionCityCn() {
        return vehicleAttributionCityCn;
    }

    public void setVehicleAttributionCityCn(String vehicleAttributionCityCn) {
        this.vehicleAttributionCityCn = vehicleAttributionCityCn;
    }

    public String getAuctionCode() {
        return auctionCode;
    }

    public void setAuctionCode(String auctionCode) {
        this.auctionCode = auctionCode;
    }

    public String getAuctionType() {
        return auctionType;
    }

    public void setAuctionType(String auctionType) {
        this.auctionType = auctionType;
    }

    public BigDecimal getMaxBidPrice() {
        return maxBidPrice;
    }

    public void setMaxBidPrice(BigDecimal maxBidPrice) {
        this.maxBidPrice = maxBidPrice;
    }

    public String getCarStoreName() {
        return carStoreName;
    }

    public void setCarStoreName(String carStoreName) {
        this.carStoreName = carStoreName;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public Date getBeginRegisterDate() {
        return beginRegisterDate;
    }

    public void setBeginRegisterDate(Date beginRegisterDate) {
        this.beginRegisterDate = beginRegisterDate;
    }

    public String getMainPhoto() {
        return mainPhoto;
    }

    public void setMainPhoto(String mainPhoto) {
        this.mainPhoto = mainPhoto;
    }

    public String getAutoInfoName() {
        return autoInfoName;
    }

    public void setAutoInfoName(String autoInfoName) {
        this.autoInfoName = autoInfoName;
    }

    public String getVehicleAttributionCity() {
        return vehicleAttributionCity;
    }

    public void setVehicleAttributionCity(String vehicleAttributionCity) {
        this.vehicleAttributionCity = vehicleAttributionCity;
    }

    public String getReportColligationRanks() {
        return reportColligationRanks;
    }

    public void setReportColligationRanks(String reportColligationRanks) {
        this.reportColligationRanks = reportColligationRanks;
    }

    public String getReportScore() {
        return reportScore;
    }

    public void setReportScore(String reportScore) {
        this.reportScore = reportScore;
    }

    public BigDecimal getMileage() {
        return mileage;
    }

    public void setMileage(BigDecimal mileage) {
        this.mileage = mileage;
    }

    public BigDecimal getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(BigDecimal startingPrice) {
        this.startingPrice = startingPrice;
    }

    public Date getAuctionStartTime() {
        return auctionStartTime;
    }

    public void setAuctionStartTime(Date auctionStartTime) {
        this.auctionStartTime = auctionStartTime;
    }

    public Date getAuctionEndTime() {
        return auctionEndTime;
    }

    public void setAuctionEndTime(Date auctionEndTime) {
        this.auctionEndTime = auctionEndTime;
    }

    public String getCarAutoNo() {
        return carAutoNo;
    }

    public void setCarAutoNo(String carAutoNo) {
        this.carAutoNo = carAutoNo;
    }

    /**
     * @return 关注ID
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id 
	 *            关注ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 关注车辆ID
     */
    public Long getAutoId() {
        return autoId;
    }

    /**
     * @param autoId 
	 *            关注车辆ID
     */
    public void setAutoId(Long autoId) {
        this.autoId = autoId;
    }

    /**
     * @return 关注人
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId 
	 *            关注人
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return 关注时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime 
	 *            关注时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return 状态：1关注，2取消
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            状态：1关注，2取消
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsEntrust() {
        return isEntrust;
    }

    public void setIsEntrust(String isEntrust) {
        this.isEntrust = isEntrust;
    }

    public String getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(String carStatus) {
        this.carStatus = carStatus;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getReportServicingRanks() {
        return reportServicingRanks;
    }

    public void setReportServicingRanks(String reportServicingRanks) {
        this.reportServicingRanks = reportServicingRanks;
    }

    public Long getAutoAuctionId() {
        return autoAuctionId;
    }

    public void setAutoAuctionId(Long autoAuctionId) {
        this.autoAuctionId = autoAuctionId;
    }
}