package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.AppVersion;

/***
 * 客户端版本信息
 */
public interface ICarAppVersionService {

    /***
     * 获取客户端最新版本
     * @param appType
     * @return
     */
    AppVersion getNewByAppType(String appType);

}
