package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarEvaluateData;

import java.util.Map;

public interface ICarEvaluateDataService {

    /**
     * 保存评价数据
     * @param carEvaluateData
     * @return
     */
    public ServiceResult<Map<String,Object>> insertSelective(CarEvaluateData carEvaluateData)  ;
}
