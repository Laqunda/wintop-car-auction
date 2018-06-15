package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarAuctionSetting;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ICarAuctionSettingReadDao {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    List<CarAuctionSetting> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    CarAuctionSetting selectById(Long id);

    /**
     * 根据地区主键查询记录
     */
    CarAuctionSetting selectByRegionId(Long id);

}