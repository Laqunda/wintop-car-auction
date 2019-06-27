package com.wintop.ms.carauction.service.impl;

import com.google.common.collect.Maps;
import com.wintop.ms.carauction.core.config.CarManagerRoleDataEnum;
import com.wintop.ms.carauction.entity.CarManagerRoleData;
import com.wintop.ms.carauction.model.CarManagerRoleDataModel;
import com.wintop.ms.carauction.service.ICarManagerRoleDataService;
import com.wintop.ms.carauction.util.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class CarManagerRoleDataServiceImpl implements ICarManagerRoleDataService {
    private IdWorker idWorker = new IdWorker(10);
    @Autowired
    private CarManagerRoleDataModel carManagerRoleDataModel;

    @Override
    public CarManagerRoleData selectByPrimaryKey(Long id) {
        return carManagerRoleDataModel.selectByPrimaryKey(id);
    }

    @Override
    public List<CarManagerRoleData> selectForCondition(Map<String, Object> map) {
        return carManagerRoleDataModel.selectForCondition(map);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteByPrimaryKey(Long id) {
        return carManagerRoleDataModel.deleteByPrimaryKey(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteForCondition(Map<String, Object> map) {
        return carManagerRoleDataModel.deleteForCondition(map);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insert(CarManagerRoleData record) {
        return carManagerRoleDataModel.insert(record);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertSelective(CarManagerRoleData record) {
        return carManagerRoleDataModel.insertSelective(record);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateByPrimaryKeySelective(CarManagerRoleData record) {
        return carManagerRoleDataModel.updateByPrimaryKeySelective(record);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateByPrimaryKey(CarManagerRoleData record) {
        return carManagerRoleDataModel.updateByPrimaryKey(record);
    }

    @Transactional( rollbackFor = Exception.class )
    @Override
    public int save(CarManagerRoleData record) {
        if (Objects.nonNull(record.getManagerId())) {
            Map<String, Object> map = Maps.newHashMap();
            map.put("managerId", record.getManagerId());
            map.put("code", CarManagerRoleDataEnum.CHABOSHI.getContent());
            this.deleteForCondition(map);
        }
        record.setId(idWorker.nextId());
        record.setCode(CarManagerRoleDataEnum.CHABOSHI.getContent());
        return this.insertSelective(record);
    }

}
