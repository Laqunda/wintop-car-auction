package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarAppSetting;

public interface ICarAppSettingWriteDao {
    /**
     * 修改首页设置
     * @param carAppSetting
     * @return
     */
    public int updateSelective(CarAppSetting carAppSetting);
}
