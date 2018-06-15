package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.Date;

public class CarAutoTransferLog implements Serializable {

    private static final long serialVersionUID = -831056556444311772L;
    /**
     * 车辆代办主表id
     */
    private Long id;

    /**
     * 过户主表ID
     */
    private Long transferId;

    /**
     * 类型：1确认过户事宜；2出票；3出牌；4交档；5提档；6过户票；7登记证；8行驶证；9手续回传；10手续回传不通过；11手续回传通过
     */
    private String type;

    /**
     * 执行时间
     */
    private Date time;

    /**
     * 办理时间
     */
    private Date handleTime;

    /**
     * 回传的照片
     */
    private String fileUrl;

    private String fileType;

    private String photo;

    private String statusName;

    private String userPhone;

    private String userName;

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    private String remark;

    private Long handlePerson;

    public Long getHandlePerson() {
        return handlePerson;
    }

    public void setHandlePerson(Long handlePerson) {
        this.handlePerson = handlePerson;
    }

    /**
     * @return 车辆代办主表id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id 
	 *            车辆代办主表id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 过户主表ID
     */
    public Long getTransferId() {
        return transferId;
    }

    /**
     * @param transferId 
	 *            过户主表ID
     */
    public void setTransferId(Long transferId) {
        this.transferId = transferId;
    }

    /**
     * @return 类型：1确认过户事宜；2出票；3出牌；4交档；5提档；6过户票；7登记证；8行驶证；9手续回传；10手续回传不通过；11手续回传通过
     */
    public String getType() {
        return type;
    }

    /**
     * @param type 
	 *            类型：1确认过户事宜；2出票；3出牌；4交档；5提档；6过户票；7登记证；8行驶证；9手续回传；10手续回传不通过；11手续回传通过
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return 执行时间
     */
    public Date getTime() {
        return time;
    }

    /**
     * @param time 
	 *            执行时间
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * @return 办理时间
     */
    public Date getHandleTime() {
        return handleTime;
    }

    /**
     * @param handleTime 
	 *            办理时间
     */
    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}