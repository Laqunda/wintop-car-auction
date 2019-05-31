package com.wintop.ms.carauction.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

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

	/**车型id*/
	private Long vehicleId;

	/**车型*/
	private String vehicleType;
	/*图片*/
	private String photo;
	/*发动机号*/
	private String engineNum;

	/*pc的报告url*/
	private String pc_url;
	/*App报告url*/
	private String app_url;
	/*报告生成时间*/
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date finishTime;

	/*用户信息*/
	private WtAppUser wtAppUser;

	/*查博士返回数据*/
	CarChaboshiVinData carChaboshiVinData;

	/*查博士买家端支付金额设置*/
	CarChaboshiPaymentConf carChaboshiPaymentConf;

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

	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getEngineNum() {
		return engineNum;
	}

	public void setEngineNum(String engineNum) {
		this.engineNum = engineNum;
	}

	public String getPc_url() {
		return pc_url;
	}

	public void setPc_url(String pc_url) {
		this.pc_url = pc_url;
	}

	public String getApp_url() {
		return app_url;
	}

	public void setApp_url(String app_url) {
		this.app_url = app_url;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public WtAppUser getWtAppUser() {
		return wtAppUser;
	}

	public void setWtAppUser(WtAppUser wtAppUser) {
		this.wtAppUser = wtAppUser;
	}

	public CarChaboshiVinData getCarChaboshiVinData() {
		return carChaboshiVinData;
	}

	public void setCarChaboshiVinData(CarChaboshiVinData carChaboshiVinData) {
		this.carChaboshiVinData = carChaboshiVinData;
	}

	public CarChaboshiPaymentConf getCarChaboshiPaymentConf() {
		return carChaboshiPaymentConf;
	}

	public void setCarChaboshiPaymentConf(CarChaboshiPaymentConf carChaboshiPaymentConf) {
		this.carChaboshiPaymentConf = carChaboshiPaymentConf;
	}

	@Override
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
