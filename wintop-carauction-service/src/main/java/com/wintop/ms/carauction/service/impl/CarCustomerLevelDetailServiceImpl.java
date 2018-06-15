package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarCustomerLevelDetail;
import com.wintop.ms.carauction.model.CarCustomerLevelDetailModel;
import com.wintop.ms.carauction.service.ICarCustomerLevelDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 客户级别信息详情
 * @author zhangzijuan
 * @date 2018-02-26
 */
@Service("carCustomerLevelDetailService")
public class CarCustomerLevelDetailServiceImpl implements ICarCustomerLevelDetailService {
    @Resource
    private CarCustomerLevelDetailModel levelDetailModel;
    private static final Logger logger = LoggerFactory.getLogger(CarCustomerLevelDetailServiceImpl.class);

    @Override
    public ServiceResult<CarCustomerLevelDetail> selectByPrimaryKey(Long id) {
        ServiceResult<CarCustomerLevelDetail> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(levelDetailModel.selectByPrimaryKey(id));
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
            result.setResult(levelDetailModel.deleteByPrimaryKey(id));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> updateByPrimaryKeySelective(CarCustomerLevelDetail record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(levelDetailModel.updateByPrimaryKeySelective(record));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> updateByPrimaryKey(CarCustomerLevelDetail record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(levelDetailModel.updateByPrimaryKey(record));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> insert(CarCustomerLevelDetail record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(levelDetailModel.insert(record));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> insertSelective(CarCustomerLevelDetail record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(levelDetailModel.insertSelective(record));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }
}