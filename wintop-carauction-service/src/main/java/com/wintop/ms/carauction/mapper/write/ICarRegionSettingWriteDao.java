package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarRegionSetting;
import com.wintop.ms.carauction.entity.Criteria;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ICarRegionSettingWriteDao {

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(CarRegionSetting record);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(CarRegionSetting record);

}