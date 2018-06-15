package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarCustomerLevelPrice;
import com.wintop.ms.carauction.model.CarCustomerLevelPriceModel;
import com.wintop.ms.carauction.service.ICarCustomerLevelPriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 客户级别对应出价
 * @author zhangzijuan
 * @date 2018-02-26
 */
@Service("carCustomerLevelPriceService")
public class CarCustomerLevelPriceServiceImpl implements ICarCustomerLevelPriceService {
    @Resource
    private CarCustomerLevelPriceModel levelPriceModel;
    private static final Logger logger = LoggerFactory.getLogger(CarCustomerLevelPriceServiceImpl.class);

    @Override
    public ServiceResult<CarCustomerLevelPrice> selectByPrimaryKey(Long id) {
        ServiceResult<CarCustomerLevelPrice> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(levelPriceModel.selectByPrimaryKey(id));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> deleteByPrimaryKey(Long id) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(levelPriceModel.deleteByPrimaryKey(id));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> updateByPrimaryKeySelective(CarCustomerLevelPrice record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(levelPriceModel.updateByPrimaryKeySelective(record));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> updateByPrimaryKey(CarCustomerLevelPrice record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(levelPriceModel.updateByPrimaryKey(record));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> insert(CarCustomerLevelPrice record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(levelPriceModel.insert(record));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> insertSelective(CarCustomerLevelPrice record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(levelPriceModel.insertSelective(record));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }
}