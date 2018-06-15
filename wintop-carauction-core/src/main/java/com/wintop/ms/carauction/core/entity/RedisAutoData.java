package com.wintop.ms.carauction.core.entity;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.Constants;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Date;

/***
 * redis存储车辆信息
 */
public class RedisAutoData {
    /***
     * 车辆id
     */
    private Long autoId;

    /**
     * 当前场次ID
     */
    private Long autoAuctionId;

    private Long regionId;

    /***
     * 车辆状态
     */
    private String status;

    /***
     * 开拍时间
     */
    private long auctionStartTime;

    /***
     * 开拍结束时间
     */
    private long auctionEndTime;

    /***
     * 最高出价
     */
    private BigDecimal maxPrice;

    /***
     * 保留价
     */
    private BigDecimal reservePrice;

    /***
     * 起拍价
     */
    private BigDecimal startingPrice;

    /***
     * 当前最高委托价
     */
    private BigDecimal entrustPrice;

    /***
     * 当前最高委托价人
     */
    private Long entrustPriceUserId;

    /***
     * 当前最高出价人
     */
    private Long maxPriceUserId;

    private String sourceType;

    //***同步数据库是否成功，0失败，1成功
    private String success;

    /**
     * 用车辆id作为reids key
     * @return
     */
    public String redisKey(){
        return Constants.CAR_AUTO_AUCTION+"_"+this.autoId;
    }

    /***
     * 将自己转为json返回
     * @return
     */
    public String toJsonStr(){
        return JSONObject.toJSONString(this);
    }


    public Long getAutoId() {
        return autoId;
    }

    public void setAutoId(Long autoId) {
        this.autoId = autoId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getAuctionStartTime() {
        return auctionStartTime;
    }

    public void setAuctionStartTime(long auctionStartTime) {
        this.auctionStartTime = auctionStartTime;
    }

    public long getAuctionEndTime() {
        return auctionEndTime;
    }

    public void setAuctionEndTime(long auctionEndTime) {
        this.auctionEndTime = auctionEndTime;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public BigDecimal getReservePrice() {
        return reservePrice;
    }

    public void setReservePrice(BigDecimal reservePrice) {
        this.reservePrice = reservePrice;
    }

    public BigDecimal getEntrustPrice() {
        return entrustPrice;
    }

    public void setEntrustPrice(BigDecimal entrustPrice) {
        this.entrustPrice = entrustPrice;
    }

    public Long getMaxPriceUserId() {
        return maxPriceUserId;
    }

    public void setMaxPriceUserId(Long maxPriceUserId) {
        this.maxPriceUserId = maxPriceUserId;
    }

    public BigDecimal getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(BigDecimal startingPrice) {
        this.startingPrice = startingPrice;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public Long getEntrustPriceUserId() {
        return entrustPriceUserId;
    }

    public void setEntrustPriceUserId(Long entrustPriceUserId) {
        this.entrustPriceUserId = entrustPriceUserId;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public Long getAutoAuctionId() {
        return autoAuctionId;
    }

    public void setAutoAuctionId(Long autoAuctionId) {
        this.autoAuctionId = autoAuctionId;
    }

    public String getSuccess() {
        if(StringUtils.isBlank(success)){
            return "1";
        }
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
