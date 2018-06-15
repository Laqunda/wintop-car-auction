package com.wintop.ms.carauction.core.model;

/**
 * 用户传递存放接口安全校验使用
 */
public class TokenModel {
    //客户端应用id
    private String appId;

    //用户id
    private Long userId;

    //随机生成的uuid
    private String token;

    public TokenModel(String appId,Long userId, String token) {
        this.appId = appId;
        this.userId = userId;
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getRedisKey(){
        return appId+"_"+userId+"_1";
    }

}
