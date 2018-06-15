package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.Date;

public class CarAutoDetectionClassOptions implements Serializable {

    private static final long serialVersionUID = 6355405369642176658L;
    /**
     * 分组id
     */
    private Long id;

    /**
     * 检测项类别ID
     */
    private Long classId;

    /**
     * 是否默认：1非默认，2默认
     */
    private String isDefault;

    /**
     * 显示名称
     */
    private String optionsName;

    /**
     * 是否关闭：1开启，2关闭
     */
    private String isClose;

    /**
     * 是否删除：1不，2删除
     */
    private String isDel;

    /**
     * 排序号，大的在前
     */
    private Integer sort;

    /**
     * 扣分大小：默认0
     */
    private Long deductMarks;

    /**
     * 是否必须拍照：1不需要，2需要
     */
    private String ifMustPhoto;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createManager;

    /**
     * 修改人
     */
    private Date editTime;

    /**
     * 修改人
     */
    private String editManager;

    /**
     * 检测类型：外观等
     */
    private String detectionType;

    /**
     * 检测类型名称
     */
    private String detectionTypeCn;

    /**
     * 损坏类型
     */
    private String damagedType;

    /**
     * 损坏类型名称
     */
    private String damagedTypeCn;

    /***
     * 是否被选中
     */
    private String ifChecked;

    /**
     * @return 分组id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id 
	 *            分组id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 检测项类别ID
     */
    public Long getClassId() {
        return classId;
    }

    /**
     * @param classId 
	 *            检测项类别ID
     */
    public void setClassId(Long classId) {
        this.classId = classId;
    }

    /**
     * @return 是否默认：1非默认，2默认
     */
    public String getIsDefault() {
        return isDefault;
    }

    /**
     * @param isDefault 
	 *            是否默认：1非默认，2默认
     */
    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * @return 显示名称
     */
    public String getOptionsName() {
        return optionsName;
    }

    /**
     * @param optionsName 
	 *            显示名称
     */
    public void setOptionsName(String optionsName) {
        this.optionsName = optionsName;
    }

    /**
     * @return 是否关闭：1开启，2关闭
     */
    public String getIsClose() {
        return isClose;
    }

    /**
     * @param isClose 
	 *            是否关闭：1开启，2关闭
     */
    public void setIsClose(String isClose) {
        this.isClose = isClose;
    }

    /**
     * @return 是否删除：1不，2删除
     */
    public String getIsDel() {
        return isDel;
    }

    /**
     * @param isDel 
	 *            是否删除：1不，2删除
     */
    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    /**
     * @return 排序号，大的在前
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * @param sort 
	 *            排序号，大的在前
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * @return 扣分大小：默认0
     */
    public Long getDeductMarks() {
        return deductMarks;
    }

    /**
     * @param deductMarks 
	 *            扣分大小：默认0
     */
    public void setDeductMarks(Long deductMarks) {
        this.deductMarks = deductMarks;
    }

    /**
     * @return 是否必须拍照：1不需要，2需要
     */
    public String getIfMustPhoto() {
        return ifMustPhoto;
    }

    /**
     * @param ifMustPhoto 
	 *            是否必须拍照：1不需要，2需要
     */
    public void setIfMustPhoto(String ifMustPhoto) {
        this.ifMustPhoto = ifMustPhoto;
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
     * @return 创建人
     */
    public String getCreateManager() {
        return createManager;
    }

    /**
     * @param createManager 
	 *            创建人
     */
    public void setCreateManager(String createManager) {
        this.createManager = createManager;
    }

    /**
     * @return 修改人
     */
    public Date getEditTime() {
        return editTime;
    }

    /**
     * @param editTime 
	 *            修改人
     */
    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    /**
     * @return 修改人
     */
    public String getEditManager() {
        return editManager;
    }

    /**
     * @param editManager 
	 *            修改人
     */
    public void setEditManager(String editManager) {
        this.editManager = editManager;
    }

    /**
     * @return 检测类型：外观等
     */
    public String getDetectionType() {
        return detectionType;
    }

    /**
     * @param detectionType 
	 *            检测类型：外观等
     */
    public void setDetectionType(String detectionType) {
        this.detectionType = detectionType;
    }

    /**
     * @return 检测类型名称
     */
    public String getDetectionTypeCn() {
        return detectionTypeCn;
    }

    /**
     * @param detectionTypeCn 
	 *            检测类型名称
     */
    public void setDetectionTypeCn(String detectionTypeCn) {
        this.detectionTypeCn = detectionTypeCn;
    }

    /**
     * @return 损坏类型
     */
    public String getDamagedType() {
        return damagedType;
    }

    /**
     * @param damagedType 
	 *            损坏类型
     */
    public void setDamagedType(String damagedType) {
        this.damagedType = damagedType;
    }

    /**
     * @return 损坏类型名称
     */
    public String getDamagedTypeCn() {
        return damagedTypeCn;
    }

    /**
     * @param damagedTypeCn 
	 *            损坏类型名称
     */
    public void setDamagedTypeCn(String damagedTypeCn) {
        this.damagedTypeCn = damagedTypeCn;
    }

    public String getIfChecked() {
        return ifChecked;
    }

    public void setIfChecked(String ifChecked) {
        this.ifChecked = ifChecked;
    }
}