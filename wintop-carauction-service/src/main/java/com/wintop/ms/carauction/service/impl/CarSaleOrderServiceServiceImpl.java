package com.wintop.ms.carauction.service.impl;


import com.wintop.ms.carauction.entity.CarSaleOrder;
import com.wintop.ms.carauction.model.CarSaleOrderModel;
import com.wintop.ms.carauction.service.ICarSaleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CarSaleOrderServiceServiceImpl implements ICarSaleOrderService {

    @Autowired
    private CarSaleOrderModel carSaleOrderModel;

    @Override
    public List<CarSaleOrder> selectCarOrder(Long customerId) {
        return carSaleOrderModel.selectCarOrder(customerId);
    }

    @Override
    public List<CarSaleOrder> selectCarSaleOrder(Long customerId) {
        return carSaleOrderModel.selectCarSaleOrder(customerId);
    }

    @Override
    public CarSaleOrder selectCarSaleOrderRetail(Long customerId) {
        return carSaleOrderModel.selectCarSaleRetail(customerId);
    }

    @Override
    public Integer selectCarOrderCount(Long customerId) {
        return carSaleOrderModel.selectCarOrderCount(customerId);
    }

    @Override
    public Integer selectCarSaleOrderCount(Long customerId) {
        return carSaleOrderModel.selectCarSaleOrderCount(customerId);
    }
}
