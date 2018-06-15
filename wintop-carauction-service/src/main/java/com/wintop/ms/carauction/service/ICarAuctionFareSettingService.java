package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAuctionFareSetting;

import java.util.List;
import java.util.Map;

/**
 * Created by 12991 on 2018/2/27.
 */
public interface ICarAuctionFareSettingService {

    /**
     * 根据条件查询记录总数
     */
    public ServiceResult<Integer> countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    ServiceResult<List<CarAuctionFareSetting>> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    ServiceResult<CarAuctionFareSetting> selectById(Long id);
    /**
     * 根据主键删除记录
     */
    ServiceResult<Integer> deleteById(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    ServiceResult<Integer> insert(CarAuctionFareSetting carAuctionFareSetting);

    /**
     * 根据主键更新属性不为空的记录
     */
    ServiceResult<Integer> updateByIdSelective(CarAuctionFareSetting carAuctionFareSetting);

    /**
     * 根据主键更新记录
     */
    ServiceResult<Integer> updateById(CarAuctionFareSetting carAuctionFareSetting);

    /**
     * 我要竞价接口
     * @param customerId
     * @return
     */
    List<CarAuctionFareSetting> selectMyFareList( Long customerId);

    /**
     * 查询所有的价格
     */
     ServiceResult<List<CarAuctionFareSetting>> selectAllFare();

    /**
     * 查询自动出价加价最高金额
     * @param customerId
     * @return
     */
    public CarAuctionFareSetting selectEnableMaxFare(Long customerId);
}
