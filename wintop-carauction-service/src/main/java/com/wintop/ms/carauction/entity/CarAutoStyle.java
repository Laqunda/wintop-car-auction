package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.Date;

public class CarAutoStyle implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ????
     */
    private Long id;

    /**
     * ????ID
     */
    private String jyid;

    /**
     * ???ID
     */
    private Long brandId;

    /**
     * ???????
     */
    private String brandName;

    /**
     * ???LOGO
     */
    private String brandLogo;

    /**
     * ??????Id
     */
    private Long brandMakeId;

    /**
     * ??????????
     */
    private String brandMakeName;

    /**
     * ???ID
     */
    private Long autoFamilyId;

    /**
     * ????????
     */
    private String autoFamilyName;

    /**
     * ?????
     */
    private String groupId;

    /**
     * ????????
     */
    private String groupName;

    /**
     * ???????
     */
    private String vehicleName;

    /**
     * ????
     */
    private String engineDesc;

    /**
     * ??????????
     */
    private String engineModel;

    /**
     * ??????????
     */
    private String gearboxName;

    /**
     * ???
     */
    private String yearPattern;

    /**
     * ????????
     */
    private String marketDate;

    /**
     * ????????
     */
    private String cfgLevel;

    /**
     * ?????
     */
    private String seat;

    /**
     * ???????
     */
    private Date createTime;

    /**
     * ???????
     */
    private String alias;

    /**
     * ???id
     */
    private Long yearPatternId;

    /**
     * ????id
     */
    private Long aliasId;

    /**
     * @return ????
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id 
	 *            ????
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return ????ID
     */
    public String getJyid() {
        return jyid;
    }

    /**
     * @param jyid 
	 *            ????ID
     */
    public void setJyid(String jyid) {
        this.jyid = jyid;
    }

    /**
     * @return ???ID
     */
    public Long getBrandId() {
        return brandId;
    }

    /**
     * @param brandId 
	 *            ???ID
     */
    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    /**
     * @return ???????
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * @param brandName 
	 *            ???????
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    /**
     * @return ???LOGO
     */
    public String getBrandLogo() {
        return brandLogo;
    }

    /**
     * @param brandLogo 
	 *            ???LOGO
     */
    public void setBrandLogo(String brandLogo) {
        this.brandLogo = brandLogo;
    }

    /**
     * @return ??????Id
     */
    public Long getBrandMakeId() {
        return brandMakeId;
    }

    /**
     * @param brandMakeId 
	 *            ??????Id
     */
    public void setBrandMakeId(Long brandMakeId) {
        this.brandMakeId = brandMakeId;
    }

    /**
     * @return ??????????
     */
    public String getBrandMakeName() {
        return brandMakeName;
    }

    /**
     * @param brandMakeName 
	 *            ??????????
     */
    public void setBrandMakeName(String brandMakeName) {
        this.brandMakeName = brandMakeName;
    }

    /**
     * @return ???ID
     */
    public Long getAutoFamilyId() {
        return autoFamilyId;
    }

    /**
     * @param autoFamilyId 
	 *            ???ID
     */
    public void setAutoFamilyId(Long autoFamilyId) {
        this.autoFamilyId = autoFamilyId;
    }

    /**
     * @return ????????
     */
    public String getAutoFamilyName() {
        return autoFamilyName;
    }

    /**
     * @param autoFamilyName 
	 *            ????????
     */
    public void setAutoFamilyName(String autoFamilyName) {
        this.autoFamilyName = autoFamilyName;
    }

    /**
     * @return ?????
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * @param groupId 
	 *            ?????
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * @return ????????
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * @param groupName 
	 *            ????????
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * @return ???????
     */
    public String getVehicleName() {
        return vehicleName;
    }

    /**
     * @param vehicleName 
	 *            ???????
     */
    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    /**
     * @return ????
     */
    public String getEngineDesc() {
        return engineDesc;
    }

    /**
     * @param engineDesc 
	 *            ????
     */
    public void setEngineDesc(String engineDesc) {
        this.engineDesc = engineDesc;
    }

    /**
     * @return ??????????
     */
    public String getEngineModel() {
        return engineModel;
    }

    /**
     * @param engineModel 
	 *            ??????????
     */
    public void setEngineModel(String engineModel) {
        this.engineModel = engineModel;
    }

    /**
     * @return ??????????
     */
    public String getGearboxName() {
        return gearboxName;
    }

    /**
     * @param gearboxName 
	 *            ??????????
     */
    public void setGearboxName(String gearboxName) {
        this.gearboxName = gearboxName;
    }

    /**
     * @return ???
     */
    public String getYearPattern() {
        return yearPattern;
    }

    /**
     * @param yearPattern 
	 *            ???
     */
    public void setYearPattern(String yearPattern) {
        this.yearPattern = yearPattern;
    }

    /**
     * @return ????????
     */
    public String getMarketDate() {
        return marketDate;
    }

    /**
     * @param marketDate 
	 *            ????????
     */
    public void setMarketDate(String marketDate) {
        this.marketDate = marketDate;
    }

    /**
     * @return ????????
     */
    public String getCfgLevel() {
        return cfgLevel;
    }

    /**
     * @param cfgLevel 
	 *            ????????
     */
    public void setCfgLevel(String cfgLevel) {
        this.cfgLevel = cfgLevel;
    }

    /**
     * @return ?????
     */
    public String getSeat() {
        return seat;
    }

    /**
     * @param seat 
	 *            ?????
     */
    public void setSeat(String seat) {
        this.seat = seat;
    }

    /**
     * @return ???????
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime 
	 *            ???????
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return ???????
     */
    public String getAlias() {
        return alias;
    }

    /**
     * @param alias 
	 *            ???????
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * @return ???id
     */
    public Long getYearPatternId() {
        return yearPatternId;
    }

    /**
     * @param yearPatternId 
	 *            ???id
     */
    public void setYearPatternId(Long yearPatternId) {
        this.yearPatternId = yearPatternId;
    }

    /**
     * @return ????id
     */
    public Long getAliasId() {
        return aliasId;
    }

    /**
     * @param aliasId 
	 *            ????id
     */
    public void setAliasId(Long aliasId) {
        this.aliasId = aliasId;
    }
}