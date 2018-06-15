package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAuctionCity;

import java.util.List;
import java.util.Map;

public interface ICarAuctionCityService {
    /**
     * 根据条件查询记录总数
     */
    ServiceResult<Integer> countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    ServiceResult<List<CarAuctionCity>> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    ServiceResult<CarAuctionCity> selectById(Long id);

    /**
     * 根据主键删除记录
     */
    ServiceResult<Integer> deleteById(Long id);

    /**
     * 根据主键逻辑删除记录
     */
    ServiceResult<Integer> updateDelById(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    ServiceResult<Integer> insert(CarAuctionCity carAuctionCity);

    /**
     * 根据主键更新属性不为空的记录
     */
    ServiceResult<Integer> updateByIdKeySelective(CarAuctionCity carAuctionCity);

    /**
     * 根据主键更新记录
     */
    ServiceResult<Integer> updateById(CarAuctionCity carAuctionCity);
}
