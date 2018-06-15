package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.WtDistrict;

import java.util.List;
import java.util.Map;

/***
 * 地区市使用
 */
public interface IWtDistrictService {
    ServiceResult<WtDistrict> findById(Long id);

    
    ServiceResult<List<WtDistrict>> findAll(Map map);
}
