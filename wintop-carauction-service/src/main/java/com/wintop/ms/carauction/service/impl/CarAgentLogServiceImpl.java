package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAgentLog;
import com.wintop.ms.carauction.model.CarAgentLogModel;
import com.wintop.ms.carauction.service.ICarAgentLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CarAgentLogServiceImpl implements ICarAgentLogService {
    @Autowired
    private CarAgentLogModel model;
    @Override
    public ServiceResult<Integer> countByExample(Map<String, Object> map) {
        Integer count = model.countByExample(map);
        return new ServiceResult<>(true,count);
    }

    @Override
    public ServiceResult<List<CarAgentLog>> selectByExample(Map<String, Object> map) {
        List<CarAgentLog> list = model.selectByExample(map);
        return new ServiceResult<>(true,list);
    }

    @Override
    public ServiceResult<CarAgentLog> selectById(Long id) {
        CarAgentLog carAgentLog = model.selectById(id);
        return new ServiceResult<>(true,carAgentLog);
    }

    @Override
    public ServiceResult<Integer> deleteById(Long id) {
        Integer count = model.deleteById(id);
        return new ServiceResult<>(true,count);
    }

    @Override
    public ServiceResult<Integer> insert(CarAgentLog carAgentLog) {
        Integer count = model.insert(carAgentLog);
        return new ServiceResult<>(true,count);
    }

    @Override
    public ServiceResult<Integer> updateByIdSelective(CarAgentLog carAgentLog) {
        Integer count = model.updateByIdSelective(carAgentLog);
        return new ServiceResult<>(true,count);
    }

    @Override
    public ServiceResult<Integer> updateById(CarAgentLog carAgentLog) {
        Integer count = model.updateById(carAgentLog);
        return new ServiceResult<>(true,count);
    }
}
