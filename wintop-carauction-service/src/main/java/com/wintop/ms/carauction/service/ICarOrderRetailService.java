package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.entity.CarOrderRetail;

public interface ICarOrderRetailService {

    /**
     * 插入记录
     * @param record
     * @return
     */
    public int insertSelective(CarOrderRetail record);
}
