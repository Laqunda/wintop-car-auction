package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.Date;

public class CarReservationSeeCar implements Serializable {

    private static final long serialVersionUID = 6945092605966751971L;
    private Long id;

    /**
     * 预约的车辆id或者场次id
     */
    private Long reservationId;

    /**
     * 联系电话
     */
    private String contactPhone;

    /**
     * 预约时间
     */
    private Date createTime;

    /**
     * 类型 1车辆 2场次
     */
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 预约的车辆id或者场次id
     */
    public Long getReservationId() {
        return reservationId;
    }

    /**
     * @param reservationId 
	 *            预约的车辆id或者场次id
     */
    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    /**
     * @return 联系电话
     */
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * @param contactPhone 
	 *            联系电话
     */
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    /**
     * @return 预约时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime 
	 *            预约时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return 类型 1车辆 2场次
     */
    public String getType() {
        return type;
    }

    /**
     * @param type 
	 *            类型 1车辆 2场次
     */
    public void setType(String type) {
        this.type = type;
    }
}