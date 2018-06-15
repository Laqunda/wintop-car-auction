package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarFinancePlatformRecord;
import com.wintop.ms.carauction.model.CarFinancePlatformRecordModel;
import com.wintop.ms.carauction.service.ICarFinancePlatformRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by 12991 on 2018/2/27.
 */
@Service
public class CarFinancePlatformRecordServiceImpl implements ICarFinancePlatformRecordService {
    @Autowired
    private CarFinancePlatformRecordModel model;
    @Override
    public ServiceResult<Integer> countByExample(Map<String, Object> map) {
        Integer count = model.countByExample(map);
        return new ServiceResult<>(true,count);
    }

    @Override
    public ServiceResult<List<CarFinancePlatformRecord>> selectByExample(Map<String, Object> map) {
        List<CarFinancePlatformRecord> list = model.selectByExample(map);
        return new ServiceResult<>(true,list);
    }

    @Override
    public ServiceResult<CarFinancePlatformRecord> selectById(Long id) {
        CarFinancePlatformRecord carFinancePlatformRecord =  model.selectById(id);
        return new ServiceResult<>(true,carFinancePlatformRecord);
    }

    @Override
    public ServiceResult<Integer> deleteById(Long id) {
        Integer count = model.deleteById(id);
        return new ServiceResult<>(true,count);
    }

    @Override
    public ServiceResult<Integer> insert(CarFinancePlatformRecord carFinancePlatformRecord) {
        Integer count = model.insert(carFinancePlatformRecord);
        return new ServiceResult<>(true,count);
    }

    @Override
    public ServiceResult<Integer> updateByIdSelective(CarFinancePlatformRecord carFinancePlatformRecord) {
        Integer count = model.updateByIdSelective(carFinancePlatformRecord);
        return new ServiceResult<>(true,count);
    }

    @Override
    public ServiceResult<Integer> updateById(CarFinancePlatformRecord carFinancePlatformRecord) {
        Integer count = model.updateById(carFinancePlatformRecord);
        return new ServiceResult<>(true,count);
    }
}
