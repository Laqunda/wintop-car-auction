package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarEvaluateLevelConf;

import java.util.List;
import java.util.Map;

public interface ICarEvaluateLevelConfService {

    /**
     * 查询星级评价列表
     */
    public List<CarEvaluateLevelConf> queryCarEvaluateLevelConfList(Map<String, Object> map);
}
