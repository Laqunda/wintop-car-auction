package com.wintop.ms.carauction.entity;

import java.io.Serializable;

public class CarManagerRole implements Serializable {

    private static final long serialVersionUID = 8977921817370694359L;
    /**
     * 角色ID
     */
    private Long id;

    /**
     * 1平台，2中心，3经销店，4代办公司
     */
    private Long roleTypeId;

    /**
     * 角色类型名称
     */
    private String typeName;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色说明
     */
    private String roleDesc;

    /**
     * 1启用，2停用
     */
    private String roleStatus;

    /**
     * 1读取全部数据，2读取自己数据
     */
    private String readType;

    /**
     * 1可操作全部数据，2只能操作自己的数据
     */
    private String writeType;

    /**
     * @return 权限ID
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id 
	 *            权限ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 1平台，2中心，3经销店，4代办公司
     */
    public Long getRoleTypeId() {
        return roleTypeId;
    }

    /**
     * @param roleTypeId 
	 * 1平台，2中心，3经销店，4代办公司
     */
    public void setRoleTypeId(Long roleTypeId) {
        this.roleTypeId = roleTypeId;
    }

    /**
     * @return 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @param roleName 
	 *            角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * @return 角色说明
     */
    public String getRoleDesc() {
        return roleDesc;
    }

    /**
     * @param roleDesc 
	 *            角色说明
     */
    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    /**
     * @return 1启用，2停用
     */
    public String getRoleStatus() {
        return roleStatus;
    }

    /**
     * @param roleStatus 
	 *            1启用，2停用
     */
    public void setRoleStatus(String roleStatus) {
        this.roleStatus = roleStatus;
    }

    public String getReadType() {
        return readType;
    }

    public void setReadType(String readType) {
        this.readType = readType;
    }

    public String getWriteType() {
        return writeType;
    }

    public void setWriteType(String writeType) {
        this.writeType = writeType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}