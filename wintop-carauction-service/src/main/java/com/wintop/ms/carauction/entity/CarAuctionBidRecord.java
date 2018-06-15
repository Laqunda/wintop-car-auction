package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CarAuctionBidRecord implements Serializable {

    private static final long serialVersionUID = 3798763970648180066L;
    /**
     * 拍卖活动表
     */
    private Long id;

    /**
     * 拍卖活动id
     */
    private Long auctionId;

    /**
     * 场次车辆id
     **/
    private Long auctionCarId;

    /**
     * 拍卖车辆ID
     */
    private Long carId;

    /**
     * 出价客户ID
     */
    private Long customerId;

    /**
     * 出价金额（累计后）
     */
    private BigDecimal bidFee;

    /**
     * 本次加价金额
     */
    private BigDecimal addFee;

    /**
     * 竞价时间
     */
    private Date bidTime;

    private String mainPhoto;
    private String autoInfoName;
    private String vehicleAttributionCity;
    private BigDecimal mileage;
    private String reportServicingRanks;
    private String reportScore;
    private Long autoId;
    private Date auctionStartTime;
    private Date auctionEndTime;
    private String reportColligationRanks;
    private String status;
    private BigDecimal maxBidPrice;
    private String carAutoNo;
    private String carStoreName;
    private BigDecimal startingPrice;
    private BigDecimal entrustPrice;
    private String bidSuccess;
    private String auctionType;
    private String vehicleAttributionCityCn;
    private String auctionCode;

    public String getVehicleAttributionCityCn() {
        return vehicleAttributionCityCn;
    }

    public void setVehicleAttributionCityCn(String vehicleAttributionCityCn) {
        this.vehicleAttributionCityCn = vehicleAttributionCityCn;
    }

    public String getAuctionType() {
        return auctionType;
    }

    public void setAuctionType(String auctionType) {
        this.auctionType = auctionType;
    }

    public BigDecimal getEntrustPrice() {
        return entrustPrice;
    }

    public void setEntrustPrice(BigDecimal entrustPrice) {
        this.entrustPrice = entrustPrice;
    }

    public String getBidSuccess() {
        return bidSuccess;
    }

    public void setBidSuccess(String bidSuccess) {
        this.bidSuccess = bidSuccess;
    }

    public BigDecimal getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(BigDecimal startingPrice) {
        this.startingPrice = startingPrice;
    }

    public String getCarStoreName() {
        return carStoreName;
    }

    public void setCarStoreName(String carStoreName) {
        this.carStoreName = carStoreName;
    }

    public String getCarAutoNo() {
        return carAutoNo;
    }

    public void setCarAutoNo(String carAutoNo) {
        this.carAutoNo = carAutoNo;
    }

    public BigDecimal getMaxBidPrice() {
        return maxBidPrice;
    }

    public void setMaxBidPrice(BigDecimal maxBidPrice) {
        this.maxBidPrice = maxBidPrice;
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

    public BigDecimal getMileage() {
        return mileage;
    }

    public void setMileage(BigDecimal mileage) {
        this.mileage = mileage;
    }

    public String getReportServicingRanks() {
        return reportServicingRanks;
    }

    public void setReportServicingRanks(String reportServicingRanks) {
        this.reportServicingRanks = reportServicingRanks;
    }

    public String getReportScore() {
        return reportScore;
    }

    public void setReportScore(String reportScore) {
        this.reportScore = reportScore;
    }

    public Long getAutoId() {
        return autoId;
    }

    public void setAutoId(Long autoId) {
        this.autoId = autoId;
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

    /**
     * @return 拍卖活动表
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id 
	 *            拍卖活动表
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 拍卖活动id
     */
    public Long getAuctionId() {
        return auctionId;
    }

    /**
     * @param auctionId 
	 *            拍卖活动id
     */
    public void setAuctionId(Long auctionId) {
        this.auctionId = auctionId;
    }

    /**
     * @return 拍卖车辆ID
     */
    public Long getCarId() {
        return carId;
    }

    /**
     * @param carId 
	 *            拍卖车辆ID
     */
    public void setCarId(Long carId) {
        this.carId = carId;
    }

    /**
     * @return 出价客户ID
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId 
	 *            出价客户ID
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * @return 出价金额（累计后）
     */
    public BigDecimal getBidFee() {
        return bidFee;
    }

    /**
     * @param bidFee 
	 *            出价金额（累计后）
     */
    public void setBidFee(BigDecimal bidFee) {
        this.bidFee = bidFee;
    }

    /**
     * @return 本次加价金额
     */
    public BigDecimal getAddFee() {
        return addFee;
    }

    /**
     * @param addFee 
	 *            本次加价金额
     */
    public void setAddFee(BigDecimal addFee) {
        this.addFee = addFee;
    }

    /**
     * @return 竞价时间
     */
    public Date getBidTime() {
        return bidTime;
    }

    /**
     * @param bidTime 
	 *            竞价时间
     */
    public void setBidTime(Date bidTime) {
        this.bidTime = bidTime;
    }

    public String getReportColligationRanks() {
        return reportColligationRanks;
    }

    public void setReportColligationRanks(String reportColligationRanks) {
        this.reportColligationRanks = reportColligationRanks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getAuctionCarId() {
        return auctionCarId;
    }

    public void setAuctionCarId(Long auctionCarId) {
        this.auctionCarId = auctionCarId;
    }

    public String getAuctionCode() {
        return auctionCode;
    }

    public void setAuctionCode(String auctionCode) {
        this.auctionCode = auctionCode;
    }
}