package com.wintop.ms.carauction.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.List;

/**
 * 查博士店铺设置表 car_chaboshi_store_conf
 * 
 * @author ruoyi
 * @date 2019-05-08
 */
public class CarChaboshiStoreConf extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 配置主键id */
	private Long id;
	/** 店铺ID */
	private Long storeId;
	/** 是否开通：1开通，2关闭 */
	private String ifOpen;
	/** 查博士余额 */
	private BigDecimal balance;
	/** 维修报告维修版，支付金额设置 */
	private BigDecimal payment;
	/** 综合版支付金额 */
	private BigDecimal paymentComposite;
	/** 多个店铺ID ,分隔 */
	private String storeIds;
	/** 多个店铺 List */
	private List<String> storeIdList;

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
	public void setIfOpen(String ifOpen) 
	{
		this.ifOpen = ifOpen;
	}

	public String getIfOpen() 
	{
		return ifOpen;
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
	public void setPaymentComposite(BigDecimal paymentComposite) 
	{
		this.paymentComposite = paymentComposite;
	}

	public BigDecimal getPaymentComposite() 
	{
		return paymentComposite;
	}

	public String getStoreIds() {
		return storeIds;
	}

	public void setStoreIds(String storeIds) {
		this.storeIds = storeIds;
	}

	public List<String> getStoreIdList() {
		return storeIdList;
	}

	public void setStoreIdList(List<String> storeIdList) {
		this.storeIdList = storeIdList;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("storeId", getStoreId())
            .append("ifOpen", getIfOpen())
            .append("balance", getBalance())
            .append("payment", getPayment())
            .append("paymentComposite", getPaymentComposite())
            .toString();
    }


}
