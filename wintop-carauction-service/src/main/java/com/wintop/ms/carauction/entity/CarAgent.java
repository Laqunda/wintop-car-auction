package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.Date;

public class CarAgent implements Serializable {

    private static final long serialVersionUID = -4425812366494059153L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 车辆ID
     */
    private Long carId;

    /**
     * 代办公司id
     */
    private Long agentCompanyId;

    /**
     * 代办公司名称
     */
    private String agentCompanyName;

    private Date createTime;

    private Long createUser;

    private String carStoreName;
    private String auctionType;
    private String status;
    private String moveToWhere;
    private String moveAddress;
    private String licenseNumber;
    private String autoInfoName;
    private String carAutoNo;
    private String mobile;
    private String name;
    private Date orderTime;
    private String orderNo;
    private String statusName;

    private Long transferId;

    private String handlerTel;

    private String manualAdd;

    private Long appUserId;

    public String getHandlerTel() {
        return handlerTel;
    }

    public void setHandlerTel(String handlerTel) {
        this.handlerTel = handlerTel;
    }

    public Long getTransferId() {
        return transferId;
    }

    public void setTransferId(Long transferId) {
        this.transferId = transferId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Long getAgentCompanyId() {
        return agentCompanyId;
    }

    public void setAgentCompanyId(Long agentCompanyId) {
        this.agentCompanyId = agentCompanyId;
    }

    public String getAgentCompanyName() {
        return agentCompanyName;
    }

    public void setAgentCompanyName(String agentCompanyName) {
        this.agentCompanyName = agentCompanyName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public String getCarStoreName() {
        return carStoreName;
    }

    public void setCarStoreName(String carStoreName) {
        this.carStoreName = carStoreName;
    }

    public String getAuctionType() {
        return auctionType;
    }

    public void setAuctionType(String auctionType) {
        this.auctionType = auctionType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMoveToWhere() {
        return moveToWhere;
    }

    public void setMoveToWhere(String moveToWhere) {
        this.moveToWhere = moveToWhere;
    }

    public String getMoveAddress() {
        return moveAddress;
    }

    public void setMoveAddress(String moveAddress) {
        this.moveAddress = moveAddress;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
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

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getManualAdd() {
        return manualAdd;
    }

    public void setManualAdd(String manualAdd) {
        this.manualAdd = manualAdd;
    }

    public Long getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(Long appUserId) {
        this.appUserId = appUserId;
    }
}