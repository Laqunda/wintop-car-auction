package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAutoConfTitle;
import com.wintop.ms.carauction.entity.Criteria;
import com.wintop.ms.carauction.mapper.read.ICarAutoConfTitleReadDao;
import com.wintop.ms.carauction.mapper.write.ICarAutoConfTitleWriteDao;
import com.wintop.ms.carauction.model.CarAutoConfTitleModel;
import com.wintop.ms.carauction.service.ICarAutoConfTitleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.Map;

@Service("CarAutoConfTitleService")
public class CarAutoConfTitleServiceImpl implements ICarAutoConfTitleService {
    @Autowired
    private CarAutoConfTitleModel carAutoConfTitleModel;
    private static final Logger logger = LoggerFactory.getLogger(CarAutoConfTitleServiceImpl.class);

    public ServiceResult<Integer> countByExample(Map<String,Object> map) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoConfTitleModel.countByExample(map);
            result.setResult(count);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
        }finally {
            return result;
        }
    }

    public ServiceResult<CarAutoConfTitle> selectByPrimaryKey(Long id) {
        ServiceResult<CarAutoConfTitle> result = new ServiceResult<>();
        try {
            CarAutoConfTitle carAutoConfTitle = this.carAutoConfTitleModel.selectByPrimaryKey(id);
            result.setResult(carAutoConfTitle);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
        }finally {
            return result;
        }
    }

    public ServiceResult<List<CarAutoConfTitle>> selectByExample(Map<String,Object> map) {
        ServiceResult<List<CarAutoConfTitle>> result = new ServiceResult<>();
        try {
            List<CarAutoConfTitle> list = this.carAutoConfTitleModel.selectByExample(map);
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
            int count = this.carAutoConfTitleModel.deleteByPrimaryKey(id);
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
    public ServiceResult<Integer> updateByPrimaryKeySelective(CarAutoConfTitle record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoConfTitleModel.updateByPrimaryKeySelective(record);
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
    public ServiceResult<Integer> updateByPrimaryKey(CarAutoConfTitle record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoConfTitleModel.updateByPrimaryKey(record);
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
    public ServiceResult<Integer> insert(CarAutoConfTitle record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoConfTitleModel.insert(record);
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
    public ServiceResult<Integer> insertSelective(CarAutoConfTitle record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoConfTitleModel.insertSelective(record);
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

    @Override
    public List<CarAutoConfTitle> selectAll(Map map) {
        return carAutoConfTitleModel.selectAll(map);
    }
}