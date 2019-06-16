package com.wintop.ms.carauction.mapper.read;


import com.wintop.ms.carauction.entity.CarManagerRolePublish;

import java.util.List;
import java.util.Map;

public interface CarManagerRolePublishReadDao {

    CarManagerRolePublish selectByPrimaryKey(Long id);

    List<CarManagerRolePublish> selectByCondition(Map<String,Object> param);
}