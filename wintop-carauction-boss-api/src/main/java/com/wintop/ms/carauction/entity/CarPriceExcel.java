package com.wintop.ms.carauction.entity;

import java.io.Serializable;

/**
 * @author zhangzijuan
 * @Description:
 * @date 2018-04-03
 */
public class CarPriceExcel implements Serializable {
    private  String code;

    private  String licenseNumber;

    private  String storeName;

    private String startPrice;

    private String reservePrice;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(String startPrice) {
        this.startPrice = startPrice;
    }

    public String getReservePrice() {
        return reservePrice;
    }

    public void setReservePrice(String reservePrice) {
        this.reservePrice = reservePrice;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
