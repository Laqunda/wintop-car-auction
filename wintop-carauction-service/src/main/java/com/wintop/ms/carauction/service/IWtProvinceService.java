package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.WtProvince;

import java.util.List;
import java.util.Map;

/***
 * 省直辖市使用
 */
public interface IWtProvinceService {
    ServiceResult<WtProvince> findById(Long id);


    ServiceResult<List<WtProvince>> findAll(Map map);
}
