package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarManagerRolePage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ICarManagerRolePageWriteDao {

    /**
     * 根据roleId删除记录
     */
    int deleteByRoleId(Long roleId);

    /**
     * 保存记录,
     */
    int insert(CarManagerRolePage record);

    int batchInsert(@Param(value = "rolePages") List<CarManagerRolePage> rolePages);

}