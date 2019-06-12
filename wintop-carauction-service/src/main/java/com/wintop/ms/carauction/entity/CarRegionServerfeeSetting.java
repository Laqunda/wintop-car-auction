package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class CarRegionServerfeeSetting implements Serializable {

    private static final long serialVersionUID = 4270437432803914572L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 拍卖地区配置ID
     */
    private Long regionSettingId;

    /**
     * 成交价
     */
    private BigDecimal startClosingPrice;

    /**
     * 成交价
     */
    private BigDecimal endClosingPrice;

    /**
     * 服务费
     */
    private BigDecimal serviceFee;

    //***服务费展示的文本
    private String showText;

    private Long centerId;

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
     * @return 拍卖地区配置ID
     */
    public Long getRegionSettingId() {
        return regionSettingId;
    }

    /**
     * @param regionSettingId 
	 *            拍卖地区配置ID
     */
    public void setRegionSettingId(Long regionSettingId) {
        this.regionSettingId = regionSettingId;
    }

    /**
     * @return 成交价
     */
    public BigDecimal getStartClosingPrice() {
        return startClosingPrice;
    }

    /**
     * @param startClosingPrice 
	 *            成交价
     */
    public void setStartClosingPrice(BigDecimal startClosingPrice) {
        this.startClosingPrice = startClosingPrice;
    }

    /**
     * @return 成交价
     */
    public BigDecimal getEndClosingPrice() {
        return endClosingPrice;
    }

    /**
     * @param endClosingPrice 
	 *            成交价
     */
    public void setEndClosingPrice(BigDecimal endClosingPrice) {
        this.endClosingPrice = endClosingPrice;
    }

    /**
     * @return 服务费
     */
    public BigDecimal getServiceFee() {
        return serviceFee;
    }

    /**
     * @param serviceFee 
	 *            服务费
     */
    public void setServiceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
    }

    public String getShowText() {
        return showText;
    }

    public void setShowText(String showText) {
        this.showText = showText;
    }

    public Long getCenterId() {
        return centerId;
    }

    public void setCenterId(Long centerId) {
        this.centerId = centerId;
    }
}