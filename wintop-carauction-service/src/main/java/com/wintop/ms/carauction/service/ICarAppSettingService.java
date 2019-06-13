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
    public ServiceResult<CarAppSetting> getAcutionHint(Map<String, Object> map);

    /**
     * 获取首页所有有配置信息
     *
     * @return
     */
    public Map<String, Object> getAppSetting();

    /**
     * 修改首页配置信息
     *
     * @param map
     * @return
     */
    public int updateSelective(Map<String,Object> map);
}
