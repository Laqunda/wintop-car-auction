package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarCustomerSign;
import com.wintop.ms.carauction.entity.CarCustomerSignLog;

import java.util.List;
import java.util.Map;

/***
 * 用户签约服务
 */
public interface ICarCustomerSignService {

    ServiceResult<CarCustomerSign> findById(Long id);

    ServiceResult<CarCustomerSign> findByCustomerId(Long customerId);

    ServiceResult<List<CarCustomerSign>> findByParams(Map params);

    ServiceResult<CarCustomerSign> insert(CarCustomerSign carCustomerSign,CarCustomerSignLog carCustomerSignLog);

    ServiceResult<CarCustomerSign> saveAuth(CarCustomerSign carCustomerSign,CarCustomerSignLog carCustomerSignLog);

    CarCustomerSign querySignByUserId(Long customerId);
}
