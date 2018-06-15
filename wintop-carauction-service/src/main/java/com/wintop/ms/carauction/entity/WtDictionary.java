package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 12991 on 2018/2/27.
 */
public class WtDictionary implements Serializable{
    private static final long serialVersionUID = -266297289032969504L;
    /**
     * 字典表id
     */
    private Long id;

    /**
     * 字典名称
     */
    private String name;

    /**
     * 字典编号
     */
    private String code;

    /**
     * 上级ID
     */
    private Long pId;

    /**
     * 编辑人账号
     */
    private String editUser;

    /**
     * 编辑时间
     */
    private Date editTime;
    /***
     * 排序号
     */
    private Integer sort;

    /**
     * 状态：1启用，2停用
     */
    private String status;

    /***
     * 字典项值
     */
    private String value;

    /**
     * @return 字典表id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            字典表id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 字典名称
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            字典名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return 字典编号
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     *            字典编号
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return 上级ID
     */
    public Long getpId() {
        return pId;
    }

    /**
     * @param pId
     *            上级ID
     */
    public void setpId(Long pId) {
        this.pId = pId;
    }

    /**
     * @return 编辑人账号
     */
    public String getEditUser() {
        return editUser;
    }

    /**
     * @param editUser
     *            编辑人账号
     */
    public void setEditUser(String editUser) {
        this.editUser = editUser;
    }

    /**
     * @return 编辑时间
     */
    public Date getEditTime() {
        return editTime;
    }

    /**
     * @param editTime
     *            编辑时间
     */
    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    /**
     * @return 状态：1启用，2停用
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     *            状态：1启用，2停用
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
