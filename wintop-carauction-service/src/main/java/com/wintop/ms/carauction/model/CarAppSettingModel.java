package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarAppSetting;
import com.wintop.ms.carauction.mapper.read.ICarAppSettingReadDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class CarAppSettingModel {

    @Autowired
    private ICarAppSettingReadDao carAppSettingReadDao;

    public CarAppSetting selectByCode(Map<String, Object> map) {
        return this.carAppSettingReadDao.selectByCode(map);
    }
}
