package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: 付陈林.
 * @Description: 现场拍--竞拍场次--本场车辆
 * @Date: 18:32 on 2018/3/6.
 * @Modified by:
 */
public class CarLocaleAuctionCar implements Serializable {

    private static final long serialVersionUID = 8071725664181146720L;
    /**
     * 现场拍--竞拍场次--场次车辆--主键
     */
    private Long id;

    /**
     * 现场拍--竞拍场次--场次主键
     */
    private Long auctionId;

    /**
     * 车辆当前的竞拍信息
     * */
    private Long autoAuctionId;

    /**
     * 拍卖的车辆主键
     */
    private Long carId;

    /**
     * 场次车辆编号
     * */
    private String auctionCode;

    /**
     * 拍卖状态：1拍卖中，2已成交，3流拍
     */
    private String auctionStatus;

    /**
     * 车辆在本场次中的排列顺序
     * */
    private Integer sort;

    private Long createPerson;

    private Date createTime;

    /**
     * 车辆编号
     * */
    private String carAutoNo;

    /**
     * 车辆标题
     * */
    private String autoInfoName;

    /**
     * 车牌号
     * */
    private String licenseNumber;

    /**
     * 起拍价
     * */
    private BigDecimal startingPrice;

    /**
     * 保留价
     * */
    private BigDecimal reservePrice;

    /**
     * 初登日期
     * */
    private Date beginRegisterDate;

    /**
     * 车辆来源
     * */
    private String sourceType;

    /**
     * 竞拍次数
     * */
    private Integer auctionNum;

    /**
     * 发车人
     * */
    private String publishUserName;

    /**
     * 表显里程
     * */
    private BigDecimal mileage;

    /**
     *使用性质
     * */
    private String useNature;

    /**
     * 是否代办
     * */
    private String ifAgent;

    /**
     * 代办费
     * */
    private BigDecimal agentPrice;

    /**
     * 服务费
     * */
    private BigDecimal servicePrice;

    /**
     * 场次标题
     * */
    private String title;

    /**
     * 车辆图片
     * */
    private String carAutoPhoto;

    /**
     * 开拍时间
     **/
    private Date startTime;

    /***
     * 店铺名称
     * */
    private String storeName;

    /**
     * 地址
     * */
    private String address;

    /**
     * 最高出价
     * */
    private BigDecimal topBidPrice;

    /**
     * 发布人
     * */
    private String publishUserMobile;

    /**
     * 车商编号
     * */
    private String userNum;

    /**
     * 车商拍牌号
     * */
    private String auctionPlateNum;
    /**
     * 初始加价额度
     */
    private BigDecimal initPriceRange;
    /**
     * 加价额度
     */
    private BigDecimal priceRange;
    /**
     * 现场竞拍--拍卖场次--状态：1待上拍，2等待开拍，3正在竞拍，4竞拍结束
     */
    private String status;

    public BigDecimal getInitPriceRange() {
        return initPriceRange;
    }

    public void setInitPriceRange(BigDecimal initPriceRange) {
        this.initPriceRange = initPriceRange;
    }

    public BigDecimal getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(BigDecimal priceRange) {
        this.priceRange = priceRange;
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
     * @return 拍卖状态：1拍卖中，2已成交，3流拍
     */
    public String getAuctionStatus() {
        return auctionStatus;
    }

    /**
     * @param auctionStatus 
	 *            拍卖状态：1拍卖中，2已成交，3流拍
     */
    public void setAuctionStatus(String auctionStatus) {
        this.auctionStatus = auctionStatus;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

    public BigDecimal getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(BigDecimal startingPrice) {
        this.startingPrice = startingPrice;
    }

    public BigDecimal getReservePrice() {
        return reservePrice;
    }

    public void setReservePrice(BigDecimal reservePrice) {
        this.reservePrice = reservePrice;
    }

    public Date getBeginRegisterDate() {
        return beginRegisterDate;
    }

    public void setBeginRegisterDate(Date beginRegisterDate) {
        this.beginRegisterDate = beginRegisterDate;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getAuctionNum() {
        return auctionNum;
    }

    public void setAuctionNum(Integer auctionNum) {
        this.auctionNum = auctionNum;
    }

    public String getPublishUserName() {
        return publishUserName;
    }

    public void setPublishUserName(String publishUserName) {
        this.publishUserName = publishUserName;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public Long getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(Long createPerson) {
        this.createPerson = createPerson;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getMileage() {
        return mileage;
    }

    public void setMileage(BigDecimal mileage) {
        this.mileage = mileage;
    }

    public String getUseNature() {
        return useNature;
    }

    public void setUseNature(String useNature) {
        this.useNature = useNature;
    }

    public String getIfAgent() {
        return ifAgent;
    }

    public void setIfAgent(String ifAgent) {
        this.ifAgent = ifAgent;
    }

    public BigDecimal getAgentPrice() {
        return agentPrice;
    }

    public void setAgentPrice(BigDecimal agentPrice) {
        this.agentPrice = agentPrice;
    }

    public BigDecimal getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(BigDecimal servicePrice) {
        this.servicePrice = servicePrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getAutoAuctionId() {
        return autoAuctionId;
    }

    public void setAutoAuctionId(Long autoAuctionId) {
        this.autoAuctionId = autoAuctionId;
    }

    public String getCarAutoPhoto() {
        return carAutoPhoto;
    }

    public void setCarAutoPhoto(String carAutoPhoto) {
        this.carAutoPhoto = carAutoPhoto;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getTopBidPrice() {
        return topBidPrice;
    }

    public void setTopBidPrice(BigDecimal topBidPrice) {
        this.topBidPrice = topBidPrice;
    }

    public String getPublishUserMobile() {
        return publishUserMobile;
    }

    public void setPublishUserMobile(String publishUserMobile) {
        this.publishUserMobile = publishUserMobile;
    }

    public String getAuctionCode() {
        return auctionCode;
    }

    public void setAuctionCode(String auctionCode) {
        this.auctionCode = auctionCode;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getAuctionPlateNum() {
        return auctionPlateNum;
    }

    public void setAuctionPlateNum(String auctionPlateNum) {
        this.auctionPlateNum = auctionPlateNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}