package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.Date;

public class CarAuctionCity implements Serializable {

    private static final long serialVersionUID = -8128702275564622071L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 拍卖地区名称
     */
    private String name;

    /**
     * 冻结资金
     */
    private Long frozenCapital;

    /**
     * 服务费
     */
    private Long serviceFee;

    /**
     * 违约天数
     */
    private Integer breachDay;

    /**
     * 违约时间
     */
    private Date breachTime;

    /**
     * 编辑时间
     */
    private Date editTime;

    /**
     * 操作人
     */
    private String editor;

    /**
     * 是否删除：1正常，-1删除隐藏
     */
    private String ifDel;

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
     * @return 拍卖地区名称
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 
	 *            拍卖地区名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return 冻结资金
     */
    public Long getFrozenCapital() {
        return frozenCapital;
    }

    /**
     * @param frozenCapital 
	 *            冻结资金
     */
    public void setFrozenCapital(Long frozenCapital) {
        this.frozenCapital = frozenCapital;
    }

    /**
     * @return 服务费
     */
    public Long getServiceFee() {
        return serviceFee;
    }

    /**
     * @param serviceFee 
	 *            服务费
     */
    public void setServiceFee(Long serviceFee) {
        this.serviceFee = serviceFee;
    }

    /**
     * @return 违约天数
     */
    public Integer getBreachDay() {
        return breachDay;
    }

    /**
     * @param breachDay 
	 *            违约天数
     */
    public void setBreachDay(Integer breachDay) {
        this.breachDay = breachDay;
    }

    /**
     * @return 违约时间
     */
    public Date getBreachTime() {
        return breachTime;
    }

    /**
     * @param breachTime 
	 *            违约时间
     */
    public void setBreachTime(Date breachTime) {
        this.breachTime = breachTime;
    }

    /**
     * @return 编辑时间
     */
    public Date getEditTime() {
        return editTime;
    }

    /**
     * @param editTime 
	 *            编辑时间
     */
    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    /**
     * @return 操作人
     */
    public String getEditor() {
        return editor;
    }

    /**
     * @param editor 
	 *            操作人
     */
    public void setEditor(String editor) {
        this.editor = editor;
    }

    /**
     * @return 是否删除：1正常，-1删除隐藏
     */
    public String getIfDel() {
        return ifDel;
    }

    /**
     * @param ifDel 
	 *            是否删除：1正常，-1删除隐藏
     */
    public void setIfDel(String ifDel) {
        this.ifDel = ifDel;
    }
}