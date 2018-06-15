package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAuto;
import com.wintop.ms.carauction.entity.CarAutoConfDetail;
import com.wintop.ms.carauction.entity.CarAutoInfoDetail;
import com.wintop.ms.carauction.entity.Criteria;
import com.wintop.ms.carauction.model.CarAutoConfDetailModel;
import com.wintop.ms.carauction.model.CarAutoInfoDetailModel;
import com.wintop.ms.carauction.model.CarAutoModel;
import com.wintop.ms.carauction.service.ICarAutoInfoDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("CarAutoInfoDetailService")
public class CarAutoInfoDetailServiceImpl implements ICarAutoInfoDetailService {
    @Autowired
    private CarAutoInfoDetailModel carAutoInfoDetailModel;
    @Autowired
    private CarAutoConfDetailModel confDetailModel;
    @Autowired
    private CarAutoModel autoModel;
    private static final Logger logger = LoggerFactory.getLogger(CarAutoInfoDetailServiceImpl.class);

    public ServiceResult<Integer> countByExample(Map<String,Object> map) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoInfoDetailModel.countByExample(map);
            result.setResult(count);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
        }finally {
            return result;
        }
    }

    public ServiceResult<CarAutoInfoDetail> selectByPrimaryKey(Long id) {
        ServiceResult<CarAutoInfoDetail> result = new ServiceResult<>();
        try {
            CarAutoInfoDetail carAutoDetectionClass = this.carAutoInfoDetailModel.selectByPrimaryKey(id);
            result.setResult(carAutoDetectionClass);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
        }finally {
            return result;
        }
    }

    public ServiceResult<List<CarAutoInfoDetail>> selectByExample(Map<String,Object> map) {
        ServiceResult<List<CarAutoInfoDetail>> result = new ServiceResult<>();
        try {
            List<CarAutoInfoDetail> list = this.carAutoInfoDetailModel.selectByExample(map);
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
            int count = this.carAutoInfoDetailModel.deleteByPrimaryKey(id);
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
    public ServiceResult updateByPrimaryKeySelective(CarAutoInfoDetail record) {
        logger.info("保存车辆基本信息");
        ServiceResult result = new ServiceResult<>();
        try {
            int count = this.carAutoInfoDetailModel.updateByPrimaryKeySelective(record);
            //同步修改车辆主表中的车辆名称等字段
            CarAuto auto = new CarAuto();
            auto.setId(record.getAutoId());
            auto.setUpdateTime(record.getUpdateTime());
            auto.setAutoInfoName(record.getAutoInfoName());
            count += autoModel.updateByPrimaryKeySelective(auto);
            if (count==2){
                result.setSuccess("0","成功");
            }else {
                result.setError("-1","保存失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }finally {
            return result;
        }
    }

    @Transactional
    public ServiceResult<Integer> updateByPrimaryKey(CarAutoInfoDetail record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoInfoDetailModel.updateByPrimaryKey(record);
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
    public ServiceResult<Integer> insert(CarAutoInfoDetail record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoInfoDetailModel.insert(record);
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
    public ServiceResult<Integer> insertSelective(CarAutoInfoDetail record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoInfoDetailModel.insertSelective(record);
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
    public CarAutoInfoDetail selectDetailByCarId(Long carId) {
        CarAutoInfoDetail infoDetail = carAutoInfoDetailModel.selectDetailByCarId(carId);
        if (infoDetail!=null) {
            List<CarAutoConfDetail> confDetails = confDetailModel.selectConfigsByCarId(carId);
            infoDetail.setConfDetails(confDetails);
        }
        return infoDetail;
    }

    @Override
    public Integer updateRemarkByautoId(CarAutoInfoDetail detail) {
        return carAutoInfoDetailModel.updateRemarkByautoId(detail);
    }

    @Override
    @Transactional
    public Integer updateDetailAndConf(CarAutoInfoDetail detail) {
        //1、保存基本信息
        this.updateByPrimaryKeySelective(detail);
        //2、保存配置信息
        confDetailModel.deleteByAutoId(detail.getAutoId());
        confDetailModel.insertArr(detail.getConfDetails());
        return 1;
    }
}