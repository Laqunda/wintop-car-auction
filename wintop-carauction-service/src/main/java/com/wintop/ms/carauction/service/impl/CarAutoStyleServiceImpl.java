package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAutoStyle;
import com.wintop.ms.carauction.entity.Criteria;
import com.wintop.ms.carauction.model.CarAutoStyleModel;
import com.wintop.ms.carauction.service.ICarAutoStyleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service("CarAutoStyleService")
public class CarAutoStyleServiceImpl implements ICarAutoStyleService {
    @Autowired
    private CarAutoStyleModel carAutoStyleModel;
    private static final Logger logger = LoggerFactory.getLogger(CarAutoStyleServiceImpl.class);

    public ServiceResult<Integer> countByExample(Criteria example) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoStyleModel.countByExample(example);
            result.setResult(count);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
        }finally {
            return result;
        }
    }

    public ServiceResult<CarAutoStyle> selectByPrimaryKey(Long id) {
        ServiceResult<CarAutoStyle> result = new ServiceResult<>();
        try {
            CarAutoStyle carAutoDetectionClass = this.carAutoStyleModel.selectByPrimaryKey(id);
            result.setResult(carAutoDetectionClass);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
        }finally {
            return result;
        }
    }

    public ServiceResult<List<CarAutoStyle>> selectByExample(Criteria example) {
        ServiceResult<List<CarAutoStyle>> result = new ServiceResult<>();
        try {
            List<CarAutoStyle> list = this.carAutoStyleModel.selectByExample(example);
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
            int count = this.carAutoStyleModel.deleteByPrimaryKey(id);
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
    public ServiceResult<Integer> updateByPrimaryKeySelective(CarAutoStyle record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoStyleModel.updateByPrimaryKeySelective(record);
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
    public ServiceResult<Integer> updateByPrimaryKey(CarAutoStyle record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoStyleModel.updateByPrimaryKey(record);
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
    public ServiceResult<Integer> insert(CarAutoStyle record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoStyleModel.insert(record);
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
    public ServiceResult<Integer> insertSelective(CarAutoStyle record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoStyleModel.insertSelective(record);
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
}