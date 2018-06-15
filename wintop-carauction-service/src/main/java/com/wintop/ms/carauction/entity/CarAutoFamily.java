package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.Date;

public class CarAutoFamily implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
    private Long id;

    /**
     * 车系名称
     */
    private String name;

    /**
     * 精友ID
     */
    private String jyid;

    /**
     * 品牌ID
     */
    private Long brandId;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 品牌厂家ID
     */
    private Long brandMakeId;

    /**
     * 品牌厂家
     */
    private String brandMakeName;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 车系图片
     */
    private String familyPhoto;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 删除时间
     */
    private Date delTime;

    /**
     * 删除状态 0未删除  1已删除
     */
    private Integer delFlag;

    /**
     * 是否轮播‘0’ 是轮播  ‘1’是不轮播
     */
    private String isLoopPlay;

    /**
     * @return 主键Id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id 
	 *            主键Id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 车系名称
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 
	 *            车系名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return 精友ID
     */
    public String getJyid() {
        return jyid;
    }

    /**
     * @param jyid 
	 *            精友ID
     */
    public void setJyid(String jyid) {
        this.jyid = jyid;
    }

    /**
     * @return 品牌ID
     */
    public Long getBrandId() {
        return brandId;
    }

    /**
     * @param brandId 
	 *            品牌ID
     */
    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    /**
     * @return 品牌名称
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * @param brandName 
	 *            品牌名称
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    /**
     * @return 品牌厂家ID
     */
    public Long getBrandMakeId() {
        return brandMakeId;
    }

    /**
     * @param brandMakeId 
	 *            品牌厂家ID
     */
    public void setBrandMakeId(Long brandMakeId) {
        this.brandMakeId = brandMakeId;
    }

    /**
     * @return 品牌厂家
     */
    public String getBrandMakeName() {
        return brandMakeName;
    }

    /**
     * @param brandMakeName 
	 *            品牌厂家
     */
    public void setBrandMakeName(String brandMakeName) {
        this.brandMakeName = brandMakeName;
    }

    /**
     * @return 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * @param sort 
	 *            排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * @return 车系图片
     */
    public String getFamilyPhoto() {
        return familyPhoto;
    }

    /**
     * @param familyPhoto 
	 *            车系图片
     */
    public void setFamilyPhoto(String familyPhoto) {
        this.familyPhoto = familyPhoto;
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
     * @return 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime 
	 *            更新时间
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

    /**
     * @return 删除状态 0未删除  1已删除
     */
    public Integer getDelFlag() {
        return delFlag;
    }

    /**
     * @param delFlag 
	 *            删除状态 0未删除  1已删除
     */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * @return 是否轮播‘0’ 是轮播  ‘1’是不轮播
     */
    public String getIsLoopPlay() {
        return isLoopPlay;
    }

    /**
     * @param isLoopPlay 
	 *            是否轮播‘0’ 是轮播  ‘1’是不轮播
     */
    public void setIsLoopPlay(String isLoopPlay) {
        this.isLoopPlay = isLoopPlay;
    }
}