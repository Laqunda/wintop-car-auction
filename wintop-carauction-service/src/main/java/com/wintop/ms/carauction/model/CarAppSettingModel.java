package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarAppSetting;
import com.wintop.ms.carauction.mapper.read.ICarAppSettingReadDao;
import com.wintop.ms.carauction.mapper.write.ICarAppSettingWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CarAppSettingModel {

    @Autowired
    private ICarAppSettingReadDao carAppSettingReadDao;
    @Autowired
    private ICarAppSettingWriteDao carAppSettingWriteDao;

    public CarAppSetting selectByCode(Map<String, Object> map) {
        return this.carAppSettingReadDao.selectByCode(map);
    }

    public List<CarAppSetting> selectAll() {
        return this.carAppSettingReadDao.selectAll();
    }

    public int updateSelective(CarAppSetting carAppSetting) {
        return this.carAppSettingWriteDao.updateSelective(carAppSetting);
    }
}
