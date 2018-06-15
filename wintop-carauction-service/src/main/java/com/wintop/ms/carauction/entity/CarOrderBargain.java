package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CarOrderBargain implements Serializable {

    private static final long serialVersionUID = -3241627520547847054L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 订单id
     */
    private Long orderId;

    private Long carId;

    private Long customerId;

    private Date createTime;

    private BigDecimal transactionFee;

    /**
     * 议价金额
     */
    private  BigDecimal bargainFee;

    /**
     * 议价状态：1待审核；2审核通过，3审核不通过
     */
    private String status;

    /**
     * 处理人
     */
    private Long authManager;

    /**
     * 处理时间
     */
    private Date authTime;

    /**
     * 处理意见
     */
    private String authMsg;

    /**
     * @return 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id 
	 *            主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 订单id
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * @param orderId 
	 *            订单id
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getBargainFee() {
        return bargainFee;
    }

    public void setBargainFee(BigDecimal bargainFee) {
        this.bargainFee = bargainFee;
    }

    /**
     * @return 议价状态：1待审核；2审核通过，3审核不通过
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            议价状态：1待审核；2审核通过，3审核不通过
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public Long getAuthManager() {
        return authManager;
    }

    public void setAuthManager(Long authManager) {
        this.authManager = authManager;
    }

    /**
     * @return 审核时间
     */
    public Date getAuthTime() {
        return authTime;
    }

    /**
     * @param authTime 
	 *            审核时间
     */
    public void setAuthTime(Date authTime) {
        this.authTime = authTime;
    }

    /**
     * @return 审核意见
     */
    public String getAuthMsg() {
        return authMsg;
    }

    /**
     * @param authMsg 
	 *            审核意见
     */
    public void setAuthMsg(String authMsg) {
        this.authMsg = authMsg;
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

    public BigDecimal getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(BigDecimal transactionFee) {
        this.transactionFee = transactionFee;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}