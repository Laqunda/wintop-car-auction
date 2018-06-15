package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.WtAppInfo;

/**
 * Created by liangtingsen on 2018/2/5.
 * 应用信息
 */
public interface IWtAppInfoReadDao {
    /**根据用户名查询用户信息*/
    WtAppInfo findByAppId(String appId);
}