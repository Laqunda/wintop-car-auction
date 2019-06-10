package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAutoConfDetail;
import com.wintop.ms.carauction.entity.Criteria;
import com.wintop.ms.carauction.model.CarAutoConfDetailModel;
import com.wintop.ms.carauction.service.ICarAutoConfDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service("CarAutoConfDetailService")
public class CarAutoConfDetailServiceImpl implements ICarAutoConfDetailService {
    @Autowired
    private CarAutoConfDetailModel carAutoConfDetailModel;
    private static final Logger logger = LoggerFactory.getLogger(CarAutoConfDetailServiceImpl.class);

    public ServiceResult<Integer> countByExample(Map<String,Object> map) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoConfDetailModel.countByExample(map);
            result.setResult(count);
            logger.debug("count: {}", count);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
        }finally {
            return result;
        }
    }

    public ServiceResult<CarAutoConfDetail> selectByPrimaryKey(Long id) {
        ServiceResult<CarAutoConfDetail> result = new ServiceResult<>();
        try {
            CarAutoConfDetail carAutoConfDetail = this.carAutoConfDetailModel.selectByPrimaryKey(id);
            result.setResult(carAutoConfDetail);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
        }finally {
            return result;
        }
    }

    public ServiceResult<List<CarAutoConfDetail>> selectByExample(Map<String,Object> map) {
        ServiceResult<List<CarAutoConfDetail>> result = new ServiceResult<>();
        try {
            List<CarAutoConfDetail> list = this.carAutoConfDetailModel.selectByExample(map);
            result.setResult(list);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
        }finally {
            return result;
        }
    }

    /**
     * 通过车辆id 进行查询
     * @param autoId
     * @return
     */
    public List<CarAutoConfDetail> selectConfigsByCarId(Long autoId) {
        List<CarAutoConfDetail> list = null;
        try {
            list = this.carAutoConfDetailModel.selectConfigsByCarId(autoId);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return list;
        }
    }

    @Transactional
    public ServiceResult<Integer> deleteByPrimaryKey(Long id) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            Integer x = this.carAutoConfDetailModel.deleteByPrimaryKey(id);
            result.setResult(x);
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }finally {
            return result;
        }
    }

    @Transactional
    public ServiceResult<Integer> updateByPrimaryKeySelective(CarAutoConfDetail record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            Integer x = this.carAutoConfDetailModel.updateByPrimaryKeySelective(record);
            result.setSuccess(true);
            result.setResult(x);
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }finally {
            return result;
        }
    }

    @Transactional
    public ServiceResult<Integer> updateByPrimaryKey(CarAutoConfDetail record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            Integer x = this.carAutoConfDetailModel.updateByPrimaryKey(record);
            result.setSuccess("0","修改成功");
            result.setResult(x);
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }finally {
            return result;
        }
    }

    @Transactional
    public ServiceResult<Integer> insert(CarAutoConfDetail record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            Integer x = this.carAutoConfDetailModel.insert(record);
            result.setSuccess("0","新增成功");
            result.setResult(x);
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }finally {
            return result;
        }
    }

    @Transactional
    public ServiceResult<Integer> insertSelective(CarAutoConfDetail record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            Integer x = this.carAutoConfDetailModel.insertSelective(record);
            result.setSuccess("0","成功");
            result.setResult(x);
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }finally {
            return result;
        }
    }

    @Override
    @Transactional
    public Integer insertArr(List<CarAutoConfDetail> recordArr,Long autoId) {
        carAutoConfDetailModel.deleteByAutoId(autoId);
        return this.carAutoConfDetailModel.insertArr(recordArr);
    }
}