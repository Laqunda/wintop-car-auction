package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarLocaleAuctionCar;

public interface ICarLocaleAuctionCarWriteDao {

    /**
     * 根据主键删除记录
     */
    int deleteById(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(CarLocaleAuctionCar carAuctionCar);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByIdSelective(CarLocaleAuctionCar carAuctionCar);

    /**
     * 根据主键更新记录
     */
    int updateById(CarLocaleAuctionCar carAuctionCar);
}