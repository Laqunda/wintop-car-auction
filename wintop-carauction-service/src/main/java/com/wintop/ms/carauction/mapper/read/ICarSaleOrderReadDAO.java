package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarSaleOrder;

import java.util.List;


public interface ICarSaleOrderReadDAO {
    List<CarSaleOrder> selectCarOrder(Long customerId);

    List<CarSaleOrder> selectCarSaleOrder(Long customerId);

    CarSaleOrder selectCarSaleRetail(Long customerId);

    Integer selectCarOrderCount (Long customerId);

    Integer selectCarSaleOrderCount (Long customerId);
}
