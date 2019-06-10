package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * CarCustomerLevel:客户级别实体
 * @author zhangzijuan
 * @date 2018-02-26
 */
public class CarCustomerLevel implements Serializable {
    private static final long serialVersionUID = -1450726987580976803L;
    /**
     * 分组id
     */
    private Long id;

    /**
     * 客户等级名称
     */
    private String levelName;

    /**
     * 用户等级编号
     */
    private String code;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private Long createManager;
    /**
     * 排序
     */
    private Integer sort;

    private String isOpen;

    private String isDefault;

    private String remark;

    private String fareIds;

    private String farePrice;

    private String createPersonName;

    public String getCreatePersonName() {
        return createPersonName;
    }

    public void setCreatePersonName(String createPersonName) {
        this.createPersonName = createPersonName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFareIds() {
        return fareIds;
    }

    public void setFareIds(String fareIds) {
        this.fareIds = fareIds;
    }

    public String getFarePrice() {
        return farePrice;
    }

    public void setFarePrice(String farePrice) {
        this.farePrice = farePrice;
    }

    /**
     * 该等级所需缴纳保证金金额
     */
    private BigDecimal depositMoney;

    /**
     * @return 分组id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id 
	 *            分组id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 客户等级名称
     */
    public String getLevelName() {
        return levelName;
    }

    /**
     * @param levelName 
	 *            客户等级名称
     */
    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    /**
     * @return 用户等级编号
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code 
	 *            用户等级编号
     */
    public void setCode(String code) {
        this.code = code;
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

    public Long getCreateManager() {
        return createManager;
    }

    public void setCreateManager(Long createManager) {
        this.createManager = createManager;
    }

    /**
     * @return 该等级所需缴纳保证金金额
     */
    public BigDecimal getDepositMoney() {
        return depositMoney;
    }

    /**
     * @param depositMoney 
	 *            该等级所需缴纳保证金金额
     */
    public void setDepositMoney(BigDecimal depositMoney) {
        this.depositMoney = depositMoney;
    }
}