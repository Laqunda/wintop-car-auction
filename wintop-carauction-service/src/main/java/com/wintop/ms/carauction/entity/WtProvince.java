package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.Date;

/***
 * 省直辖市
 */
public class WtProvince implements Serializable{

    private Long provinceId;
    private String provinceName;
    private Long provinceSort;
    private Date createTime;
    private Date modifyTime;
    private String delFlag;
    private String shortName;

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Long getProvinceSort() {
        return provinceSort;
    }

    public void setProvinceSort(Long provinceSort) {
        this.provinceSort = provinceSort;
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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
