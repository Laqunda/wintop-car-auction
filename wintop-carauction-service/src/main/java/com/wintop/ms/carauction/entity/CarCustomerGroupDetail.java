package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * CarCustomerGroupDetail:用户分组信息详情
 * @author zhangzijuan
 * @date 2018-02-26
 */
public class CarCustomerGroupDetail implements Serializable {
    private static final long serialVersionUID = -8499280548548462846L;
    /**
     * 分组id
     */
    private Long id;

    /**
     * 客户分组id
     */
    private Long groupId;

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
    public Long getGroupId() {
        return groupId;
    }

    /**
     * @param groupId 
	 *            客户分组id
     */
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
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

    public Long getCreateManager() {
        return createManager;
    }

    public void setCreateManager(Long createManager) {
        this.createManager = createManager;
    }
}