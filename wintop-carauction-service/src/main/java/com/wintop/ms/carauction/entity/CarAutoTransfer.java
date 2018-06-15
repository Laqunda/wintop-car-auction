package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class CarAutoTransfer implements Serializable {
    private static final long serialVersionUID = -1659783220583267329L;
    /**
     * 车辆代办主表id
     */
    private Long id;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 检测车辆ID
     */
    private Long autoId;

    /**
     * 办理人人ID
     */
    private Long handleUserId;

    /**
     * 办理人姓名
     */
    private String handleUserName;

    /**
     * 办理人手机号
     */
    private String handleUserMobile;

    /**
     * 代办移交资料拍照
     */
    private String commissionPhoto;

    /**
     * 移交时间
     */
    private Date createTime;

    /**
     * 移交人
     */
    private Long createUser;

    /**
     * 状态：1交接完成，2确认过户事宜完成，3出票，4出牌，5交档，6提档，7手续上传，8过户票，9登记证，10行驶证，11手续回传，12手续回传确认通过，13手续回传确认不通过
     */
    private String status;

    /**
     * 前往何处：1本市外迁均可，2只能外迁，3只能本市
     */
    private String moveToWhere;

    /**
     * 迁往具体地址
     */
    private String moveAddress;

    /**
     * 移交资料内容
     */
    private String transferDoc;

    /**
     * 审核人
     */
    private Long authManager;

    /**
     * 审核时间
     */
    private Date authTime;

    /**
     * 审核意见
     */
    private String authMsg;

    private String authManagerName;

    private List<CarAutoTransferLog> logList;

    public List<CarAutoTransferLog> getLogList() {
        return logList;
    }

    public void setLogList(List<CarAutoTransferLog> logList) {
        this.logList = logList;
    }

    public String getAuthManagerName() {
        return authManagerName;
    }

    public void setAuthManagerName(String authManagerName) {
        this.authManagerName = authManagerName;
    }

    public Long getAuthManager() {
        return authManager;
    }

    public void setAuthManager(Long authManager) {
        this.authManager = authManager;
    }

    private String type;

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

    public Long getAutoId() {
        return autoId;
    }

    public void setAutoId(Long autoId) {
        this.autoId = autoId;
    }

    public Long getHandleUserId() {
        return handleUserId;
    }

    public void setHandleUserId(Long handleUserId) {
        this.handleUserId = handleUserId;
    }

    public String getHandleUserName() {
        return handleUserName;
    }

    public void setHandleUserName(String handleUserName) {
        this.handleUserName = handleUserName;
    }

    public String getHandleUserMobile() {
        return handleUserMobile;
    }

    public void setHandleUserMobile(String handleUserMobile) {
        this.handleUserMobile = handleUserMobile;
    }

    public String getCommissionPhoto() {
        return commissionPhoto;
    }

    public void setCommissionPhoto(String commissionPhoto) {
        this.commissionPhoto = commissionPhoto;
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

    public String getTransferDoc() {
        return transferDoc;
    }

    public void setTransferDoc(String transferDoc) {
        this.transferDoc = transferDoc;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}