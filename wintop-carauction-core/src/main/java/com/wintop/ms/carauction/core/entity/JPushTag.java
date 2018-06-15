package com.wintop.ms.carauction.core.entity;

/**
 * Created by liangtingsen on 2018/3/7.
 * jpush推送消息 使用 用户对象
 */
public class JPushTag {
    private String appId;

    private String  userId;

    public JPushTag(String appId,String userId){
        this.appId = appId;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return appId+"_"+userId;
    }
}
