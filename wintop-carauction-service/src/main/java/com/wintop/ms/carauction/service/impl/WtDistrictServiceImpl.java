package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.WtCity;
import com.wintop.ms.carauction.entity.WtDistrict;
import com.wintop.ms.carauction.mapper.read.IWtCityReadDao;
import com.wintop.ms.carauction.mapper.read.IWtDistrictReadDao;
import com.wintop.ms.carauction.service.IWtCityService;
import com.wintop.ms.carauction.service.IWtDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/***
 * 区县使用
 */
@Service
public class WtDistrictServiceImpl implements IWtDistrictService {

    @Autowired
    private IWtDistrictReadDao readDao;

    @Override
    public ServiceResult<WtDistrict> findById(Long id) {
        ServiceResult<WtDistrict> result = new ServiceResult<>();
        result.setResult(readDao.findById(id));
        result.setSuccess("0","成功");
        return result;
    }

    @Override
    public ServiceResult<List<WtDistrict>> findAll(Map map) {
        ServiceResult<List<WtDistrict>> result = new ServiceResult<>();
        result.setResult(readDao.findAll(map));
        result.setSuccess("0","成功");
        return result;
    }
}
