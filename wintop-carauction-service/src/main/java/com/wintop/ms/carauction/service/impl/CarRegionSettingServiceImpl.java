package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.entity.CarRegionServerfeeSetting;
import com.wintop.ms.carauction.entity.CarRegionSetting;
import com.wintop.ms.carauction.model.CarRegionServerfeeSettingModel;
import com.wintop.ms.carauction.model.CarRegionSettingModel;
import com.wintop.ms.carauction.service.ICarRegionSettingService;
import com.wintop.ms.carauction.util.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CarRegionSettingServiceImpl implements ICarRegionSettingService {
    @Autowired
    private CarRegionSettingModel regionSettingModel;
    @Autowired
    private CarRegionServerfeeSettingModel serverfeeSettingModel;

    @Override
    public int countByExample(Map<String, Object> map) {
        return regionSettingModel.countByExample(map);
    }

    @Override
    public List<CarRegionSetting> selectByExample(Map<String, Object> map) {
        List<CarRegionSetting> regionSettings = regionSettingModel.selectByExample(map);
        for(CarRegionSetting regionSetting:regionSettings){
            List<CarRegionServerfeeSetting> serverfeeSettings = serverfeeSettingModel.selectByRegionSettingId(regionSetting.getId());
            regionSetting.setServerfeeSettingList(serverfeeSettings);
        }
        return regionSettings;
    }

    @Override
    public CarRegionSetting selectByPrimaryKey(Long id) {
        CarRegionSetting regionSetting = regionSettingModel.selectByPrimaryKey(id);
        if(regionSetting!=null){
            List<CarRegionServerfeeSetting> serverfeeSettings = serverfeeSettingModel.selectByRegionSettingId(id);
            regionSetting.setServerfeeSettingList(serverfeeSettings);
        }
        return regionSetting;
    }

    @Override
    public CarRegionSetting selectByRegionId(Long regionId) {
        return regionSettingModel.selectByRegionId(regionId);
    }

    @Override
    @Transactional
    public int insert(CarRegionSetting record) {
        int count = regionSettingModel.insert(record);
        serverfeeSettingModel.saveBatchServerfee(record.getId(),record.getServerfeeSettings());
        return count;
    }

    @Override
    @Transactional
    public int updateByPrimaryKeySelective(CarRegionSetting record) {
        serverfeeSettingModel.deleteByRegionSettingId(record.getId());
        int count = regionSettingModel.updateByPrimaryKeySelective(record);
        serverfeeSettingModel.saveBatchServerfee(record.getId(),record.getServerfeeSettings());
        return count;
    }

    /**
     *根据当前时间获取配置的支付和过户违约时间
     * @param date
     * @param regionId
     * @param type,1支付违约时间，2过户违约时间
     * @return
     */
    public Date getBreachTime(Date date, Long regionId, String type){
        return regionSettingModel.getBreachTime(date, regionId, type);
    }
}
