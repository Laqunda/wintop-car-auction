package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarOrderLog;
import com.wintop.ms.carauction.model.CarOrderLogModel;
import com.wintop.ms.carauction.service.ICarOrderLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CarOrderLogServiceImpl implements ICarOrderLogService {
    @Autowired
    private CarOrderLogModel model;
    @Override
    public Integer countByExample(Map<String, Object> map) {
        Integer count = model.countByExample(map);
        return count;
    }

    @Override
    public List<CarOrderLog> selectByExample(Map<String, Object> map) {
        List<CarOrderLog> list = model.selectByExample(map);
        return list;
    }

    @Override
    public CarOrderLog selectById(Long id) {
        CarOrderLog carOrderLog = model.selectById(id);
        return carOrderLog;
    }

    @Override
    public Integer insert(CarOrderLog carOrderLog) {
        Integer count = model.insert(carOrderLog);
        return count;
    }

}
