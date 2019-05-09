package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarAppSetting;

import java.util.Map;

public interface ICarAppSettingReadDao {

    CarAppSetting selectByCode(Map<String, Object> map);
}
