package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 12991 on 2018/3/5.
 */
public class CarQuestion implements Serializable{
    private static final long serialVersionUID = 583067217180146485L;
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 所属分类id
     */
    private Long classifyId;

    /**
     * 是否开启，0开启 1未开启
     */
    private String isOpen;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private Long createPerson;

    /**
     * 内容
     */
    private String content;

    private String description;

    //**1首页展示，2首页不展示
    private String showIndex;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 分类问题名称
     */
    private String classifyName;

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return 所属分类id
     */
    public Long getClassifyId() {
        return classifyId;
    }

    /**
     * @param classifyId
     *            所属分类id
     */
    public void setClassifyId(Long classifyId) {
        this.classifyId = classifyId;
    }

    /**
     * @return 是否开启，0开启 1未开启
     */
    public String getIsOpen() {
        return isOpen;
    }

    /**
     * @param isOpen
     *            是否开启，0开启 1未开启
     */
    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
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
     * @return 联系电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     *            联系电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
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
    public Long getCreatePerson() {
        return createPerson;
    }

    /**
     * @param createPerson
     *            创建人
     */
    public void setCreatePerson(Long createPerson) {
        this.createPerson = createPerson;
    }

    /**
     * @return 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     *            内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    public String getShowIndex() {
        return showIndex;
    }

    public void setShowIndex(String showIndex) {
        this.showIndex = showIndex;
    }
}
