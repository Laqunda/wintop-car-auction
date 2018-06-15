package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.entity.AppVersion;
import com.wintop.ms.carauction.model.CarAppVersionModel;
import com.wintop.ms.carauction.service.ICarAppVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 * 客户端版本信息
 */
@Service
public class CarAppVersionService implements ICarAppVersionService {

    @Autowired
    private CarAppVersionModel versionModel;

    @Override
    public AppVersion getNewByAppType(String appType) {
        return versionModel.getNewByAppType(appType);
    }
}
