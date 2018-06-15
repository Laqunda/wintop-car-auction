package com.wintop.ms.carauction.entity;

import java.io.Serializable;
import java.util.Date;

    /**
    * AppUserToken:用户token设置实体
    * @author zhangzijuan
    * @date 2018-02-08
    */
public class AppUserToken implements Serializable {
    private static final long serialVersionUID = -2925788042202341921L;
    /**
     * 用户注册ID
     */
    private Long id;

    /**
     * 用户名，默认同手机号
     */
    private String userId;

    /**
     * 应用ID，其他平台需要与运通用户打通，需要创建应用并生成appId，例如二手车业务
     */
    private String appId;

    /**
     * 登陆授权token信息
     */
    private String token;

    /**
     * 运通用户体系对外唯一标示
     */
    private String openId;

    /**
     * token刷新时间
     */
    private Date updateTime;

    /**
     * 凭证有效期时长：单位秒默认24小时
     */
    private Long expiresin;

    /**
     * @return 用户注册ID
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id 
	 *            用户注册ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 用户名，默认同手机号
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId 
	 *            用户名，默认同手机号
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return 应用ID，其他平台需要与运通用户打通，需要创建应用并生成appId，例如二手车业务
     */
    public String getAppId() {
        return appId;
    }

    /**
     * @param appId 
	 *            应用ID，其他平台需要与运通用户打通，需要创建应用并生成appId，例如二手车业务
     */
    public void setAppId(String appId) {
        this.appId = appId;
    }

    /**
     * @return 登陆授权token信息
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token 
	 *            登陆授权token信息
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return 运通用户体系对外唯一标示
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * @param openId 
	 *            运通用户体系对外唯一标示
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * @return token刷新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime 
	 *            token刷新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return 凭证有效期时长：单位秒默认24小时
     */
    public Long getExpiresin() {
        return expiresin;
    }

    /**
     * @param expiresin 
	 *            凭证有效期时长：单位秒默认24小时
     */
    public void setExpiresin(Long expiresin) {
        this.expiresin = expiresin;
    }
}