package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.entity.CarOrderRetail;
import com.wintop.ms.carauction.entity.CarSaleOrder;

public interface ICarOrderRetailService {

    /**
     * 插入记录
     * @param record
     * @return
     */
    public void insertSelective(CarOrderRetail record);

    /**
     * 订单明细表
     * @param id
     * @return
     */
    public CarSaleOrder selectRetailById(Long id);
}
