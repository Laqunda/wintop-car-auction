package com.wintop.ms.carauction.service.impl;

import com.google.common.collect.Maps;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAppSetting;
import com.wintop.ms.carauction.model.CarAppSettingModel;
import com.wintop.ms.carauction.service.ICarAppSettingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service("carAppSettingService")
public class CarAppSettingServiceImpl implements ICarAppSettingService {
    private static final Logger logger = LoggerFactory.getLogger(CarAppSettingServiceImpl.class);
    @Autowired
    private CarAppSettingModel carAppSettingModel;

    /**
     * 获取竞价提示
     * @param map
     * @return
     */
    @Override
    public ServiceResult<Map<String,Object>> getAcutionHint(Map<String, Object> map) {
        ServiceResult<Map<String, Object>> result = new ServiceResult<>();
        try {
            Map<String, Object> data = Maps.newHashMap();
            CarAppSetting carAppSetting = carAppSettingModel.selectByCode(map);
            if (isNotEmpty(carAppSetting)) {
                data.put("result", Boolean.valueOf(carAppSetting.getContent()));
            } else {
                data.put("result", Boolean.FALSE);
            }
            result.setResult(data);
            result.setSuccess("0","成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.setError("-1","异常");
        }
        return result;
    }

    public static <T> boolean isNotEmpty(T t) {
        return Optional.ofNullable(t).isPresent();
    }
}
