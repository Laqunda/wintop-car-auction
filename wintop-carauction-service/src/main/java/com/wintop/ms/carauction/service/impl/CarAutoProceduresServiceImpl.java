package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAutoProcedures;
import com.wintop.ms.carauction.entity.Criteria;
import com.wintop.ms.carauction.model.CarAutoProceduresModel;
import com.wintop.ms.carauction.service.ICarAutoProceduresService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service("carAutoProceduresService")
public class CarAutoProceduresServiceImpl implements ICarAutoProceduresService {
    @Autowired
    private CarAutoProceduresModel carAutoProceduresModel;
    private static final Logger logger = LoggerFactory.getLogger(CarAutoProceduresServiceImpl.class);

    public ServiceResult<Integer> countByExample(Criteria example) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoProceduresModel.countByExample(example);
            result.setResult(count);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
        }finally {
            return result;
        }
    }

    public ServiceResult<CarAutoProcedures> selectByPrimaryKey(Long id) {
        ServiceResult<CarAutoProcedures> result = new ServiceResult<>();
        try {
            CarAutoProcedures carAutoDetectionClass = this.carAutoProceduresModel.selectByPrimaryKey(id);
            result.setResult(carAutoDetectionClass);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
        }finally {
            return result;
        }
    }

    public ServiceResult<List<CarAutoProcedures>> selectByExample(Criteria example) {
        ServiceResult<List<CarAutoProcedures>> result = new ServiceResult<>();
        try {
            List<CarAutoProcedures> list = this.carAutoProceduresModel.selectByExample(example);
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
            int count = this.carAutoProceduresModel.deleteByPrimaryKey(id);
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
    public ServiceResult<Integer> updateByPrimaryKeySelective(CarAutoProcedures record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoProceduresModel.updateByPrimaryKeySelective(record);
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
    public ServiceResult<Integer> updateByPrimaryKey(CarAutoProcedures record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoProceduresModel.updateByPrimaryKey(record);
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
    public Integer insert(CarAutoProcedures record) {
        return this.carAutoProceduresModel.insert(record);
    }

    @Transactional
    public ServiceResult<Integer> insertSelective(CarAutoProcedures record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoProceduresModel.insertSelective(record);
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
     *根据车辆d查询车辆手续信息
     * @param carId
     * @return
     */
    public ServiceResult<CarAutoProcedures> getAutoProceduresByCarId(Long carId){
        ServiceResult<CarAutoProcedures> result=new ServiceResult<>();
        try {
            CarAutoProcedures autoProcedures = this.carAutoProceduresModel.getAutoProceduresByCarId(carId);
            if(autoProcedures!=null){
                result.setResult(autoProcedures);
                result.setSuccess("0","查询成功");
            }else{
                result.setSuccess("114","该车辆没有手续信息");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
        }
        return result;
    }
}