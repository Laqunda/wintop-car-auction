package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CarCustomerDepositLog implements Serializable {
    private static final long serialVersionUID = -890722093950222414L;
    private Long id;

    /**
     * 保证金主表id
     */
    private Long depositId;

    /**
     * 保证金金额
     */
    private BigDecimal depositAmount;

    /**
     * 附件路径
     */
    private String fileUrl;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人id
     */
    private Long createPerson;

    /**
     * 备注
     */
    private String remark;

    /**
     * 操作人类型  1买家 2管理员 3代办
     */
    private String userType;

    /**
     * 操作类型 1冻结  2解冻  
     */
    private String logType;

    private Long userId;

    public BigDecimal getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 保证金主表id
     */
    public Long getDepositId() {
        return depositId;
    }

    /**
     * @param depositId 
	 *            保证金主表id
     */
    public void setDepositId(Long depositId) {
        this.depositId = depositId;
    }



    /**
     * @return 附件路径
     */
    public String getFileUrl() {
        return fileUrl;
    }

    /**
     * @param fileUrl 
	 *            附件路径
     */
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    /**
     * @return 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime 
	 *            创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return 创建人id
     */
    public Long getCreatePerson() {
        return createPerson;
    }

    /**
     * @param createPerson 
	 *            创建人id
     */
    public void setCreatePerson(Long createPerson) {
        this.createPerson = createPerson;
    }

    /**
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark 
	 *            备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return 操作人类型  1买家 2管理员 3代办
     */
    public String getUserType() {
        return userType;
    }

    /**
     * @param userType 
	 *            操作人类型  1买家 2管理员 3代办
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * @return 操作类型 1冻结  2解冻  
     */
    public String getLogType() {
        return logType;
    }

    /**
     * @param logType 
	 *            操作类型 1冻结  2解冻  
     */
    public void setLogType(String logType) {
        this.logType = logType;
    }
}