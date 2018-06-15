package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAuto;
import com.wintop.ms.carauction.entity.CarAutoPhoto;
import com.wintop.ms.carauction.model.CarAutoModel;
import com.wintop.ms.carauction.model.CarAutoPhotoModel;
import com.wintop.ms.carauction.service.ICarAutoPhotoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.Map;

@Service("CarAutoPhotoService")
public class CarAutoPhotoServiceImpl implements ICarAutoPhotoService {
    @Autowired
    private CarAutoPhotoModel carAutoPhotoModel;
    @Autowired
    private CarAutoModel autoModel;

    private static final Logger logger = LoggerFactory.getLogger(CarAutoPhotoServiceImpl.class);

    public ServiceResult<Integer> countByExample(Map<String,Object> map) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoPhotoModel.countByExample(map);
            result.setResult(count);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
        }finally {
            return result;
        }
    }

    public ServiceResult<CarAutoPhoto> selectByPrimaryKey(Long id) {
        ServiceResult<CarAutoPhoto> result = new ServiceResult<>();
        try {
            CarAutoPhoto carAutoDetectionClass = this.carAutoPhotoModel.selectByPrimaryKey(id);
            result.setResult(carAutoDetectionClass);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
        }finally {
            return result;
        }
    }

    public ServiceResult<List<CarAutoPhoto>> selectByExample(Map<String,Object> map) {
        ServiceResult<List<CarAutoPhoto>> result = new ServiceResult<>();
        try {
            List<CarAutoPhoto> list = this.carAutoPhotoModel.selectByExample(map);
            result.setResult(list);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
        }finally {
            return result;
        }
    }

    @Override
    public List<CarAutoPhoto> selectByAutoId(Long autoId) {
        return carAutoPhotoModel.selectByCarId(autoId);
    }

    @Transactional
    public ServiceResult<Integer> deleteByPrimaryKey(Long id) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            if(this.carAutoPhotoModel.deleteByPrimaryKey(id)>0) {
                result.setSuccess("0", "成功");
            }else {
                result.setError("-1","删除失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","删除失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }finally {
            return result;
        }
    }

    @Transactional
    public ServiceResult<Integer> updateByPrimaryKeySelective(CarAutoPhoto record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoPhotoModel.updateByPrimaryKeySelective(record);
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
    public ServiceResult<Integer> updateByPrimaryKey(CarAutoPhoto record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoPhotoModel.updateByPrimaryKey(record);
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
    public ServiceResult<CarAutoPhoto> insert(CarAutoPhoto record) {
        ServiceResult<CarAutoPhoto> result = new ServiceResult<>();
        try {
            if (this.carAutoPhotoModel.insert(record)>0){
                result.setResult(record);
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
    public ServiceResult<Integer> insertSelective(CarAutoPhoto record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoPhotoModel.insertSelective(record);
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
    public Integer insertArr(List<CarAutoPhoto> photoList,Long autoId) {
        if (photoList!=null && photoList.size()>0) {
            CarAuto carAuto = new CarAuto();
            carAuto.setId(autoId);
            carAuto.setMainPhoto(photoList.get(0).getPhotoUrl());
            //第一步先清空车辆照片
            carAutoPhotoModel.deleteByAutoId(autoId);
            //保存车辆图片，并将第一张图设置为主图
            carAutoPhotoModel.insertArr(photoList);
            autoModel.updateByPrimaryKeySelective(carAuto);
        }
        return 1;
    }
}