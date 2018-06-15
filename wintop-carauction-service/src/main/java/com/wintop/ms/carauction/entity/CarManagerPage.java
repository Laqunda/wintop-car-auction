package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.Date;

public class CarManagerPage implements Serializable {

    private static final long serialVersionUID = 2161243128811873888L;
    /**
     * 页面ID
     */
    private Long id;

    /**
     * 父菜单ID
     */
    private Long parentId;

    /**
     * 页面名称
     */
    private String designation;

    /**
     *
     */
    private String url;

    /**
     * 页面描述
     */
    private String characterization;

    /**
     * 1启用，2不启用
     */
    private String status;

    /**
     * 页面创建时间
     */
    private Date createTime;

    /**
     * 页面修改时间
     */
    private Date modifyTime;

    private Long createPerson;

    private Long modifyPerson;

    //1,2,3级
    private String level;

    /**
     * @return 页面ID
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id 
	 *            页面ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 父菜单ID
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * @param parentId 
	 *            父菜单ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * @return 页面名称
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * @param designation 
	 *            页面名称
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * @return 页面控制器名称
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url 
	 *            页面控制器名称
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return 页面描述
     */
    public String getCharacterization() {
        return characterization;
    }

    /**
     * @param characterization 
	 *            页面描述
     */
    public void setCharacterization(String characterization) {
        this.characterization = characterization;
    }

    /**
     * @return 1启用，2不启用
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            1启用，2不启用
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return 页面创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime 
	 *            页面创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return 页面修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * @param modifyTime 
	 *            页面修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Long getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(Long createPerson) {
        this.createPerson = createPerson;
    }

    public Long getModifyPerson() {
        return modifyPerson;
    }

    public void setModifyPerson(Long modifyPerson) {
        this.modifyPerson = modifyPerson;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}