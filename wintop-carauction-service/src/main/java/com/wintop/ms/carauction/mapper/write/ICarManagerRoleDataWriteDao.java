package com.wintop.ms.carauction.mapper.write;


import com.wintop.ms.carauction.entity.CarManagerRoleData;

import java.util.Map;

public interface ICarManagerRoleDataWriteDao {
    int deleteByPrimaryKey(Long id);

    int insert(CarManagerRoleData record);

    int insertSelective(CarManagerRoleData record);

    int updateByPrimaryKeySelective(CarManagerRoleData record);

    int updateByPrimaryKey(CarManagerRoleData record);

    int deleteForCondition(Map<String, Object> map);
}