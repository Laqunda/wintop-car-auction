package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CarAutoConfTitle implements Serializable {

    private static final long serialVersionUID = -509701847509485415L;
    private Long id;

    /**
     * 配置标题
     */
    private String title;

    /**
     * 创建人
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 状态：1可用，2停用
     */
    private String status;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人
     */
    private String updateUser;

    /***
     * 选项个数
     */
    private Integer optionSize;

    /***
     * 当前车辆当前配置的所选项id
     */
    private String confOption;
    /***
     * 当前车辆当前配置的所选项名称
     */
    private String confOptionCn;

    /***
     * 选项
     */
    private List<Map<String,String>> optionList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 配置标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title 
	 *            配置标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return 创建人
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime 
	 *            创建人
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * @param createUser 
	 *            创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * @return 状态：1可用，2停用
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            状态：1可用，2停用
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * @param sort 
	 *            排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * @return 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime 
	 *            修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return 修改人
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * @param updateUser 
	 *            修改人
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Integer getOptionSize() {
        return optionSize;
    }

    public void setOptionSize(Integer optionSize) {
        this.optionSize = optionSize;
    }

    public List<Map<String, String>> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<Map<String, String>> optionList) {
        this.optionList = optionList;
    }

    public String getConfOption() {
        return confOption;
    }

    public void setConfOption(String confOption) {
        this.confOption = confOption;
    }

    public String getConfOptionCn() {
        return confOptionCn;
    }

    public void setConfOptionCn(String confOptionCn) {
        this.confOptionCn = confOptionCn;
    }
}