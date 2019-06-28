package com.wintop.ms.carauction.mapper.write;


import com.wintop.ms.carauction.entity.CarManagerRoleLog;

public interface ICarManagerRoleLogWriteDao {
    int deleteByPrimaryKey(Long id);

    int insert(CarManagerRoleLog record);

    int insertSelective(CarManagerRoleLog record);

    int updateByPrimaryKeySelective(CarManagerRoleLog record);

    int updateByPrimaryKey(CarManagerRoleLog record);
}