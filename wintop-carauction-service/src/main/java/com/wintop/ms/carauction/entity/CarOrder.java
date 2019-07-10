package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CarOrder implements Serializable {

    private static final long serialVersionUID = 330840271791603084L;
    /**
     * 订单id
     */
    private Long id;

    /**
     * 拍卖活动id
     */
    private Long auctionId;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 车辆id
     */
    private Long carId;

    /**
     * 客户id
     */
    private Long customerId;

    /**
     * 成交价
     */
    private BigDecimal transactionFee;

    //***争议车款
    private BigDecimal bargainFee;

    /**
     * 服务费
     */
    private BigDecimal serviceFee;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 支付金额
     */
    private BigDecimal payFee;

    /**
     * 支付审核时间
     */
    private Date payAuthTime;

    /**
     * 支付审核人
     */
    private String payAuthManager;

    /**
     * 状态：1待支付、2议价中、3支付待审核，4支付审核失败、5支付完成、6退款、7违约取消
     */
    private String status;

    /**
     * 保留价
     */
    private BigDecimal lockFee;

    /**
     * 订单创建时间
     */
    private Date createTime;

    /**
     * 发布时间
     */
    private Date publishTime;
    private String publishUserName;
    /**
     * 支付截至时间
     */
    private Date payEndTime;

    //***关联car_auto_auction表id
    private Long autoAuctionId;

    //***车辆信息
    private String autoInfoName;
    private String mainPhoto;
    private String carAutoNo;
    private String reportColligationRanks;
    private String reportServicingRanks;
    private String vehicleAttributionCity;
    private String vehicleAttributionCityCn;
    private String vehicleAttributionProvinceCn;
    private String vehicleAttributionProvince;
    private BigDecimal mileage;
    private  Date autoUpdateTime;//车辆修改时间
    private String autoAuthMsg;//车辆审核留言
    //订单数量
    private Integer orderNum;

    private String moveToWhere;
    private Integer unPay;
    private Integer dealing;
    private Integer finish;
    private Integer unEvaluated;
    /**
     * 违约类型，0未违约，1买家违约，2卖家违约
     */
    private String breachType;

    private String ifAgent;
    private String auctionType;
    private String initiatAuthMsg;
    private String isBreach;
    private BigDecimal agentFee;

    private BigDecimal reservePrice;
    private BigDecimal startingPrice;
    private Date authTime;
    private String authMsg;
    private Date startTime;
    private Date auctionStartTime;
    private Date auctionEndTime;
    private String sourceType;
    private String remark;
    private String licenseNumber;
    private Long views;

    private String userName;
    private String mobile;

    private BigDecimal amountFee;

    private Long auctionBidRecordId;

    private String payWay;
    //***线下支付凭证
    private String payEvidence;
    //***支付流水
    private String logNo;
    private String identityNumber;
    private String storeName;
    private String simpleName;
    private String address;
    private String vin;
    private String moveAddress;
    private String statusName;
    //****等级日期
    private String registDate;
    private String cityName;
    //***上拍次数
    private int auctionNum;
    //***违约金
    private BigDecimal breakFee;
    //**争议状态
    private String breachStatus;
    private String agentCompanyName;
    private String handleUserName;

    private String ifNew;
    private Date manufactureDate;
    private String environmentCn;
    private Long publishUserId;
    private Long createUser;
    private BigDecimal topBidPrice;
    private Long breachId;
    private String serviceTel;
    //**8拍牌号
    private String auctionPlateNum;

    private String customerName;

    //现场拍场次内号码
    private String auctionCode;
    //现场拍场次名称
    private String auctionName;
    //成交人车上号
    private String userNum;
    //车辆初次上牌日期
    private Date beginRegisterDate;

    /*卖家有无评价1：已评价 0：未评价*/
    private String managerEvaluate;
    /*买家有无评价1：已评价 0：未评价*/
    private String userEvaluate;


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getServiceTel() {
        return serviceTel;
    }

    public void setServiceTel(String serviceTel) {
        this.serviceTel = serviceTel;
    }

    public String getVehicleAttributionProvinceCn() {
        return vehicleAttributionProvinceCn;
    }

    public void setVehicleAttributionProvinceCn(String vehicleAttributionProvinceCn) {
        this.vehicleAttributionProvinceCn = vehicleAttributionProvinceCn;
    }

    public String getVehicleAttributionProvince() {
        return vehicleAttributionProvince;
    }

    public void setVehicleAttributionProvince(String vehicleAttributionProvince) {
        this.vehicleAttributionProvince = vehicleAttributionProvince;
    }

    public BigDecimal getTopBidPrice() {
        return topBidPrice;
    }

    public void setTopBidPrice(BigDecimal topBidPrice) {
        this.topBidPrice = topBidPrice;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Long getPublishUserId() {
        return publishUserId;
    }

    public void setPublishUserId(Long publishUserId) {
        this.publishUserId = publishUserId;
    }

    public String getIfNew() {
        return ifNew;
    }

    public void setIfNew(String ifNew) {
        this.ifNew = ifNew;
    }

    public Date getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(Date manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public String getEnvironmentCn() {
        return environmentCn;
    }

    public void setEnvironmentCn(String environmentCn) {
        this.environmentCn = environmentCn;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getIfAgent() {
        return ifAgent;
    }

    public void setIfAgent(String ifAgent) {
        this.ifAgent = ifAgent;
    }

    public String getAuctionType() {
        return auctionType;
    }

    public void setAuctionType(String auctionType) {
        this.auctionType = auctionType;
    }

    public String getInitiatAuthMsg() {
        return initiatAuthMsg;
    }

    public void setInitiatAuthMsg(String initiatAuthMsg) {
        this.initiatAuthMsg = initiatAuthMsg;
    }

    public String getIsBreach() {
        return isBreach;
    }

    public void setIsBreach(String isBreach) {
        this.isBreach = isBreach;
    }

    public String getMoveToWhere() {
        return moveToWhere;
    }

    public void setMoveToWhere(String moveToWhere) {
        this.moveToWhere = moveToWhere;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public BigDecimal getAgentFee() {
        return agentFee;
    }

    public void setAgentFee(BigDecimal agentFee) {
        this.agentFee = agentFee;
    }

    public Integer getUnEvaluated() {
        return unEvaluated;
    }

    public void setUnEvaluated(Integer unEvaluated) {
        this.unEvaluated = unEvaluated;
    }

    /**
     * @return 订单id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id 
	 *            订单id
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
     * @return 订单编号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * @param orderNo 
	 *            订单编号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * @return 车辆id
     */
    public Long getCarId() {
        return carId;
    }

    /**
     * @param carId 
	 *            车辆id
     */
    public void setCarId(Long carId) {
        this.carId = carId;
    }

    /**
     * @return 客户id
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId 
	 *            客户id
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * @return 成交价
     */
    public BigDecimal getTransactionFee() {
        return transactionFee;
    }

    /**
     * @param transactionFee 
	 *            成交价
     */
    public void setTransactionFee(BigDecimal transactionFee) {
        this.transactionFee = transactionFee;
    }

    public BigDecimal getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
    }

    /**
     * @return 支付时间
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     * @param payTime 
	 *            支付时间
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * @return 支付金额
     */
    public BigDecimal getPayFee() {
        return payFee;
    }

    /**
     * @param payFee 
	 *            支付金额
     */
    public void setPayFee(BigDecimal payFee) {
        this.payFee = payFee;
    }

    /**
     * @return 支付审核时间
     */
    public Date getPayAuthTime() {
        return payAuthTime;
    }

    /**
     * @param payAuthTime 
	 *            支付审核时间
     */
    public void setPayAuthTime(Date payAuthTime) {
        this.payAuthTime = payAuthTime;
    }

    /**
     * @return 支付审核人
     */
    public String getPayAuthManager() {
        return payAuthManager;
    }

    /**
     * @param payAuthManager 
	 *            支付审核人
     */
    public void setPayAuthManager(String payAuthManager) {
        this.payAuthManager = payAuthManager;
    }

    /**
     * @return 状态：1待支付、2议价中、3支付待审核，4支付审核失败、5支付完成、6退款、7违约取消
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            状态：1待支付、2议价中、3支付待审核，4支付审核失败、5支付完成、6退款、7违约取消
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return 保留价
     */
    public BigDecimal getLockFee() {
        return lockFee;
    }

    /**
     * @param lockFee 
	 *            保留价
     */
    public void setLockFee(BigDecimal lockFee) {
        this.lockFee = lockFee;
    }

    /**
     * @return 订单创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime 
	 *            订单创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return 支付截至时间
     */
    public Date getPayEndTime() {
        return payEndTime;
    }

    /**
     * @param payEndTime 
	 *            支付截至时间
     */
    public void setPayEndTime(Date payEndTime) {
        this.payEndTime = payEndTime;
    }

    public String getAutoInfoName() {
        return autoInfoName;
    }

    public void setAutoInfoName(String autoInfoName) {
        this.autoInfoName = autoInfoName;
    }

    public String getMainPhoto() {
        return mainPhoto;
    }

    public void setMainPhoto(String mainPhoto) {
        this.mainPhoto = mainPhoto;
    }

    public String getCarAutoNo() {
        return carAutoNo;
    }

    public void setCarAutoNo(String carAutoNo) {
        this.carAutoNo = carAutoNo;
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

    public String getBreachType() {
        return breachType;
    }

    public void setBreachType(String breachType) {
        this.breachType = breachType;
    }

    public Integer getUnPay() {
        return unPay;
    }

    public void setUnPay(Integer unPay) {
        this.unPay = unPay;
    }

    public Integer getDealing() {
        return dealing;
    }

    public void setDealing(Integer dealing) {
        this.dealing = dealing;
    }

    public Integer getFinish() {
        return finish;
    }

    public void setFinish(Integer finish) {
        this.finish = finish;
    }

    public BigDecimal getReservePrice() {
        return reservePrice;
    }

    public void setReservePrice(BigDecimal reservePrice) {
        this.reservePrice = reservePrice;
    }

    public BigDecimal getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(BigDecimal startingPrice) {
        this.startingPrice = startingPrice;
    }

    public Date getAuthTime() {
        return authTime;
    }

    public void setAuthTime(Date authTime) {
        this.authTime = authTime;
    }

    public String getAuthMsg() {
        return authMsg;
    }

    public void setAuthMsg(String authMsg) {
        this.authMsg = authMsg;
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

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public BigDecimal getAmountFee() {
        return amountFee;
    }

    public void setAmountFee(BigDecimal amountFee) {
        this.amountFee = amountFee;
    }

    public Long getAuctionBidRecordId() {
        return auctionBidRecordId;
    }

    public void setAuctionBidRecordId(Long auctionBidRecordId) {
        this.auctionBidRecordId = auctionBidRecordId;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public String getPayEvidence() {
        return payEvidence;
    }

    public void setPayEvidence(String payEvidence) {
        this.payEvidence = payEvidence;
    }

    public String getLogNo() {
        return logNo;
    }

    public void setLogNo(String logNo) {
        this.logNo = logNo;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getMoveAddress() {
        return moveAddress;
    }

    public void setMoveAddress(String moveAddress) {
        this.moveAddress = moveAddress;
    }

    public String getRegistDate() {
        return registDate;
    }

    public void setRegistDate(String registDate) {
        this.registDate = registDate;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getAuctionNum() {
        return auctionNum;
    }

    public void setAuctionNum(int auctionNum) {
        this.auctionNum = auctionNum;
    }

    public BigDecimal getBargainFee() {
        return bargainFee;
    }

    public void setBargainFee(BigDecimal bargainFee) {
        this.bargainFee = bargainFee;
    }

    public BigDecimal getBreakFee() {
        return breakFee;
    }

    public void setBreakFee(BigDecimal breakFee) {
        this.breakFee = breakFee;
    }

    public String getBreachStatus() {
        return breachStatus;
    }

    public void setBreachStatus(String breachStatus) {
        this.breachStatus = breachStatus;
    }

    public Long getAutoAuctionId() {
        return autoAuctionId;
    }

    public void setAutoAuctionId(Long autoAuctionId) {
        this.autoAuctionId = autoAuctionId;
    }

    public String getAgentCompanyName() {
        return agentCompanyName;
    }

    public void setAgentCompanyName(String agentCompanyName) {
        this.agentCompanyName = agentCompanyName;
    }

    public String getHandleUserName() {
        return handleUserName;
    }

    public void setHandleUserName(String handleUserName) {
        this.handleUserName = handleUserName;
    }

    public CarOrder(Long id, String status) {
        this.id = id;
        this.status = status;
    }

    public String getVehicleAttributionCityCn() {
        return vehicleAttributionCityCn;
    }

    public void setVehicleAttributionCityCn(String vehicleAttributionCityCn) {
        this.vehicleAttributionCityCn = vehicleAttributionCityCn;
    }

    public CarOrder() {
    }

    public Date getAutoUpdateTime() {
        return autoUpdateTime;
    }

    public void setAutoUpdateTime(Date autoUpdateTime) {
        this.autoUpdateTime = autoUpdateTime;
    }

    public Long getBreachId() {
        return breachId;
    }

    public void setBreachId(Long breachId) {
        this.breachId = breachId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getPublishUserName() {
        return publishUserName;
    }

    public void setPublishUserName(String publishUserName) {
        this.publishUserName = publishUserName;
    }

    public String getAutoAuthMsg() {
        return autoAuthMsg;
    }

    public void setAutoAuthMsg(String autoAuthMsg) {
        this.autoAuthMsg = autoAuthMsg;
    }

    public String getAuctionPlateNum() {
        return auctionPlateNum;
    }

    public void setAuctionPlateNum(String auctionPlateNum) {
        this.auctionPlateNum = auctionPlateNum;
    }

    public String getAuctionCode() {
        return auctionCode;
    }

    public void setAuctionCode(String auctionCode) {
        this.auctionCode = auctionCode;
    }

    public String getAuctionName() {
        return auctionName;
    }

    public void setAuctionName(String auctionName) {
        this.auctionName = auctionName;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public Date getBeginRegisterDate() {
        return beginRegisterDate;
    }

    public void setBeginRegisterDate(Date beginRegisterDate) {
        this.beginRegisterDate = beginRegisterDate;
    }

    public String getManagerEvaluate() {
        return managerEvaluate;
    }

    public void setManagerEvaluate(String managerEvaluate) {
        this.managerEvaluate = managerEvaluate;
    }

    public String getUserEvaluate() {
        return userEvaluate;
    }

    public void setUserEvaluate(String userEvaluate) {
        this.userEvaluate = userEvaluate;
    }

    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}