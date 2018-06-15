package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * CarCustomerEntrustCar:买家委托车辆
 * @author zhangzijuan
 * @date 2018-02-27
 */
public class CarCustomerEntrustCar implements Serializable {
    private static final long serialVersionUID = 1587909368751090748L;
    /**
     * 委托id
     */
    private Long id;

    /**
     * 车辆id
     */
    private Long carId;

    /**
     * 用户ID
     */
    private Long customerId;

    private Long autoAuctionId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createManager;

    /**
     * 委托价
     */
    private BigDecimal entrustFee;

    /**
     * 状态：1启用，2停用
     */
    private String status;

    private Date updateTime;

    /**
     * @return 委托id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id 
	 *            委托id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 车辆id
     */
    public Long getCarId() {
        return carId;
    }

    /**
     * @param carId 
	 *            车辆id
     */
    public void setCarId(Long carId) {
        this.carId = carId;
    }

    /**
     * @return 用户ID
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId 
	 *            用户ID
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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
     * @return 创建人
     */
    public String getCreateManager() {
        return createManager;
    }

    /**
     * @param createManager 
	 *            创建人
     */
    public void setCreateManager(String createManager) {
        this.createManager = createManager;
    }

    /**
     * @return 委托价
     */
    public BigDecimal getEntrustFee() {
        return entrustFee;
    }

    /**
     * @param entrustFee 
	 *            委托价
     */
    public void setEntrustFee(BigDecimal entrustFee) {
        this.entrustFee = entrustFee;
    }

    /**
     * @return 状态：1启用，2停用
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            状态：1启用，2停用
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getAutoAuctionId() {
        return autoAuctionId;
    }

    public void setAutoAuctionId(Long autoAuctionId) {
        this.autoAuctionId = autoAuctionId;
    }
}