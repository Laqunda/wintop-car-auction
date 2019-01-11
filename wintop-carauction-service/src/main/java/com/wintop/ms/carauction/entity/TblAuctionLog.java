package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TblAuctionLog implements Serializable {

    private static final long serialVersionUID = 7596073212443810035L;
    private Long id;

    /**
     * 基站物理ID(十六进制)
     */
    private String stationRealCode;

    /**
     * 拍牌物理ID(十六进制)
     */
    private String boardRealId;

    /**
     * 基站口令
     */
    private String token;

    /**
     * 拍卖场次id
     */
    private Long auctionTimesId;

    /**
     * 拍卖场次
     */
    private String auctionTimesName;

    /**
     * 基站ID(冗余字段)(基站主键)
     */
    private Long bsId;

    /**
     * 拍牌显示名字(冗余字段)
     */
    private String boardRealName;

    /**
     * 竞拍时间(精确到毫秒)
     */
    private Date auctionTime;

    private Long localAuctionId;

    private Long auctionCarId;

    private Long carId;

    private Long customerId;

    private BigDecimal bidFee;

    /**
     * 是否时间切割拍牌（一个拍卖场只能有一个（0：正常拍牌；1：时间切割拍牌））
     */
    private String cuttingSign;

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
     * @return 拍牌物理ID(十六进制)
     */
    public String getBoardRealId() {
        return boardRealId;
    }

    /**
     * @param boardRealId 
	 *            拍牌物理ID(十六进制)
     */
    public void setBoardRealId(String boardRealId) {
        this.boardRealId = boardRealId;
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
     * @return 拍卖场次id
     */
    public Long getAuctionTimesId() {
        return auctionTimesId;
    }

    /**
     * @param auctionTimesId 
	 *            拍卖场次id
     */
    public void setAuctionTimesId(Long auctionTimesId) {
        this.auctionTimesId = auctionTimesId;
    }

    public String getAuctionTimesName() {
        return auctionTimesName;
    }

    public void setAuctionTimesName(String auctionTimesName) {
        this.auctionTimesName = auctionTimesName;
    }

    /**
     * @return 基站ID(冗余字段)(基站主键)
     */
    public Long getBsId() {
        return bsId;
    }

    /**
     * @param bsId 
	 *            基站ID(冗余字段)(基站主键)
     */
    public void setBsId(Long bsId) {
        this.bsId = bsId;
    }

    /**
     * @return 拍牌显示名字(冗余字段)
     */
    public String getBoardRealName() {
        return boardRealName;
    }

    /**
     * @param boardRealName 
	 *            拍牌显示名字(冗余字段)
     */
    public void setBoardRealName(String boardRealName) {
        this.boardRealName = boardRealName;
    }

    /**
     * @return 竞拍时间(精确到毫秒)
     */
    public Date getAuctionTime() {
        return auctionTime;
    }

    /**
     * @param auctionTime 
	 *            竞拍时间(精确到毫秒)
     */
    public void setAuctionTime(Date auctionTime) {
        this.auctionTime = auctionTime;
    }

    public Long getLocalAuctionId() {
        return localAuctionId;
    }

    public void setLocalAuctionId(Long localAuctionId) {
        this.localAuctionId = localAuctionId;
    }

    public Long getAuctionCarId() {
        return auctionCarId;
    }

    public void setAuctionCarId(Long auctionCarId) {
        this.auctionCarId = auctionCarId;
    }

    public BigDecimal getBidFee() {
        return bidFee;
    }

    public void setBidFee(BigDecimal bidFee) {
        this.bidFee = bidFee;
    }

    public String getCuttingSign() {
        return cuttingSign;
    }

    public void setCuttingSign(String cuttingSign) {
        this.cuttingSign = cuttingSign;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}