package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAppAccord;
import com.wintop.ms.carauction.model.CarAppAccordModel;
import com.wintop.ms.carauction.service.ICarAppAccordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by 12991 on 2018/2/26.
 */
@Service
public class CarAppAccordServiceImpl implements ICarAppAccordService {
    @Autowired
    private CarAppAccordModel model;

    @Override
    public ServiceResult<Integer> countByExample(Map<String, Object> map) {
        Integer count = model.countByExample(map);
        return new ServiceResult<>(true,count);
    }

    @Override
    public ServiceResult<List<CarAppAccord>> selectByExample(Map<String, Object> map) {
        List<CarAppAccord> list = model.selectByExample(map);
        return new ServiceResult<>(true,list);
    }

    @Override
    public ServiceResult<CarAppAccord> selectById(Long id) {
        CarAppAccord carAppAccord =  model.selectById(id);
        return new ServiceResult<>(true,carAppAccord);
    }

    @Override
    public ServiceResult<Integer> deleteById(Long id) {
        Integer count = model.deleteById(id);
        return new ServiceResult<>(true,count);
    }

    @Override
    public ServiceResult<Integer> insert(CarAppAccord carAppAccord) {
        Integer count = model.insert(carAppAccord);
        return new ServiceResult<>(true,count);
    }

    @Override
    public ServiceResult<Integer> updateByIdSelective(CarAppAccord carAppAccord) {
        Integer count = model.updateByIdSelective(carAppAccord);
        return new ServiceResult<>(true,count);
    }

    @Override
    public ServiceResult<Integer> updateById(CarAppAccord carAppAccord) {
        Integer count = model.updateById(carAppAccord);
        return new ServiceResult<>(true,count);
    }

    @Override
    public ServiceResult<CarAppAccord> findByCode(String code) {
        CarAppAccord carAppAccord = model.findByCode(code);
        return new ServiceResult<>(true,carAppAccord);
    }

}
