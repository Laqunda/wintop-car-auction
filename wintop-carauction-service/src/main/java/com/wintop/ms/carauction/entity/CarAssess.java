package com.wintop.ms.carauction.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * 车辆评估表 car_assess
 * 
 * @author ruoyi
 * @date 2019-05-05
 */
public class CarAssess extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 评估主键ID */
	private Long id;
	/** 车辆VIN码 */
	private String vin;
	/** 表现里程单位KM */
	private Integer mileage;
	/** 车辆品牌车型车系名称 */
	private String name;
	/** 初次上牌日期 */
	private Date beginRegisterDate;
	/** 颜色 */
	private String color;
	/** 排量L */
	private BigDecimal engineCapacity;
	/** 使用性质：1运营，2非运营，3营转非，4租赁营运，5租赁非营运 */
	private String function;
	/** 发动机号 */
	private String engineNumber;
	/** 出厂日期 */
	private Date manufactureDate;
	/** 年检到期日期 */
	private Date yearInsurance;
	/** 车牌号 */
	private String plateNum;
	/** 过户次数 */
	private Integer transferNumber;
	/** 车辆性质：1公户，2私户，3外事车 */
	private String autoNature;
	/** 评估人 */
	private Long createUser;
	/** 状态：1草稿，2，完成评估，3提交审核 */
	private String status;
	/** 车辆照片 */
	private String carPhoto;
	/** 证件照片 */
	private String certificatePhoto;
	/** 其他照片 */
	private String otherPhoto;
	/** 颜色中文 */
	private String colorCn;
	/** 所属城市 */
	private String regionId;
	/** 品牌id */
	private String autoBrand;
	/** 品牌名称 */
	private String autoBrandCn;
	/** 车型id */
	private String autoStyle;
	/** 车型名称 */
	private String autoStyleCn;
	/** 车系id */
	private String autoSeries;
	/** 车系名称 */
	private String autoSeriesCn;
	/** 编辑时间 */
	private Date editTime;
	/** 车辆编码 */
	private Long autoId;
	/******************************* 关联字段 *******************************/
	/** 店铺名称 */
	private String storeName;
	/** 评估结果：1评估中，2采购前科，3战败，4确认采购 */
	private String followResult;
	/** 代办状态：1过户事宜确定中、2出牌确认中、3交档确认中、4提档确认中、5手续上传中、6手续回传确认中、7手续回传不通过、8代办完结、9争议处理中、10.交易关闭 */
	private String transferStatus;
	/** 质检报告综合等级：A,B,C.. */
	private String reportColligationRanks;
	/** 参数配置 */
	private Map<String,Object> ParamConf;
	/** 车主 */
	private String ownerName;
	private CarAssessOrder order;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setVin(String vin) 
	{
		this.vin = vin;
	}

	public String getVin() 
	{
		return vin;
	}
	public void setMileage(Integer mileage) 
	{
		this.mileage = mileage;
	}

	public Integer getMileage() 
	{
		return mileage;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	public void setBeginRegisterDate(Date beginRegisterDate) 
	{
		this.beginRegisterDate = beginRegisterDate;
	}

	public Date getBeginRegisterDate() 
	{
		return beginRegisterDate;
	}
	public void setColor(String color) 
	{
		this.color = color;
	}

	public String getColor() 
	{
		return color;
	}
	public void setEngineCapacity(BigDecimal engineCapacity) 
	{
		this.engineCapacity = engineCapacity;
	}

	public BigDecimal getEngineCapacity() 
	{
		return engineCapacity;
	}
	public void setFunction(String function) 
	{
		this.function = function;
	}

	public String getFunction() 
	{
		return function;
	}
	public void setEngineNumber(String engineNumber) 
	{
		this.engineNumber = engineNumber;
	}

	public String getEngineNumber() 
	{
		return engineNumber;
	}
	public void setManufactureDate(Date manufactureDate) 
	{
		this.manufactureDate = manufactureDate;
	}

	public Date getManufactureDate() 
	{
		return manufactureDate;
	}
	public void setYearInsurance(Date yearInsurance) 
	{
		this.yearInsurance = yearInsurance;
	}

	public Date getYearInsurance() 
	{
		return yearInsurance;
	}
	public void setPlateNum(String plateNum) 
	{
		this.plateNum = plateNum;
	}

	public String getPlateNum() 
	{
		return plateNum;
	}
	public void setTransferNumber(Integer transferNumber) 
	{
		this.transferNumber = transferNumber;
	}

	public Integer getTransferNumber() 
	{
		return transferNumber;
	}
	public void setAutoNature(String autoNature) 
	{
		this.autoNature = autoNature;
	}

	public String getAutoNature() 
	{
		return autoNature;
	}
	public void setCreateUser(Long createUser) 
	{
		this.createUser = createUser;
	}

	public Long getCreateUser() 
	{
		return createUser;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	public void setCarPhoto(String carPhoto) 
	{
		this.carPhoto = carPhoto;
	}

	public String getCarPhoto() 
	{
		return carPhoto;
	}
	public void setCertificatePhoto(String certificatePhoto) 
	{
		this.certificatePhoto = certificatePhoto;
	}

	public String getCertificatePhoto() 
	{
		return certificatePhoto;
	}
	public void setOtherPhoto(String otherPhoto) 
	{
		this.otherPhoto = otherPhoto;
	}

	public String getOtherPhoto() 
	{
		return otherPhoto;
	}
	public void setColorCn(String colorCn) 
	{
		this.colorCn = colorCn;
	}

	public String getColorCn() 
	{
		return colorCn;
	}
	public void setRegionId(String regionId) 
	{
		this.regionId = regionId;
	}

	public String getRegionId() 
	{
		return regionId;
	}
	public void setAutoBrand(String autoBrand) 
	{
		this.autoBrand = autoBrand;
	}

	public String getAutoBrand() 
	{
		return autoBrand;
	}
	public void setAutoBrandCn(String autoBrandCn) 
	{
		this.autoBrandCn = autoBrandCn;
	}

	public String getAutoBrandCn() 
	{
		return autoBrandCn;
	}
	public void setAutoStyle(String autoStyle) 
	{
		this.autoStyle = autoStyle;
	}

	public String getAutoStyle() 
	{
		return autoStyle;
	}
	public void setAutoStyleCn(String autoStyleCn) 
	{
		this.autoStyleCn = autoStyleCn;
	}

	public String getAutoStyleCn() 
	{
		return autoStyleCn;
	}
	public void setAutoSeries(String autoSeries) 
	{
		this.autoSeries = autoSeries;
	}

	public String getAutoSeries() 
	{
		return autoSeries;
	}
	public void setAutoSeriesCn(String autoSeriesCn) 
	{
		this.autoSeriesCn = autoSeriesCn;
	}

	public String getAutoSeriesCn() 
	{
		return autoSeriesCn;
	}
	public void setEditTime(Date editTime) 
	{
		this.editTime = editTime;
	}

	public Date getEditTime() 
	{
		return editTime;
	}

	public Long getAutoId() {
		return autoId;
	}

	public void setAutoId(Long autoId) {
		this.autoId = autoId;
	}

	public CarAssessOrder getOrder() {
		return order;
	}

	public void setOrder(CarAssessOrder order) {
		this.order = order;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getFollowResult() {
		return followResult;
	}

	public void setFollowResult(String followResult) {
		this.followResult = followResult;
	}

	public String getTransferStatus() {
		return transferStatus;
	}

	public void setTransferStatus(String transferStatus) {
		this.transferStatus = transferStatus;
	}

	public String getReportColligationRanks() {
		return reportColligationRanks;
	}

	public void setReportColligationRanks(String reportColligationRanks) {
		this.reportColligationRanks = reportColligationRanks;
	}

	public Map<String, Object> getParamConf() {
		return ParamConf;
	}

	public void setParamConf(Map<String, Object> paramConf) {
		ParamConf = paramConf;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
}
