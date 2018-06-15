package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAutoDetectionClassOptions;
import com.wintop.ms.carauction.entity.Criteria;
import com.wintop.ms.carauction.model.CarAutoDetectionClassOptionsModel;
import com.wintop.ms.carauction.service.ICarAutoDetectionClassOptionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service("CarAutoDetectionClassOptionsService")
public class CarAutoDetectionClassOptionsServiceImpl implements ICarAutoDetectionClassOptionsService {
    @Autowired
    private CarAutoDetectionClassOptionsModel carAutoDetectionClassOptionsModel;
    private static final Logger logger = LoggerFactory.getLogger(CarAutoDetectionClassOptionsServiceImpl.class);

    public ServiceResult<Integer> countByExample(Criteria example) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoDetectionClassOptionsModel.countByExample(example);
            result.setResult(count);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
        }finally {
            return result;
        }
    }

    public ServiceResult<CarAutoDetectionClassOptions> selectByPrimaryKey(Long id) {
        ServiceResult<CarAutoDetectionClassOptions> result = new ServiceResult<>();
        try {
            CarAutoDetectionClassOptions carAutoDetectionClassOptions = this.carAutoDetectionClassOptionsModel.selectByPrimaryKey(id);
            result.setResult(carAutoDetectionClassOptions);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
        }finally {
            return result;
        }
    }

    public ServiceResult<List<CarAutoDetectionClassOptions>> selectByExample(Criteria example) {
        ServiceResult<List<CarAutoDetectionClassOptions>> result = new ServiceResult<>();
        try {
            List list = this.carAutoDetectionClassOptionsModel.selectByExample(example);
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
            Integer x = this.carAutoDetectionClassOptionsModel.deleteByPrimaryKey(id);
            result.setResult(x);
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
    public ServiceResult<Integer> updateByPrimaryKeySelective(CarAutoDetectionClassOptions record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            Integer x = this.carAutoDetectionClassOptionsModel.updateByPrimaryKeySelective(record);
            result.setResult(x);
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
    public ServiceResult<Integer> updateByPrimaryKey(CarAutoDetectionClassOptions record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            Integer x = this.carAutoDetectionClassOptionsModel.updateByPrimaryKey(record);
            result.setResult(x);
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
    public ServiceResult<Integer> insert(CarAutoDetectionClassOptions record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            Integer x = this.carAutoDetectionClassOptionsModel.insert(record);
            result.setResult(x);
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
    public ServiceResult<Integer> insertSelective(CarAutoDetectionClassOptions record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            Integer x = this.carAutoDetectionClassOptionsModel.insertSelective(record);
            result.setResult(x);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }finally {
            return result;
        }
    }
}