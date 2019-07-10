package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarEvaluateTagConf;

import java.util.List;
import java.util.Map;

/**
 * @author mazg
 * @Description: 评价标签配置
 * @date 2019-05-06
 */
public interface ICarEvaluateTagConfReadDao {

    /**
     * 查询评价标签配置列表
     */
    public List<CarEvaluateTagConf> queryCarEvaluateTagConfList(Map<String, Object> map);

    /**
     * 通过id进行查询
     *
     * @param id
     * @return
     */
    CarEvaluateTagConf selectByPrimaryKey(Long id);

    /**
     * 查询子节点数据
     * @param map
     * @return
     */
    List<String> selectChildrenTagConfList(Map<String, Object> map);
}
