package com.wintop.ms.carauction.service;


import com.wintop.ms.carauction.entity.CarOrderRetail;

import java.util.List;
import java.util.Map;

public interface ICarSaleOrderService {

    List<CarOrderRetail> selectCarSaleOrder(Map<String,Object> paramMap);

    CarOrderRetail selectCarSaleOrderRetail(Map<String,Object> paramMap);

    Integer selectCarSaleOrderCount (Map<String,Object> paramMap);

}
