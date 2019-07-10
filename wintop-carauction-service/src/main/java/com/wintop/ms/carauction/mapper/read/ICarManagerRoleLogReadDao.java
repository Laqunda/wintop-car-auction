package com.wintop.ms.carauction.mapper.read;


import com.wintop.ms.carauction.entity.CarManagerRoleLog;

import java.util.List;
import java.util.Map;

public interface ICarManagerRoleLogReadDao {

    CarManagerRoleLog selectByPrimaryKey(Long id);

    List<CarManagerRoleLog> selectByCondition(Map<String,Object> map);

    int selectByConditionCount(Map<String,Object> map);

    List<CarManagerRoleLog> selectByConditionForPage(Map<String, Object> map);

    Integer selectByConditionForCount(Map<String, Object> map);
}