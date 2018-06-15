package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarCustomerSignLog;

import java.util.List;

/***
 * 用户签约日志服务
 */
public interface ICarCustomerSignLogService {

    ServiceResult<CarCustomerSignLog> findById(Long id);

    ServiceResult<List<CarCustomerSignLog>> findBySignId (Long signId);

    void insert(CarCustomerSignLog carCustomerSignLog);

}
