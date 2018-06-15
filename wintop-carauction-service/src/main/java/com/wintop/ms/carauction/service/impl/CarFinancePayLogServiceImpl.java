package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarFinancePayLog;
import com.wintop.ms.carauction.model.CarFinancePayLogModel;
import com.wintop.ms.carauction.service.ICarFinancePayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by 12991 on 2018/2/27.
 */
@Service
public class CarFinancePayLogServiceImpl implements ICarFinancePayLogService {
    @Autowired
    private CarFinancePayLogModel model;
    @Override
    public ServiceResult<Integer> countByExample(Map<String, Object> map) {
        Integer count = model.countByExample(map);
        return new ServiceResult<>(true,count);
    }

    @Override
    public ServiceResult<List<CarFinancePayLog>> selectByExample(Map<String, Object> map) {
        List<CarFinancePayLog> list = model.selectByExample(map);
        return new ServiceResult<>(true,list);
    }

    @Override
    public ServiceResult<CarFinancePayLog> selectById(Long id) {
        CarFinancePayLog carFinancePayLog =  model.selectById(id);
        return new ServiceResult<>(true,carFinancePayLog);
    }

    @Override
    public ServiceResult<Integer> insert(CarFinancePayLog carFinancePayLog) {
        Integer count = model.insert(carFinancePayLog);
        return new ServiceResult<>(true,count);
    }

}
