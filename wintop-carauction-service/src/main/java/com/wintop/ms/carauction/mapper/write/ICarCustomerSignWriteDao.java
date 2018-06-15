package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarCustomerSign;

/***
 * 会员签约主表
 */
public interface ICarCustomerSignWriteDao {
    void insert(CarCustomerSign carCustomerSign);

    void update(CarCustomerSign carCustomerSign);


    Integer insertSelective(CarCustomerSign carCustomerSign);

    Integer updateByPrimaryKeySelective(CarCustomerSign carCustomerSign);

    Integer updateByUserId(CarCustomerSign carCustomerSign);
}