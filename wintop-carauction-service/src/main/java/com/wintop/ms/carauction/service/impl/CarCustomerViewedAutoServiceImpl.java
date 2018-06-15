package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarCustomerViewedAuto;
import com.wintop.ms.carauction.model.CarCustomerViewedAutoModel;
import com.wintop.ms.carauction.service.ICarCustomerViewedAutoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by 12991 on 2018/3/12.
 */
@Service("carCustomerViewedAutoService")
public class CarCustomerViewedAutoServiceImpl implements ICarCustomerViewedAutoService{
    @Resource
    private CarCustomerViewedAutoModel carCustomerViewedAutoModel;
    private static final Logger logger = LoggerFactory.getLogger(CarCustomerViewedAutoServiceImpl.class);


    @Override
    public ServiceResult<CarCustomerViewedAuto> selectByPrimaryKey(Long id) {
        ServiceResult<CarCustomerViewedAuto> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(carCustomerViewedAutoModel.selectByPrimaryKey(id));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public ServiceResult<Integer> deleteByPrimaryKey(Long id) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(carCustomerViewedAutoModel.deleteByPrimaryKey(id));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> updateByPrimaryKeySelective(CarCustomerViewedAuto record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(carCustomerViewedAutoModel.updateByPrimaryKeySelective(record));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> updateByPrimaryKey(CarCustomerViewedAuto record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(carCustomerViewedAutoModel.updateByPrimaryKey(record));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Integer insert(CarCustomerViewedAuto record) {
        return carCustomerViewedAutoModel.insert(record);
    }

    @Override
    public ServiceResult<Integer> insertSelective(CarCustomerViewedAuto record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(carCustomerViewedAutoModel.insertSelective(record));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 查询用户浏览车辆列表
     */
    @Override
    public List<CarCustomerViewedAuto> selectUserViewList(Map<String,Object> map){
        return carCustomerViewedAutoModel.selectUserViewList(map);
    }

    /**
     * 查询用户浏览车辆列表数量
     */
    @Override
    public int queryUserViewCount(Map<String,Object> map){
        return carCustomerViewedAutoModel.queryUserViewCount(map);
    }

    /**
     * 更新浏览记录时间
     * @param carId
     * @param customerId
     * @return
     */
    @Override
    public int updateViewTime(Long carId, Long customerId){
        return carCustomerViewedAutoModel.updateViewTime(carId, customerId);
    }

    /**
     * 查询是否存在浏览记录
     * @param carId
     * @param customerId
     * @return
     */
    @Override
    public CarCustomerViewedAuto queryViewRecord(Long carId,  Long customerId){
        return carCustomerViewedAutoModel.queryViewRecord(carId, customerId);
    }
}
