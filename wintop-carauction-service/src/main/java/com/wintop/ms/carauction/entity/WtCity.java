package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.Date;

/***
 * 地区城市
 */
public class WtCity implements Serializable{
    private Long cityId;
    private String cityName;
    private Long citySort;
    private Long provinceId;
    private Date createTime;
    private Date modifyTime;
    private String delFlag;

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Long getCitySort() {
        return citySort;
    }

    public void setCitySort(Long citySort) {
        this.citySort = citySort;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
