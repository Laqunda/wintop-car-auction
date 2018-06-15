package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.WtProvince;
import com.wintop.ms.carauction.mapper.read.IWtProvinceReadDao;
import com.wintop.ms.carauction.service.IWtProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/***
 * 省直辖市使用
 */
@Service
public class WtProvinceServiceImpl implements IWtProvinceService {

    @Autowired
    private IWtProvinceReadDao readDao;

    @Override
    public ServiceResult<WtProvince> findById(Long id) {
        ServiceResult<WtProvince> result = new ServiceResult<>();
        result.setResult(readDao.findById(id));
        result.setSuccess("0","成功");
        return result;
    }

    @Override
    public ServiceResult<List<WtProvince>> findAll(Map map) {
        ServiceResult<List<WtProvince>> result = new ServiceResult<>();
        result.setResult(readDao.findAll(map));
        result.setSuccess("0","成功");
        return result;
    }
}
