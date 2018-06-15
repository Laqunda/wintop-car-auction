package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 12991 on 2018/2/27.
 */
public class CarAuctionFareSetting implements Serializable {

    private static final long serialVersionUID = 7002607044509759224L;

    /**
     * 拍卖出价ID
     */
    private Long id;

    /**
     * 拍卖出价
     */
    private BigDecimal farePrice;

    private String editUser;

    /**
     * 编辑时间
     */
    private Date editTime;

    /**
     * 状态：1正常，2停用
     */
    private String status;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态（0不可点击，1可点击）
     */
    private String clickable;

    /**
     * @return 拍卖出价ID
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            拍卖出价ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 拍卖出价
     */
    public BigDecimal getFarePrice() {
        return farePrice;
    }

    /**
     * @param farePrice
     *            拍卖出价
     */
    public void setFarePrice(BigDecimal farePrice) {
        this.farePrice = farePrice;
    }

    public String getEditUser() {
        return editUser;
    }

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
     * @return 状态：1正常，2停用
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     *            状态：1正常，2停用
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

    public String getClickable() {
        return clickable;
    }

    public void setClickable(String clickable) {
        this.clickable = clickable;
    }
}
