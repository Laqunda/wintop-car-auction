package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarCustomerLog;
import com.wintop.ms.carauction.model.CarCustomerAuthModel;
import com.wintop.ms.carauction.model.CarCustomerDepositModel;
import com.wintop.ms.carauction.model.CarCustomerLogModel;
import com.wintop.ms.carauction.service.ICarCustomerLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 客户变更日志业务层
 * @author zhangzijuan
 * @date 2018-02-26
 */
@Service("carCustomerLogService")
public class CarCustomerLogServiceImpl implements ICarCustomerLogService {
    @Resource
    private CarCustomerLogModel customerLogModel;
    @Resource
    private CarCustomerAuthModel authModel;
    @Resource
    private CarCustomerDepositModel depositModel;
    private static final Logger logger = LoggerFactory.getLogger(CarCustomerLogServiceImpl.class);

    @Override
    public ServiceResult<CarCustomerLog> selectByPrimaryKey(Long id) {
        ServiceResult<CarCustomerLog> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(customerLogModel.selectByPrimaryKey(id));
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
            result.setResult(customerLogModel.deleteByPrimaryKey(id));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> updateByPrimaryKeySelective(CarCustomerLog record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(customerLogModel.updateByPrimaryKeySelective(record));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> updateByPrimaryKey(CarCustomerLog record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(customerLogModel.updateByPrimaryKey(record));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> insert(CarCustomerLog record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(customerLogModel.insert(record));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> insertSelective(CarCustomerLog record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(customerLogModel.insertSelective(record));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据用户Id查询用户日志
     *@Author:zhangzijuan
     *@date 2018/3/20
     *@param:userId
     */
    public ServiceResult<List<CarCustomerLog>> selectUserLogByUserId(Long userId){
        ServiceResult<List<CarCustomerLog>> result=new ServiceResult<>();
        try {
            List<CarCustomerLog> logs=customerLogModel.selectUserLogByUserId(userId);
            if(logs!=null && logs.size()!=0){
                for (CarCustomerLog log:logs){
                    String status=log.getStatus();
                    Long logId=log.getLogId();
                    if(status!=null && logId!=null){
                        //如果是认证记录，根据业务id查询认证信息
                        if("2".equals(status)){
                            log.setCustomerAuth(authModel.selectByPrimaryKey(logId));
                            //如果是缴纳保证金记录，根据业务id查询保证金信息
                        }else if("5".equals(status)){
                            log.setCustomerDeposit(depositModel.selectByPrimaryKey(logId));
                        }
                    }
                }
                result.setResult(logs);
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            }else {
                result.setSuccess(ResultCode.NO_OBJECT.strValue(),ResultCode.NO_OBJECT.getRemark());
            }
        }catch (Exception e){
            result.setSuccess(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }
}