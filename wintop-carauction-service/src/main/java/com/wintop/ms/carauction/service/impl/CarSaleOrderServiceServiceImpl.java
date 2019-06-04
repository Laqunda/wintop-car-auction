package com.wintop.ms.carauction.service.impl;


import com.wintop.ms.carauction.entity.CarOrderRetail;
import com.wintop.ms.carauction.model.CarSaleOrderModel;
import com.wintop.ms.carauction.service.ICarSaleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CarSaleOrderServiceServiceImpl implements ICarSaleOrderService {

    @Autowired
    private CarSaleOrderModel carSaleOrderModel;

    @Override
    public List<CarOrderRetail> selectCarSaleOrder(Map<String, Object> paramMap) {
        return carSaleOrderModel.selectCarSaleOrder(paramMap);
    }

    @Override
    public CarOrderRetail selectCarSaleOrderRetail(Map<String, Object> paramMap) {
        return carSaleOrderModel.selectCarSaleRetail(paramMap);
    }

    @Override
    public Integer selectCarSaleOrderCount(Map<String, Object> paramMap) {
        return carSaleOrderModel.selectCarSaleOrderCount(paramMap);
    }
}
