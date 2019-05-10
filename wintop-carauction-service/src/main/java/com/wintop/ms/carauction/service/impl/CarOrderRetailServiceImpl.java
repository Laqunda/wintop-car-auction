package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.entity.CarOrderRetail;
import com.wintop.ms.carauction.model.CarOrderRetailModel;
import com.wintop.ms.carauction.service.ICarOrderRetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("carOrderRetailService")
public class CarOrderRetailServiceImpl implements ICarOrderRetailService {

    @Autowired
    private CarOrderRetailModel carOrderRetailModel;

    @Override
    public int insertSelective(CarOrderRetail record) {
        return carOrderRetailModel.insertSelective(record);
    }
}
