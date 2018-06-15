package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAutoDetectionClass;
import com.wintop.ms.carauction.entity.CarAutoDetectionData;
import com.wintop.ms.carauction.entity.Criteria;
import com.wintop.ms.carauction.model.CarAutoDetectionDataModel;
import com.wintop.ms.carauction.model.CarAutoDetectionDataPhotoModel;
import com.wintop.ms.carauction.service.ICarAutoDetectionDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("CarAutoDetectionDataService")
public class CarAutoDetectionDataServiceImpl implements ICarAutoDetectionDataService {
    @Autowired
    private CarAutoDetectionDataModel carAutoDetectionDataModel;
    @Autowired
    private CarAutoDetectionDataPhotoModel carAutoDetectionDataPhotoModel;

    private static final Logger logger = LoggerFactory.getLogger(CarAutoDetectionDataServiceImpl.class);

    public ServiceResult<Integer> countByExample(Criteria example) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoDetectionDataModel.countByExample(example);
            result.setResult(count);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
        }finally {
            return result;
        }
    }

    public ServiceResult<CarAutoDetectionData> selectByPrimaryKey(Long id) {
        ServiceResult<CarAutoDetectionData> result = new ServiceResult<>();
        try {
            CarAutoDetectionData carAutoDetectionClass = this.carAutoDetectionDataModel.selectByPrimaryKey(id);
            result.setResult(carAutoDetectionClass);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
        }finally {
            return result;
        }
    }

    public ServiceResult<List<CarAutoDetectionData>> selectByExample(Criteria example) {
        ServiceResult<List<CarAutoDetectionData>> result = new ServiceResult<>();
        try {
            List<CarAutoDetectionData> list = this.carAutoDetectionDataModel.selectByExample(example);
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
            int count = this.carAutoDetectionDataModel.deleteByPrimaryKey(id);
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
    public ServiceResult<Integer> updateByPrimaryKeySelective(CarAutoDetectionData record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoDetectionDataModel.updateByPrimaryKeySelective(record);
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
    public ServiceResult<Integer> updateByPrimaryKey(CarAutoDetectionData record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoDetectionDataModel.updateByPrimaryKey(record);
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
    public ServiceResult<Integer> insert(CarAutoDetectionData record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoDetectionDataModel.insert(record);
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
    public ServiceResult<Integer> insertSelective(CarAutoDetectionData record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoDetectionDataModel.insertSelective(record);
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
    @Transactional
    public void saveDetectionData(CarAutoDetectionClass detectionClass,Long autoId) {
        //先清理该检测类目下面的所有历史数据，再次保存新数据
        Map map = new HashMap();
        map.put("autoId",autoId);
        map.put("classId",detectionClass.getId());
        carAutoDetectionDataModel.deleteByClassIdAutoId(map);
        carAutoDetectionDataPhotoModel.deleteByClassIdAutoId(map);
        if (detectionClass.getDataList()!=null && detectionClass.getDataList().size()>0) {
             carAutoDetectionDataModel.insertArr(detectionClass.getDataList());
        }
        if (detectionClass.getPhotoList()!=null && detectionClass.getPhotoList().size()>0) {
             carAutoDetectionDataPhotoModel.insertArr(detectionClass.getPhotoList());
        }
    }
}