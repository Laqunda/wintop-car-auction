package com.wintop.ms.carauction.service;


import com.wintop.ms.carauction.entity.CarSaleOrder;

import java.util.List;
import java.util.Map;

public interface ICarSaleOrderService {

    List<CarSaleOrder> selectCarOrder(Map<String,Object> paramMap);

    List<CarSaleOrder> selectCarSaleOrder(Map<String,Object> paramMap);

    CarSaleOrder selectCarSaleOrderRetail(Long customerId);

    Integer selectCarOrderCount (Long customerId);

    Integer selectCarSaleOrderCount (Long customerId);

}
