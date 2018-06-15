package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarCustomerSignLog;

/***
 * 会员签约日志表
 */
public interface ICarCustomerSignLogWriteDao {

    void insert(CarCustomerSignLog carCustomerSign);

}
