package com.wintop.ms.carauction.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 查博士日志表 car_chaboshi_log
 * 
 * @author ruoyi
 * @date 2019-05-08
 */
public class CarChaboshiLog extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 查询日志主键id */
	private Long id;
	/** 类型：1店铺，2个人 */
	private String userType;
	/** 查询人ID */
	private Long userId;
	/** 输入的vin码 */
	private String vin;
	/** 查询日志 */
	private String responseMsg;
	/** 报告来源 1查博士 2 数据库 */
	private String sourceType;
	/** 查询结果 1查询成功，2查询失败，3，查询中 */
	private String responseResult;
	/** 支付金额 */
	private BigDecimal money;
	/** 查询版本 1维修版 2综合版 */
	private String edition;
	/** 查博士订单 */
	private String orderId;
	/** 查博士返回信息 */
	private String orderMsg;
	/** 查询人名称 */
	private String userName;

	/** 店铺id */
	private Long storeId;

	/*支付流水id*/
	private Long payLogId;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setUserType(String userType) 
	{
		this.userType = userType;
	}

	public String getUserType() 
	{
		return userType;
	}
	public void setUserId(Long userId) 
	{
		this.userId = userId;
	}

	public Long getUserId() 
	{
		return userId;
	}
	public void setVin(String vin) 
	{
		this.vin = vin;
	}

	public String getVin() 
	{
		return vin;
	}
	public void setResponseMsg(String responseMsg) 
	{
		this.responseMsg = responseMsg;
	}

	public String getResponseMsg() 
	{
		return responseMsg;
	}
	public void setSourceType(String sourceType) 
	{
		this.sourceType = sourceType;
	}

	public String getSourceType() 
	{
		return sourceType;
	}
	public void setResponseResult(String responseResult) 
	{
		this.responseResult = responseResult;
	}

	public String getResponseResult() 
	{
		return responseResult;
	}
	public void setMoney(BigDecimal money) 
	{
		this.money = money;
	}

	public BigDecimal getMoney() 
	{
		return money;
	}
	public void setEdition(String edition) 
	{
		this.edition = edition;
	}

	public String getEdition() 
	{
		return edition;
	}
	public void setOrderId(String orderId) 
	{
		this.orderId = orderId;
	}

	public String getOrderId() 
	{
		return orderId;
	}
	public void setOrderMsg(String orderMsg) 
	{
		this.orderMsg = orderMsg;
	}

	public String getOrderMsg() 
	{
		return orderMsg;
	}
	public void setUserName(String userName) 
	{
		this.userName = userName;
	}

	public String getUserName() 
	{
		return userName;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getPayLogId() {
		return payLogId;
	}

	public void setPayLogId(Long payLogId) {
		this.payLogId = payLogId;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("createTime", getCreateTime())
            .append("userType", getUserType())
            .append("userId", getUserId())
            .append("vin", getVin())
            .append("responseMsg", getResponseMsg())
            .append("sourceType", getSourceType())
            .append("responseResult", getResponseResult())
            .append("money", getMoney())
            .append("edition", getEdition())
            .append("orderId", getOrderId())
            .append("orderMsg", getOrderMsg())
            .append("userName", getUserName())
            .toString();
    }
}
