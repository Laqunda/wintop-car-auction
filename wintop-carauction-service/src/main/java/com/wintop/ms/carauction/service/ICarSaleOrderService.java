package com.wintop.ms.carauction.service;


import com.wintop.ms.carauction.entity.CarSaleOrder;

import java.util.List;
import java.util.Map;

public interface ICarSaleOrderService {

    List<CarSaleOrder> selectCarOrder(Long customerId);

    List<CarSaleOrder> selectCarSaleOrder(Long customerId);

    CarSaleOrder selectCarSaleOrderRetail(Long customerId);

    Integer selectCarOrderCount (Long customerId);

    Integer selectCarSaleOrderCount (Long customerId);

}
