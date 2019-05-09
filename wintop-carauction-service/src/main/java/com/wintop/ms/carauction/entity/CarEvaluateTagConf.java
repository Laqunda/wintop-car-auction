package com.wintop.ms.carauction.entity;

import java.math.BigDecimal;

public class CarEvaluateTagConf {
    /** 评价配置主键ID*/
    private Long id;

    /** 评论标题*/
    private String title;

    /** 上级分类：-1时一级标签*/
    private Long pId;

    /** 评价类型：1检测报告；2现场拍服务评价；3成交车辆单评价*/
    private String type;

    /** 星级：根据此数判断是否达到对应的等级*/
    private BigDecimal star;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getStar() {
        return star;
    }

    public void setStar(BigDecimal star) {
        this.star = star;
    }
}
