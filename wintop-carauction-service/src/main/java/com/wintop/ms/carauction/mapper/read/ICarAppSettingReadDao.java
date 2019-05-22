package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarAppSetting;

import java.util.List;
import java.util.Map;

public interface ICarAppSettingReadDao {

    CarAppSetting selectByCode(Map<String, Object> map);

    /**
     * 查询全部的 app 端设置
     * @return
     */
    List<CarAppSetting> selectAll();
}
