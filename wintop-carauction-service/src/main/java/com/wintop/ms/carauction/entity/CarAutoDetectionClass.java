package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CarAutoDetectionClass implements Serializable {

    private static final long serialVersionUID = -991792092577684018L;
    /**
     * 分组id
     */
    private Long id;

    /**
     * 客户等级名称
     */
    private String className;

    /**
     * 上级检测类ID：0顶级
     */
    private Long pId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createManager;

    /**
     * 是否关闭：1开启，2关闭
     */
    private String isClose;

    /**
     * 是否删除：1不，2删除
     */
    private String isDel;

    /**
     * 修改人
     */
    private Date editTime;

    /**
     * 修改人
     */
    private String editManager;

    /**
     * 排序号，大的在前
     */
    private Integer sort;

    /***
     * 车辆检测项分类编号
     */
    private Integer classNo;

    /***
     * 检测项位置类型
     * 1前，2后
     */
    private String positionType;
    // --------------- 展示使用
    /***
     * 问题描述，可显示多个问题
     */
    private String problemDescription;
    /**
     * 图片，显示多张图片
     */
    private String photoUrl;

    /***
     * 检测项选项
     */
    private List<CarAutoDetectionClassOptions> optionsList = new ArrayList<>();
    /***
     * 损坏点照片
     */
    private List<CarAutoDetectionDataPhoto> photoList = new ArrayList<>();
    /***
     * 损坏点信息
     */
    private List<CarAutoDetectionData> dataList = new ArrayList<>();

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
     * @return 客户等级名称
     */
    public String getClassName() {
        return className;
    }

    /**
     * @param className 
	 *            客户等级名称
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * @return 上级检测类ID：0顶级
     */
    public Long getpId() {
        return pId;
    }

    /**
     * @param pId 
	 *            上级检测类ID：0顶级
     */
    public void setpId(Long pId) {
        this.pId = pId;
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

    public Integer getClassNo() {
        return classNo;
    }

    public void setClassNo(Integer classNo) {
        this.classNo = classNo;
    }

    public String getPositionType() {
        return positionType;
    }

    public void setPositionType(String positionType) {
        this.positionType = positionType;
    }

    public List<CarAutoDetectionClassOptions> getOptionsList() {
        return optionsList;
    }

    public void setOptionsList(List<CarAutoDetectionClassOptions> optionsList) {
        this.optionsList = optionsList;
    }

    public List<CarAutoDetectionDataPhoto> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<CarAutoDetectionDataPhoto> photoList) {
        this.photoList = photoList;
    }

    public List<CarAutoDetectionData> getDataList() {
        return dataList;
    }

    public void setDataList(List<CarAutoDetectionData> dataList) {
        this.dataList = dataList;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}