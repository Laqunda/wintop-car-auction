package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.entity.CarManagerRoleLog;
import com.wintop.ms.carauction.entity.CarManagerUser;

import java.util.List;
import java.util.Map;

public interface ICarManagerRoleLogService {

    CarManagerRoleLog selectByPrimaryKey(Long id);

    List<CarManagerRoleLog> selectByCondition(Map<String, Object> map);
    
    int selectByConditionCount(Map<String, Object> map);

    int deleteByPrimaryKey(Long id);

    int insert(CarManagerRoleLog record);

    int insertSelective(CarManagerRoleLog record);

    int updateByPrimaryKeySelective(CarManagerRoleLog record);

    int updateByPrimaryKey(CarManagerRoleLog record);

    int saveOrUpdate(CarManagerRoleLog record, CarManagerUser user);

}
