package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CarAutoProcedures implements Serializable {

    private static final long serialVersionUID = -7127259142320989395L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 车辆ID
     */
    private Long autoId;

    /**
     * 购置税
     */
    private Long purchaseTax;

    /**
     * 是否有行驶证：1有，2没有
     */
    private String drivingLicense;

    /**
     * 是否有登记证：1有，2没有
     */
    private String registrationCertificate;

    /**
     * 说明书：1有，2没有
     */
    private String instruction;

    /**
     * 抢险到期时间
     */
    private Date compulsoryInsurance;

    /**
     * 商业险到期日期
     */
    private Date businessInsurance;

    /**
     * 过户次数
     */
    private Integer transferNumber;

    /**
     * 新车发票:1有，2没有
     */
    private String newCarInvoice;

    /**
     * 新车质保：1保内，2已过保
     */
    private String qualityGuarantee;

    /**
     * 钥匙数量
     */
    private Integer carKeys;

    /**
     * 保养手册
     */
    private String maintenanceManual;

    /**
     * 年检到期时间
     */
    private Date yearInsurance;

    /**
     * 过户票：1有，2没有
     */
    private String ticketOfTransfer;



    /**
     * 手续补办费用：1买家承担，2卖家承担
     */
    private String costPrice;

    /**
     * 未处理的违章
     */
    private String unIllegal;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private Long createUser;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人
     */
    private Long updateUser;

    /***
     * 违章罚款数
     */
    private BigDecimal illegalPrice;

    /**违章承担方：1买家，2卖家
     * */
    private String illegalWho;

    /**
     * 违章扣分数*/
    private String illegalScore;

    /**
     *运费承担方：1买家，2卖家
     */
    private String freightWho="1";

    /***
     * 提档费承担方：1买家，2卖家
     */
    private String mentionFeeWho="1";

    /***
     * 过户费：1买家承担，2卖家承担
     */
    private String transferFee;
    /**
     * 前往何处：1本市外迁均可，2只能外迁，3只能本市
     */
    private String moveToWhere;

    /**
     * @return 前往何处：1本市外迁均可，2只能外迁，3只能本市
     */
    public String getMoveToWhere() {
        return moveToWhere;
    }

    /**
     * @param moveToWhere
     *            前往何处：1本市外迁均可，2只能外迁，3只能本市
     */
    public void setMoveToWhere(String moveToWhere) {
        this.moveToWhere = moveToWhere;
    }
    public BigDecimal getIllegalPrice() {
        return illegalPrice;
    }

    public void setIllegalPrice(BigDecimal illegalPrice) {
        this.illegalPrice = illegalPrice;
    }

    public String getIllegalWho() {
        return illegalWho;
    }

    public void setIllegalWho(String illegalWho) {
        this.illegalWho = illegalWho;
    }

    public String getIllegalScore() {
        return illegalScore;
    }

    public void setIllegalScore(String illegalScore) {
        this.illegalScore = illegalScore;
    }

    /**
     * @return 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id 
	 *            主键
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
     * @return 购置税
     */
    public Long getPurchaseTax() {
        return purchaseTax;
    }

    /**
     * @param purchaseTax 
	 *            购置税
     */
    public void setPurchaseTax(Long purchaseTax) {
        this.purchaseTax = purchaseTax;
    }

    /**
     * @return 是否有行驶证：1有，2没有
     */
    public String getDrivingLicense() {
        return drivingLicense;
    }

    /**
     * @param drivingLicense 
	 *            是否有行驶证：1有，2没有
     */
    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    /**
     * @return 是否有登记证：1有，2没有
     */
    public String getRegistrationCertificate() {
        return registrationCertificate;
    }

    /**
     * @param registrationCertificate 
	 *            是否有登记证：1有，2没有
     */
    public void setRegistrationCertificate(String registrationCertificate) {
        this.registrationCertificate = registrationCertificate;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    /**
     * @return 说明书：1有，2没有


    /**
     * @return 抢险到期时间
     */
    public Date getCompulsoryInsurance() {
        return compulsoryInsurance;
    }

    /**
     * @param compulsoryInsurance 
	 *            抢险到期时间
     */
    public void setCompulsoryInsurance(Date compulsoryInsurance) {
        this.compulsoryInsurance = compulsoryInsurance;
    }

    /**
     * @return 商业险到期日期
     */
    public Date getBusinessInsurance() {
        return businessInsurance;
    }

    /**
     * @param businessInsurance 
	 *            商业险到期日期
     */
    public void setBusinessInsurance(Date businessInsurance) {
        this.businessInsurance = businessInsurance;
    }

    /**
     * @return 过户次数
     */
    public Integer getTransferNumber() {
        return transferNumber;
    }

    /**
     * @param transferNumber 
	 *            过户次数
     */
    public void setTransferNumber(Integer transferNumber) {
        this.transferNumber = transferNumber;
    }

    /**
     * @return 新车发票:1有，2没有
     */
    public String getNewCarInvoice() {
        return newCarInvoice;
    }

    /**
     * @param newCarInvoice 
	 *            新车发票:1有，2没有
     */
    public void setNewCarInvoice(String newCarInvoice) {
        this.newCarInvoice = newCarInvoice;
    }

    /**
     * @return 新车质保：1保内，2已过保
     */
    public String getQualityGuarantee() {
        return qualityGuarantee;
    }

    /**
     * @param qualityGuarantee 
	 *            新车质保：1保内，2已过保
     */
    public void setQualityGuarantee(String qualityGuarantee) {
        this.qualityGuarantee = qualityGuarantee;
    }

    /**
     * @return 钥匙数量
     */
    public Integer getCarKeys() {
        return carKeys;
    }

    /**
     * @param carKeys 
	 *            钥匙数量
     */
    public void setCarKeys(Integer carKeys) {
        this.carKeys = carKeys;
    }

    /**
     * @return 保养手册
     */
    public String getMaintenanceManual() {
        return maintenanceManual;
    }

    /**
     * @param maintenanceManual 
	 *            保养手册
     */
    public void setMaintenanceManual(String maintenanceManual) {
        this.maintenanceManual = maintenanceManual;
    }

    /**
     * @return 年检到期时间
     */
    public Date getYearInsurance() {
        return yearInsurance;
    }

    /**
     * @param yearInsurance 
	 *            年检到期时间
     */
    public void setYearInsurance(Date yearInsurance) {
        this.yearInsurance = yearInsurance;
    }

    /**
     * @return 过户票：1有，2没有
     */
    public String getTicketOfTransfer() {
        return ticketOfTransfer;
    }

    /**
     * @param ticketOfTransfer 
	 *            过户票：1有，2没有
     */
    public void setTicketOfTransfer(String ticketOfTransfer) {
        this.ticketOfTransfer = ticketOfTransfer;
    }


    /**
     * @return 手续补办费用：1买家承担，2卖家承担
     */
    public String getCostPrice() {
        return costPrice;
    }

    /**
     * @param costPrice 
	 *            手续补办费用：1买家承担，2卖家承担
     */
    public void setCostPrice(String costPrice) {
        this.costPrice = costPrice;
    }

    /**
     * @return 未处理的违章
     */
    public String getUnIllegal() {
        return unIllegal;
    }

    /**
     * @param unIllegal 
	 *            未处理的违章
     */
    public void setUnIllegal(String unIllegal) {
        this.unIllegal = unIllegal;
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
    public Long getCreateUser() {
        return createUser;
    }

    /**
     * @param createUser 
	 *            创建人
     */
    public void setCreateUser(Long createUser) {
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
    public Long getUpdateUser() {
        return updateUser;
    }

    /**
     * @param updateUser 
	 *            修改人
     */
    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public String getFreightWho() {
        return freightWho;
    }

    public void setFreightWho(String freightWho) {
        this.freightWho = freightWho;
    }

    public String getMentionFeeWho() {
        return mentionFeeWho;
    }

    public void setMentionFeeWho(String mentionFeeWho) {
        this.mentionFeeWho = mentionFeeWho;
    }

    public String getTransferFee() {
        return transferFee;
    }

    public void setTransferFee(String transferFee) {
        this.transferFee = transferFee;
    }
}