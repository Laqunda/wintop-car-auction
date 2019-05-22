package com.wintop.ms.carauction.service.impl;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Maps;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAppSetting;
import com.wintop.ms.carauction.model.CarAppSettingModel;
import com.wintop.ms.carauction.service.ICarAppSettingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
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

    @Override
    public Map<String, Object> getAppSetting() {
        Map<String, Object> result = Maps.newHashMap();
        List<CarAppSetting> recordList = carAppSettingModel.selectAll();
        recordList.forEach(record -> {
            result.put(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, record.getCode()), record.getContent());
        });
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateSelective(Map<String,Object> map) {
        int result = 0;
        for (Map.Entry<String,Object> param:map.entrySet()) {
            try {
                CarAppSetting carAppSetting = new CarAppSetting();
                carAppSetting.setCode(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, param.getKey()));
                carAppSetting.setContent(param.getValue().toString());
                result = carAppSettingModel.updateSelective(carAppSetting);
            } catch (Exception e) {
                logger.info("修改首页配置错误",e);
                e.printStackTrace();
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
        }
        return result;
    }

    public static <T> boolean isNotEmpty(T t) {
        return Optional.ofNullable(t).isPresent();
    }
}
