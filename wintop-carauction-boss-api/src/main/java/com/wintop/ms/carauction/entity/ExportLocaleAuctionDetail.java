package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class ExportLocaleAuctionDetail implements Serializable {
    /**
     * 店铺名称
     */
    private String storeName;

    /**
     * 参与竞拍车辆
     */
    private Integer auctionNum;

    /**
     * 竞拍成功车辆
     */
    private Integer sucessNum;

    /**
     *
     */
    private String maxRate;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getAuctionNum() {
        return auctionNum;
    }

    public void setAuctionNum(Integer auctionNum) {
        this.auctionNum = auctionNum;
    }

    public Integer getSucessNum() {
        return sucessNum;
    }

    public void setSucessNum(Integer sucessNum) {
        this.sucessNum = sucessNum;
    }

    public String getMaxRate() {
        return maxRate;
    }

    public void setMaxRate(String maxRate) {
        this.maxRate = maxRate;
    }
}
