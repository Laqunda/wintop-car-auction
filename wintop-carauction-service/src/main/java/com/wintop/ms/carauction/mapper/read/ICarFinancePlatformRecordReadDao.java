package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarFinancePlatformRecord;

import java.util.List;
import java.util.Map;

/**
 * Created by 12991 on 2018/2/27.
 */
public interface ICarFinancePlatformRecordReadDao {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    List<CarFinancePlatformRecord> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    CarFinancePlatformRecord selectById(Long id);
}
