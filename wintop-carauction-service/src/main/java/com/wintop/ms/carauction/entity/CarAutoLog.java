package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.Date;

public class CarAutoLog implements Serializable {

    private static final long serialVersionUID = 2257113257639583336L;
    /**
     * 日志ID
     */
    private Long id;

    /**
     * 车辆ID
     */
    private Long autoId;

    /**
     * 车辆操作说明
     */
    private String msg;

    /**
     * 车辆状态
     */
    private String status;

    /**
     * 操作时间
     */
    private Date time;

    /**
     * 操作人类型：1卖家、2买家、3中心、4代办、5、平台
     */
    private String userType;

    /**
     * 操作用户ID
     */
    private Long userId;

    /**
     * 操作人手机号
     */
    private String userMobile;

    /**
     * 操作人姓名
     */
    private String userName;

    private String statusName;

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    /**
     * @return 日志ID
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id 
	 *            日志ID
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
     * @return 车辆操作说明
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg 
	 *            车辆操作说明
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return 车辆状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            车辆状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return 操作时间
     */
    public Date getTime() {
        return time;
    }

    /**
     * @param time 
	 *            操作时间
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * @return 操作人类型：1卖家、2买家、3中心、4代办、5、平台
     */
    public String getUserType() {
        return userType;
    }

    /**
     * @param userType 
	 *            操作人类型：1卖家、2买家、3中心、4代办、5、平台
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * @return 操作用户ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId 
	 *            操作用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return 操作人手机号
     */
    public String getUserMobile() {
        return userMobile;
    }

    /**
     * @param userMobile 
	 *            操作人手机号
     */
    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    /**
     * @return 操作人姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName 
	 *            操作人姓名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
}