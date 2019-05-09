package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAutoDetectionDataPhoto;
import com.wintop.ms.carauction.entity.Criteria;
import com.wintop.ms.carauction.model.CarAutoDetectionDataPhotoModel;
import com.wintop.ms.carauction.service.ICarAutoDetectionDataPhotoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service("CarAutoDetectionDataPhotoService")
public class CarAutoDetectionDataPhotoServiceImpl implements ICarAutoDetectionDataPhotoService {
    @Autowired
    private CarAutoDetectionDataPhotoModel carAutoDetectionDataPhotoModel;
    private static final Logger logger = LoggerFactory.getLogger(CarAutoDetectionDataPhotoServiceImpl.class);

    public ServiceResult<Integer> countByExample(Criteria example) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoDetectionDataPhotoModel.countByExample(example);
            result.setResult(count);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
        }finally {
            return result;
        }
    }

    public ServiceResult<CarAutoDetectionDataPhoto> selectByPrimaryKey(Long id) {
        ServiceResult<CarAutoDetectionDataPhoto> result = new ServiceResult<>();
        try {
            CarAutoDetectionDataPhoto carAutoDetectionClass = this.carAutoDetectionDataPhotoModel.selectByPrimaryKey(id);
            result.setResult(carAutoDetectionClass);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","异常");
        }finally {
            return result;
        }
    }

    public ServiceResult<List<CarAutoDetectionDataPhoto>> selectByExample(Criteria example) {
        ServiceResult<List<CarAutoDetectionDataPhoto>> result = new ServiceResult<>();
        try {
            List<CarAutoDetectionDataPhoto> list = this.carAutoDetectionDataPhotoModel.selectByExample(example);
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
     * 根据车辆id 查询图片
     * @param carId
     * @return
     */
    @Override
    public ServiceResult<List<CarAutoDetectionDataPhoto>> selectByAutoId(Long carId) {
        ServiceResult<List<CarAutoDetectionDataPhoto>> result = new ServiceResult<>();
        try {
            List<CarAutoDetectionDataPhoto> list = this.carAutoDetectionDataPhotoModel.selectByAutoId(carId);
            result.setResult(list);
            result.setSuccess("0", "成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.setError("-1","异常");
        } finally {
            return result;
        }
    }

    @Transactional
    public ServiceResult<Integer> deleteByPrimaryKey(Long id) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoDetectionDataPhotoModel.deleteByPrimaryKey(id);
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
    public ServiceResult<Integer> updateByPrimaryKeySelective(CarAutoDetectionDataPhoto record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoDetectionDataPhotoModel.updateByPrimaryKeySelective(record);
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
    public ServiceResult<Integer> updateByPrimaryKey(CarAutoDetectionDataPhoto record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoDetectionDataPhotoModel.updateByPrimaryKey(record);
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
    public ServiceResult<Integer> insert(CarAutoDetectionDataPhoto record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoDetectionDataPhotoModel.insert(record);
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
    public ServiceResult<Integer> insertSelective(CarAutoDetectionDataPhoto record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoDetectionDataPhotoModel.insertSelective(record);
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