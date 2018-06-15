package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarCustomerGroupDetail;
import com.wintop.ms.carauction.model.CarCustomerGroupDetailModel;
import com.wintop.ms.carauction.service.ICarCustomerGroupDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户分组信息详情
 * @author zhangzijuan
 * @date 2018-02-26
 */
@Service("carCustomerGroupDetailService")
public class CarCustomerGroupDetailServiceImpl implements ICarCustomerGroupDetailService {
    @Resource
    private CarCustomerGroupDetailModel groupDetailModel;
    private static final Logger logger = LoggerFactory.getLogger(CarCustomerGroupDetailServiceImpl.class);

    @Override
    public ServiceResult<CarCustomerGroupDetail> selectByPrimaryKey(Long id) {
        ServiceResult<CarCustomerGroupDetail> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(groupDetailModel.selectByPrimaryKey(id));
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
            result.setResult(groupDetailModel.deleteByPrimaryKey(id));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> updateByPrimaryKeySelective(CarCustomerGroupDetail record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(groupDetailModel.updateByPrimaryKeySelective(record));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> updateByPrimaryKey(CarCustomerGroupDetail record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(groupDetailModel.updateByPrimaryKey(record));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> insert(CarCustomerGroupDetail record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(groupDetailModel.insert(record));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> insertSelective(CarCustomerGroupDetail record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(groupDetailModel.insertSelective(record));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }
}