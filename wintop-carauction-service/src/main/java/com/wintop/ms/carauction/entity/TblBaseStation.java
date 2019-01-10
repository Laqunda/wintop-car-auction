package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.Date;

public class TblBaseStation implements Serializable {

    private static final long serialVersionUID = 3892934216624532474L;
    private Long id;

    /**
     * 基站物理ID(十六进制)
     */
    private String stationRealCode;

    /**
     * 基站代码(系统显示用)
     */
    private String stationCode;

    /**
     * 基站口令
     */
    private String token;

    /**
     * 拍卖场名称
     */
    private String auctionName;

    /**
     * 系统负责人
     */
    private String sysUser;

    /**
     * 负责人联系方式
     */
    private String phone;

    /**
     * 是否启用(0:启用；1:未启用)默认值为0
     */
    private String enable;

    /**
     * 备注
     */
    private String remarks;

    private Long createPerson;

    private Date createTime;

    private Long modifyPerson;

    private Date modifyTime;

    private Long delPerson;

    private Date delTime;
    //,,"1" 未删除  “2” 已删除
    private String delFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 基站物理ID(十六进制)
     */
    public String getStationRealCode() {
        return stationRealCode;
    }

    /**
     * @param stationRealCode 
	 *            基站物理ID(十六进制)
     */
    public void setStationRealCode(String stationRealCode) {
        this.stationRealCode = stationRealCode;
    }

    /**
     * @return 基站代码(系统显示用)
     */
    public String getStationCode() {
        return stationCode;
    }

    /**
     * @param stationCode 
	 *            基站代码(系统显示用)
     */
    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    /**
     * @return 基站口令
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token 
	 *            基站口令
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return 拍卖场名称
     */
    public String getAuctionName() {
        return auctionName;
    }

    /**
     * @param auctionName 
	 *            拍卖场名称
     */
    public void setAuctionName(String auctionName) {
        this.auctionName = auctionName;
    }

    /**
     * @return 系统负责人
     */
    public String getSysUser() {
        return sysUser;
    }

    /**
     * @param sysUser 
	 *            系统负责人
     */
    public void setSysUser(String sysUser) {
        this.sysUser = sysUser;
    }

    /**
     * @return 负责人联系方式
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone 
	 *            负责人联系方式
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return 是否启用(0:启用；1:未启用)默认值为0
     */
    public String getEnable() {
        return enable;
    }

    /**
     * @param enable 
	 *            是否启用(0:启用；1:未启用)默认值为0
     */
    public void setEnable(String enable) {
        this.enable = enable;
    }

    /**
     * @return 备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param remarks 
	 *            备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(Long createPerson) {
        this.createPerson = createPerson;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getModifyPerson() {
        return modifyPerson;
    }

    public void setModifyPerson(Long modifyPerson) {
        this.modifyPerson = modifyPerson;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Long getDelPerson() {
        return delPerson;
    }

    public void setDelPerson(Long delPerson) {
        this.delPerson = delPerson;
    }

    public Date getDelTime() {
        return delTime;
    }

    public void setDelTime(Date delTime) {
        this.delTime = delTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}