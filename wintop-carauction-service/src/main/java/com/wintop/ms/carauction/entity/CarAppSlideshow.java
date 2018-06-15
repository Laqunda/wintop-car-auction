package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 12991 on 2018/2/26.
 */
public class CarAppSlideshow implements Serializable {

    private static final long serialVersionUID = 138103120193089069L;
    /**
     * 轮播图
     */
    private Long id;

    /**
     * 轮播图标题
     */
    private String title;

    /**
     * 轮播图类型：1、拍卖场次，2车辆，3拍卖大厅，4问题描述，5关于我们，6网页地址，7图文
     */
    private String openType;

    /**
     * 轮播图片
     */
    private String img;

    /**
     * 描述
     */
    private String describe;

    /**
     * 打开对象内容，ID或http地址
     */
    private String openObj;

    /**
     * 排序，大的在前
     */
    private Integer sort;

    /**
     * 是否启用：1启用，2不启用，3删除
     */
    private String ifShow;

    /**
     * 编辑人
     */
    private Long editor;

    /**
     * 编辑时间
     */
    private Date editTime;

    /**
     * 类型（现场播报1，轮播图2）
     */
    private String type;

    /**
     * @return 轮播图
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            轮播图
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 轮播图标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            轮播图标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return 轮播图类型：1、拍卖场次，2车辆，3拍卖大厅，4问题描述，5关于我们，6网页地址
     */
    public String getOpenType() {
        return openType;
    }

    /**
     * @param openType
     *            轮播图类型：1、拍卖场次，2车辆，3拍卖大厅，4问题描述，5关于我们，6网页地址
     */
    public void setOpenType(String openType) {
        this.openType = openType;
    }

    /**
     * @return 轮播图片
     */
    public String getImg() {
        return img;
    }

    /**
     * @param img
     *            轮播图片
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * @return 描述
     */
    public String getDescribe() {
        return describe;
    }

    /**
     * @param describe
     *            描述
     */
    public void setDescribe(String describe) {
        this.describe = describe;
    }

    /**
     * @return 打开对象内容，ID或http地址
     */
    public String getOpenObj() {
        return openObj;
    }

    /**
     * @param openObj
     *            打开对象内容，ID或http地址
     */
    public void setOpenObj(String openObj) {
        this.openObj = openObj;
    }

    /**
     * @return 排序，大的在前
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * @param sort
     *            排序，大的在前
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * @return 是否启用：1启用，2不启用，3删除
     */
    public String getIfShow() {
        return ifShow;
    }

    /**
     * @param ifShow
     *            是否启用：1启用，2不启用，3删除
     */
    public void setIfShow(String ifShow) {
        this.ifShow = ifShow;
    }

    /**
     * @return 编辑人
     */
    public Long getEditor() {
        return editor;
    }

    /**
     * @param editor
     *            编辑人
     */
    public void setEditor(Long editor) {
        this.editor = editor;
    }

    /**
     * @return 编辑时间
     */
    public Date getEditTime() {
        return editTime;
    }

    /**
     * @param editTime
     *            编辑时间
     */
    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    /**
     * @return 类型（现场播报1，轮播图2）
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            类型（现场播报1，轮播图2）
     */
    public void setType(String type) {
        this.type = type;
    }
}
