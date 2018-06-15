package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.WtCity;
import com.wintop.ms.carauction.entity.WtProvince;
import com.wintop.ms.carauction.mapper.read.IWtCityReadDao;
import com.wintop.ms.carauction.mapper.read.IWtProvinceReadDao;
import com.wintop.ms.carauction.service.IWtCityService;
import com.wintop.ms.carauction.service.IWtProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/***
 * 地区市使用
 */
@Service
public class WtCityServiceImpl implements IWtCityService {

    @Autowired
    private IWtCityReadDao readDao;

    @Override
    public ServiceResult<WtCity> findById(Long id) {
        ServiceResult<WtCity> result = new ServiceResult<>();
        result.setResult(readDao.findById(id));
        result.setSuccess("0","成功");
        return result;
    }

    @Override
    public ServiceResult<List<WtCity>> findAll(Map map) {
        ServiceResult<List<WtCity>> result = new ServiceResult<>();
        result.setResult(readDao.findAll(map));
        result.setSuccess("0","成功");
        return result;
    }
}
