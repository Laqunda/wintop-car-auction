package com.wintop.ms.carauction.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 查博士买家端支付金额设置表 car_chaboshi_payment_conf
 * 
 * @author ruoyi
 * @date 2019-05-08
 */
public class CarChaboshiPaymentConf extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 配置主键id */
	private Long id;
	/** 维修报告维修版，支付金额设置 */
	private BigDecimal payment;
	/** 综合版支付金额 */
	private BigDecimal paymentComposite;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
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

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("payment", getPayment())
            .append("paymentComposite", getPaymentComposite())
            .toString();
    }
}
