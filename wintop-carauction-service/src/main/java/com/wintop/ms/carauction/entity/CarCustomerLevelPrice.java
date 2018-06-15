package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * CarCustomerLevelPrice:客户级别对应出价
 * @author zhangzijuan
 * @date 2018-02-26
 */
public class CarCustomerLevelPrice implements Serializable {
    private static final long serialVersionUID = -3811960117776820838L;
    /**
     * 分组id
     */
    private Long id;

    /**
     * 客户分组id
     */
    private Long levelId;

    /**
     * 该分组可出价格
     */
    private Long fareId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private Long createManager;

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
     * @return 客户分组id
     */
    public Long getLevelId() {
        return levelId;
    }

    /**
     * @param levelId 
	 *            客户分组id
     */
    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    /**
     * @return 该分组可出价格
     */
    public Long getFareId() {
        return fareId;
    }

    /**
     * @param fareId 
	 *            该分组可出价格
     */
    public void setFareId(Long fareId) {
        this.fareId = fareId;
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

    public Long getCreateManager() {
        return createManager;
    }

    public void setCreateManager(Long createManager) {
        this.createManager = createManager;
    }
}