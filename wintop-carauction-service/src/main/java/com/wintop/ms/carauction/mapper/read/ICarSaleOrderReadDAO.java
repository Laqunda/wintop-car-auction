package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarOrderRetail;

import java.util.List;
import java.util.Map;


public interface ICarSaleOrderReadDAO {
    List<CarOrderRetail> selectCarOrder(Map<String,Object> paramMap);

    List<CarOrderRetail> selectCarSaleOrder(Map<String,Object> paramMap);

    CarOrderRetail selectCarSaleRetail(Map<String,Object> paramMap);

    Integer selectCarOrderCount (Long customerId);

    Integer selectCarSaleOrderCount (Map<String,Object> paramMap);

    CarOrderRetail selectRetailById(Long id);
}
