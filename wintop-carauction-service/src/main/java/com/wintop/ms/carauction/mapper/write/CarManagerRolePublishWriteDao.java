package com.wintop.ms.carauction.mapper.write;


import com.wintop.ms.carauction.entity.CarManagerRolePublish;

public interface CarManagerRolePublishWriteDao {
    int deleteByPrimaryKey(Long id);

    int insertSelective(CarManagerRolePublish record);

    int updateByPrimaryKeySelective(CarManagerRolePublish record);
}