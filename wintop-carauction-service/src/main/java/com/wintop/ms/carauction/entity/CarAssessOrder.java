package com.wintop.ms.carauction.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 评估采购单表 car_assess_order
 *
 * @author ruoyi
 * @date 2019-05-05
 */
public class CarAssessOrder extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 采购单ID
     */
    private Long id;
    /**
     * 评估id
     */
    private Long assessId;
    /**
     * 跟进ID
     */
    private Long followId;
    /**
     * 采购类型：1置换，2合作，3实车寄售，4网络寄售，5收购，6主机厂竞价
     */
    private String procurementType;
    /**
     * 采购价格
     */
    private BigDecimal price;
    /**
     * 采购价格
     */
    private BigDecimal paidPrice;
    /**
     * 采购日期
     */
    private Date procurementDate;
    /**
     * 所属店铺
     */
    private Long storeId;
    /**
     * 店铺名称
     */
    private String storeName;
    /**
     * 是否新车置换：1是，2否
     */
    private String ifOldNew;
    /**
     * 预计整备费用
     */
    private BigDecimal entiretyPrice;
    /**
     * 新车指导价
     */
    private BigDecimal newCarPrice;
    /**
     * 车辆所在地
     */
    private String carAddress;
    /**
     * 购置税证：1有，2无
     */
    private String purchaseTax;
    /**
     * 是否有行驶证：1有，2无
     */
    private String drivingLicense;
    /**
     * 是否有登记证：1有，2无
     */
    private String registrationCertificate;
    /**
     * 是否有加装：1有，2无
     */
    private String ifAdd;
    /**
     * 钥匙数量
     */
    private Integer carKeys;
    /**
     * 新车发票：1有，2无
     */
    private String newCarInvoice;
    /**
     * 新车质保：1保内，2保外
     */
    private String qualityGuarantee;
    /**
     * 新车保养手车：1有，2无
     */
    private String maintenanceManual;
    /**
     * 交强险单：1有，2无
     */
    private String sali;
    /**
     * 交强险到期日
     */
    private Date saliEndDate;
    /**
     * 商业险：1有，2无
     */
    private String commercialInsurance;
    /**
     * 商业险到期时间
     */
    private Date commercialInsuranceEndDate;
    /**
     * 商业险金额
     */
    private BigDecimal commercialInsurancePrice;
    /**
     * 车船税：1有，2无
     */
    private String transportTax;
    /**
     * 车船税到期日
     */
    private Date transportTaxEndDate;
    /**
     * 客户姓名
     */
    private String customerName;
    /**
     * 客户电话
     */
    private String customerTel;
    /**
     * 客户来源
     */
    private String customerSource;
    /**
     * 状态 1待审核，2审核通过，-1审核不通过 3审核撤销
     */
    private String status;

    private CarAssess carAssess;

    private CarStore carStore;

    private CarAssessFollowData follow;

    private String regionId;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setAssessId(Long assessId) {
        this.assessId = assessId;
    }

    public Long getAssessId() {
        return assessId;
    }

    public void setFollowId(Long followId) {
        this.followId = followId;
    }

    public Long getFollowId() {
        return followId;
    }

    public void setProcurementType(String procurementType) {
        this.procurementType = procurementType;
    }

    public String getProcurementType() {
        return procurementType;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setProcurementDate(Date procurementDate) {
        this.procurementDate = procurementDate;
    }

    public Date getProcurementDate() {
        return procurementDate;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setIfOldNew(String ifOldNew) {
        this.ifOldNew = ifOldNew;
    }

    public String getIfOldNew() {
        return ifOldNew;
    }

    public void setEntiretyPrice(BigDecimal entiretyPrice) {
        this.entiretyPrice = entiretyPrice;
    }

    public BigDecimal getEntiretyPrice() {
        return entiretyPrice;
    }

    public void setNewCarPrice(BigDecimal newCarPrice) {
        this.newCarPrice = newCarPrice;
    }

    public BigDecimal getNewCarPrice() {
        return newCarPrice;
    }

    public void setCarAddress(String carAddress) {
        this.carAddress = carAddress;
    }

    public String getCarAddress() {
        return carAddress;
    }

    public void setPurchaseTax(String purchaseTax) {
        this.purchaseTax = purchaseTax;
    }

    public String getPurchaseTax() {
        return purchaseTax;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public void setRegistrationCertificate(String registrationCertificate) {
        this.registrationCertificate = registrationCertificate;
    }

    public String getRegistrationCertificate() {
        return registrationCertificate;
    }

    public void setIfAdd(String ifAdd) {
        this.ifAdd = ifAdd;
    }

    public String getIfAdd() {
        return ifAdd;
    }

    public void setCarKeys(Integer carKeys) {
        this.carKeys = carKeys;
    }

    public Integer getCarKeys() {
        return carKeys;
    }

    public void setNewCarInvoice(String newCarInvoice) {
        this.newCarInvoice = newCarInvoice;
    }

    public String getNewCarInvoice() {
        return newCarInvoice;
    }

    public void setQualityGuarantee(String qualityGuarantee) {
        this.qualityGuarantee = qualityGuarantee;
    }

    public String getQualityGuarantee() {
        return qualityGuarantee;
    }

    public void setMaintenanceManual(String maintenanceManual) {
        this.maintenanceManual = maintenanceManual;
    }

    public String getMaintenanceManual() {
        return maintenanceManual;
    }

    public void setSali(String sali) {
        this.sali = sali;
    }

    public String getSali() {
        return sali;
    }

    public void setSaliEndDate(Date saliEndDate) {
        this.saliEndDate = saliEndDate;
    }

    public Date getSaliEndDate() {
        return saliEndDate;
    }

    public void setCommercialInsurance(String commercialInsurance) {
        this.commercialInsurance = commercialInsurance;
    }

    public String getCommercialInsurance() {
        return commercialInsurance;
    }

    public void setCommercialInsuranceEndDate(Date commercialInsuranceEndDate) {
        this.commercialInsuranceEndDate = commercialInsuranceEndDate;
    }

    public Date getCommercialInsuranceEndDate() {
        return commercialInsuranceEndDate;
    }

    public void setCommercialInsurancePrice(BigDecimal commercialInsurancePrice) {
        this.commercialInsurancePrice = commercialInsurancePrice;
    }

    public BigDecimal getCommercialInsurancePrice() {
        return commercialInsurancePrice;
    }

    public void setTransportTax(String transportTax) {
        this.transportTax = transportTax;
    }

    public String getTransportTax() {
        return transportTax;
    }

    public void setTransportTaxEndDate(Date transportTaxEndDate) {
        this.transportTaxEndDate = transportTaxEndDate;
    }

    public Date getTransportTaxEndDate() {
        return transportTaxEndDate;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerTel(String customerTel) {
        this.customerTel = customerTel;
    }

    public String getCustomerTel() {
        return customerTel;
    }

    public void setCustomerSource(String customerSource) {
        this.customerSource = customerSource;
    }

    public String getCustomerSource() {
        return customerSource;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public CarAssessFollowData getFollow() {
        return follow;
    }

    public void setFollow(CarAssessFollowData follow) {
        this.follow = follow;
    }

    private String mainPhoto;

    private String createUser;


    public String getMainPhoto() {
        return mainPhoto;
    }

    public void setMainPhoto(String mainPhoto) {
        this.mainPhoto = mainPhoto;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    private String autoInfoName;

    public String getAutoInfoName() {
        return autoInfoName;
    }

    public void setAutoInfoName(String autoInfoName) {
        this.autoInfoName = autoInfoName;
    }

    public BigDecimal getPaidPrice() {
        return paidPrice;
    }

    public void setPaidPrice(BigDecimal paidPrice) {
        this.paidPrice = paidPrice;
    }

    public CarAssess getCarAssess() {
        return carAssess;
    }

    public void setCarAssess(CarAssess carAssess) {
        this.carAssess = carAssess;
    }

    public CarStore getCarStore() {
        return carStore;
    }

    public void setCarStore(CarStore carStore) {
        this.carStore = carStore;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }
}
