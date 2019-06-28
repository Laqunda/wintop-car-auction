package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.entity.CarManagerRoleLog;

import java.util.List;
import java.util.Map;

public interface ICarManagerRoleLogService {

    public CarManagerRoleLog selectByPrimaryKey(Long id);

    public List<CarManagerRoleLog> selectByCondition(Map<String, Object> map);

    public int deleteByPrimaryKey(Long id);

    public int insert(CarManagerRoleLog record);

    public int insertSelective(CarManagerRoleLog record);

    public int updateByPrimaryKeySelective(CarManagerRoleLog record);

    public int updateByPrimaryKey(CarManagerRoleLog record);

    public int saveOrUpdate(CarManagerRoleLog record, Long managerId);

}
