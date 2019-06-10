package com.wintop.ms.carauction.mapper.read;


import com.wintop.ms.carauction.entity.CarChaboshiVinData;

import java.util.List;
import java.util.Map;

public interface ICarChaboshiVinDataReadDao {

    CarChaboshiVinData selectByPrimaryKey(Long id);

    List<CarChaboshiVinData> selectByCondition(Map<String, Object> param);
}