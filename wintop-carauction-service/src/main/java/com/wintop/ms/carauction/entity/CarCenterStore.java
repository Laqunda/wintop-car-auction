package com.wintop.ms.carauction.entity;

import java.io.Serializable;

public class CarCenterStore implements Serializable {

    private static final long serialVersionUID = 1397516687082758855L;
    private Long id;

    /**
     * 二手车中心ID
     */
    private Long centerId;

    /**
     * 4S店id
     */
    private Long storeId;

    private String storeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 二手车中心ID
     */
    public Long getCenterId() {
        return centerId;
    }

    /**
     * @param centerId 
	 *            二手车中心ID
     */
    public void setCenterId(Long centerId) {
        this.centerId = centerId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}