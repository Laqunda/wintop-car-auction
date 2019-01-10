package com.wintop.ms.carauction.mapper.read;

import java.util.List;
import java.util.Map;

import com.wintop.ms.carauction.entity.TblBaseStation;

public interface TblBaseStationReadDao {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    List<TblBaseStation> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    TblBaseStation selectByPrimaryKey(Long id);

    /**
     * 根据物理ID查询记录
     */
    TblBaseStation selectByRealCode(String realCode);
}