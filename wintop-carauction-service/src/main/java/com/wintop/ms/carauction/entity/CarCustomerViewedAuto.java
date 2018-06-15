package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 12991 on 2018/3/12.
 */
public class CarCustomerViewedAuto implements Serializable{
    private static final long serialVersionUID = 7711661073312580044L;
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 车辆ID
     */
    private Long autoId;

    /**
     * 浏览人
     */
    private Long userId;

    /**
     * 浏览时间
     */
    private Date createTime;

    private String autoInfoName;
    private String vehicleAttributionCity;
    private BigDecimal startingPrice;
    private String carAutoNo;
    private Long carAutoId;
    private BigDecimal mileage;
    private Date auctionStartTime;
    private Date auctionEndTime;
    private String mainPhoto;
    private String auctionType;
    private String isFollow;
    private String status;
    private String reportColligationRanks;
    private String reportServicingRanks;
    private String engineCapacity;
    private String engineCapacityUnit;
    private String environment;
    private String environmentCn;

    private Date beginRegisterDate;
    private String carStoreName;
    private String vehicleAttributionCityCn;
    private BigDecimal maxBidPrice;

    private Long autoAuctionId;

    public String getVehicleAttributionCityCn() {
        return vehicleAttributionCityCn;
    }

    public void setVehicleAttributionCityCn(String vehicleAttributionCityCn) {
        this.vehicleAttributionCityCn = vehicleAttributionCityCn;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAutoId() {
        return autoId;
    }

    public void setAutoId(Long autoId) {
        this.autoId = autoId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public BigDecimal getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(BigDecimal startingPrice) {
        this.startingPrice = startingPrice;
    }

    public String getCarAutoNo() {
        return carAutoNo;
    }

    public void setCarAutoNo(String carAutoNo) {
        this.carAutoNo = carAutoNo;
    }

    public Long getCarAutoId() {
        return carAutoId;
    }

    public void setCarAutoId(Long carAutoId) {
        this.carAutoId = carAutoId;
    }

    public BigDecimal getMileage() {
        return mileage;
    }

    public void setMileage(BigDecimal mileage) {
        this.mileage = mileage;
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

    public String getMainPhoto() {
        return mainPhoto;
    }

    public void setMainPhoto(String mainPhoto) {
        this.mainPhoto = mainPhoto;
    }

    public String getAuctionType() {
        return auctionType;
    }

    public void setAuctionType(String auctionType) {
        this.auctionType = auctionType;
    }

    public String getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(String isFollow) {
        this.isFollow = isFollow;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReportColligationRanks() {
        return reportColligationRanks;
    }

    public void setReportColligationRanks(String reportColligationRanks) {
        this.reportColligationRanks = reportColligationRanks;
    }

    public String getReportServicingRanks() {
        return reportServicingRanks;
    }

    public void setReportServicingRanks(String reportServicingRanks) {
        this.reportServicingRanks = reportServicingRanks;
    }

    public String getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(String engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public String getEngineCapacityUnit() {
        return engineCapacityUnit;
    }

    public void setEngineCapacityUnit(String engineCapacityUnit) {
        this.engineCapacityUnit = engineCapacityUnit;
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

    public String getEnvironmentCn() {
        return environmentCn;
    }

    public void setEnvironmentCn(String environmentCn) {
        this.environmentCn = environmentCn;
    }

    public Long getAutoAuctionId() {
        return autoAuctionId;
    }

    public void setAutoAuctionId(Long autoAuctionId) {
        this.autoAuctionId = autoAuctionId;
    }
}
