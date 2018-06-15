package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 12991 on 2018/3/5.
 */
public class CarQuestionClassify implements Serializable{
    private static final long serialVersionUID = 5775492728461096993L;
    private Long id;

    /**
     * 分类名称
     */
    private String classifyName;

    /**
     * 是否开启，1开启，2未开启
     */
    private String isOpen;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private Long createPerson;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 分类名称
     */
    public String getClassifyName() {
        return classifyName;
    }

    /**
     * @param classifyName
     *            分类名称
     */
    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    /**
     * @return 是否开启，1开启，2未开启
     */
    public String getIsOpen() {
        return isOpen;
    }

    /**
     * @param isOpen
     *            是否开启，1开启，2未开启
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
}
