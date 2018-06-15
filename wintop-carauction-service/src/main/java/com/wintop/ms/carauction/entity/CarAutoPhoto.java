package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.Date;

public class CarAutoPhoto implements Serializable {

    private static final long serialVersionUID = 2281291795191208923L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 车辆ID
     */
    private Long autoId;

    /**
     * 照片
     */
    private String photoUrl;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人
     */
    private String updateUser;
    /***
     * 状态： 1草稿、2审核中、3审核不通过、4撤回审核中、5等待上拍、6等待开拍、7正在竞拍、8成交-等待付款、9议价处理中、10付款信息待审核、11已付款、12过户中、14争议处理中、15手续回传确认中、16交易完成、17待评价、18交易关闭、19流拍
     */
    private String photoType;

    /**
     * @return 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id 
	 *            主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 车辆ID
     */
    public Long getAutoId() {
        return autoId;
    }

    /**
     * @param autoId 
	 *            车辆ID
     */
    public void setAutoId(Long autoId) {
        this.autoId = autoId;
    }

    /**
     * @return 照片
     */
    public String getPhotoUrl() {
        return photoUrl;
    }

    /**
     * @param photoUrl 
	 *            照片
     */
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
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

    public String getPhotoType() {
        return photoType;
    }

    public void setPhotoType(String photoType) {
        this.photoType = photoType;
    }
}