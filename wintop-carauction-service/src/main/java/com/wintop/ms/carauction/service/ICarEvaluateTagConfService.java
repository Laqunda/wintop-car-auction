package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarEvaluateTagConf;

import java.util.List;
import java.util.Map;

public interface ICarEvaluateTagConfService {

    /**
     * 查询评价标签配置列表
     */
    public Map<String, List<CarEvaluateTagConf>> queryEvaluateTagConfTreeList(Map<String, Object> param);

    /**
     * 查询评价标签配置列表
     */
    public List<CarEvaluateTagConf> queryEvaluateTagConfList(Map<String, Object> param);

    /**
     * 通过id进行查询
     * @param id
     * @return
     */
    public CarEvaluateTagConf selectByPrimaryKey(Long id);
    /**
     * 查询子节点数据
     * @param map
     * @return
     */
    public Map<Object, List<Map<String, Object>>> selectChildrenTagConfList(Map<String, Object> map);
    /**
     * 插入数据
     * @param carEvaluateTagConf
     * @return
     */
    public Integer insertSelective(CarEvaluateTagConf carEvaluateTagConf);
    /**
     * 修改数据
     * @param carEvaluateTagConf
     * @return
     */
    public Integer updateByPrimaryKeySelective(CarEvaluateTagConf carEvaluateTagConf);
    /**
     * 删除数据
     * @param id
     * @return
     */
    public Integer deleteByPrimaryKey(Long id);
    /**
     * 删除数据
     * @param map
     * @return
     */
    public Integer deleteByCondition(Map<String, Object> map);
}
