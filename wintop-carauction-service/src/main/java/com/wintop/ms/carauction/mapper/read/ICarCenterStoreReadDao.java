package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CommonNameVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ICarCenterStoreReadDao {

    /**
     * 查询所有记录集
     */
    List<CommonNameVo> selectAllStore(@Param("centerId") Long centerId);

    /**
     * 查询单个中心所有店铺ID集合
     * @return
     */
    List<Long> selectAllStoreIds(Long centId);

    /**
     * 根据条件查询出结果
     * @param map
     * @return
     */
    List<CommonNameVo> selectByCondition(Map<String, Object> map);
}