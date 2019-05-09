package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarEntrustRecord;
import com.wintop.ms.carauction.entity.CarEvaluateTagConf;
import com.wintop.ms.carauction.mapper.read.ICarEvaluateTagConfReadDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CarEvaluateTagConfModel {

    @Autowired
    private ICarEvaluateTagConfReadDao readDao;

    /**
     * 查询评价标签配置列表
     */
    public List<CarEvaluateTagConf> queryCarEvaluateTagConfList(Map<String,Object> map){
        return  readDao.queryCarEvaluateTagConfList(map);
    }

}
