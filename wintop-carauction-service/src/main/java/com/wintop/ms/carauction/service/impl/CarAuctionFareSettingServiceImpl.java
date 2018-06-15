package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAuctionFareSetting;
import com.wintop.ms.carauction.model.CarAuctionFareSettingModel;
import com.wintop.ms.carauction.service.ICarAuctionFareSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by 12991 on 2018/2/27.
 */
@Service
public class CarAuctionFareSettingServiceImpl implements ICarAuctionFareSettingService {
    @Autowired
    private CarAuctionFareSettingModel model;

    @Override
    public ServiceResult<Integer> countByExample(Map<String, Object> map) {
        Integer count = model.countByExample(map);
        return new ServiceResult<>(true,count);
    }

    @Override
    public ServiceResult<List<CarAuctionFareSetting>> selectByExample(Map<String, Object> map) {
        List<CarAuctionFareSetting> list = model.selectByExample(map);
        return new ServiceResult<>(true,list);
    }

    @Override
    public ServiceResult<CarAuctionFareSetting> selectById(Long id) {
        CarAuctionFareSetting carAuctionFareSetting =  model.selectById(id);
        return new ServiceResult<>(true,carAuctionFareSetting);
    }

    @Override
    public ServiceResult<Integer> deleteById(Long id) {
        Integer count = model.deleteById(id);
        return new ServiceResult<>(true,count);
    }

    @Override
    public ServiceResult<Integer> insert(CarAuctionFareSetting carAuctionFareSetting) {
        Integer count = model.insert(carAuctionFareSetting);
        return new ServiceResult<>(true,count);
    }

    @Override
    public ServiceResult<Integer> updateByIdSelective(CarAuctionFareSetting carAuctionFareSetting) {
        Integer count = model.updateByIdSelective(carAuctionFareSetting);
        return new ServiceResult<>(true,count);
    }

    @Override
    public ServiceResult<Integer> updateById(CarAuctionFareSetting carAuctionFareSetting) {
        Integer count = model.updateById(carAuctionFareSetting);
        return new ServiceResult<>(true,count);
    }

    /**
     * 我要竞价接口
     * @param customerId
     * @return
     */
    @Override
    public List<CarAuctionFareSetting> selectMyFareList(Long customerId){
        return model.selectMyFareList(customerId);
    }
    @Override
    public ServiceResult<List<CarAuctionFareSetting>> selectAllFare() {
        List<CarAuctionFareSetting> list = model.selectAllFare();
        return new ServiceResult<>(true,list);
    }

    /**
     * 查询自动出价加价最高金额
     * @param customerId
     * @return
     */
    public CarAuctionFareSetting selectEnableMaxFare(Long customerId){
        return model.selectEnableMaxFare(customerId);
    }
}
