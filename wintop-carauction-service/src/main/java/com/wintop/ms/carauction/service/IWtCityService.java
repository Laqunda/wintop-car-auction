package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.WtCity;

import java.util.List;
import java.util.Map;

/***
 * 地区市使用
 */
public interface IWtCityService {
    ServiceResult<WtCity> findById(Long id);

    
    ServiceResult<List<WtCity>> findAll(Map map);
}
