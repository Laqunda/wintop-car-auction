package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TblAuctionLog implements Serializable {

    private static final long serialVersionUID = 7596073212443810035L;
    private Long id;

    /**
     * 基站物理ID(十六进制)
     */
    private String stationRealId;

    /**
     * 拍牌物理ID(十六进制)
     */
    private String boardRealId;

    /**
     * 基站口令
     */
    private String token;

    /**
     * 拍卖场次
     */
    private String auctionTimesName;

    /**
     * 拍牌显示名字(冗余字段)
     */
    private String boardName;

    /**
     * 竞拍时间(精确到毫秒)
     */
    private Date auctionTime;

    private Long localeAuctionId;

    private Long auctionCarId;

    private Long carId;

    private Long customerId;

    //加价幅度
    private BigDecimal priceRange;

    private BigDecimal bidFee;

    /**
     * 0：正常拍牌；1：时间切割拍牌，2网页调价器，3幅度调整
     */
    private String priceType;

    /**
     * 0有效，1无效
     */
    private String enable;

    private Long parentId;

    private Long createPerson;

    private Date createTime;

    private Long modifyPerson;

    private Date modifyTime;

    /**
     * 会员名称
     */
    private String name;

    /**
     * 车辆标题
     */
    private String  autoInfoName;
    /**
     * 车辆编号
     */
    private String  carAutoNo;

    /**
     * 车辆编号
     */
    private String  licenseNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAutoInfoName() {
        return autoInfoName;
    }

    public void setAutoInfoName(String autoInfoName) {
        this.autoInfoName = autoInfoName;
    }

    public String getCarAutoNo() {
        return carAutoNo;
    }

    public void setCarAutoNo(String carAutoNo) {
        this.carAutoNo = carAutoNo;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStationRealId() {
        return stationRealId;
    }

    public void setStationRealId(String stationRealId) {
        this.stationRealId = stationRealId;
    }

    /**
     * @return 拍牌物理ID(十六进制)
     */
    public String getBoardRealId() {
        return boardRealId;
    }

    /**
     * @param boardRealId 
	 *            拍牌物理ID(十六进制)
     */
    public void setBoardRealId(String boardRealId) {
        this.boardRealId = boardRealId;
    }

    /**
     * @return 基站口令
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token 
	 *            基站口令
     */
    public void setToken(String token) {
        this.token = token;
    }

    public String getAuctionTimesName() {
        return auctionTimesName;
    }

    public void setAuctionTimesName(String auctionTimesName) {
        this.auctionTimesName = auctionTimesName;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    /**
     * @return 竞拍时间(精确到毫秒)
     */
    public Date getAuctionTime() {
        return auctionTime;
    }

    /**
     * @param auctionTime 
	 *            竞拍时间(精确到毫秒)
     */
    public void setAuctionTime(Date auctionTime) {
        this.auctionTime = auctionTime;
    }

    public Long getLocaleAuctionId() {
        return localeAuctionId;
    }

    public void setLocaleAuctionId(Long localeAuctionId) {
        this.localeAuctionId = localeAuctionId;
    }

    public Long getAuctionCarId() {
        return auctionCarId;
    }

    public void setAuctionCarId(Long auctionCarId) {
        this.auctionCarId = auctionCarId;
    }

    public BigDecimal getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(BigDecimal priceRange) {
        this.priceRange = priceRange;
    }

    public BigDecimal getBidFee() {
        return bidFee;
    }

    public void setBidFee(BigDecimal bidFee) {
        this.bidFee = bidFee;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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
}