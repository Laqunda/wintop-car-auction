package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarOrderLog;

import java.util.List;
import java.util.Map;

public interface ICarOrderLogService {
    /**
     * 根据条件查询记录总数
     */
    Integer countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    List<CarOrderLog> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    CarOrderLog selectById(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    Integer insert(CarOrderLog carOrderLog);

}
