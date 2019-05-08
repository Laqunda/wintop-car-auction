package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class CarAuto implements Serializable {

    private static final long serialVersionUID = -3639355662812328381L;
    /**
     * 车辆id
     */
    private Long id;

    /**
     * 发布者ID
     */
    private Long publishUserId;

    /**
     * 发布人姓名
     */
    private String publishUserName;

    /**
     * 发布人手机号
     */
    private String publishUserMobile;

    /**
     * 车辆名称=品牌+车系+车型
     */
    private String autoInfoName;

    /**
     * 店铺4S店id
     */
    private Long storeId;
    /**
     * 店铺名称
     */
    private String storeName;

    /**
     * 车辆来源：1个人车源，2公务车，3 4S店置换，4店铺车，5试乘试驾车
     */
    private String sourceType;

    /**
     * 浏览次数
     */
    private Long views;

    /**
     * 发布时间
     */
    private Date publishTime;

    /**
     * 报告评分
     */
    private Long reportScore;

    /**
     * 质检报告综合等级：A,B,C..
     */
    private String reportColligationRanks;

    /**
     * 质检报告整备等级：++，+，-，--
     */
    private String reportServicingRanks;

    /**
     * 检测报告时间
     */
    private Date reportTime;

    /**
     * 状态：0草稿、1待审核，2审核通过，3审核不通过，4上拍，5流拍、6成交 、8待议价 、7待付款 、9已付款、10违约、11正在过户、12交易完成，13交易关闭，14争议中
     */
    private String status;

    private String carAutoNo;

    /**
     * 车辆首图
     */
    private String mainPhoto;

    /**
     * 是否新车：1二手车，2新车
     */
    private String ifNew;

    /**
     * 创建人
     */
    private Long createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private Long updateUser;

    /**
     * 修改时间
     */
    private Date updateTime;

    //**上拍次数
    private Integer auctionNum;

    //***区域ID
    private Long regionId;

    /**
     * VO字段
     */
    //纬度
    private String storeLatitude;
    //经度
    private String storeLongitude;
    private String auctionType;
    private String vehicleAttributionCity;
    private String vehicleAttributionCityCn;
    private Date beginRegisterDate;
    private BigDecimal mileage;
    private BigDecimal startingPrice;
    private BigDecimal servicePrice;
    private Date auctionStartTime;
    private Date auctionEndTime;
    private String carStoreName;
    private String linkManName;
    private String linkManMobile;
    private String remark;
    private String moveToWhere;
    private BigDecimal myBidPrice;
    private String auctionAddress;
    private List<CarAutoPhoto> carAutoPhotos;
    private String address;
    private Date startTime;
    private String engineCapacity;
    private String engineCapacityUnit;
    private String isFollow;
    private String environment;
    private String isEntrust;
    private String tipsMessage;
    private String sellerBear;
    private String buyerBear;
    private String costPrice;
    private BigDecimal illegalPrice;
    private int illegalScore;
    private String illegalWho;

    /**
     * PC列表字段
     */
    private String useNature;
    private String carNature;
    private String licenseNumber;
    private String manufactureDate;
    private String vehicleDriver;
    private String engine_capacity_unit;
    private String engine_capacity;
    private String vin;
    private String title;
    private String carStoreNme;
    private String ifAgent;
    private BigDecimal serviceFee;
    private BigDecimal agentFee;
    private BigDecimal maxBidFee;
    private String statusName;
    private String orderStatus;
    private String createUserName;
    private String ownerPrice;

    private String logMsg;
    private String ownerName;
    private String ownerMobile;
    private String bidTime;
    private String mobile;
    private String name;
    private Date expectedFeedbackTime;
    private String logUserMobile;
    private String logUserName;
    private Long autoAuctionId;

    private String auctionCode;
    private String maxPriceUserId;
    private String transferFlag;

    public String getMaxPriceUserId() {
        return maxPriceUserId;
    }

    public void setMaxPriceUserId(String maxPriceUserId) {
        this.maxPriceUserId = maxPriceUserId;
    }

    public String getAuctionCode() {
        return auctionCode;
    }

    public void setAuctionCode(String auctionCode) {
        this.auctionCode = auctionCode;
    }

    public Long getAutoAuctionId() {
        return autoAuctionId;
    }

    public void setAutoAuctionId(Long autoAuctionId) {
        this.autoAuctionId = autoAuctionId;
    }

    public String getLogUserMobile() {
        return logUserMobile;
    }

    public void setLogUserMobile(String logUserMobile) {
        this.logUserMobile = logUserMobile;
    }

    public String getLogUserName() {
        return logUserName;
    }

    public void setLogUserName(String logUserName) {
        this.logUserName = logUserName;
    }

    public Date getExpectedFeedbackTime() {
        return expectedFeedbackTime;
    }

    public void setExpectedFeedbackTime(Date expectedFeedbackTime) {
        this.expectedFeedbackTime = expectedFeedbackTime;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerMobile() {
        return ownerMobile;
    }

    public void setOwnerMobile(String ownerMobile) {
        this.ownerMobile = ownerMobile;
    }

    public String getBidTime() {
        return bidTime;
    }

    public void setBidTime(String bidTime) {
        this.bidTime = bidTime;
    }

    public String getLogMsg() {
        return logMsg;
    }

    public void setLogMsg(String logMsg) {
        this.logMsg = logMsg;
    }

    public String getOwnerPrice() {
        return ownerPrice;
    }

    public void setOwnerPrice(String ownerPrice) {
        this.ownerPrice = ownerPrice;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
    }

    public BigDecimal getAgentFee() {
        return agentFee;
    }

    public void setAgentFee(BigDecimal agentFee) {
        this.agentFee = agentFee;
    }

    public BigDecimal getMaxBidFee() {
        return maxBidFee;
    }

    public void setMaxBidFee(BigDecimal maxBidFee) {
        this.maxBidFee = maxBidFee;
    }

    public String getStatusName() {
        return statusName;
    }

    public String getIfAgent() {
        return ifAgent;
    }

    public void setIfAgent(String ifAgent) {
        this.ifAgent = ifAgent;
    }

    public String getCarStoreNme() {
        return carStoreNme;
    }

    public void setCarStoreNme(String carStoreNme) {
        this.carStoreNme = carStoreNme;
    }

    public String getUseNature() {
        return useNature;
    }

    public void setUseNature(String useNature) {
        this.useNature = useNature;
    }

    public String getCarNature() {
        return carNature;
    }

    public void setCarNature(String carNature) {
        this.carNature = carNature;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(String manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public String getVehicleDriver() {
        return vehicleDriver;
    }

    public void setVehicleDriver(String vehicleDriver) {
        this.vehicleDriver = vehicleDriver;
    }

    public String getEngine_capacity_unit() {
        return engine_capacity_unit;
    }

    public void setEngine_capacity_unit(String engine_capacity_unit) {
        this.engine_capacity_unit = engine_capacity_unit;
    }

    public String getEngine_capacity() {
        return engine_capacity;
    }

    public void setEngine_capacity(String engine_capacity) {
        this.engine_capacity = engine_capacity;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 保留价
     * */
    private BigDecimal reservePrice;

    private BigDecimal transactionFee;
    public String getIsEntrust() {
        return isEntrust;
    }

    public void setIsEntrust(String isEntrust) {
        this.isEntrust = isEntrust;
    }

    /**
     * @return 车辆id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id 
	 *            车辆id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 发布者ID
     */
    public Long getPublishUserId() {
        return publishUserId;
    }

    /**
     * @param publishUserId
	 *            发布者ID
     */
    public void setPublishUserId(Long publishUserId) {
        this.publishUserId = publishUserId;
    }

    /**
     * @return 发布人姓名
     */
    public String getPublishUserName() {
        return publishUserName;
    }

    /**
     * @param publishUserName
	 *            发布人姓名
     */
    public void setPublishUserName(String publishUserName) {
        this.publishUserName = publishUserName;
    }

    /**
     * @return 发布人手机号
     */
    public String getPublishUserMobile() {
        return publishUserMobile;
    }

    /**
     * @param publishUserMobile
	 *            发布人手机号
     */
    public void setPublishUserMobile(String publishUserMobile) {
        this.publishUserMobile = publishUserMobile;
    }

    /**
     * @return 车辆名称=品牌+车系+车型
     */
    public String getAutoInfoName() {
        return autoInfoName;
    }

    /**
     * @param autoInfoName 
	 *            车辆名称=品牌+车系+车型
     */
    public void setAutoInfoName(String autoInfoName) {
        this.autoInfoName = autoInfoName;
    }

    /**
     * @return 店铺4S店id
     */
    public Long getStoreId() {
        return storeId;
    }

    /**
     * @param storeId 
	 *            店铺4S店id
     */
    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    /**
     * @return 车辆来源：1个人车源，2公务车，3 4S店置换，4店铺车，5试乘试驾车
     */
    public String getSourceType() {
        return sourceType;
    }

    /**
     * @param sourceType 
	 *            车辆来源：1个人车源，2公务车，3 4S店置换，4店铺车，5试乘试驾车
     */
    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    /**
     * @return 浏览次数
     */
    public Long getViews() {
        return views;
    }

    /**
     * @param views 
	 *            浏览次数
     */
    public void setViews(Long views) {
        this.views = views;
    }

    /**
     * @return 发布时间
     */
    public Date getPublishTime() {
        return publishTime;
    }

    /**
     * @param publishTime
	 *            发布时间
     */
    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    /**
     * @return 报告评分
     */
    public Long getReportScore() {
        return reportScore;
    }

    /**
     * @param reportScore 
	 *            报告评分
     */
    public void setReportScore(Long reportScore) {
        this.reportScore = reportScore;
    }

    /**
     * @return 质检报告综合等级：A,B,C..
     */
    public String getReportColligationRanks() {
        return reportColligationRanks;
    }

    /**
     * @param reportColligationRanks 
	 *            质检报告综合等级：A,B,C..
     */
    public void setReportColligationRanks(String reportColligationRanks) {
        this.reportColligationRanks = reportColligationRanks;
    }

    /**
     * @return 质检报告整备等级：++，+，-，--
     */
    public String getReportServicingRanks() {
        return reportServicingRanks;
    }

    /**
     * @param reportServicingRanks 
	 *            质检报告整备等级：++，+，-，--
     */
    public void setReportServicingRanks(String reportServicingRanks) {
        this.reportServicingRanks = reportServicingRanks;
    }

    /**
     * @return 检测报告时间
     */
    public Date getReportTime() {
        return reportTime;
    }

    /**
     * @param reportTime 
	 *            检测报告时间
     */
    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    /**
     * @return 状态：0草稿、1待审核，2审核通过，3审核不通过，4上拍，5流拍、6成交 、8待议价 、7待付款 、9已付款、10违约、11正在过户、12交易完成，13交易关闭，14争议中
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            状态：0草稿、1待审核，2审核通过，3审核不通过，4上拍，5流拍、6成交 、8待议价 、7待付款 、9已付款、10违约、11正在过户、12交易完成，13交易关闭，14争议中
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public String getCarAutoNo() {
        return carAutoNo;
    }

    public void setCarAutoNo(String carAutoNo) {
        this.carAutoNo = carAutoNo;
    }

    /**
     * @return 车辆首图
     */
    public String getMainPhoto() {
        return mainPhoto;
    }

    /**
     * @param mainPhoto 
	 *            车辆首图
     */
    public void setMainPhoto(String mainPhoto) {
        this.mainPhoto = mainPhoto;
    }

    /**
     * @return 是否新车：1二手车，2新车
     */
    public String getIfNew() {
        return ifNew;
    }

    /**
     * @param ifNew 
	 *            是否新车：1二手车，2新车
     */
    public void setIfNew(String ifNew) {
        this.ifNew = ifNew;
    }

    /**
     * @return 创建人
     */
    public Long getCreateUser() {
        return createUser;
    }

    /**
     * @param createUser 
	 *            创建人
     */
    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    /**
     * @return 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime 
	 *            创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return 修改人
     */
    public Long getUpdateUser() {
        return updateUser;
    }

    /**
     * @param updateUser 
	 *            修改人
     */
    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * @return 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /***
     * 过户费：1买家承担，2卖家承担
     */
    public String transferFee;

    /**
     * @param updateTime 
	 *            修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getAuctionType() {
        return auctionType;
    }

    public void setAuctionType(String auctionType) {
        this.auctionType = auctionType;
    }

    public String getVehicleAttributionCity() {
        return vehicleAttributionCity;
    }

    public void setVehicleAttributionCity(String vehicleAttributionCity) {
        this.vehicleAttributionCity = vehicleAttributionCity;
    }

    public Date getBeginRegisterDate() {
        return beginRegisterDate;
    }

    public void setBeginRegisterDate(Date beginRegisterDate) {
        this.beginRegisterDate = beginRegisterDate;
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

    public BigDecimal getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(BigDecimal servicePrice) {
        this.servicePrice = servicePrice;
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

    public String getCarStoreName() {
        return carStoreName;
    }

    public void setCarStoreName(String carStoreName) {
        this.carStoreName = carStoreName;
    }

    public String getLinkManMobile() {
        return linkManMobile;
    }

    public void setLinkManMobile(String linkManMobile) {
        this.linkManMobile = linkManMobile;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getMyBidPrice() {
        return myBidPrice;
    }

    public void setMyBidPrice(BigDecimal myBidPrice) {
        this.myBidPrice = myBidPrice;
    }

    public String getLinkManName() {
        return linkManName;
    }

    public void setLinkManName(String linkManName) {
        this.linkManName = linkManName;
    }

    public String getMoveToWhere() {
        return moveToWhere;
    }

    public void setMoveToWhere(String moveToWhere) {
        this.moveToWhere = moveToWhere;
    }

    public List<CarAutoPhoto> getCarAutoPhotos() {
        return carAutoPhotos;
    }

    public void setCarAutoPhotos(List<CarAutoPhoto> carAutoPhotos) {
        this.carAutoPhotos = carAutoPhotos;
    }

    public Integer getAuctionNum() {
        return auctionNum;
    }

    public void setAuctionNum(Integer auctionNum) {
        this.auctionNum = auctionNum;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getAuctionAddress() {
        return auctionAddress;
    }

    public void setAuctionAddress(String auctionAddress) {
        this.auctionAddress = auctionAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
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

    public String getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(String isFollow) {
        this.isFollow = isFollow;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getTipsMessage() {
        return tipsMessage;
    }

    public void setTipsMessage(String tipsMessage) {
        this.tipsMessage = tipsMessage;
    }

    public String getSellerBear() {
        return sellerBear;
    }

    public void setSellerBear(String sellerBear) {
        this.sellerBear = sellerBear;
    }

    public String getBuyerBear() {
        return buyerBear;
    }

    public void setBuyerBear(String buyerBear) {
        this.buyerBear = buyerBear;
    }

    public String getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(String costPrice) {
        this.costPrice = costPrice;
    }

    public BigDecimal getIllegalPrice() {
        return illegalPrice;
    }

    public void setIllegalPrice(BigDecimal illegalPrice) {
        this.illegalPrice = illegalPrice;
    }

    public int getIllegalScore() {
        return illegalScore;
    }

    public void setIllegalScore(int illegalScore) {
        this.illegalScore = illegalScore;
    }

    public String getIllegalWho() {
        return illegalWho;
    }

    public void setIllegalWho(String illegalWho) {
        this.illegalWho = illegalWho;
    }

    public BigDecimal getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(BigDecimal transactionFee) {
        this.transactionFee = transactionFee;
    }

    public BigDecimal getReservePrice() {
        return reservePrice;
    }

    public void setReservePrice(BigDecimal reservePrice) {
        this.reservePrice = reservePrice;
    }

    public CarAuto(Long id, String status,Date updateTime) {
        this.id = id;
        this.status = status;
        this.updateTime=updateTime;
    }

    public CarAuto() {
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getVehicleAttributionCityCn() {
        return vehicleAttributionCityCn;
    }

    public void setVehicleAttributionCityCn(String vehicleAttributionCityCn) {
        this.vehicleAttributionCityCn = vehicleAttributionCityCn;
    }

    public String getStoreLatitude() {
        return storeLatitude;
    }

    public void setStoreLatitude(String storeLatitude) {
        this.storeLatitude = storeLatitude;
    }

    public String getStoreLongitude() {
        return storeLongitude;
    }

    public void setStoreLongitude(String storeLongitude) {
        this.storeLongitude = storeLongitude;
    }

    public String getTransferFee() {
        return transferFee;
    }

    public void setTransferFee(String transferFee) {
        this.transferFee = transferFee;
    }

    public String getTransferFlag() {
        return transferFlag;
    }

    public void setTransferFlag(String transferFlag) {
        this.transferFlag = transferFlag;
    }
}