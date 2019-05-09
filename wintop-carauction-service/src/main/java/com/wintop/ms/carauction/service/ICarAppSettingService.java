package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAppSetting;

import java.util.Map;

public interface ICarAppSettingService {

    /**
     * 获取竞价提示
     *
     * @param map
     * @return
     */
    public ServiceResult<Map<String,Object>> getAcutionHint(Map<String, Object> map);
}
