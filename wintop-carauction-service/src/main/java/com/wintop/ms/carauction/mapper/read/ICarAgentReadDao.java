package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarAgent;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ICarAgentReadDao {
    /**
     * 根据条件查询记录总数
     */
    int countByParam(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    List<CarAgent> selectListByParam(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    CarAgent selectById(Long id);

    CarAgent selectByOrderId(@Param(value = "orderId") Long orderId);
}