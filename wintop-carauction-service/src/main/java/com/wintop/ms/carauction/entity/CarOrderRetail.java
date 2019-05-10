package com.wintop.ms.carauction.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CarOrderRetail {
    private Long id;

    private String orderNo;

    private Long carId;

    private BigDecimal transactionFee;

    private BigDecimal earnestMoney;

    private String paymentType;

    private BigDecimal downPayments;

    private BigDecimal mortgageAmount;

    private Long mortgagePeriod;

    private BigDecimal monthlyRepayment;

    private String transfer;

    private String salesType;

    private Long salesConsultant;

    private Date salesDate;

    private String insuranceCompany;

    private Date commercialInsuranceExpires;

    private Date compulsoryInsuranceExpires;

    private BigDecimal mortgageCharges;

    private BigDecimal insuranceRebate;

    private BigDecimal boutiqueProfit;

    private BigDecimal guaranteedProfit;

    private BigDecimal addProfits;

    private BigDecimal otherExpenses;

    private String remarks;

    private String vehicleOwnerType;

    private String phone;

    private String name;

    private String source;

    private String idCard;

    private String address;

    private String drawee;

    private String openingBank;

    private String bankAccount;

    private String otherAccounts;

    private Long createUser;

    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
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

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType == null ? null : paymentType.trim();
    }

    public BigDecimal getDownPayments() {
        return downPayments;
    }

    public void setDownPayments(BigDecimal downPayments) {
        this.downPayments = downPayments;
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

    public String getTransfer() {
        return transfer;
    }

    public void setTransfer(String transfer) {
        this.transfer = transfer == null ? null : transfer.trim();
    }

    public String getSalesType() {
        return salesType;
    }

    public void setSalesType(String salesType) {
        this.salesType = salesType == null ? null : salesType.trim();
    }

    public Long getSalesConsultant() {
        return salesConsultant;
    }

    public void setSalesConsultant(Long salesConsultant) {
        this.salesConsultant = salesConsultant;
    }

    public Date getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(Date salesDate) {
        this.salesDate = salesDate;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany == null ? null : insuranceCompany.trim();
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

    public BigDecimal getAddProfits() {
        return addProfits;
    }

    public void setAddProfits(BigDecimal addProfits) {
        this.addProfits = addProfits;
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
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getVehicleOwnerType() {
        return vehicleOwnerType;
    }

    public void setVehicleOwnerType(String vehicleOwnerType) {
        this.vehicleOwnerType = vehicleOwnerType == null ? null : vehicleOwnerType.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getDrawee() {
        return drawee;
    }

    public void setDrawee(String drawee) {
        this.drawee = drawee == null ? null : drawee.trim();
    }

    public String getOpeningBank() {
        return openingBank;
    }

    public void setOpeningBank(String openingBank) {
        this.openingBank = openingBank == null ? null : openingBank.trim();
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount == null ? null : bankAccount.trim();
    }

    public String getOtherAccounts() {
        return otherAccounts;
    }

    public void setOtherAccounts(String otherAccounts) {
        this.otherAccounts = otherAccounts == null ? null : otherAccounts.trim();
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}