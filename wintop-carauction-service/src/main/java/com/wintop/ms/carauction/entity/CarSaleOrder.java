package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CarSaleOrder implements Serializable {
    private static final long serialVersionUID = -7026968454853917820L;
    //车辆 主照片
    private String mainPhoto;
    //车辆名称=品牌+车系+车型
    private String autoInfoName;
    //质检报告综合等级
    private String reportColligationRanks;
    //质检报告整备等级
    private String reportServicingRanks;
    //归属省份中文
    private String vehicleAttributionCityCN;
    //里程表里程
    private BigDecimal mileage;
    //成交价
    private BigDecimal transactionFee;
    //定金
    private BigDecimal earnestMoney;
    //店铺名称
    private String storeName;
    //买家
    private String name;
    //销售员
    private String managerName;

    public String getMainPhoto() {
        return mainPhoto;
    }

    public void setMainPhoto(String mainPhoto) {
        this.mainPhoto = mainPhoto;
    }

    public String getAutoInfoName() {
        return autoInfoName;
    }

    public void setAutoInfoName(String autoInfoName) {
        this.autoInfoName = autoInfoName;
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

    public String getVehicleAttributionCityCN() {
        return vehicleAttributionCityCN;
    }

    public void setVehicleAttributionCityCN(String vehicleAttributionCityCN) {
        this.vehicleAttributionCityCN = vehicleAttributionCityCN;
    }

    public BigDecimal getMileage() {
        return mileage;
    }

    public void setMileage(BigDecimal mileage) {
        this.mileage = mileage;
    }

    public BigDecimal getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(BigDecimal transactionFee) {
        this.transactionFee = transactionFee;
    }

    public BigDecimal getEarnestMoney() {
        return earnestMoney;
    }

    public void setEarnestMoney(BigDecimal earnestMoney) {
        this.earnestMoney = earnestMoney;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
    //付款方式
    private String paymentType;
    //评估人
    private String carAssess;
    //过户
    private String transfer;
    //出售日期
    private String salesDate;
    //保险公司
    private String insuranceCompany;
    //商业险到期
    private Date commercialInsuranceExpires;
    //交强险到期
    private Date compulsoryInsuranceExpires;
    //按揭手续费
    private BigDecimal mortgageCharges;
    //保险返利
    private BigDecimal insuranceRebate;
    //精品利润
    private BigDecimal boutiqueProfit;
    //质保利润
    private BigDecimal guaranteedProfit;
    //加装利润
    private BigDecimal addProfit;
    //其他费用
    private BigDecimal otherExpenses;
    //备注
    private String remarks;
    //车主类型
    private String vehicleOwnerType;
    //手机号
    private String phone;
    //来源
    private String source;
    //身份证号
    private String idCard;
    //地址
    private String address;
    //付款人
    private String drawee;
    //开户行
    private String openingBank;
    //银行账号
    private String bankAccount;
    //其他账号
    private String otherAccounts;
    //按揭金额
    private BigDecimal mortgageAmount;
    //按揭期数
    private Long mortgagePeriod;
    //每月还款
    private BigDecimal monthlyRepayment;

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getCarAssess() {
        return carAssess;
    }

    public void setCarAssess(String carAssess) {
        this.carAssess = carAssess;
    }

    public String getTransfer() {
        return transfer;
    }

    public void setTransfer(String transfer) {
        this.transfer = transfer;
    }

    public String getSalesDate() {
        return salesDate;
    }

    public void setSaleDate(String salesDate) {
        this.salesDate = salesDate;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public Date getCommercialInsuranceExpires() {
        return commercialInsuranceExpires;
    }

    public void setCommercialInsuranceExpires(Date commercialInsuranceExpires) {
        this.commercialInsuranceExpires = commercialInsuranceExpires;
    }

    public Date getCompulsoryInsuranceExpires() {
        return compulsoryInsuranceExpires;
    }

    public void setCompulsoryInsuranceExpires(Date compulsoryInsuranceExpires) {
        this.compulsoryInsuranceExpires = compulsoryInsuranceExpires;
    }

    public BigDecimal getMortgageCharges() {
        return mortgageCharges;
    }

    public void setMortgageCharges(BigDecimal mortgageCharges) {
        this.mortgageCharges = mortgageCharges;
    }

    public BigDecimal getInsuranceRebate() {
        return insuranceRebate;
    }

    public void setInsuranceRebate(BigDecimal insuranceRebate) {
        this.insuranceRebate = insuranceRebate;
    }

    public BigDecimal getBoutiqueProfit() {
        return boutiqueProfit;
    }

    public void setBoutiqueProfit(BigDecimal boutiqueProfit) {
        this.boutiqueProfit = boutiqueProfit;
    }

    public BigDecimal getGuaranteedProfit() {
        return guaranteedProfit;
    }

    public void setGuaranteedProfit(BigDecimal guaranteedProfit) {
        this.guaranteedProfit = guaranteedProfit;
    }

    public BigDecimal getAddProfit() {
        return addProfit;
    }

    public void setAddProfit(BigDecimal addProfit) {
        this.addProfit = addProfit;
    }

    public BigDecimal getOtherExpenses() {
        return otherExpenses;
    }

    public void setOtherExpenses(BigDecimal otherExpenses) {
        this.otherExpenses = otherExpenses;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getVehicleOwnerType() {
        return vehicleOwnerType;
    }

    public void setVehicleOwnerType(String vehicleOwnerType) {
        this.vehicleOwnerType = vehicleOwnerType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDrawee() {
        return drawee;
    }

    public void setDrawee(String drawee) {
        this.drawee = drawee;
    }

    public String getOpeningBank() {
        return openingBank;
    }

    public void setOpeningBank(String openingBank) {
        this.openingBank = openingBank;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getOtherAccounts() {
        return otherAccounts;
    }

    public void setOtherAccounts(String otherAccounts) {
        this.otherAccounts = otherAccounts;
    }

    public BigDecimal getMortgageAmount() {
        return mortgageAmount;
    }

    public void setMortgageAmount(BigDecimal mortgageAmount) {
        this.mortgageAmount = mortgageAmount;
    }

    public Long getMortgagePeriod() {
        return mortgagePeriod;
    }

    public void setMortgagePeriod(Long mortgagePeriod) {
        this.mortgagePeriod = mortgagePeriod;
    }

    public BigDecimal getMonthlyRepayment() {
        return monthlyRepayment;
    }

    public void setMonthlyRepayment(BigDecimal monthlyRepayment) {
        this.monthlyRepayment = monthlyRepayment;
    }
    //ID
    private Long id;
    //车辆ID
    private Long carId;
    //订单编号
    private String orderNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    private BigDecimal downPayments;

    public BigDecimal getDownPayments() {
        return downPayments;
    }

    public void setDownPayments(BigDecimal downPayments) {
        this.downPayments = downPayments;
    }
    //出售类型
    private String salesType;
    //销售顾问ID
    private Long salesConsultant;

    public String getSalesType() {
        return salesType;
    }

    public void setSalesType(String salesType) {
        this.salesType = salesType;
    }

    public Long getSalesConsultant() {
        return salesConsultant;
    }

    public void setSalesConsultant(Long salesConsultant) {
        this.salesConsultant = salesConsultant;
    }
    //订单创建时间
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    private String mortgagePlan;

    public String getMortgagePlan() {
        return mortgagePlan;
    }

    public void setMortgagePlan(String mortgagePlan) {
        this.mortgagePlan = mortgagePlan;
    }
}
