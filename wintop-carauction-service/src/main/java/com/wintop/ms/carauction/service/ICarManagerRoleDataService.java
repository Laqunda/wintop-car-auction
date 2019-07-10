package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.entity.CarManagerRoleData;

import java.util.List;
import java.util.Map;

public interface ICarManagerRoleDataService {

    public CarManagerRoleData selectByPrimaryKey(Long id);

    public List<CarManagerRoleData> selectForCondition(Map<String, Object> map);

    public int deleteByPrimaryKey(Long id);

    public int deleteForCondition(Map<String, Object> map);

    public int insert(CarManagerRoleData record);

    public int insertSelective(CarManagerRoleData record);

    public int updateByPrimaryKeySelective(CarManagerRoleData record);

    public int updateByPrimaryKey(CarManagerRoleData record);

    public int save(CarManagerRoleData record);

}
