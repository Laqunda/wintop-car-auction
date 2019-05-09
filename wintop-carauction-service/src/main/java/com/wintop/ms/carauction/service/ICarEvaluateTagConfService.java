package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarEvaluateTagConf;

import java.util.List;
import java.util.Map;

public interface ICarEvaluateTagConfService {

    public Map<String, List<CarEvaluateTagConf>> queryEvaluateTagConfTreeList(Map<String, Object> param);
}
