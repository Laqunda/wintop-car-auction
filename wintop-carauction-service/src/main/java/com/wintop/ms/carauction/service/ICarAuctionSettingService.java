package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAuctionSetting;

import java.util.List;
import java.util.Map;

public interface ICarAuctionSettingService {
    /**
     * 根据条件查询记录总数
     */
    public Integer countByExample(Map<String,Object> map);


    /**
     * 根据条件查询记录集
     */
    public List<CarAuctionSetting> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    public CarAuctionSetting selectById(Long id);

    /**
     * 根据主键查询记录
     */
    public CarAuctionSetting selectByRegionId(Long regionId);

    /**
     * 根据主键删除记录
     */
    public Integer deleteById(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    public Integer insert(CarAuctionSetting record);

    /**
     * 根据主键更新属性不为空的记录
     */
    public Integer updateByIdSelective(CarAuctionSetting record);

    /**
     * 根据主键更新记录
     */
    public Integer updateById(CarAuctionSetting record);
}
