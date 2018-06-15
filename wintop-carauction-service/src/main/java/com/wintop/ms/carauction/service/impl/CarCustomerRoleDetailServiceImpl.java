package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarCustomerRoleDetail;
import com.wintop.ms.carauction.model.CarCustomerRoleDetailModel;
import com.wintop.ms.carauction.service.ICarCustomerRoleDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 客户角色信息详情
 * @author zhangzijuan
 * @date 2018-02-26
 */
@Service("carCustomerRoleDetailService")
public class CarCustomerRoleDetailServiceImpl implements ICarCustomerRoleDetailService {
    @Resource
    private CarCustomerRoleDetailModel customerRoleDetailModel;
    private static final Logger logger = LoggerFactory.getLogger(CarCustomerRoleDetailServiceImpl.class);

    @Override
    public ServiceResult<CarCustomerRoleDetail> selectByPrimaryKey(Long id) {
        ServiceResult<CarCustomerRoleDetail> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(customerRoleDetailModel.selectByPrimaryKey(id));
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
            result.setResult(customerRoleDetailModel.deleteByPrimaryKey(id));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> updateByPrimaryKeySelective(CarCustomerRoleDetail record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(customerRoleDetailModel.updateByPrimaryKeySelective(record));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> updateByPrimaryKey(CarCustomerRoleDetail record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(customerRoleDetailModel.updateByPrimaryKey(record));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> insert(CarCustomerRoleDetail record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(customerRoleDetailModel.insert(record));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> insertSelective(CarCustomerRoleDetail record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(customerRoleDetailModel.insertSelective(record));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }
}