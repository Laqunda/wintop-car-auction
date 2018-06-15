package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CarCenter implements Serializable {

    private static final long serialVersionUID = 5283224098794698344L;
    /**
     * 二手车中心ID
     */
    private Long id;

    /**
     * 二手车中心编号
     */
    private Integer centerCode;

    /**
     * 二手车中心名称
     */
    private String centerName;

    /**
     * 二手车中心地址
     */
    private String address;

    /**
     * 联系电话
     */
    private String centerTel;

    /**
     * 状态：1启用，2停用，3删除
     */
    private String status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建人
     */
    private Long createPerson;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private Long updatePerson;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人
     */
    private Long delPerson;

    /**
     * 修改时间
     */
    private Date delTime;

    private Long regionId;

    private String storeIds;

    private List<CommonNameVo> storeList;

    /**
     * @return 二手车中心ID
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id 
	 *            二手车中心ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 二手车中心编号
     */
    public Integer getCenterCode() {
        return centerCode;
    }

    /**
     * @param centerCode 
	 *            二手车中心编号
     */
    public void setCenterCode(Integer centerCode) {
        this.centerCode = centerCode;
    }

    /**
     * @return 二手车中心名称
     */
    public String getCenterName() {
        return centerName;
    }

    /**
     * @param centerName 
	 *            二手车中心名称
     */
    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    /**
     * @return 二手车中心地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address 
	 *            二手车中心地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return 联系电话
     */
    public String getCenterTel() {
        return centerTel;
    }

    /**
     * @param centerTel 
	 *            联系电话
     */
    public void setCenterTel(String centerTel) {
        this.centerTel = centerTel;
    }

    /**
     * @return 状态：1启用，2停用，3删除
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            状态：1启用，2停用，3删除
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
	 *            备注
     */
    public void setRamark(String remark) {
        this.remark = remark;
    }

    /**
     * @return 创建人
     */
    public Long getCreatePerson() {
        return createPerson;
    }

    /**
     * @param createPerson
	 *            创建人
     */
    public void setCreatePerson(Long createPerson) {
        this.createPerson = createPerson;
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
     * @return 修改人
     */
    public Long getUpdatePerson() {
        return updatePerson;
    }

    /**
     * @param updatePerson
	 *            修改人
     */
    public void setUpdatePerson(Long updatePerson) {
        this.updatePerson = updatePerson;
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
     * @return 修改人
     */
    public Long getDelPerson() {
        return delPerson;
    }

    /**
     * @param delPerson
	 *            修改人
     */
    public void setDelPerson(Long delPerson) {
        this.delPerson = delPerson;
    }

    /**
     * @return 修改时间
     */
    public Date getDelTime() {
        return delTime;
    }

    /**
     * @param delTime 
	 *            修改时间
     */
    public void setDelTime(Date delTime) {
        this.delTime = delTime;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStoreIds() {
        return storeIds;
    }

    public void setStoreIds(String storeIds) {
        this.storeIds = storeIds;
    }

    public List<CommonNameVo> getStoreList() {
        return storeList;
    }

    public void setStoreList(List<CommonNameVo> storeList) {
        this.storeList = storeList;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }
}