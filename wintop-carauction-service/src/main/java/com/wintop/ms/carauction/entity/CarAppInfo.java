package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 12991 on 2018/2/27.
 */
public class CarAppInfo implements Serializable{
    private static final long serialVersionUID = 2735691942843174966L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 应用ID
     */
    private String appId;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 版本号
     */
    private String version;

    /**
     * 关于我们
     */
    private String about;

    /**
     * logo地址
     */
    private String logo;

    /**
     * 编辑人
     */
    private String editor;

    /**
     * 编辑时间
     */
    private Date editTime;

    /**
     * 客户端类型：1卖家，2买家，3代办，4中心
     */
    private String type;

    /**
     * 版本升级地址
     */
    private String updateUrl;

    private String tel;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

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
     * @return 应用ID
     */
    public String getAppId() {
        return appId;
    }

    /**
     * @param appId
     *            应用ID
     */
    public void setAppId(String appId) {
        this.appId = appId;
    }

    /**
     * @return 应用名称
     */
    public String getAppName() {
        return appName;
    }

    /**
     * @param appName
     *            应用名称
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }

    /**
     * @return 版本号
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version
     *            版本号
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return 关于我们
     */
    public String getAbout() {
        return about;
    }

    /**
     * @param about
     *            关于我们
     */
    public void setAbout(String about) {
        this.about = about;
    }

    /**
     * @return logo地址
     */
    public String getLogo() {
        return logo;
    }

    /**
     * @param logo
     *            logo地址
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /**
     * @return 编辑人
     */
    public String getEditor() {
        return editor;
    }

    /**
     * @param editor
     *            编辑人
     */
    public void setEditor(String editor) {
        this.editor = editor;
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
     * @return 客户端类型：1卖家，2买家，3代办，4中心
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            客户端类型：1卖家，2买家，3代办，4中心
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return 版本升级地址
     */
    public String getUpdateUrl() {
        return updateUrl;
    }

    /**
     * @param updateUrl
     *            版本升级地址
     */
    public void setUpdateUrl(String updateUrl) {
        this.updateUrl = updateUrl;
    }
}
