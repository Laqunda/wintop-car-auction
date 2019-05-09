package com.wintop.ms.carauction.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 查博士店铺资金流水表 car_chaboshi_store_account
 * 
 * @author ruoyi
 * @date 2019-05-08
 */
public class CarChaboshiStoreAccount extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 配置主键id */
	private Long id;
	/** 店铺ID */
	private Long storeId;
	/** 进出帐类型：1进账，2出账 */
	private String type;
	/** 余额 */
	private BigDecimal balance;
	/** 支付金额 */
	private BigDecimal payment;
	/** 业务类型：1充值，2查询报告 ,3退款 */
	private String serviceType;
	/**
	 * 创建人ID
	 */
	private Long userId;
	private String userName;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setStoreId(Long storeId) 
	{
		this.storeId = storeId;
	}

	public Long getStoreId() 
	{
		return storeId;
	}
	public void setType(String type) 
	{
		this.type = type;
	}

	public String getType() 
	{
		return type;
	}
	public void setBalance(BigDecimal balance) 
	{
		this.balance = balance;
	}

	public BigDecimal getBalance() 
	{
		return balance;
	}
	public void setPayment(BigDecimal payment) 
	{
		this.payment = payment;
	}

	public BigDecimal getPayment() 
	{
		return payment;
	}
	public void setServiceType(String serviceType) 
	{
		this.serviceType = serviceType;
	}

	public String getServiceType() 
	{
		return serviceType;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("storeId", getStoreId())
            .append("type", getType())
            .append("balance", getBalance())
            .append("payment", getPayment())
            .append("serviceType", getServiceType())
            .append("createTime", getCreateTime())
            .toString();
    }
}
