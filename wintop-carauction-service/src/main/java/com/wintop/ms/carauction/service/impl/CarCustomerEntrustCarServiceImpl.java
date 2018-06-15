package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarCustomerEntrustCar;
import com.wintop.ms.carauction.model.CarCustomerEntrustCarModel;
import com.wintop.ms.carauction.service.ICarCustomerEntrustCarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 买家委托车辆业务层
 * @author zhangzijuan
 * @date 2018-02-26
 */
@Service
public class CarCustomerEntrustCarServiceImpl implements ICarCustomerEntrustCarService {
    @Resource
    private CarCustomerEntrustCarModel entrustCarModel;
    private static final Logger logger = LoggerFactory.getLogger(CarCustomerEntrustCarServiceImpl.class);

    @Override
    public ServiceResult<CarCustomerEntrustCar> selectByPrimaryKey(Long id) {
        ServiceResult<CarCustomerEntrustCar> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(entrustCarModel.selectByPrimaryKey(id));
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
            result.setResult(entrustCarModel.deleteByPrimaryKey(id));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> updateByPrimaryKeySelective(CarCustomerEntrustCar record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(entrustCarModel.updateByPrimaryKeySelective(record));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> updateByPrimaryKey(CarCustomerEntrustCar record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(entrustCarModel.updateByPrimaryKey(record));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Integer insert(CarCustomerEntrustCar record) {
        return entrustCarModel.insert(record);
    }

    @Override
    public ServiceResult<Integer> insertSelective(CarCustomerEntrustCar record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(entrustCarModel.insertSelective(record));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 查询车辆委托价
     * @param carId
     * @param customerId
     * @return
     */
    @Override
    public CarCustomerEntrustCar selectEntrustByCarId(Long carId, Long customerId){
        CarCustomerEntrustCar entrustCar = entrustCarModel.selectEntrustByCarId(carId,customerId);
        return entrustCar;
    }

    @Override
    public Integer updateCarEntrustPrice(CarCustomerEntrustCar entrustCar) {
        return entrustCarModel.updateCarEntrustPrice(entrustCar);
    }

    /**
     * 设置车辆委托价状态
     * @param entrustCar
     * @return
     */
    @Override
    public Integer updateCarEntrustPriceStatus(CarCustomerEntrustCar entrustCar){
        return entrustCarModel.updateCarEntrustPriceStatus(entrustCar);
    }

    /**
     * 删除委托价
     * @return
     */
    @Override
    public Integer deleteCarEntrustPriceStatus(CarCustomerEntrustCar entrustCar){
        return entrustCarModel.deleteCarEntrustPriceStatus(entrustCar);
    }

    /**
     * 查询所有有效委托价列表
     * @param carId
     * @return
     */
    public List<CarCustomerEntrustCar> selectValidEntrustList(Long carId){
        return entrustCarModel.selectValidEntrustList(carId);
    }

}