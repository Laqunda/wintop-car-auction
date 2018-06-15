package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class CarAutoInfoDetail implements Serializable {

    private static final long serialVersionUID = -6212885000127576580L;
    /**
     * 车辆id
     */
    private Long id;

    /**
     * 车辆ID
     */
    private Long autoId;

    /**
     * 车辆vin码
     */
    private String vin;

    /**
     * 车辆品牌
     */
    private String autoBrand;
    /**
     * 车辆品牌
     */
    private String autoBrandCn;
    /**
     * 车型id
     */
    private String autoStyle;
    /**
     * 车型
     */
    private String autoStyleCn;
    /**
     * 车系id
     */
    private String autoSeries;
    /**
     * 车系
     */
    private String autoSeriesCn;

    /**
     * 车辆排量
     */
    private String engineCapacity;

    /**
     * 汽车排量单位T、L
     */
    private String engineCapacityUnit;

    /**
     * 环保标准：国二、国三、国四、国五
     */
    private String environment;
    /**
     * 环保标准：国二、国三、国四、国五
     */
    private String environmentCn;

    /**
     * 供油系统：汽油、柴油
     */
    private String oilSupplySystem;
    /**
     * 供油系统：汽油、柴油
     */
    private String oilSupplySystemCn;
    /**
     * 变速器
     */
    private String transmission;
    /**
     * 变速器
     */
    private String transmissionCn;

    /**
     * 车辆驱动形式：前置后置
     */
    private String vehicleDriver;
    /**
     * 车辆驱动形式：前置后置
     */
    private String vehicleDriverCn;

    /**
     * 里程表里程
     */
    private Long mileage;

    /**
     * 车辆颜色
     */
    private String color;

    /**
     * 车辆颜色
     */
    private String colorCn;

    /**
     * 是否改装过颜色|1:改装 0:未改装
     */
    private String colorChanged;

    /**
     * 出厂日期
     */
    private Date manufactureDate;

    /**
     * 初次上牌时间
     */
    private Date beginRegisterDate;

    /**
     * 归属省份
     */
    private String vehicleAttributionProvince;
    /**
     * 归属省份
     */
    private String vehicleAttributionProvinceCn;

    /**
     * 归属城市
     */
    private String vehicleAttributionCity;
    /**
     * 归属城市
     */
    private String vehicleAttributionCityCn;

    /**
     * 车牌
     */
    private String licenseNumber;

    /**
     * 车辆性质|私户personal,公户common,外事车foreign
     */
    private String carNature;
    /**
     * 车辆性质|私户personal,公户common,外事车foreign
     */
    private String carNatureCn;

    /**
     * 车辆用途|operational:营运,non_operational:非营运,transform:营转非,lease租赁,lease_non_operational租赁非营运
     */
    private String useNature;
    /**
     * 车辆用途|operational:营运,non_operational:非营运,transform:营转非,lease租赁,lease_non_operational租赁非营运
     */
    private String useNatureCn;

    /**
     * 是否改装|1:改装 0:未改装
     */
    private String isModification;

    /**
     * 新车指导价
     */
    private BigDecimal originalPrice;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 进气方式
     */
    private String intakeMethod;
    /**
     * 进气方式
     */
    private String intakeMethodCn;

    /**
     * 备注图片
     */
    private String remarkPhoto;

    /**
     * 车辆外形：suv 跑车等，字典数据
     */
    private String carShape;

    /**
     * 车型：跑车、suv
     */
    private String carShapeCn;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人
     */
    private String updateUser;

    /**
     * vo字段
     */
    private String autoInfoName;
    private String carAutoNo;
    /***
     * 质检报告综合等级：A,B,C..
     */
    private String reportColligationRanks;
    /***
     * 质检报告整备等级：++，+，-，--
     */
    private String reportServicingRanks;
    private String status;
    /**是否新车：1二手车，2新车*/
    private String ifNew;

    private List<CarAutoConfDetail> confDetails;

    /**
     * @return 车辆id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id 
	 *            车辆id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 车辆ID
     */
    public Long getAutoId() {
        return autoId;
    }

    /**
     * @param autoId 
	 *            车辆ID
     */
    public void setAutoId(Long autoId) {
        this.autoId = autoId;
    }

    /**
     * @return 车辆vin码
     */
    public String getVin() {
        return vin;
    }

    /**
     * @param vin 
	 *            车辆vin码
     */
    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getAutoBrand() {
        return autoBrand;
    }

    public void setAutoBrand(String autoBrand) {
        this.autoBrand = autoBrand;
    }

    public String getAutoStyle() {
        return autoStyle;
    }

    public void setAutoStyle(String autoStyle) {
        this.autoStyle = autoStyle;
    }

    public String getAutoSeries() {
        return autoSeries;
    }

    public void setAutoSeries(String autoSeries) {
        this.autoSeries = autoSeries;
    }

    /**
     * @return 车辆排量
     */
    public String getEngineCapacity() {
        return engineCapacity;
    }

    /**
     * @param engineCapacity 
	 *            车辆排量
     */
    public void setEngineCapacity(String engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    /**
     * @return 汽车排量单位T、L
     */
    public String getEngineCapacityUnit() {
        return engineCapacityUnit;
    }

    /**
     * @param engineCapacityUnit 
	 *            汽车排量单位T、L
     */
    public void setEngineCapacityUnit(String engineCapacityUnit) {
        this.engineCapacityUnit = engineCapacityUnit;
    }

    /**
     * @return 环保标准：国二、国三、国四、国五
     */
    public String getEnvironment() {
        return environment;
    }

    /**
     * @param environment 
	 *            环保标准：国二、国三、国四、国五
     */
    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    /**
     * @return 供油系统：汽油、柴油
     */
    public String getOilSupplySystem() {
        return oilSupplySystem;
    }

    /**
     * @param oilSupplySystem 
	 *            供油系统：汽油、柴油
     */
    public void setOilSupplySystem(String oilSupplySystem) {
        this.oilSupplySystem = oilSupplySystem;
    }

    /**
     * @return 变速器
     */
    public String getTransmission() {
        return transmission;
    }

    /**
     * @param transmission 
	 *            变速器
     */
    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    /**
     * @return 车辆驱动形式：前置后置
     */
    public String getVehicleDriver() {
        return vehicleDriver;
    }

    /**
     * @param vehicleDriver 
	 *            车辆驱动形式：前置后置
     */
    public void setVehicleDriver(String vehicleDriver) {
        this.vehicleDriver = vehicleDriver;
    }

    /**
     * @return 里程表里程
     */
    public Long getMileage() {
        return mileage;
    }

    /**
     * @param mileage 
	 *            里程表里程
     */
    public void setMileage(Long mileage) {
        this.mileage = mileage;
    }

    /**
     * @return 车辆颜色
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color 
	 *            车辆颜色
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return 是否改装过颜色|1:改装 0:未改装
     */
    public String getColorChanged() {
        return colorChanged;
    }

    /**
     * @param colorChanged 
	 *            是否改装过颜色|1:改装 0:未改装
     */
    public void setColorChanged(String colorChanged) {
        this.colorChanged = colorChanged;
    }

    /**
     * @return 出厂日期
     */
    public Date getManufactureDate() {
        return manufactureDate;
    }

    /**
     * @param manufactureDate 
	 *            出厂日期
     */
    public void setManufactureDate(Date manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    /**
     * @return 初次上牌时间
     */
    public Date getBeginRegisterDate() {
        return beginRegisterDate;
    }

    /**
     * @param beginRegisterDate 
	 *            初次上牌时间
     */
    public void setBeginRegisterDate(Date beginRegisterDate) {
        this.beginRegisterDate = beginRegisterDate;
    }

    /**
     * @return 归属省份
     */
    public String getVehicleAttributionProvince() {
        return vehicleAttributionProvince;
    }

    /**
     * @param vehicleAttributionProvince 
	 *            归属省份
     */
    public void setVehicleAttributionProvince(String vehicleAttributionProvince) {
        this.vehicleAttributionProvince = vehicleAttributionProvince;
    }

    /**
     * @return 归属城市
     */
    public String getVehicleAttributionCity() {
        return vehicleAttributionCity;
    }

    /**
     * @param vehicleAttributionCity 
	 *            归属城市
     */
    public void setVehicleAttributionCity(String vehicleAttributionCity) {
        this.vehicleAttributionCity = vehicleAttributionCity;
    }

    /**
     * @return 车牌
     */
    public String getLicenseNumber() {
        return licenseNumber;
    }

    /**
     * @param licenseNumber 
	 *            车牌
     */
    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    /**
     * @return 车辆性质|私户personal,公户common,外事车foreign
     */
    public String getCarNature() {
        return carNature;
    }

    /**
     * @param carNature 
	 *            车辆性质|私户personal,公户common,外事车foreign
     */
    public void setCarNature(String carNature) {
        this.carNature = carNature;
    }

    /**
     * @return 车辆用途|operational:营运,non_operational:非营运,transform:营转非,lease租赁,lease_non_operational租赁非营运
     */
    public String getUseNature() {
        return useNature;
    }

    /**
     * @param useNature 
	 *            车辆用途|operational:营运,non_operational:非营运,transform:营转非,lease租赁,lease_non_operational租赁非营运
     */
    public void setUseNature(String useNature) {
        this.useNature = useNature;
    }

    /**
     * @return 是否改装|1:改装 0:未改装
     */
    public String getIsModification() {
        return isModification;
    }

    /**
     * @param isModification 
	 *            是否改装|1:改装 0:未改装
     */
    public void setIsModification(String isModification) {
        this.isModification = isModification;
    }

    /**
     * @return 新车指导价
     */
    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    /**
     * @param originalPrice 
	 *            新车指导价
     */
    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    /**
     * @return 备注信息
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark 
	 *            备注信息
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return 进气方式
     */
    public String getIntakeMethod() {
        return intakeMethod;
    }

    /**
     * @param intakeMethod 
	 *            进气方式
     */
    public void setIntakeMethod(String intakeMethod) {
        this.intakeMethod = intakeMethod;
    }

    /**
     * @return 备注图片
     */
    public String getRemarkPhoto() {
        return remarkPhoto;
    }

    /**
     * @param remarkPhoto 
	 *            备注图片
     */
    public void setRemarkPhoto(String remarkPhoto) {
        this.remarkPhoto = remarkPhoto;
    }

    /**
     * @return 车辆外形：suv 跑车等，字典数据
     */
    public String getCarShape() {
        return carShape;
    }

    /**
     * @param carShape 
	 *            车辆外形：suv 跑车等，字典数据
     */
    public void setCarShape(String carShape) {
        this.carShape = carShape;
    }

    /**
     * @return 车型：跑车、suv
     */
    public String getCarShapeCn() {
        return carShapeCn;
    }

    /**
     * @param carShapeCn 
	 *            车型：跑车、suv
     */
    public void setCarShapeCn(String carShapeCn) {
        this.carShapeCn = carShapeCn;
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
    public String getCreateUser() {
        return createUser;
    }

    /**
     * @param createUser 
	 *            创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * @return 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime 
	 *            修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return 修改人
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * @param updateUser 
	 *            修改人
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getAutoInfoName() {
        return autoInfoName;
    }

    public void setAutoInfoName(String autoInfoName) {
        this.autoInfoName = autoInfoName;
    }

    public List<CarAutoConfDetail> getConfDetails() {
        return confDetails;
    }

    public void setConfDetails(List<CarAutoConfDetail> confDetails) {
        this.confDetails = confDetails;
    }

    public String getEnvironmentCn() {
        return environmentCn;
    }

    public void setEnvironmentCn(String environmentCn) {
        this.environmentCn = environmentCn;
    }

    public String getOilSupplySystemCn() {
        return oilSupplySystemCn;
    }

    public void setOilSupplySystemCn(String oilSupplySystemCn) {
        this.oilSupplySystemCn = oilSupplySystemCn;
    }

    public String getTransmissionCn() {
        return transmissionCn;
    }

    public void setTransmissionCn(String transmissionCn) {
        this.transmissionCn = transmissionCn;
    }

    public String getVehicleDriverCn() {
        return vehicleDriverCn;
    }

    public void setVehicleDriverCn(String vehicleDriverCn) {
        this.vehicleDriverCn = vehicleDriverCn;
    }

    public String getColorCn() {
        return colorCn;
    }

    public void setColorCn(String colorCn) {
        this.colorCn = colorCn;
    }

    public String getVehicleAttributionProvinceCn() {
        return vehicleAttributionProvinceCn;
    }

    public void setVehicleAttributionProvinceCn(String vehicleAttributionProvinceCn) {
        this.vehicleAttributionProvinceCn = vehicleAttributionProvinceCn;
    }

    public String getVehicleAttributionCityCn() {
        return vehicleAttributionCityCn;
    }

    public void setVehicleAttributionCityCn(String vehicleAttributionCityCn) {
        this.vehicleAttributionCityCn = vehicleAttributionCityCn;
    }

    public String getCarNatureCn() {
        return carNatureCn;
    }

    public void setCarNatureCn(String carNatureCn) {
        this.carNatureCn = carNatureCn;
    }

    public String getUseNatureCn() {
        return useNatureCn;
    }

    public void setUseNatureCn(String useNatureCn) {
        this.useNatureCn = useNatureCn;
    }

    public String getIntakeMethodCn() {
        return intakeMethodCn;
    }

    public void setIntakeMethodCn(String intakeMethodCn) {
        this.intakeMethodCn = intakeMethodCn;
    }

    public String getAutoBrandCn() {
        return autoBrandCn;
    }

    public void setAutoBrandCn(String autoBrandCn) {
        this.autoBrandCn = autoBrandCn;
    }

    public String getAutoStyleCn() {
        return autoStyleCn;
    }

    public void setAutoStyleCn(String autoStyleCn) {
        this.autoStyleCn = autoStyleCn;
    }

    public String getAutoSeriesCn() {
        return autoSeriesCn;
    }

    public void setAutoSeriesCn(String autoSeriesCn) {
        this.autoSeriesCn = autoSeriesCn;
    }

    public String getCarAutoNo() {
        return carAutoNo;
    }

    public void setCarAutoNo(String carAutoNo) {
        this.carAutoNo = carAutoNo;
    }

    public String getReportColligationRanks() {
        return reportColligationRanks;
    }

    public void setReportColligationRanks(String reportColligationRanks) {
        this.reportColligationRanks = reportColligationRanks;
    }

    public String getReportServicingRanks() {
        return reportServicingRanks;
    }

    public void setReportServicingRanks(String reportServicingRanks) {
        this.reportServicingRanks = reportServicingRanks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIfNew() {
        return ifNew;
    }

    public void setIfNew(String ifNew) {
        this.ifNew = ifNew;
    }
}