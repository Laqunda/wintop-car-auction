package com.wintop.ms.carauction.service.impl;


import com.wintop.ms.carauction.entity.CarSaleOrder;
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
    public List<CarSaleOrder> selectCarSaleOrder(Map<String,Object> paramMap) {
        return carSaleOrderModel.selectCarSaleOrder(paramMap);
    }

    @Override
    public CarSaleOrder selectCarSaleOrderRetail(Map<String,Object> paramMap) {
        return carSaleOrderModel.selectCarSaleRetail(paramMap);
    }

    @Override
    public Integer selectCarSaleOrderCount(Long customerId) {
        return carSaleOrderModel.selectCarSaleOrderCount(customerId);
    }
}
