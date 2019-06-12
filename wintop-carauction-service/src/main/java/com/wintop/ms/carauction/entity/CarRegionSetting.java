package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class CarRegionSetting implements Serializable {

    private static final long serialVersionUID = 3283802269448054215L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 竞拍城市ID
     */
    private Long regionId;

    /**
     * 拍卖地区名称
     */
    private String regionName;

    /**
     * 冻结资金
     */
    private BigDecimal frozenCapital;

    /**
     * 付款违约天数
     */
    private Integer payBreachDay;

    /**
     * 付款违约时间
     */
    private Date payBreachTime;

    /**
     * 编辑时间
     */
    private Date createTime;

    /**
     * 操作人
     */
    private Long createPerson;

    /**
     * 是否删除：1正常，2删除
     */
    private String status;

    /**
     * 过户违约天
     */
    private Integer transferBreachDay;

    /**
     * 过户违约时间
     */
    private Date transferBreachTime;

    private BigDecimal agentFee;

    /**
     * 编辑时间
     */
    private Date updateTime;

    /**
     * 操作人
     */
    private Long updatePerson;

    private String serverfeeSettings;

    private Long centerId;

    private List<CarRegionServerfeeSetting> serverfeeSettingList;

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
     * @return 竞拍城市ID
     */
    public Long getRegionId() {
        return regionId;
    }

    /**
     * @param regionId 
	 *            竞拍城市ID
     */
    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    /**
     * @return 拍卖地区名称
     */
    public String getRegionName() {
        return regionName;
    }

    /**
     * @param regionName 
	 *            拍卖地区名称
     */
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    /**
     * @return 冻结资金
     */
    public BigDecimal getFrozenCapital() {
        return frozenCapital;
    }

    /**
     * @param frozenCapital 
	 *            冻结资金
     */
    public void setFrozenCapital(BigDecimal frozenCapital) {
        this.frozenCapital = frozenCapital;
    }

    /**
     * @return 付款违约天数
     */
    public Integer getPayBreachDay() {
        return payBreachDay;
    }

    /**
     * @param payBreachDay 
	 *            付款违约天数
     */
    public void setPayBreachDay(Integer payBreachDay) {
        this.payBreachDay = payBreachDay;
    }

    /**
     * @return 付款违约时间
     */
    public Date getPayBreachTime() {
        return payBreachTime;
    }

    /**
     * @param payBreachTime 
	 *            付款违约时间
     */
    public void setPayBreachTime(Date payBreachTime) {
        this.payBreachTime = payBreachTime;
    }

    /**
     * @return 编辑时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime 
	 *            编辑时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return 操作人
     */
    public Long getCreatePerson() {
        return createPerson;
    }

    /**
     * @param createPerson 
	 *            操作人
     */
    public void setCreatePerson(Long createPerson) {
        this.createPerson = createPerson;
    }

    /**
     * @return 是否删除：1正常，2删除
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            是否删除：1正常，2删除
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return 过户违约天
     */
    public Integer getTransferBreachDay() {
        return transferBreachDay;
    }

    /**
     * @param transferBreachDay 
	 *            过户违约天
     */
    public void setTransferBreachDay(Integer transferBreachDay) {
        this.transferBreachDay = transferBreachDay;
    }

    /**
     * @return 过户违约时间
     */
    public Date getTransferBreachTime() {
        return transferBreachTime;
    }

    /**
     * @param transferBreachTime 
	 *            过户违约时间
     */
    public void setTransferBreachTime(Date transferBreachTime) {
        this.transferBreachTime = transferBreachTime;
    }

    public BigDecimal getAgentFee() {
        return agentFee;
    }

    public void setAgentFee(BigDecimal agentFee) {
        this.agentFee = agentFee;
    }

    /**
     * @return 编辑时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime 
	 *            编辑时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return 操作人
     */
    public Long getUpdatePerson() {
        return updatePerson;
    }

    /**
     * @param updatePerson 
	 *            操作人
     */
    public void setUpdatePerson(Long updatePerson) {
        this.updatePerson = updatePerson;
    }

    public String getServerfeeSettings() {
        return serverfeeSettings;
    }

    public void setServerfeeSettings(String serverfeeSettings) {
        this.serverfeeSettings = serverfeeSettings;
    }

    public List<CarRegionServerfeeSetting> getServerfeeSettingList() {
        return serverfeeSettingList;
    }

    public void setServerfeeSettingList(List<CarRegionServerfeeSetting> serverfeeSettingList) {
        this.serverfeeSettingList = serverfeeSettingList;
    }

    public Long getCenterId() {
        return centerId;
    }

    public void setCenterId(Long centerId) {
        this.centerId = centerId;
    }
}