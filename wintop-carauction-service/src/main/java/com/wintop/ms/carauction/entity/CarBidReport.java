package com.wintop.ms.carauction.entity;

import java.io.Serializable;

public class CarBidReport implements Serializable {
    private static final long serialVersionUID = 4055575571518497667L;
    /**
     * 会员所属店铺
     */
    private String userStoreName;

    /**
     * 车辆编号
     */
    private String carAutoNo;

    /**
     * 竞拍状态
     */
    private String auctionStatus;

    public String getUserStoreName() {
        return userStoreName;
    }

    public void setUserStoreName(String userStoreName) {
        this.userStoreName = userStoreName;
    }

    public String getCarAutoNo() {
        return carAutoNo;
    }

    public void setCarAutoNo(String carAutoNo) {
        this.carAutoNo = carAutoNo;
    }

    public String getAuctionStatus() {
        return auctionStatus;
    }

    public void setAuctionStatus(String auctionStatus) {
        this.auctionStatus = auctionStatus;
    }
}
