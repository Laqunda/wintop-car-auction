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
     * ɾ��״̬ 0δɾ��  1��ɾ��
     */
    private Integer delFlag;

    /**
     * �Ƿ��ֲ���0�� ���ֲ�  ��1���ǲ��ֲ�
     */
    private String isLoopPlay;

    /**
     * @return ����Id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id 
	 *            ����Id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return ��ϵ����
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 
	 *            ��ϵ����
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return ����ID
     */
    public String getJyid() {
        return jyid;
    }

    /**
     * @param jyid 
	 *            ����ID
     */
    public void setJyid(String jyid) {
        this.jyid = jyid;
    }

    /**
     * @return Ʒ��ID
     */
    public Long getBrandId() {
        return brandId;
    }

    /**
     * @param brandId 
	 *            Ʒ��ID
     */
    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    /**
     * @return Ʒ������
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * @param brandName 
	 *            Ʒ������
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    /**
     * @return Ʒ�Ƴ���ID
     */
    public Long getBrandMakeId() {
        return brandMakeId;
    }

    /**
     * @param brandMakeId 
	 *            Ʒ�Ƴ���ID
     */
    public void setBrandMakeId(Long brandMakeId) {
        this.brandMakeId = brandMakeId;
    }

    /**
     * @return Ʒ�Ƴ���
     */
    public String getBrandMakeName() {
        return brandMakeName;
    }

    /**
     * @param brandMakeName 
	 *            Ʒ�Ƴ���
     */
    public void setBrandMakeName(String brandMakeName) {
        this.brandMakeName = brandMakeName;
    }

    /**
     * @return ����
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * @param sort 
	 *            ����
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * @return ��ϵͼƬ
     */
    public String getFamilyPhoto() {
        return familyPhoto;
    }

    /**
     * @param familyPhoto 
	 *            ��ϵͼƬ
     */
    public void setFamilyPhoto(String familyPhoto) {
        this.familyPhoto = familyPhoto;
    }

    /**
     * @return ����ʱ��
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime 
	 *            ����ʱ��
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return ����ʱ��
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime 
	 *            ����ʱ��
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return ɾ��ʱ��
     */
    public Date getDelTime() {
        return delTime;
    }

    /**
     * @param delTime 
	 *            ɾ��ʱ��
     */
    public void setDelTime(Date delTime) {
        this.delTime = delTime;
    }

    /**
     * @return ɾ��״̬ 0δɾ��  1��ɾ��
     */
    public Integer getDelFlag() {
        return delFlag;
    }

    /**
     * @param delFlag 
	 *            ɾ��״̬ 0δɾ��  1��ɾ��
     */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * @return �Ƿ��ֲ���0�� ���ֲ�  ��1���ǲ��ֲ�
     */
    public String getIsLoopPlay() {
        return isLoopPlay;
    }

    /**
     * @param isLoopPlay 
	 *            �Ƿ��ֲ���0�� ���ֲ�  ��1���ǲ��ֲ�
     */
    public void setIsLoopPlay(String isLoopPlay) {
        this.isLoopPlay = isLoopPlay;
    }
}