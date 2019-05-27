package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarSaleOrder;

import java.util.List;
import java.util.Map;


public interface ICarSaleOrderReadDAO {
    List<CarSaleOrder> selectCarOrder(Map<String,Object> paramMap);

    List<CarSaleOrder> selectCarSaleOrder(Map<String,Object> paramMap);

    CarSaleOrder selectCarSaleRetail(Map<String,Object> paramMap);

    Integer selectCarOrderCount (Long customerId);

    Integer selectCarSaleOrderCount (Map<String,Object> paramMap);

    CarSaleOrder selectRetailById(Long id);
}
