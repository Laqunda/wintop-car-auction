package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarEvaluateData;

public interface ICarEvaluateDataWriteDao {
    Integer insertSelective(CarEvaluateData carEvaluateData);
}