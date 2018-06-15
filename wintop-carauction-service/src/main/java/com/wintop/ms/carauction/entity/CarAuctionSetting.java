package com.wintop.ms.carauction.entity;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CarAuctionSetting implements Serializable {

    private static final long serialVersionUID = 2401892357268831219L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 拍卖持续时间，单位秒
     */
    private Integer keepTime;

    /**
     * 保证金金额
     */
    private Long deposit;

    /**
     * 再次延长的拍卖时长
     */
    private Integer endKeepTime;

    /**
     * 延时加价时间
     */
    private Integer delayedTime;

    private BigDecimal serverFee;

    private BigDecimal agentFee;

    private Long regionId;

    private String regionName;

    //**是否启用：1正常，2不启用
    private String status;

    /**
     * 编辑时间
     */
    private Date createTime;

    /**
     * 操作人
     */
    private Long createPerson;

    /**
     * 编辑时间
     */
    private Date updateTime;

    /**
     * 操作人
     */
    private Long updatePerson;

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
     * @return 拍卖持续时间，单位秒
     */
    public Integer getKeepTime() {
        return keepTime;
    }

    /**
     * @param keepTime
     *            拍卖持续时间，单位秒
     */
    public void setKeepTime(Integer keepTime) {
        this.keepTime = keepTime;
    }

    /**
     * @return 保证金金额
     */
    public Long getDeposit() {
        return deposit;
    }

    /**
     * @param deposit
     *            保证金金额
     */
    public void setDeposit(Long deposit) {
        this.deposit = deposit;
    }

    /**
     * @return 再次延长的拍卖时长
     */
    public Integer getEndKeepTime() {
        return endKeepTime;
    }

    /**
     * @param endKeepTime
     *            再次延长的拍卖时长
     */
    public void setEndKeepTime(Integer endKeepTime) {
        this.endKeepTime = endKeepTime;
    }

    /**
     * @return 延时加价时间
     */
    public Integer getDelayedTime() {
        return delayedTime;
    }

    /**
     * @param delayedTime
     *            延时加价时间
     */
    public void setDelayedTime(Integer delayedTime) {
        this.delayedTime = delayedTime;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
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

    public BigDecimal getServerFee() {
        return serverFee;
    }

    public void setServerFee(BigDecimal serverFee) {
        this.serverFee = serverFee;
    }

    public BigDecimal getAgentFee() {
        return agentFee;
    }

    public void setAgentFee(BigDecimal agentFee) {
        this.agentFee = agentFee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

}