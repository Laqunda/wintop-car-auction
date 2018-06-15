package com.wintop.ms.carauction.core.entity;

/***
 * android--app 版本更新
 */
public class AppVersion {
    private Long id;
    private String appName;//app名称
    private String newAppSize;//新app大小
    private String newAppVersionCode;//新app版本号
    private String newAppVersionName;//新app版本名
    private String newAppUpdateDesc;//新app更新描述
    private String newAppReleaseTime;//新app发布时间
    private String newAppUrl;//新app下载地址
    private String isForceUpdate;//是否强制更新:1不强制，2强制
    private String appType;//app类型：1android买家端，2android卖家端、 3iOS卖家端

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getNewAppSize() {
        return newAppSize;
    }

    public void setNewAppSize(String newAppSize) {
        this.newAppSize = newAppSize;
    }

    public String getNewAppVersionCode() {
        return newAppVersionCode;
    }

    public void setNewAppVersionCode(String newAppVersionCode) {
        this.newAppVersionCode = newAppVersionCode;
    }

    public String getNewAppVersionName() {
        return newAppVersionName;
    }

    public void setNewAppVersionName(String newAppVersionName) {
        this.newAppVersionName = newAppVersionName;
    }

    public String getNewAppUpdateDesc() {
        return newAppUpdateDesc;
    }

    public void setNewAppUpdateDesc(String newAppUpdateDesc) {
        this.newAppUpdateDesc = newAppUpdateDesc;
    }

    public String getNewAppReleaseTime() {
        return newAppReleaseTime;
    }

    public void setNewAppReleaseTime(String newAppReleaseTime) {
        this.newAppReleaseTime = newAppReleaseTime;
    }

    public String getNewAppUrl() {
        return newAppUrl;
    }

    public void setNewAppUrl(String newAppUrl) {
        this.newAppUrl = newAppUrl;
    }

    public String getIsForceUpdate() {
        return isForceUpdate;
    }

    public void setIsForceUpdate(String isForceUpdate) {
        this.isForceUpdate = isForceUpdate;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
