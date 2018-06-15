package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.List;

public class CarManagerRoleType implements Serializable {

    private static final long serialVersionUID = 4820257155209848671L;
    /**
     * 权限ID
     * 1平台，2中心，3经销店，4代办公司
     */
    private Long id;

    /**
     * 权限名称
     */
    private String typeName;

    private List<CarManagerRole> roleList;

    private List<CommonNameVo> storeVoList;

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
     * @return 权限名称
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * @param typeName 
	 *            权限名称
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<CarManagerRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<CarManagerRole> roleList) {
        this.roleList = roleList;
    }

    public List<CommonNameVo> getStoreVoList() {
        return storeVoList;
    }

    public void setStoreVoList(List<CommonNameVo> storeVoList) {
        this.storeVoList = storeVoList;
    }
}