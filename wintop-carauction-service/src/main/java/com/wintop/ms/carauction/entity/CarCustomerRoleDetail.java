package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * CarCustomerRoleDetail:客户角色信息详情
 * @author zhangzijuan
 * @date 2018-02-26
 */
public class CarCustomerRoleDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 分组id
     */
    private Long id;

    /**
     * 客户角色id
     */
    private Long roleId;

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
     * @return 客户角色id
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * @param roleId 
	 *            客户角色id
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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