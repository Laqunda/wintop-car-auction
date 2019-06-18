package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.entity.CarManagerRolePublish;

import java.util.List;
import java.util.Map;

public interface ICarManagerRolePublishService {

    public CarManagerRolePublish selectByPrimaryKey(Long id);

    public List<CarManagerRolePublish> selectByCondition(Map<String, Object> param);

    public Map<Long,List<CarManagerRolePublish>> selectTreeByCondition(Map<String, Object> param);

    public int deleteByPrimaryKey(Long id);

    public int insertSelective(CarManagerRolePublish record);

    public int insert(CarManagerRolePublish record);

    public int updateByPrimaryKeySelective(CarManagerRolePublish record);

    public void saveOrUpdate(List<CarManagerRolePublish> recordList);

}
