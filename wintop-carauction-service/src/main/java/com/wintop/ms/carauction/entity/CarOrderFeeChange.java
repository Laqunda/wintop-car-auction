package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.Date;

public class CarOrderFeeChange implements Serializable {

    private static final long serialVersionUID = -8279543150990987736L;
    /**
     * 变更id
     */
    private Long id;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 成交价
     */
    private Long transactionFee;

    /**
     * 创建人账号
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 上一次价格
     */
    private Long oldFee;

    /**
     * 修改后费用
     */
    private Long newFee;

    /**
     * @return 变更id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id 
	 *            变更id
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

    /**
     * @return 成交价
     */
    public Long getTransactionFee() {
        return transactionFee;
    }

    /**
     * @param transactionFee 
	 *            成交价
     */
    public void setTransactionFee(Long transactionFee) {
        this.transactionFee = transactionFee;
    }

    /**
     * @return 创建人账号
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * @param createUser 
	 *            创建人账号
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
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
     * @return 上一次价格
     */
    public Long getOldFee() {
        return oldFee;
    }

    /**
     * @param oldFee 
	 *            上一次价格
     */
    public void setOldFee(Long oldFee) {
        this.oldFee = oldFee;
    }

    /**
     * @return 修改后费用
     */
    public Long getNewFee() {
        return newFee;
    }

    /**
     * @param newFee 
	 *            修改后费用
     */
    public void setNewFee(Long newFee) {
        this.newFee = newFee;
    }
}