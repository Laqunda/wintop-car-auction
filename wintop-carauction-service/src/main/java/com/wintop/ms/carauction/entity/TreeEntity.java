package com.wintop.ms.carauction.entity;

import java.util.List;

public class TreeEntity {
    private Long id;
    private Long pId;
    private String name;
    private boolean checked;

    private List<TreeEntity> treeEntityList;

    public List<TreeEntity> getTreeEntityList() {
        return treeEntityList;
    }

    public void setTreeEntityList(List<TreeEntity> treeEntityList) {
        this.treeEntityList = treeEntityList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
