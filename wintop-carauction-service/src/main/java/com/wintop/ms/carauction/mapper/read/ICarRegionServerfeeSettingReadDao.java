package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarRegionServerfeeSetting;
import com.wintop.ms.carauction.entity.Criteria;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ICarRegionServerfeeSettingReadDao {

    /**
     * 根据成交价查询记录集
     */
    CarRegionServerfeeSetting selectByClosingPrice(@Param("closingPrice") BigDecimal closingPrice);

    /**
     * 根据地区配置主键查询记录
     */
    List<CarRegionServerfeeSetting> selectByRegionSettingId(@Param("regionSettingId") Long regionSettingId);

}