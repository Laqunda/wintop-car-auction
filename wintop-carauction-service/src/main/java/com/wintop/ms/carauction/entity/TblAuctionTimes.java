package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class TblAuctionTimes implements Serializable {

    private static final long serialVersionUID = -3573385811247971118L;

    /**
     * 拍牌物理ID(十六进制)
     */
    private String boardRealId;

    /**
     * 拍牌显示名字
     */
    private String boardName;

    /**
     * 基站物理ID(十六进制)
     */
    private String stationRealId;

    /**
     * 基站代码
     */
    private String stationName;

    /**
     * 后台拍卖场次ID
     */
    private Long localeAuctionId;

    private Long auctionCarId;

    private Long carId;

    /**
     * 拍卖场次名字
     */
    private String auctionTimesName;

    /**
     * 初始加价幅度
     */
    private BigDecimal initPriceRange;

    private BigDecimal priceRange;

    //起拍价
    private BigDecimal startingPrice;

    private String cuttingSign;

    private Long customerId;

    public String getBoardRealId() {
        return boardRealId;
    }

    public void setBoardRealId(String boardRealId) {
        this.boardRealId = boardRealId;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public String getStationRealId() {
        return stationRealId;
    }

    public void setStationRealId(String stationRealId) {
        this.stationRealId = stationRealId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Long getLocaleAuctionId() {
        return localeAuctionId;
    }

    public void setLocaleAuctionId(Long localeAuctionId) {
        this.localeAuctionId = localeAuctionId;
    }

    public String getAuctionTimesName() {
        return auctionTimesName;
    }

    public void setAuctionTimesName(String auctionTimesName) {
        this.auctionTimesName = auctionTimesName;
    }

    public BigDecimal getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(BigDecimal priceRange) {
        this.priceRange = priceRange;
    }

    public BigDecimal getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(BigDecimal startingPrice) {
        this.startingPrice = startingPrice;
    }

    public Long getAuctionCarId() {
        return auctionCarId;
    }

    public void setAuctionCarId(Long auctionCarId) {
        this.auctionCarId = auctionCarId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getCuttingSign() {
        return cuttingSign;
    }

    public void setCuttingSign(String cuttingSign) {
        this.cuttingSign = cuttingSign;
    }

    public BigDecimal getInitPriceRange() {
        return initPriceRange;
    }

    public void setInitPriceRange(BigDecimal initPriceRange) {
        this.initPriceRange = initPriceRange;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}