package com.wintop.ms.carauction.entity;

import java.sql.Timestamp;

/**
 * Created by liangtingsen on 2018/3/6.
 * 柠檬消息
 */
public class CarMessage {
    private Long id;
    private String title;
    private String openObjType;//开对象类型：1系统消息，2车辆，3网页地址，4订单、5拍卖场次、6签约界面、7认证界面、8保证金
    private Long openObjId;
    private Timestamp pushTime;
    private Long userId;
    private String isRead;
    private Timestamp readTime;
    private String content;
    private String userType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOpenObjType() {
        return openObjType;
    }

    public void setOpenObjType(String openObjType) {
        this.openObjType = openObjType;
    }

    public Long getOpenObjId() {
        return openObjId;
    }

    public void setOpenObjId(Long openObjId) {
        this.openObjId = openObjId;
    }

    public Timestamp getPushTime() {
        return pushTime;
    }

    public void setPushTime(Timestamp pushTime) {
        this.pushTime = pushTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public Timestamp getReadTime() {
        return readTime;
    }

    public void setReadTime(Timestamp readTime) {
        this.readTime = readTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
