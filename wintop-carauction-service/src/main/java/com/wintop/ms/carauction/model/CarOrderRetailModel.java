package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarOrderRetail;
import com.wintop.ms.carauction.mapper.write.ICarOrderRetailWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CarOrderRetailModel {

    @Autowired
    private ICarOrderRetailWriteDao carOrderRetailWriteDao;

    public int insertSelective(CarOrderRetail record){
        return carOrderRetailWriteDao.insertSelective(record);
    }
}
