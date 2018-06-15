package com.wintop.ms.carauction.entity;

import java.io.Serializable;

public class CarManagerRolePage implements Serializable {

    private static final long serialVersionUID = 3775097385262214474L;
    /**
     * 权限页面关系表ID
     */
    private Long id;

    /**
     * 权限ID
     */
    private Long roleId;

    /**
     * 页面ID
     */
    private Long pageId;

    /**
     * @return 权限页面关系表ID
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id 
	 *            权限页面关系表ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 权限ID
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * @param roleId 
	 *            权限ID
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPageId() {
        return pageId;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }
}