package com.wintop.ms.carauction.entity;

import java.io.Serializable;

/**
 * Created by 12991 on 2018/3/10.
 */
public class InviteFriend implements Serializable{

    private static final long serialVersionUID = 1L;
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 图片logo
     */
    private String photo;
    /**
     * 描述
     */
    private String desc;
    /**
     * 连接地址
     */
    private String address;

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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
