package com.wintop.ms.carauction.mapper.read;


import com.wintop.ms.carauction.entity.CarManagerRoleData;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ICarManagerRoleDataReadDao {

    CarManagerRoleData selectByPrimaryKey(Long id);

    List<CarManagerRoleData> selectAll(Map<String, Object> map);
}