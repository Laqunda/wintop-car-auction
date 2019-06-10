package com.wintop.ms.carauction.mapper.write;


import com.wintop.ms.carauction.entity.CarChaboshiVinData;

public interface ICarChaboshiVinDataWriteDao {
    int deleteByPrimaryKey(Long id);

    int insertSelective(CarChaboshiVinData record);

    int updateByPrimaryKeySelective(CarChaboshiVinData record);

}