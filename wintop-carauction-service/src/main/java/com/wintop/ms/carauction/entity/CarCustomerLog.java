package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * CarCustomerLog:客户变更日志实体
 * @author zhangzijuan
 * @date 2018-02-26
 */
public class CarCustomerLog implements Serializable {
    private static final long serialVersionUID = 6357547615102087638L;
    /**
     * 日志ID
     */
    private Long id;

    /**
     * 客户ID
     */
    private Long userId;

    /**
     * 状态：1正常，2冻结，-1删除
     */
    private String status;

    /**
     * 操作说明
     */
    private String msg;

    /**
     * 操作人ID
     */
    private Long editUserId;

    /**
     * 操作人类型
     */
    private String editType;

    private String file;

    private Long logId;

    private Date editTime;

    private String statusName;

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    private CarCustomerAuth customerAuth;

    private CarCustomerDeposit customerDeposit;

    public CarCustomerDeposit getCustomerDeposit() {
        return customerDeposit;
    }

    public void setCustomerDeposit(CarCustomerDeposit customerDeposit) {
        this.customerDeposit = customerDeposit;
    }

    public CarCustomerAuth getCustomerAuth() {
        return customerAuth;
    }

    public void setCustomerAuth(CarCustomerAuth customerAuth) {
        this.customerAuth = customerAuth;
    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    /**
     * @return 日志ID
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id 
	 *            日志ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 客户ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId 
	 *            客户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return 状态：1正常，2冻结，-1删除
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            状态：1正常，2冻结，-1删除
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return 操作说明
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg 
	 *            操作说明
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return 操作人ID
     */
    public Long getEditUserId() {
        return editUserId;
    }

    /**
     * @param editUserId 
	 *            操作人ID
     */
    public void setEditUserId(Long editUserId) {
        this.editUserId = editUserId;
    }

    /**
     * @return 操作人类型
     */
    public String getEditType() {
        return editType;
    }

    /**
     * @param editType 
	 *            操作人类型
     */
    public void setEditType(String editType) {
        this.editType = editType;
    }
}