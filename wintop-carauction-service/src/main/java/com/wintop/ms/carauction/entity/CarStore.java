package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.Date;

public class CarStore implements Serializable {

    private static final long serialVersionUID = 7005745752235872281L;
    /**
     * id
     */
    private Long id;

    /**
     * 4s店编号
     */
    private String code;

    /**
     * 4s店名称
     */
    private String name;

    /**
     * 4s店简称
     */
    private String simpleName;

    /**
     * 4s店照片
     */
    private String storePhoto;

    /**
     * 地址
     */
    private String address;

    /**
     * 服务电话
     */
    private String serviceTel;

    /**
     * 坐标维度
     */
    private Double latitude;

    /**
     * 坐标经度
     */
    private Double longitude;

    private String status;

    /**
     * 创建时间
     */
    private Date createTime;
    private Long createPerson;

    /**
     * 修改时间
     */
    private Date updateTime;
    private Long updatePerson;

    /**
     * 删除时间
     */
    private Date delTime;

    /**
     * 删除标识 0:未删除 1:已删除
     */
    private Long delPerson;

    private String remark;

    private Long regionId;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id 
	 *            id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 4s店编号
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code 
	 *            4s店编号
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return 4s店名称
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
	 *            4s店名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return 4s店简称
     */
    public String getSimpleName() {
        return simpleName;
    }

    /**
     * @param simpleName 
	 *            4s店简称
     */
    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    /**
     * @return 4s店照片
     */
    public String getStorePhoto() {
        return storePhoto;
    }

    /**
     * @param storePhoto 
	 *            4s店照片
     */
    public void setStorePhoto(String storePhoto) {
        this.storePhoto = storePhoto;
    }

    /**
     * @return 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address 
	 *            地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return 服务电话
     */
    public String getServiceTel() {
        return serviceTel;
    }

    /**
     * @param serviceTel 
	 *            服务电话
     */
    public void setServiceTel(String serviceTel) {
        this.serviceTel = serviceTel;
    }

    /**
     * @return 坐标维度
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude
	 *            坐标维度
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return 坐标经度
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude
	 *            坐标经度
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * @return 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime 
	 *            创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime 
	 *            修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return 删除时间
     */
    public Date getDelTime() {
        return delTime;
    }

    /**
     * @param delTime 
	 *            删除时间
     */
    public void setDelTime(Date delTime) {
        this.delTime = delTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(Long createPerson) {
        this.createPerson = createPerson;
    }

    public Long getUpdatePerson() {
        return updatePerson;
    }

    public void setUpdatePerson(Long updatePerson) {
        this.updatePerson = updatePerson;
    }

    public Long getDelPerson() {
        return delPerson;
    }

    public void setDelPerson(Long delPerson) {
        this.delPerson = delPerson;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }
}