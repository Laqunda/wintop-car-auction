package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * CarCustomerRole:用户角色
 * @author zhangzijuan
 * @date 2018-02-26
 */
public class CarCustomerRole implements Serializable {
    private static final long serialVersionUID = -3283946584429692133L;
    /**
     * 会员角色设置ID
     */
    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 编号
     */
    private String code;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createManager;

    /**
     * 状态：1可用，-1停用
     */
    private String status;

    /**
     * @return 会员角色设置ID
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id 
	 *            会员角色设置ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 角色名称
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 
	 *            角色名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return 编号
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code 
	 *            编号
     */
    public void setCode(String code) {
        this.code = code;
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

    /**
     * @return 状态：1可用，-1停用
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            状态：1可用，-1停用
     */
    public void setStatus(String status) {
        this.status = status;
    }
}