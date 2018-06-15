package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * CarCustomerLevelDetail:客户级别信息详情实体
 * @author zhangzijuan
 * @date 2018-02-26
 */
public class CarCustomerLevelDetail implements Serializable {
    private static final long serialVersionUID = 7112975316155610805L;
    /**
     * 分组id
     */
    private Long id;

    /**
     * 客户分组id
     */
    private Long levelId;

    /**
     * 用户ID
     */
    private Long customerId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createManager;

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
     * @return 用户ID
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId 
	 *            用户ID
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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
}