package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.Date;

public class CarAutoDetectionData implements Serializable {

    private static final long serialVersionUID = 1815762291311340470L;
    /**
     * 检测异常id
     */
    private Long id;

    /**
     * 检测项类别ID
     */
    private Long classId;

    /**
     * 检测车辆ID
     */
    private Long autoId;

    /**
     * 问题描述
     */
    private String problemDescription;

    /**
     * 扣分大小
     */
    private Long decreasedScore;

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
     * 创建时间
     */
    private Date createTime;

    /***
     * 损坏点id
     */
    private Long optionsId;

    /**
     * @return 检测异常id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id 
	 *            检测异常id
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
     * @return 检测车辆ID
     */
    public Long getAutoId() {
        return autoId;
    }

    /**
     * @param autoId 
	 *            检测车辆ID
     */
    public void setAutoId(Long autoId) {
        this.autoId = autoId;
    }

    /**
     * @return 问题描述
     */
    public String getProblemDescription() {
        return problemDescription;
    }

    /**
     * @param problemDescription 
	 *            问题描述
     */
    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    /**
     * @return 扣分大小
     */
    public Long getDecreasedScore() {
        return decreasedScore;
    }

    /**
     * @param decreasedScore 
	 *            扣分大小
     */
    public void setDecreasedScore(Long decreasedScore) {
        this.decreasedScore = decreasedScore;
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

    public Long getOptionsId() {
        return optionsId;
    }

    public void setOptionsId(Long optionsId) {
        this.optionsId = optionsId;
    }
}