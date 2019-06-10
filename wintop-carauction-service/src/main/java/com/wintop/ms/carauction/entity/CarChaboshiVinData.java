package com.wintop.ms.carauction.entity;

import java.util.Date;

public class CarChaboshiVinData {
    /** 主键ID*/
    private Long id;

    /** 车型*/
    private String modelName;

    /** 车系*/
    private String seriesName;

    /** Vin 码*/
    private String vin;

    /** 报告生成时间*/
    private String makeReportDate;

    /** 报告编号*/
    private String reportNo;

    /**  生成厂商*/
    private String manufacturer;

    /**  生产年份*/
    private String makeDate;

    /** 产地*/
    private String productionArea;

    /**  车辆类型*/
    private String carType;

    /**  变速箱类型*/
    private String transmissionType;

    /** 排量*/
    private String displacement;

    /** 排放标准*/
    private String effluentStandard;

    /** 是否火烧(0-否 1-是)*/
    private Integer carFireFlag;

    /** 是否水泡(0-否 1-是)*/
    private Integer carWaterFlag;

    /** 重要组成部件是否有维修(0-否 1-是)*/
    private Integer carComponentRecordsFlag;

    /** 结构件是否有维修(0-否1-是）*/
    private Integer carConstructRecordsFlag;

    /**  外观覆盖件是否有维修(0-否 1-是）*/
    private Integer carOutsideRecordsFlag;

    /**  公里数是否正常(0-否 1-是）*/
    private Integer mileageIsNormalFlag;

    /**  查博士预估公里数(如果为0，说明没有估出来)*/
    private Integer mileageEstimate;

    /**  最后一次保养时间*/
    private String lastMainTainTime;

    /**  每年保养次数*/
    private String mainTainTimes;

    /**  最后一次维修时间*/
    private String lastRepairTime;

    /** 每年行驶公里数*/
    private String mileageEveryYear;

    /**  该车所有的详细维修记录(普通报告)*/
    private String normalRepairRecords;

    /**  结构详细维修记录*/
    private String constructAnalyzeRepairRecords;

    /**  重要组成部件详细维修记录*/
    private String componentAnalyzeRepairRecords;

    /**  外观覆盖件详细维修记录*/
    private String outsideAnalyzeRepairRecords;

    /**  维修详细的内容*/
    private String content;

    /**  维修的日期(yyyy-MM-dd)*/
    private String date;

    /**  维修的材料*/
    private String materal;

    /**  维修的公里数*/
    private String mileage;

    /**  支付类型(为空)*/
    private String payType;

    /**  维修类型*/
    private String type;

    /** 备注*/
    private String remark;

    /** 创建时间*/
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName == null ? null : modelName.trim();
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName == null ? null : seriesName.trim();
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin == null ? null : vin.trim();
    }

    public String getMakeReportDate() {
        return makeReportDate;
    }

    public void setMakeReportDate(String makeReportDate) {
        this.makeReportDate = makeReportDate == null ? null : makeReportDate.trim();
    }

    public String getReportNo() {
        return reportNo;
    }

    public void setReportNo(String reportNo) {
        this.reportNo = reportNo == null ? null : reportNo.trim();
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer == null ? null : manufacturer.trim();
    }

    public String getMakeDate() {
        return makeDate;
    }

    public void setMakeDate(String makeDate) {
        this.makeDate = makeDate == null ? null : makeDate.trim();
    }

    public String getProductionArea() {
        return productionArea;
    }

    public void setProductionArea(String productionArea) {
        this.productionArea = productionArea == null ? null : productionArea.trim();
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType == null ? null : carType.trim();
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType == null ? null : transmissionType.trim();
    }

    public String getDisplacement() {
        return displacement;
    }

    public void setDisplacement(String displacement) {
        this.displacement = displacement == null ? null : displacement.trim();
    }

    public String getEffluentStandard() {
        return effluentStandard;
    }

    public void setEffluentStandard(String effluentStandard) {
        this.effluentStandard = effluentStandard == null ? null : effluentStandard.trim();
    }

    public Integer getCarFireFlag() {
        return carFireFlag;
    }

    public void setCarFireFlag(Integer carFireFlag) {
        this.carFireFlag = carFireFlag;
    }

    public Integer getCarWaterFlag() {
        return carWaterFlag;
    }

    public void setCarWaterFlag(Integer carWaterFlag) {
        this.carWaterFlag = carWaterFlag;
    }

    public Integer getCarComponentRecordsFlag() {
        return carComponentRecordsFlag;
    }

    public void setCarComponentRecordsFlag(Integer carComponentRecordsFlag) {
        this.carComponentRecordsFlag = carComponentRecordsFlag;
    }

    public Integer getCarConstructRecordsFlag() {
        return carConstructRecordsFlag;
    }

    public void setCarConstructRecordsFlag(Integer carConstructRecordsFlag) {
        this.carConstructRecordsFlag = carConstructRecordsFlag;
    }

    public Integer getCarOutsideRecordsFlag() {
        return carOutsideRecordsFlag;
    }

    public void setCarOutsideRecordsFlag(Integer carOutsideRecordsFlag) {
        this.carOutsideRecordsFlag = carOutsideRecordsFlag;
    }

    public Integer getMileageisNormalFlag() {
        return mileageIsNormalFlag;
    }

    public void setMileageisNormalFlag(Integer mileageIsNormalFlag) {
        this.mileageIsNormalFlag = mileageIsNormalFlag;
    }

    public Integer getMileageEstimate() {
        return mileageEstimate;
    }

    public void setMileageEstimate(Integer mileageEstimate) {
        this.mileageEstimate = mileageEstimate;
    }

    public String getLastMainTainTime() {
        return lastMainTainTime;
    }

    public void setLastMainTainTime(String lastMainTainTime) {
        this.lastMainTainTime = lastMainTainTime == null ? null : lastMainTainTime.trim();
    }

    public String getMainTainTimes() {
        return mainTainTimes;
    }

    public void setMainTainTimes(String mainTainTimes) {
        this.mainTainTimes = mainTainTimes == null ? null : mainTainTimes.trim();
    }

    public String getLastRepairTime() {
        return lastRepairTime;
    }

    public void setLastRepairTime(String lastRepairTime) {
        this.lastRepairTime = lastRepairTime == null ? null : lastRepairTime.trim();
    }

    public String getMileageEveryYear() {
        return mileageEveryYear;
    }

    public void setMileageEveryYear(String mileageEveryYear) {
        this.mileageEveryYear = mileageEveryYear == null ? null : mileageEveryYear.trim();
    }

    public String getNormalRepairRecords() {
        return normalRepairRecords;
    }

    public void setNormalRepairRecords(String normalRepairRecords) {
        this.normalRepairRecords = normalRepairRecords == null ? null : normalRepairRecords.trim();
    }

    public String getConstructAnalyzeRepairRecords() {
        return constructAnalyzeRepairRecords;
    }

    public void setConstructAnalyzeRepairRecords(String constructAnalyzeRepairRecords) {
        this.constructAnalyzeRepairRecords = constructAnalyzeRepairRecords == null ? null : constructAnalyzeRepairRecords.trim();
    }

    public String getComponentAnalyzeRepairRecords() {
        return componentAnalyzeRepairRecords;
    }

    public void setComponentAnalyzeRepairRecords(String componentAnalyzeRepairRecords) {
        this.componentAnalyzeRepairRecords = componentAnalyzeRepairRecords == null ? null : componentAnalyzeRepairRecords.trim();
    }

    public String getOutsideAnalyzeRepairRecords() {
        return outsideAnalyzeRepairRecords;
    }

    public void setOutsideAnalyzeRepairRecords(String outsideAnalyzeRepairRecords) {
        this.outsideAnalyzeRepairRecords = outsideAnalyzeRepairRecords == null ? null : outsideAnalyzeRepairRecords.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    public String getMateral() {
        return materal;
    }

    public void setMateral(String materal) {
        this.materal = materal == null ? null : materal.trim();
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage == null ? null : mileage.trim();
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}