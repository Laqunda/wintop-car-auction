package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarOrderFeeChange;
import com.wintop.ms.carauction.model.CarOrderFeeChangeModel;
import com.wintop.ms.carauction.service.ICarOrderFeeChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CarOrderFeeChangeServiceImpl implements ICarOrderFeeChangeService {
    @Autowired
    private CarOrderFeeChangeModel model;
    @Override
    public ServiceResult<Integer> countByExample(Map<String, Object> map) {
        Integer count = model.countByExample(map);
        return new ServiceResult<>(true,count);
    }

    @Override
    public ServiceResult<List<CarOrderFeeChange>> selectByExample(Map<String, Object> map) {
        List<CarOrderFeeChange> list = model.selectByExample(map);
        return new ServiceResult<>(true,list);
    }

    @Override
    public ServiceResult<CarOrderFeeChange> selectById(Long id) {
        CarOrderFeeChange carOrderFeeChange = model.selectById(id);
        return new ServiceResult<>(true,carOrderFeeChange);
    }

    @Override
    public ServiceResult<Integer> deleteById(Long id) {
        Integer count = model.deleteById(id);
        return new ServiceResult<>(true,count);
    }

    @Override
    public ServiceResult<Integer> insert(CarOrderFeeChange carOrderFeeChange) {
        Integer count = model.insert(carOrderFeeChange);
        return new ServiceResult<>(true,count);
    }

    @Override
    public ServiceResult<Integer> updateByIdSelective(CarOrderFeeChange carOrderFeeChange) {
        Integer count = model.updateByIdSelective(carOrderFeeChange);
        return new ServiceResult<>(true,count);
    }

    @Override
    public ServiceResult<Integer> updateById(CarOrderFeeChange carOrderFeeChange) {
        Integer count = model.updateById(carOrderFeeChange);
        return new ServiceResult<>(true,count);
    }
}
