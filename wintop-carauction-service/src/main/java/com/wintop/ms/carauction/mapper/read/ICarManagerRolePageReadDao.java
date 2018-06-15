package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarManagerRolePage;
import java.util.List;
import java.util.Map;

public interface ICarManagerRolePageReadDao {

    /**
     * 根据roleId查询所有数据
     */
    List<CarManagerRolePage> selectAll(Long roleId);

    /**
     * 根据roleId查询pageId
     */
    List<String> selectAllPages(Long roleId);

}