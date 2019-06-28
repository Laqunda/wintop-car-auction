package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarEvaluateTagConf;

import java.util.Map;

public interface ICarEvaluateTagConfWriteDao {

    /**
     * 插入数据
     * @param carEvaluateTagConf
     * @return
     */
    Integer insertSelective(CarEvaluateTagConf carEvaluateTagConf);
    /**
     * 修改数据
     * @param carEvaluateTagConf
     * @return
     */
    Integer updateByPrimaryKeySelective(CarEvaluateTagConf carEvaluateTagConf);
    /**
     * 删除数据
     * @param id
     * @return
     */
    Integer deleteByPrimaryKey(Long id);
    /**
     * 删除数据
     * @param map
     * @return
     */
    Integer deleteByCondition(Map<String,Object> map);
}
