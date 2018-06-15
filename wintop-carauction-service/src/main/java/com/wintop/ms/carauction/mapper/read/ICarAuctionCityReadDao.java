package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarAuctionCity;
import java.util.List;
import java.util.Map;

public interface ICarAuctionCityReadDao {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    List<CarAuctionCity> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    CarAuctionCity selectById(Long id);
}