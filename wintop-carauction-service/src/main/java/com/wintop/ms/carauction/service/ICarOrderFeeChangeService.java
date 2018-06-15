package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarOrderFeeChange;

import java.util.List;
import java.util.Map;

public interface ICarOrderFeeChangeService {

    /**
     * 根据条件查询记录总数
     */
    ServiceResult<Integer> countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    ServiceResult<List<CarOrderFeeChange>> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    ServiceResult<CarOrderFeeChange> selectById(Long id);

    /**
     * 根据主键删除记录
     */
    ServiceResult<Integer> deleteById(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    ServiceResult<Integer> insert(CarOrderFeeChange carOrderFeeChange);

    /**
     * 根据主键更新属性不为空的记录
     */
    ServiceResult<Integer> updateByIdSelective(CarOrderFeeChange carOrderFeeChange);

    /**
     * 根据主键更新记录
     */
    ServiceResult<Integer> updateById(CarOrderFeeChange carOrderFeeChange);

}
