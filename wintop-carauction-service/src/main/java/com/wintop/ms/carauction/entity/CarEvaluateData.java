package com.wintop.ms.carauction.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CarEvaluateData {
    /** */
    private Long id;

    /** 对应type字段，type=1时为检测报告id；type=2时为现场拍场次id；type=3时为订单id*/
    private Long objId;

    /** 评价内容*/
    private String content;

    /** 评价类型：1、检测报告评价；2、现场拍评价；3、订单评价*/
    private Long type;

    /** 星级*/
    private BigDecimal starLevel;

    /** 评价时间*/
    private Date createTime;

    /** 评价人*/
    private Long createPerson;

    /** 评价人角色*/
    private String role;

    /** 评价标签中文组合*/
    private String tags;

    /** 评价标签id组合*/
    private String tagIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getObjId() {
        return objId;
    }

    public void setObjId(Long objId) {
        this.objId = objId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public BigDecimal getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(BigDecimal starLevel) {
        this.starLevel = starLevel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(Long createPerson) {
        this.createPerson = createPerson;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }
}