package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarRegionServerfeeSetting;
import com.wintop.ms.carauction.entity.Criteria;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ICarRegionServerfeeSettingWriteDao {

    /**
     * 根据地区配置主键删除记录
     */
    int deleteByRegionSettingId(Long regionSettingId);

    /**
     * 保存记录
     */
    int insert(CarRegionServerfeeSetting record);

    /**
     * 保存记录
     * @param record
     */
    int insertSelective(CarRegionServerfeeSetting record);

    /**
     * 修改记录
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(CarRegionServerfeeSetting record);

}