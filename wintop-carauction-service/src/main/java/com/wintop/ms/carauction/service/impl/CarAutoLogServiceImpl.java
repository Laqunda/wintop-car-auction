package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAutoLog;
import com.wintop.ms.carauction.entity.Criteria;
import com.wintop.ms.carauction.model.CarAutoLogModel;
import com.wintop.ms.carauction.service.ICarAutoLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.Map;

@Service("CarAutoLogService")
public class CarAutoLogServiceImpl implements ICarAutoLogService {
    @Autowired
    private CarAutoLogModel carAutoLogModel;
    private static final Logger logger = LoggerFactory.getLogger(CarAutoLogServiceImpl.class);

    public ServiceResult<Integer> countByExample(Criteria example) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoLogModel.countByExample(example);
            result.setResult(count);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
        }finally {
            return result;
        }
    }

    public ServiceResult<CarAutoLog> selectByPrimaryKey(Long id) {
        ServiceResult<CarAutoLog> result = new ServiceResult<>();
        try {
            CarAutoLog carAutoDetectionClass = this.carAutoLogModel.selectByPrimaryKey(id);
            result.setResult(carAutoDetectionClass);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
        }finally {
            return result;
        }
    }

    public ServiceResult<List<CarAutoLog>> selectByExample(Criteria example) {
        ServiceResult<List<CarAutoLog>> result = new ServiceResult<>();
        try {
            List<CarAutoLog> list = this.carAutoLogModel.selectByExample(example);
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
    public ServiceResult<Integer> insert(CarAutoLog record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoLogModel.insert(record);
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
     * 查询车辆轨迹
     *@Author:zhangzijuan
     *@date 2018/3/23
     *@param:
     */
    public List<CarAutoLog> selectCarLogByCarId(Map<String,Object> map){
        return carAutoLogModel.selectCarLogByCarId(map);
    }

    public CarAutoLog selectCarLog(Map<String,Object> map){
        return carAutoLogModel.selectCarLog(map);
    }
}