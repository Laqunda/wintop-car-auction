package com.wintop.ms.carauction.mapper.read;


import com.wintop.ms.carauction.entity.CarManagerRoleLog;

import java.util.List;
import java.util.Map;

public interface ICarManagerRoleLogReadDao {

    CarManagerRoleLog selectByPrimaryKey(Long id);

    List<CarManagerRoleLog> selectByCondtion(Map<String,Object> map);
}