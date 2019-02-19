package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ElectronAuctionCar implements Serializable {
    private static final long serialVersionUID = 922014641112643172L;
    private String auctionTimesName;
    private Date startTime;
    private String carAutoNo;
    private String autoInfoName;
    private String licenseNumber;
    private BigDecimal startingPrice;
    private BigDecimal servicePrice;
    //,,0等待开拍，1拍卖中，2已成交，3流拍，4已设置二拍
    private String auctionStatus;
    private String storeName;
    //,,最后价格
    private BigDecimal lastPrice;

    public String getAuctionTimesName() {
        return auctionTimesName;
    }

    public void setAuctionTimesName(String auctionTimesName) {
        this.auctionTimesName = auctionTimesName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
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

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
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

    public String getAuctionStatus() {
        return auctionStatus;
    }

    public void setAuctionStatus(String auctionStatus) {
        this.auctionStatus = auctionStatus;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public BigDecimal getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(BigDecimal lastPrice) {
        this.lastPrice = lastPrice;
    }
}

