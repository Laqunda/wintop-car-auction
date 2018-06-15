package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAppAccord;

import java.util.List;
import java.util.Map;

/**
 * Created by 12991 on 2018/2/26.
 */
public interface ICarAppAccordService {

    /**
     * 根据条件查询记录总数
     */
    public ServiceResult<Integer> countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    ServiceResult<List<CarAppAccord>> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    ServiceResult<CarAppAccord> selectById(Long id);
    /**
     * 根据主键删除记录
     */
    ServiceResult<Integer> deleteById(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    ServiceResult<Integer> insert(CarAppAccord carAppAccord);

    /**
     * 根据主键更新属性不为空的记录
     */
    ServiceResult<Integer> updateByIdSelective(CarAppAccord carAppAccord);

    /**
     * 根据主键更新记录
     */
    ServiceResult<Integer> updateById(CarAppAccord carAppAccord);

    /***
     * 根据编号查询协议内容
     * @param code
     * @return
     */
    ServiceResult<CarAppAccord> findByCode(String code);
}
