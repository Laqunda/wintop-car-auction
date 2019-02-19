package com.wintop.ms.carauction.entity;

import java.io.Serializable;

/**
 * class_name: CarDataExcel
 * package: com.wintop.ms.carauction.entity
 * describe: 车辆数据excel表格实体类
 * creat_user: lizhaoyang
 * creat_date-time: 2018/8/23/15:18
 **/
public class CarDataExcel implements Serializable{
    //车辆编号
    private String id;
    //经销店
    private String storeName;
    //车牌号码
    private String licenseNumber;
    //车架号vin
    private String vin;
    //品牌型号
    private String autoBrandCn;
    //出厂时间
    private String manufactureDate;
    //登记日期
    private String beginRegisterDate;
    //年检
    private String yearInsurance;
    //保险截止日期
    private String businessInsurance;
    //保险截止日期(强险)
    private String compulsoryInsurance;
    //排量
    private String engineCapacity;
    //颜色
    private String colorCn;
    //表里显程(万公里)
    private String mileage;
    //是否需要代办过户/转籍
    private String ifAgent;
    //使用性质
    private String useNatureCn;
    //行驶证
    private String drivingLicense;
    //登记证
    private String registrationCertificate;
    //过户变更次数
    private String transferNumber;
    //购置证
    private String purchaseTax;
    //违章情况
    private String unIllegal;
    //备注（过户后手续留存特殊需要、本市或外迁、是否指定过户场地等）该车辆如有重大车损、事故及故障、影响过户的所有因素、是否为转籍车辆必须注明）
    private String remark;

    private String remark1;

    public CarDataExcel() {
    }

    public CarDataExcel(String id, String storeName, String licenseNumber, String vin, String autoBrandCn, String manufactureDate, String beginRegisterDate, String yearInsurance, String businessInsurance, String compulsoryInsurance, String engineCapacity, String colorCn, String mileage, String ifAgent, String useNatureCn, String drivingLicense, String registrationCertificate, String transferNumber, String purchaseTax, String unIllegal, String remark, String remark1) {
        this.id = id;
        this.storeName = storeName;
        this.licenseNumber = licenseNumber;
        this.vin = vin;
        this.autoBrandCn = autoBrandCn;
        this.manufactureDate = manufactureDate;
        this.beginRegisterDate = beginRegisterDate;
        this.yearInsurance = yearInsurance;
        this.businessInsurance = businessInsurance;
        this.compulsoryInsurance = compulsoryInsurance;
        this.engineCapacity = engineCapacity;
        this.colorCn = colorCn;
        this.mileage = mileage;
        this.ifAgent = ifAgent;
        this.useNatureCn = useNatureCn;
        this.drivingLicense = drivingLicense;
        this.registrationCertificate = registrationCertificate;
        this.transferNumber = transferNumber;
        this.purchaseTax = purchaseTax;
        this.unIllegal = unIllegal;
        this.remark = remark;
        this.remark1 = remark1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getAutoBrandCn() {
        return autoBrandCn;
    }

    public void setAutoBrandCn(String autoBrandCn) {
        this.autoBrandCn = autoBrandCn;
    }

    public String getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(String manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public String getBeginRegisterDate() {
        return beginRegisterDate;
    }

    public void setBeginRegisterDate(String beginRegisterDate) {
        this.beginRegisterDate = beginRegisterDate;
    }

    public String getYearInsurance() {
        return yearInsurance;
    }

    public void setYearInsurance(String yearInsurance) {
        this.yearInsurance = yearInsurance;
    }

    public String getBusinessInsurance() {
        return businessInsurance;
    }

    public void setBusinessInsurance(String businessInsurance) {
        this.businessInsurance = businessInsurance;
    }

    public String getCompulsoryInsurance() {
        return compulsoryInsurance;
    }

    public void setCompulsoryInsurance(String compulsoryInsurance) {
        this.compulsoryInsurance = compulsoryInsurance;
    }

    public String getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(String engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public String getColorCn() {
        return colorCn;
    }

    public void setColorCn(String colorCn) {
        this.colorCn = colorCn;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getIfAgent() {
        return ifAgent;
    }

    public void setIfAgent(String ifAgent) {
        this.ifAgent = ifAgent;
    }

    public String getUseNatureCn() {
        return useNatureCn;
    }

    public void setUseNatureCn(String useNatureCn) {
        this.useNatureCn = useNatureCn;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    public String getRegistrationCertificate() {
        return registrationCertificate;
    }

    public void setRegistrationCertificate(String registrationCertificate) {
        this.registrationCertificate = registrationCertificate;
    }

    public String getTransferNumber() {
        return transferNumber;
    }

    public void setTransferNumber(String transferNumber) {
        this.transferNumber = transferNumber;
    }

    public String getPurchaseTax() {
        return purchaseTax;
    }

    public void setPurchaseTax(String purchaseTax) {
        this.purchaseTax = purchaseTax;
    }

    public String getUnIllegal() {
        return unIllegal;
    }

    public void setUnIllegal(String unIllegal) {
        this.unIllegal = unIllegal;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarDataExcel)) return false;

        CarDataExcel that = (CarDataExcel) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (storeName != null ? !storeName.equals(that.storeName) : that.storeName != null) return false;
        if (licenseNumber != null ? !licenseNumber.equals(that.licenseNumber) : that.licenseNumber != null)
            return false;
        if (vin != null ? !vin.equals(that.vin) : that.vin != null) return false;
        if (autoBrandCn != null ? !autoBrandCn.equals(that.autoBrandCn) : that.autoBrandCn != null) return false;
        if (manufactureDate != null ? !manufactureDate.equals(that.manufactureDate) : that.manufactureDate != null)
            return false;
        if (beginRegisterDate != null ? !beginRegisterDate.equals(that.beginRegisterDate) : that.beginRegisterDate != null)
            return false;
        if (yearInsurance != null ? !yearInsurance.equals(that.yearInsurance) : that.yearInsurance != null)
            return false;
        if (businessInsurance != null ? !businessInsurance.equals(that.businessInsurance) : that.businessInsurance != null)
            return false;
        if (compulsoryInsurance != null ? !compulsoryInsurance.equals(that.compulsoryInsurance) : that.compulsoryInsurance != null)
            return false;
        if (engineCapacity != null ? !engineCapacity.equals(that.engineCapacity) : that.engineCapacity != null)
            return false;
        if (colorCn != null ? !colorCn.equals(that.colorCn) : that.colorCn != null) return false;
        if (mileage != null ? !mileage.equals(that.mileage) : that.mileage != null) return false;
        if (ifAgent != null ? !ifAgent.equals(that.ifAgent) : that.ifAgent != null) return false;
        if (useNatureCn != null ? !useNatureCn.equals(that.useNatureCn) : that.useNatureCn != null) return false;
        if (drivingLicense != null ? !drivingLicense.equals(that.drivingLicense) : that.drivingLicense != null)
            return false;
        if (registrationCertificate != null ? !registrationCertificate.equals(that.registrationCertificate) : that.registrationCertificate != null)
            return false;
        if (transferNumber != null ? !transferNumber.equals(that.transferNumber) : that.transferNumber != null)
            return false;
        if (purchaseTax != null ? !purchaseTax.equals(that.purchaseTax) : that.purchaseTax != null) return false;
        if (unIllegal != null ? !unIllegal.equals(that.unIllegal) : that.unIllegal != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        return remark1 != null ? remark1.equals(that.remark1) : that.remark1 == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (storeName != null ? storeName.hashCode() : 0);
        result = 31 * result + (licenseNumber != null ? licenseNumber.hashCode() : 0);
        result = 31 * result + (vin != null ? vin.hashCode() : 0);
        result = 31 * result + (autoBrandCn != null ? autoBrandCn.hashCode() : 0);
        result = 31 * result + (manufactureDate != null ? manufactureDate.hashCode() : 0);
        result = 31 * result + (beginRegisterDate != null ? beginRegisterDate.hashCode() : 0);
        result = 31 * result + (yearInsurance != null ? yearInsurance.hashCode() : 0);
        result = 31 * result + (businessInsurance != null ? businessInsurance.hashCode() : 0);
        result = 31 * result + (compulsoryInsurance != null ? compulsoryInsurance.hashCode() : 0);
        result = 31 * result + (engineCapacity != null ? engineCapacity.hashCode() : 0);
        result = 31 * result + (colorCn != null ? colorCn.hashCode() : 0);
        result = 31 * result + (mileage != null ? mileage.hashCode() : 0);
        result = 31 * result + (ifAgent != null ? ifAgent.hashCode() : 0);
        result = 31 * result + (useNatureCn != null ? useNatureCn.hashCode() : 0);
        result = 31 * result + (drivingLicense != null ? drivingLicense.hashCode() : 0);
        result = 31 * result + (registrationCertificate != null ? registrationCertificate.hashCode() : 0);
        result = 31 * result + (transferNumber != null ? transferNumber.hashCode() : 0);
        result = 31 * result + (purchaseTax != null ? purchaseTax.hashCode() : 0);
        result = 31 * result + (unIllegal != null ? unIllegal.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (remark1 != null ? remark1.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CarDataExcel{" +
                "id='" + id + '\'' +
                ", storeName='" + storeName + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", vin='" + vin + '\'' +
                ", autoBrandCn='" + autoBrandCn + '\'' +
                ", manufactureDate='" + manufactureDate + '\'' +
                ", beginRegisterDate='" + beginRegisterDate + '\'' +
                ", yearInsurance='" + yearInsurance + '\'' +
                ", businessInsurance='" + businessInsurance + '\'' +
                ", compulsoryInsurance='" + compulsoryInsurance + '\'' +
                ", engineCapacity='" + engineCapacity + '\'' +
                ", colorCn='" + colorCn + '\'' +
                ", mileage='" + mileage + '\'' +
                ", ifAgent='" + ifAgent + '\'' +
                ", useNatureCn='" + useNatureCn + '\'' +
                ", drivingLicense='" + drivingLicense + '\'' +
                ", registrationCertificate='" + registrationCertificate + '\'' +
                ", transferNumber='" + transferNumber + '\'' +
                ", purchaseTax='" + purchaseTax + '\'' +
                ", unIllegal='" + unIllegal + '\'' +
                ", remark='" + remark + '\'' +
                ", remark1='" + remark1 + '\'' +
                '}';
    }
}
