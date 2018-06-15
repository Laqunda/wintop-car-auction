package com.wintop.ms.carauction.entity;

import java.sql.Timestamp;

/**
 * 客户端应用配置表
 * Created by liangtingsen on 2018/2/3.
 */
public class WtAppInfo {
    private Long id;//主键
    private String appId;//应用标示
    private String version;//版本
    private String about;//关于
    private String logo;//logo
    private String editor;//编辑人
    private Timestamp editTime;//编辑时间
    private String type;//类型：客户端类型：1买家，2管理端（店铺、中心、代办）
    private String updateUrl;//客户端更新应用Url

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public Timestamp getEditTime() {
        return editTime;
    }

    public void setEditTime(Timestamp editTime) {
        this.editTime = editTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpdateUrl() {
        return updateUrl;
    }

    public void setUpdateUrl(String updateUrl) {
        this.updateUrl = updateUrl;
    }
}
