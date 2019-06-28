package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.config.CarManagerRoleDataEnum;
import com.wintop.ms.carauction.core.config.CarManagerRoleLogEnum;
import com.wintop.ms.carauction.entity.CarManagerRoleLog;
import com.wintop.ms.carauction.model.CarManagerRoleLogModel;
import com.wintop.ms.carauction.service.ICarManagerRoleLogService;
import com.wintop.ms.carauction.util.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class CarManagerRoleLogServiceImpl implements ICarManagerRoleLogService {
    private IdWorker idWorker = new IdWorker(10);
    @Autowired
    private CarManagerRoleLogModel carManagerRoleLogModel;

    @Override
    public CarManagerRoleLog selectByPrimaryKey(Long id) {
        return carManagerRoleLogModel.selectByPrimaryKey(id);
    }

    @Override
    public List<CarManagerRoleLog> selectByCondtion(Map<String, Object> map) {
        return carManagerRoleLogModel.selectByCondtion(map);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return carManagerRoleLogModel.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CarManagerRoleLog record) {
        return carManagerRoleLogModel.insert(record);
    }

    @Override
    public int insertSelective(CarManagerRoleLog record) {
        return carManagerRoleLogModel.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(CarManagerRoleLog record) {
        return carManagerRoleLogModel.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CarManagerRoleLog record) {
        return carManagerRoleLogModel.updateByPrimaryKey(record);
    }

    @Override
    public int saveOrUpdate(CarManagerRoleLog record,Long managerId) {
        record.setCreateTime(new Date());
        record.setCreatePerson(managerId);
        record.setStatusCn(CarManagerRoleLogEnum.PASS.getEnum(record.getStatus()).getMsg());
        if (Objects.isNull(record.getId())) {
            record.setId(idWorker.nextId());
            return this.insertSelective(record);
        }
        return this.updateByPrimaryKeySelective(record);
    }
}
