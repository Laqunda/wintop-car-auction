package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarFinanceStoreRecord;
import com.wintop.ms.carauction.model.CarFinanceStoreRecordModel;
import com.wintop.ms.carauction.service.ICarFinanceStoreRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by 12991 on 2018/2/27.
 */
@Service
public class CarFinanceStoreRecordServiceImpl implements ICarFinanceStoreRecordService {
    @Autowired
    private CarFinanceStoreRecordModel model;
    @Override
    public ServiceResult<Integer> countByExample(Map<String, Object> map) {
        Integer count = model.countByExample(map);
        return new ServiceResult<>(true,count);
    }

    @Override
    public ServiceResult<List<CarFinanceStoreRecord>> selectByExample(Map<String, Object> map) {
        List<CarFinanceStoreRecord> list = model.selectByExample(map);
        return new ServiceResult<>(true,list);
    }

    @Override
    public ServiceResult<CarFinanceStoreRecord> selectById(Long id) {
        CarFinanceStoreRecord carFinanceStoreRecord =  model.selectById(id);
        return new ServiceResult<>(true,carFinanceStoreRecord);
    }

    @Override
    public ServiceResult<Integer> deleteById(Long id) {
        Integer count = model.deleteById(id);
        return new ServiceResult<>(true,count);
    }

    @Override
    public ServiceResult<Integer> insert(CarFinanceStoreRecord carFinanceStoreRecord) {
        Integer count = model.insert(carFinanceStoreRecord);
        return new ServiceResult<>(true,count);
    }

    @Override
    public ServiceResult<Integer> updateByIdSelective(CarFinanceStoreRecord carFinanceStoreRecord) {
        Integer count = model.updateByIdSelective(carFinanceStoreRecord);
        return new ServiceResult<>(true,count);
    }

    @Override
    public ServiceResult<Integer> updateById(CarFinanceStoreRecord carFinanceStoreRecord) {
        Integer count = model.updateById(carFinanceStoreRecord);
        return new ServiceResult<>(true,count);
    }
}
