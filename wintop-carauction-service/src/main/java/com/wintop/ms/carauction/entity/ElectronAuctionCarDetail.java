package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ElectronAuctionCarDetail implements Serializable {

    private static final long serialVersionUID = 3800964742813924494L;
    private Long localeAuctionId;
    private Long auctionCarId;
    private ElectronAuctionCar auctionCar;
    //,,出价记录
    private ListEntity<ElectronAuctionBidRecord> bidRecords;
    //,,加价记录
    private ListEntity<ElectronAuctionBidRecord> addRecords;
    //,,价格幅度调整记录
    private ListEntity<ElectronAuctionBidRecord> rangeRecords;

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

    public ElectronAuctionCar getAuctionCar() {
        return auctionCar;
    }

    public void setAuctionCar(ElectronAuctionCar auctionCar) {
        this.auctionCar = auctionCar;
    }

    public ListEntity<ElectronAuctionBidRecord> getBidRecords() {
        return bidRecords;
    }

    public void setBidRecords(ListEntity<ElectronAuctionBidRecord> bidRecords) {
        this.bidRecords = bidRecords;
    }

    public ListEntity<ElectronAuctionBidRecord> getAddRecords() {
        return addRecords;
    }

    public void setAddRecords(ListEntity<ElectronAuctionBidRecord> addRecords) {
        this.addRecords = addRecords;
    }

    public ListEntity<ElectronAuctionBidRecord> getRangeRecords() {
        return rangeRecords;
    }

    public void setRangeRecords(ListEntity<ElectronAuctionBidRecord> rangeRecords) {
        this.rangeRecords = rangeRecords;
    }
}
