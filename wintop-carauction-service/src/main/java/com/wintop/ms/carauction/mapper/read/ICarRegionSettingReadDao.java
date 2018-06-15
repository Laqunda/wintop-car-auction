package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarRegionSetting;
import java.util.List;
import java.util.Map;

public interface ICarRegionSettingReadDao {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    List<CarRegionSetting> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    CarRegionSetting selectByPrimaryKey(Long id);

    /**
     * 根据地区主键查询记录
     */
    CarRegionSetting selectByRegionId(Long regionId);

}