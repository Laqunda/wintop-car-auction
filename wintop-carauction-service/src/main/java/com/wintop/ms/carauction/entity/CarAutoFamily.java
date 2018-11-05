package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.Date;

public class CarAutoFamily implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ����Id
     */
    private Long id;

    /**
     * ��ϵ����
     */
    private String name;

    /**
     * ����ID
     */
    private String jyid;

    /**
     * Ʒ��ID
     */
    private Long brandId;

    /**
     * Ʒ������
     */
    private String brandName;

    /**
     * Ʒ�Ƴ���ID
     */
    private Long brandMakeId;

    /**
     * Ʒ�Ƴ���
     */
    private String brandMakeName;

    /**
     * ����
     */
    private Integer sort;

    /**
     * ��ϵͼƬ
     */
    private String familyPhoto;

    /**
     * ����ʱ��
     */
    private Date createTime;

    /**
     * ����ʱ��
     */
    private Date updateTime;

    /**
     * ɾ��ʱ��
     */
    private Date delTime;

    /**
     */
    private Integer delFlag;

    /**
     */
    private String isLoopPlay;

    /**
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     */
    public String getJyid() {
        return jyid;
    }

    /**
     * @param jyid 
     */
    public void setJyid(String jyid) {
        this.jyid = jyid;
    }

    /**
     */
    public Long getBrandId() {
        return brandId;
    }

    /**
     * @param brandId 
     */
    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    /**
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * @param brandName 
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    /**
     */
    public Long getBrandMakeId() {
        return brandMakeId;
    }

    /**
     * @param brandMakeId 
     */
    public void setBrandMakeId(Long brandMakeId) {
        this.brandMakeId = brandMakeId;
    }

    /**
     */
    public String getBrandMakeName() {
        return brandMakeName;
    }

    /**
     * @param brandMakeName 
     */
    public void setBrandMakeName(String brandMakeName) {
        this.brandMakeName = brandMakeName;
    }

    /**
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * @param sort 
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     */
    public String getFamilyPhoto() {
        return familyPhoto;
    }

    /**
     * @param familyPhoto 
     */
    public void setFamilyPhoto(String familyPhoto) {
        this.familyPhoto = familyPhoto;
    }

    /**
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime 
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime 
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     */
    public Date getDelTime() {
        return delTime;
    }

    /**
     * @param delTime 
     */
    public void setDelTime(Date delTime) {
        this.delTime = delTime;
    }

    /**
     */
    public Integer getDelFlag() {
        return delFlag;
    }

    /**
     * @param delFlag 
     */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    /**
     */
    public String getIsLoopPlay() {
        return isLoopPlay;
    }

    /**
     * @param isLoopPlay 
     */
    public void setIsLoopPlay(String isLoopPlay) {
        this.isLoopPlay = isLoopPlay;
    }
}