package com.wintop.ms.carauction.entity;

import java.io.Serializable;

public class CarAutoDetectionDataPhoto implements Serializable {

    private static final long serialVersionUID = -3459542275721427100L;
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
     * 照片组
     */
    private String photoUrl;

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
     * @return 照片组
     */
    public String getPhotoUrl() {
        return photoUrl;
    }

    /**
     * @param photoUrl 
	 *            照片组
     */
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}