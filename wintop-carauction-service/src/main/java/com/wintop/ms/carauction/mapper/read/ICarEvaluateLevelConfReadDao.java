package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarEvaluateLevelConf;
import com.wintop.ms.carauction.entity.CarEvaluateTagConf;

import java.util.List;
import java.util.Map;

public interface ICarEvaluateLevelConfReadDao {

    /**
     * 查询星级评价列表
     */
    List<CarEvaluateLevelConf> queryCarEvaluateLevelConfList(Map<String, Object> map);


}
