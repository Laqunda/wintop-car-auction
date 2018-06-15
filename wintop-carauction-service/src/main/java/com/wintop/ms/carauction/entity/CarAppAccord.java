package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 12991 on 2018/2/26.
 */
public class CarAppAccord implements Serializable{
    private static final long serialVersionUID = 565876564079698133L;
    /**
     * 轮播图
     */
    private Long id;

    /**
     * 轮播图标题
     */
    private String title;

    /**
     * 协议类型：1注册、2认证、3在线签约、4保证金、5拍卖、6出价。。
     */
    private String type;

    /**
     * 简介描述
     */
    private String describe;

    /**
     * 发布作者或部门,页面显示用
     */
    private String author;

    /**
     * 发布日期，页面显示用
     */
    private String pulishDate;

    /**
     * 是否启用：1启用，2不启用，-1删除
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
     * 协议内容
     */
    private String content;

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
     * @return 协议类型：1注册、2认证、3在线签约、4保证金、5拍卖、6出价。。
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            协议类型：1注册、2认证、3在线签约、4保证金、5拍卖、6出价。。
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return 简介描述
     */
    public String getDescribe() {
        return describe;
    }

    /**
     * @param describe
     *            简介描述
     */
    public void setDescribe(String describe) {
        this.describe = describe;
    }

    /**
     * @return 发布作者或部门,页面显示用
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author
     *            发布作者或部门,页面显示用
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return 发布日期，页面显示用
     */
    public String getPulishDate() {
        return pulishDate;
    }

    /**
     * @param pulishDate
     *            发布日期，页面显示用
     */
    public void setPulishDate(String pulishDate) {
        this.pulishDate = pulishDate;
    }

    /**
     * @return 是否启用：1启用，2不启用，-1删除
     */
    public String getIfShow() {
        return ifShow;
    }

    /**
     * @param ifShow
     *            是否启用：1启用，2不启用，-1删除
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
     * @return 协议内容
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     *            协议内容
     */
    public void setContent(String content) {
        this.content = content;
    }

}
