package com.wintop.ms.carauction.entity;

public class CarManagerRoleData {
    /**
     * 主键
     */
    private Long id;
    /**
     * 管理员id
     */
    private Long managerId;
    /**
     * 分类的code 编码
     */
    private String code;
    /**
     * 是否有权限1：有权限，0无权限
     */
    private String isRole;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getIsRole() {
        return isRole;
    }

    public void setIsRole(String isRole) {
        this.isRole = isRole == null ? null : isRole.trim();
    }
}