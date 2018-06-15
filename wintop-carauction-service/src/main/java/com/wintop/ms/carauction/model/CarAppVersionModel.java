package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.core.entity.AppVersion;
import com.wintop.ms.carauction.mapper.read.ICarAppVersionReadDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/***
 * 客户端版本信息
 */
@Repository
public class CarAppVersionModel {

    @Autowired
    private ICarAppVersionReadDao readDao;

    /***
     * 获取客户端最新版本
     * @param appType
     * @return
     */
    public AppVersion getNewByAppType(String appType){
        return readDao.findNewByAppType(appType);
    }

}
