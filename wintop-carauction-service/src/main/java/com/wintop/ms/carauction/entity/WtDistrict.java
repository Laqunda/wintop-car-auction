package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.Date;

/***
 * 区县
 */
public class WtDistrict implements Serializable{
    private Long districtId;
    private String districtName;
    private Long districtSort;
    private Long cityId;
    private Date createTime;
    private Date modifyTime;
    private String delFlag;//0可用

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public Long getDistrictSort() {
        return districtSort;
    }

    public void setDistrictSort(Long districtSort) {
        this.districtSort = districtSort;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
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
