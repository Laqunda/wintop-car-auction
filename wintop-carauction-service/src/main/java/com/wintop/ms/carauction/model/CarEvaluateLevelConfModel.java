package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarEvaluateLevelConf;
import com.wintop.ms.carauction.mapper.read.ICarEvaluateLevelConfReadDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CarEvaluateLevelConfModel {

    @Autowired
    private ICarEvaluateLevelConfReadDao readDao;

    /**
     * 查询星级评价列表
     */
    public List<CarEvaluateLevelConf> queryCarEvaluateLevelConfList(Map<String, Object> map) {
        return readDao.queryCarEvaluateLevelConfList(map);
    }

}
