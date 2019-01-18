package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ElectronAuctionDetail implements Serializable {

    private static final long serialVersionUID = -1059826664686748094L;

    private Long localeAuctionId;
    private Long auctionCarId;
    //0没有拍卖车辆，1有正在竞拍车辆
    private String synch;
    private ElectronAuctionCar auctionCar;
    private List<ElectronAuctionBidRecord> bidRecords;
    private BigDecimal priceRange;
    private BigDecimal bidFee;

    public Long getLocaleAuctionId() {
        return localeAuctionId;
    }

    public void setLocaleAuctionId(Long localeAuctionId) {
        this.localeAuctionId = localeAuctionId;
    }

    public Long getAuctionCarId() {
        return auctionCarId;
    }

    public void setAuctionCarId(Long auctionCarId) {
        this.auctionCarId = auctionCarId;
    }

    public String getSynch() {
        return synch;
    }

    public void setSynch(String synch) {
        this.synch = synch;
    }

    public ElectronAuctionCar getAuctionCar() {
        return auctionCar;
    }

    public void setAuctionCar(ElectronAuctionCar auctionCar) {
        this.auctionCar = auctionCar;
    }

    public List<ElectronAuctionBidRecord> getBidRecords() {
        return bidRecords;
    }

    public void setBidRecords(List<ElectronAuctionBidRecord> bidRecords) {
        this.bidRecords = bidRecords;
    }

    public BigDecimal getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(BigDecimal priceRange) {
        this.priceRange = priceRange;
    }

    public BigDecimal getBidFee() {
        return bidFee;
    }

    public void setBidFee(BigDecimal bidFee) {
        this.bidFee = bidFee;
    }

}
