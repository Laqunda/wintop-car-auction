package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CarAgentCompany implements Serializable {

    private static final long serialVersionUID = -452306330974464762L;
    /**
     * 主键
     */
    private Long id;

    private Integer companyCode;

    /**
     * 代办公司名称
     */
    private String companyName;

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
     * 状态：1启用，2停用，3删除
     */
    private String status;

    /**
     * 代办公司地址
     */
    private String address;

    private Long handlerId;

    /**
     * 负责人名称
     */
    private String handlerName;

    private String handlerTel;

    /**
     * 备注
     */
    private String remark;

    /**
     * 修改人
     */
    private Long delPerson;

    /**
     * 修改时间
     */
    private Date delTime;

    private String storeIds;

    private List<CommonNameVo> storeList;

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

    public Integer getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(Integer companyCode) {
        this.companyCode = companyCode;
    }

    /**
     * @return 代办公司名称
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName
     *            代办公司名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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
     * @return 代办公司地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     *            代办公司地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return 负责人名称
     */
    public String getHandlerName() {
        return handlerName;
    }

    /**
     * @param handlerName
     *            负责人名称
     */
    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }

    public Long getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(Long handlerId) {
        this.handlerId = handlerId;
    }

    public String getHandlerTel() {
        return handlerTel;
    }

    public void setHandlerTel(String handlerTel) {
        this.handlerTel = handlerTel;
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
    public void setRemark(String remark) {
        this.remark = remark;
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
}