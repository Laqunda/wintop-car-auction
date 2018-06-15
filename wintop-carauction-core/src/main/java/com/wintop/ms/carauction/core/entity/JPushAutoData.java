package com.wintop.ms.carauction.core.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/***
 * jpush推送车辆 动态信息使用
 */
public class JPushAutoData implements Serializable {

    private BigDecimal maxPrice;//当前最高价
    private BigDecimal startingPrice;//起拍价
    private String auctionStartTime;//开拍时间
    private String auctionEndTime;//竞拍结束时间
    private String serverTime;//服务器当前时间
    private String status;//车辆状态
    private Long autoId;//车辆id
    private Long maxPriceUserId;//最高出价人

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getAuctionEndTime() {
        return auctionEndTime;
    }

    public void setAuctionEndTime(String auctionEndTime) {
        this.auctionEndTime = auctionEndTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getAutoId() {
        return autoId;
    }

    public void setAutoId(Long autoId) {
        this.autoId = autoId;
    }

    public BigDecimal getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(BigDecimal startingPrice) {
        this.startingPrice = startingPrice;
    }

    public String getAuctionStartTime() {
        return auctionStartTime;
    }

    public void setAuctionStartTime(String auctionStartTime) {
        this.auctionStartTime = auctionStartTime;
    }

    public String getServerTime() {
        return String.valueOf(new Date().getTime());
    }

    public void setServerTime(String serverTime) {
        this.serverTime = serverTime;
    }

    public Long getMaxPriceUserId() {
        return maxPriceUserId;
    }

    public void setMaxPriceUserId(Long maxPriceUserId) {
        this.maxPriceUserId = maxPriceUserId;
    }
}
