package com.wintop.ms.carauction.service;


import com.wintop.ms.carauction.entity.CarSaleOrder;

import java.util.List;
import java.util.Map;

public interface ICarSaleOrderService {

    List<CarSaleOrder> selectCarSaleOrder(Map<String,Object> paramMap);

    CarSaleOrder selectCarSaleOrderRetail(Map<String,Object> paramMap);

    Integer selectCarSaleOrderCount (Long customerId);

}
