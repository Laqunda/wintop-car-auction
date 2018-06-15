package com.wintop.ms.carauction.entity;

import java.io.Serializable;

public class CarRelateStatus implements Serializable {

    private static final long serialVersionUID = -1330322196236425861L;
    private Integer id;

    /**
     * 状态码
     */
    private String status;

    /**
     * 状态名称
     */
    private String statusName;

    /**
     * 类型  1 会员相关状态 2.车辆相关状态、3代办相关状态 4订单相关状态
     */
    private String type;

    /**
     * 备注
     */
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 状态码
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            状态码
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return 状态名称
     */
    public String getStatusName() {
        return statusName;
    }

    /**
     * @param statusName 
	 *            状态名称
     */
    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    /**
     * @return 类型  1 会员相关状态 2.车辆相关状态、3代办相关状态 4订单相关状态
     */
    public String getType() {
        return type;
    }

    /**
     * @param type 
	 *            类型  1 会员相关状态 2.车辆相关状态、3代办相关状态 4订单相关状态
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark 
	 *            备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}