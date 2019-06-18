package com.wintop.ms.carauction.mapper.write;


import com.wintop.ms.carauction.entity.CarManagerRolePublish;

import java.util.Map;

public interface CarManagerRolePublishWriteDao {
    int deleteByPrimaryKey(Long id);

    int insertSelective(CarManagerRolePublish record);

    int insert(CarManagerRolePublish record);

    int deleteByCondition(Map<String, Object> map);

    int updateByPrimaryKeySelective(CarManagerRolePublish record);
}