package com.wintop.ms.carauction.entity;

import java.util.Date;

public class CarManagerRoleLog {
    /**
     * 主键
     */
    private Long id;

    /**
     * 申请人id
     */
    private Long applyId;

    /**
     * 申请时间
     */
    private Date applyTime;

    /**
     * 审核状态 1提交申请，2审核通过，-1审核不通过 3审核撤销
     */
    private String status;

    /**
     * 审核状态描述
     */
    private String statusCn;

    /**
     * 审批意见
     */
    private String logMsg;

    /**
     * 操作时间
     */
    private Date createTime;

    /**
     * 操作用户ID
     */
    private Long createPerson;

    /**
     * 店铺id
     */
    private Long storeId;

    /**
     * vin 码
     */
    private String vin;

    /**
     * 1:老订单查询 2：新的查询
     */
    private String searchType;

    /**
     * 查询版本 1维修版 2综合版
     */
        private String edition;

    /**
     * 车型名称
     */
    private String vehicleType;

    /**
     * 车型图片
     */
    private String photo;

    /**
     * 申请人名称
     */
    private String applyName;

    /**
     * 审批人名称
     */
    private String auditName;

    /**
     * 店铺名称
     */
    private String storeName;

    /**
     * 店铺简称
     */
    private String storeSimpleName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getStatusCn() {
        return statusCn;
    }

    public void setStatusCn(String statusCn) {
        this.statusCn = statusCn == null ? null : statusCn.trim();
    }

    public String getLogMsg() {
        return logMsg;
    }

    public void setLogMsg(String logMsg) {
        this.logMsg = logMsg == null ? null : logMsg.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(Long createPerson) {
        this.createPerson = createPerson;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public String getAuditName() {
        return auditName;
    }

    public void setAuditName(String auditName) {
        this.auditName = auditName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreSimpleName() {
        return storeSimpleName;
    }

    public void setStoreSimpleName(String storeSimpleName) {
        this.storeSimpleName = storeSimpleName;
    }

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }
}