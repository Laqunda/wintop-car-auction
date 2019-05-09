package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarEvaluateData;
import com.wintop.ms.carauction.mapper.write.ICarEvaluateDataWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CarEvaluateDataModel {

    @Autowired
    private ICarEvaluateDataWriteDao writeDao;

    public Integer insertSelective(CarEvaluateData carEvaluateData){
        return writeDao.insertSelective(carEvaluateData);
    }
}
