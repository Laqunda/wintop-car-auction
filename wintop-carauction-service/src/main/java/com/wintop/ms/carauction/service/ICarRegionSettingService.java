package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.entity.CarRegionServerfeeSetting;
import com.wintop.ms.carauction.entity.CarRegionSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ICarRegionSettingService {
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

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(CarRegionSetting record);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(CarRegionSetting record);

    /**
     *根据当前时间获取配置的支付和过户违约时间
     * @param date
     * @param regionId
     * @param type,1支付违约时间，2过户违约时间
     * @return
     */
    public Date getBreachTime(Date date, Long regionId, String type);

    /**
     * 保存记录
     * @param record
     */
    public int insertServerfeeSelective(CarRegionServerfeeSetting record);

    /**
     * 修改记录
     * @param record
     * @return
     */
    public int updateServerfeeByPrimaryKeySelective(CarRegionServerfeeSetting record);
}
