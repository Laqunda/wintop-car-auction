package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.Date;

/***
 * 用户签约主表
 */
public class CarCustomerSign implements Serializable {
    private Long id;//主键

    private Long customerId;//签约客户

    private String status;//签约状态：1客户签字，2签约审核通过，3审核失败作废

    private Date signatureTime;//签字时间

    private Date authTime;//审核时间

    private Long authManager;//审核人

    private CarCustomerSignLog signLog;//签约日志

    private String authMsg;

    private String isDelete;

    /**用户主状态*/
    private String userStatus;
    /**保证金状态*/
    private String depositStatus;

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public Long getAuthManager() {
        return authManager;
    }
    public void setAuthManager(Long authManager) {
        this.authManager = authManager;
    }


    public String getAuthMsg() {
        return authMsg;
    }

    public void setAuthMsg(String authMsg) {
        this.authMsg = authMsg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getSignatureTime() {
        return signatureTime;
    }

    public void setSignatureTime(Date signatureTime) {
        this.signatureTime = signatureTime;
    }

    public Date getAuthTime() {
        return authTime;
    }

    public void setAuthTime(Date authTime) {
        this.authTime = authTime;
    }
    public CarCustomerSignLog getSignLog() {
        return signLog;
    }

    public void setSignLog(CarCustomerSignLog signLog) {
        this.signLog = signLog;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getDepositStatus() {
        return depositStatus;
    }

    public void setDepositStatus(String depositStatus) {
        this.depositStatus = depositStatus;
    }
}
