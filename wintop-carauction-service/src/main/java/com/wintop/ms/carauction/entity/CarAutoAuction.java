package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CarAutoAuction implements Serializable {

    private static final long serialVersionUID = 1520680723029576088L;
    /**
     * 拍卖信息id
     */
    private Long id;
    /**
     * 拍卖车辆ID
     */
    private Long autoId;
    /**
     * 车主姓名
     */
    private String ownerName;
    /**
     * 车主电话
     */
    private String ownerMobile;
    /**
     * 看车联系人姓名
     */
    private String linkManName;
    /**
     * 看车联系人电话
     */
    private String linkManMobile;
    /**
     * 开拍时间
     */
    private Date auctionStartTime;
    /**
     * 期望反馈时间
     */
    private Date expectedFeedbackTime;
    /**
     * 竞拍结束时间(线上)
     */
    private Date auctionEndTime;
    /**
     * 是否需要代办：1需要，2不需要
     */
    private String ifAgent;
    /**
     * 起拍价
     */
    private BigDecimal startingPrice;
    /**
     * 保留价
     */
    private BigDecimal reservePrice;
    /**
     * 服务费
     */
    private BigDecimal servicePrice;
    /**
     * 代办费用
     */
    private BigDecimal agentPrice;
    /**
     * 车主意向价
     */
    private BigDecimal ownerPrice;
    /**
     * 拍卖类型（1线上、2线下)
     */
    private String auctionType;
    /**
     * 开放范围，空则全部开放
     */
    private String openLimitCn;
    /**
     * 开放范围id拼接
     */
    private String openLimit;
    /**
     * 车辆当前所在地
     */
    private String carLocation;
    /**
     * 备注
     */
    private String remark;
    /**
     * 最高出价人
     * */
    private Long topPricerId;
    /**
     *最高出价金额
     * */
    private BigDecimal topBidPrice;
    /**
     * 最高出价时间
     * */
    private Date topBidTime;
    /**
     * 总出价次数
     * */
    private Integer bidsCount;
    /**
     * 总竞价人数
     * */
    private Integer bidersCount;
    /**
     * 状态
     * */
    private String status;
    /**
     * 创建人
     * */
    private Long createPerson;
    /**
     * 创建时间
     * */
    private Date createTime;
    /**
     * 修改人
     * */
    private Long modifyPerson;
    /**
     * 修改时间
     * */
    private Date modifyTime;
    /**
     * 删除人
     * */
    private Long delPerson;
    /**
     * 删除时间
     * */
    private Date delTime;
    /**
     * 删除状态
     * */
    private String delFlag;
    /**
     * 店铺id
     * */
    private Long storeId;
    /**
     * 店铺简称（4S店）
     * */
    private String storeName;
    /**
     * 车辆状态
     */
    private String carStatus;
    /**
     * 车辆来源
     * */
    private String sourceType;
    /**
     * 我的出价
     * */
    private BigDecimal myBidPrice;
    /**
    * 是否过保留价（0未过，1是已过，2未过我最高。3已过我最高）
    */
    private String outReserve;

    /**
     * 前往何处：1本市外迁均可，2只能外迁，3只能本市
     */
    private String moveToWhere;

    private Long regionId;

    /**
     * 线上转线下状态: 0:未转渠道 1:已转渠道
     */
    private String transferFlag;

    /**
     * @return 前往何处：1本市外迁均可，2只能外迁，3只能本市
     */
    public String getMoveToWhere() {
        return moveToWhere;
    }

    /**
     * @param moveToWhere
     *            前往何处：1本市外迁均可，2只能外迁，3只能本市
     */
    private Date auctionEndDefaultTime;

    public Date getAuctionEndDefaultTime() {
        return auctionEndDefaultTime;
    }

    public void setAuctionEndDefaultTime(Date auctionEndDefaultTime) {
        this.auctionEndDefaultTime = auctionEndDefaultTime;
    }

    public void setMoveToWhere(String moveToWhere) {
        this.moveToWhere = moveToWhere;
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

    public String getLinkManName() {
        return linkManName;
    }

    public void setLinkManName(String linkManName) {
        this.linkManName = linkManName;
    }

    public String getLinkManMobile() {
        return linkManMobile;
    }

    public void setLinkManMobile(String linkManMobile) {
        this.linkManMobile = linkManMobile;
    }

    public Date getAuctionStartTime() {
        return auctionStartTime;
    }

    public void setAuctionStartTime(Date auctionStartTime) {
        this.auctionStartTime = auctionStartTime;
    }

    public Date getExpectedFeedbackTime() {
        return expectedFeedbackTime;
    }

    public void setExpectedFeedbackTime(Date expectedFeedbackTime) {
        this.expectedFeedbackTime = expectedFeedbackTime;
    }

    public Date getAuctionEndTime() {
        return auctionEndTime;
    }

    public void setAuctionEndTime(Date auctionEndTime) {
        this.auctionEndTime = auctionEndTime;
    }

    public String getIfAgent() {
        return ifAgent;
    }

    public void setIfAgent(String ifAgent) {
        this.ifAgent = ifAgent;
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

    public BigDecimal getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(BigDecimal servicePrice) {
        this.servicePrice = servicePrice;
    }

    public BigDecimal getAgentPrice() {
        return agentPrice;
    }

    public void setAgentPrice(BigDecimal agentPrice) {
        this.agentPrice = agentPrice;
    }

    public BigDecimal getOwnerPrice() {
        return ownerPrice;
    }

    public void setOwnerPrice(BigDecimal ownerPrice) {
        this.ownerPrice = ownerPrice;
    }

    public String getAuctionType() {
        return auctionType;
    }

    public void setAuctionType(String auctionType) {
        this.auctionType = auctionType;
    }

    public String getOpenLimitCn() {
        return openLimitCn;
    }

    public void setOpenLimitCn(String openLimitCn) {
        this.openLimitCn = openLimitCn;
    }

    public String getOpenLimit() {
        return openLimit;
    }

    public void setOpenLimit(String openLimit) {
        this.openLimit = openLimit;
    }

    public String getCarLocation() {
        return carLocation;
    }

    public void setCarLocation(String carLocation) {
        this.carLocation = carLocation;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getTopPricerId() {
        return topPricerId;
    }

    public void setTopPricerId(Long topPricerId) {
        this.topPricerId = topPricerId;
    }

    public BigDecimal getTopBidPrice() {
        return topBidPrice;
    }

    public void setTopBidPrice(BigDecimal topBidPrice) {
        this.topBidPrice = topBidPrice;
    }

    public Date getTopBidTime() {
        return topBidTime;
    }

    public void setTopBidTime(Date topBidTime) {
        this.topBidTime = topBidTime;
    }

    public Integer getBidsCount() {
        return bidsCount;
    }

    public void setBidsCount(Integer bidsCount) {
        this.bidsCount = bidsCount;
    }

    public Integer getBidersCount() {
        return bidersCount;
    }

    public void setBidersCount(Integer bidersCount) {
        this.bidersCount = bidersCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Long getModifyPerson() {
        return modifyPerson;
    }

    public void setModifyPerson(Long modifyPerson) {
        this.modifyPerson = modifyPerson;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Long getDelPerson() {
        return delPerson;
    }

    public void setDelPerson(Long delPerson) {
        this.delPerson = delPerson;
    }

    public Date getDelTime() {
        return delTime;
    }

    public void setDelTime(Date delTime) {
        this.delTime = delTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(String carStatus) {
        this.carStatus = carStatus;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public BigDecimal getMyBidPrice() {
        return myBidPrice;
    }

    public void setMyBidPrice(BigDecimal myBidPrice) {
        this.myBidPrice = myBidPrice;
    }

    public String getOutReserve() {
        return outReserve;
    }

    public void setOutReserve(String outReserve) {
        this.outReserve = outReserve;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getTransferFlag() {
        return transferFlag;
    }

    public void setTransferFlag(String transferFlag) {
        this.transferFlag = transferFlag;
    }
}