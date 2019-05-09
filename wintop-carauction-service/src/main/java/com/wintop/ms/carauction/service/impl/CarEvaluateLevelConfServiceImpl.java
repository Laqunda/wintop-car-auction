package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarEvaluateLevelConf;
import com.wintop.ms.carauction.model.CarEvaluateLevelConfModel;
import com.wintop.ms.carauction.service.ICarEvaluateLevelConfService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("carEvaluateLevelConfService")
public class CarEvaluateLevelConfServiceImpl implements ICarEvaluateLevelConfService {

    @Resource
    private CarEvaluateLevelConfModel carEvaluateLevelConfModel;

    @Override
    public List<CarEvaluateLevelConf> queryCarEvaluateLevelConfList(Map<String, Object> map) {
        List<CarEvaluateLevelConf> list = carEvaluateLevelConfModel.queryCarEvaluateLevelConfList(map);
        return list;
    }
}
