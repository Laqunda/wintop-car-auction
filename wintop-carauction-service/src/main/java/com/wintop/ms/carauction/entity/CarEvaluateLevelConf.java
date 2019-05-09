package com.wintop.ms.carauction.entity;

import java.math.BigDecimal;

public class CarEvaluateLevelConf {
    /**
     * 评价星际配置主键id
     */
    private Long id;
    /**
     * 评价等级描述：1较差，2中等，3满意，4很满意
     */
    private String level;
    /**
     * 星级：根据此数判断是否达到对应的等级
     */
    private BigDecimal star;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public BigDecimal getStar() {
        return star;
    }

    public void setStar(BigDecimal star) {
        this.star = star;
    }
}