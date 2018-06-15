package com.wintop.ms.carauction.entity;

import java.io.Serializable;

public class CarAgentCompanyStore implements Serializable {

    private static final long serialVersionUID = 1944837870685094522L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 代办公司ID
     */
    private Long companyId;

    /**
     * 4S店ID
     */
    private Long storeId;

    private String storeName;

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
     * @return 代办公司ID
     */
    public Long getCompanyId() {
        return companyId;
    }

    /**
     * @param companyId 
	 *            代办公司ID
     */
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
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