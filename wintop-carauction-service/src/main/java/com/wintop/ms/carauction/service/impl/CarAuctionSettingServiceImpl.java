package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAuctionSetting;
import com.wintop.ms.carauction.model.CarAuctionSettingModel;
import com.wintop.ms.carauction.service.ICarAuctionSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CarAuctionSettingServiceImpl implements ICarAuctionSettingService {
    @Autowired
    private CarAuctionSettingModel model;
    @Override
    public Integer countByExample(Map<String, Object> map) {
        Integer count = model.countByExample(map);
        return count;
    }

    @Override
    public List<CarAuctionSetting> selectByExample(Map<String, Object> map) {
        List<CarAuctionSetting> list = model.selectByExample(map);
        return list;
    }

    @Override
    public CarAuctionSetting selectById(Long id) {
        CarAuctionSetting carAuctionSetting = model.selectById(id);
        return carAuctionSetting;
    }

    /**
     * 根据主键查询记录
     */
    public CarAuctionSetting selectByRegionId(Long regionId){
        return model.selectByRegionId(regionId);
    }

    @Override
    public Integer deleteById(Long id) {
        Integer count = model.deleteById(id);
        return count;
    }

    @Override
    public Integer insert(CarAuctionSetting carAuctionSetting) {
        Integer count = model.insert(carAuctionSetting);
        return count;
    }

    @Override
    public Integer updateByIdSelective(CarAuctionSetting carAuctionSetting) {
        Integer count = model.updateByIdSelective(carAuctionSetting);
        return count;
    }

    @Override
    public Integer updateById(CarAuctionSetting carAuctionSetting) {
        Integer count = model.updateById(carAuctionSetting);
        return count;
    }
}
