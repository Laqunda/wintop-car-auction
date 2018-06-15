package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAutoTransferLog;
import com.wintop.ms.carauction.entity.Criteria;
import com.wintop.ms.carauction.model.CarAutoTransferLogModel;
import com.wintop.ms.carauction.service.ICarAutoTransferLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.Map;

@Service("CarAutoTransferLogService")
public class CarAutoTransferLogServiceImpl implements ICarAutoTransferLogService {
    @Autowired
    private CarAutoTransferLogModel carAutoTransferLogModel;
    private static final Logger logger = LoggerFactory.getLogger(CarAutoTransferLogServiceImpl.class);

    public ServiceResult<Integer> countByExample(Map<String,Object> map) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoTransferLogModel.countByExample(map);
            result.setResult(count);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
        }finally {
            return result;
        }
    }

    public ServiceResult<CarAutoTransferLog> selectByPrimaryKey(Long id) {
        ServiceResult<CarAutoTransferLog> result = new ServiceResult<>();
        try {
            CarAutoTransferLog carAutoDetectionClass = this.carAutoTransferLogModel.selectByPrimaryKey(id);
            result.setResult(carAutoDetectionClass);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
        }finally {
            return result;
        }
    }

    public ServiceResult<List<CarAutoTransferLog>> selectByExample(Map<String,Object> map) {
        ServiceResult<List<CarAutoTransferLog>> result = new ServiceResult<>();
        try {
            List<CarAutoTransferLog> list = this.carAutoTransferLogModel.selectByExample(map);
            result.setResult(list);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
        }finally {
            return result;
        }
    }

    @Transactional
    public ServiceResult<Integer> deleteByPrimaryKey(Long id) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoTransferLogModel.deleteByPrimaryKey(id);
            result.setResult(count);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }finally {
            return result;
        }
    }


    @Transactional
    public ServiceResult<Integer> insert(CarAutoTransferLog record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoTransferLogModel.insert(record);
            result.setResult(count);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }finally {
            return result;
        }
    }

    @Transactional
    public ServiceResult<Integer> insertSelective(CarAutoTransferLog record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoTransferLogModel.insertSelective(record);
            result.setResult(count);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }finally {
            return result;
        }
    }

    /**
     * 当前订单过户流程标记
     * @param map
     * @return
     */
    public List<CarAutoTransferLog> queryTransferFlowList(Map<String,Object> map){
        return carAutoTransferLogModel.queryTransferFlowList(map);
    }

    /**
     * 查询过户轨迹
     *@Author:zhangzijuan
     *@date 2018/3/26
     *@param:
     */
    public ServiceResult<List<CarAutoTransferLog>> queryTransferList(Map<String,Object> map){
        ServiceResult<List<CarAutoTransferLog>> result=new ServiceResult<>();
        try {
            List<CarAutoTransferLog> logs=carAutoTransferLogModel.queryTransferList(map);
            if(logs!=null && logs.size()>0){
                result.setResult(logs);
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            }else {
                result.setError(ResultCode.NO_OBJECT.strValue(),ResultCode.NO_OBJECT.getRemark());
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /**
     * 查询订单手续信息
     * @param orderId
     * @return
     */
    public List<CarAutoTransferLog> selectTransferLogByOrderId(Long orderId){
        return carAutoTransferLogModel.selectTransferLogByOrderId(orderId);
    }
}