package com.wintop.ms.carauction.entity;

import java.util.HashMap;
import java.util.Map;

/**
 */
public class Criteria {
    /**
     */
    private Map<String, Object> condition;

    /**
     */
    protected boolean distinct;

    /**
     */
    protected String orderByClause;

    private Integer mysqlOffset;

    private Integer mysqlLength;

    protected Criteria(Criteria example) {
        this.orderByClause = example.orderByClause;
        this.condition = example.condition;
        this.distinct = example.distinct;
        this.mysqlLength = example.mysqlLength;
        this.mysqlOffset = example.mysqlOffset;
    }

    public Criteria() {
        condition = new HashMap<String, Object>();
    }

    public void clear() {
        condition.clear();
        orderByClause = null;
        distinct = false;
        this.mysqlOffset = null;
        this.mysqlLength = null;
    }

    /**
     * @param condition 
     */
    public Criteria put(String condition, Object value) {
        this.condition.put(condition, value);
        return (Criteria) this;
    }

    /**
     * @param orderByClause 
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * @param distinct 
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public void setCondition(Map<String, Object> condition) {
        this.condition = condition;
    }

    public Map<String, Object> getCondition() {
        return condition;
    }

    /**
     * @param mysqlOffset 
     */
    public void setMysqlOffset(Integer mysqlOffset) {
        this.mysqlOffset = mysqlOffset;
    }

    /**
     * @param mysqlLength 
     */
    public void setMysqlLength(Integer mysqlLength) {
        this.mysqlLength = mysqlLength;
    }
}