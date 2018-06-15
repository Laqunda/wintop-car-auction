package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * CarCustomerGroup:用户分组
 * @author zhangzijuan
 * @date 2018-02-26
 */
public class CarCustomerGroup implements Serializable {
    private static final long serialVersionUID = 3866585322428745593L;
    /**
     * 分组id
     */
    private Long id;

    /**
     * 客户分组名称
     */
    private String groupName;

    /**
     * 分组编号
     */
    private String code;

    /**
     * 状态：1启用，-1停用
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createManager;

    public String getCreateManager() {
        return createManager;
    }

    public void setCreateManager(String createManager) {
        this.createManager = createManager;
    }

    /**
     * 对应的用户数量
     */
    private Integer userNum;

    public Integer getUserNum() {
        return userNum;
    }

    public void setUserNum(Integer userNum) {
        this.userNum = userNum;
    }

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
     * @return 客户分组名称
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * @param groupName 
	 *            客户分组名称
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * @return 分组编号
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code 
	 *            分组编号
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return 状态：1启用，-1停用
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            状态：1启用，-1停用
     */
    public void setStatus(String status) {
        this.status = status;
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


}