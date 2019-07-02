package com.wintop.ms.carauction.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.wintop.ms.carauction.core.config.CarManagerRoleLogEnum;
import com.wintop.ms.carauction.entity.CarManagerRoleLog;
import com.wintop.ms.carauction.entity.CarManagerUser;
import com.wintop.ms.carauction.model.CarManagerRoleLogModel;
import com.wintop.ms.carauction.service.ICarChaboshiLogService;
import com.wintop.ms.carauction.service.ICarManagerRoleLogService;
import com.wintop.ms.carauction.service.ICarManagerUserService;
import com.wintop.ms.carauction.util.utils.IdWorker;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CarManagerRoleLogServiceImpl implements ICarManagerRoleLogService {
    private IdWorker idWorker = new IdWorker(10);
    private static final Logger logger = LoggerFactory.getLogger(CustomerQuitLogServiceImpl.class);
    @Autowired
    private CarManagerRoleLogModel carManagerRoleLogModel;

    @Autowired
    private ICarChaboshiLogService carChaboshiLogService;

    @Autowired
    private ICarManagerUserService carManagerUserService;

    @Override
    public CarManagerRoleLog selectByPrimaryKey(Long id) {
        return carManagerRoleLogModel.selectByPrimaryKey(id);
    }

    @Override
    public List<CarManagerRoleLog> selectByCondition(Map<String, Object> map) {
        return carManagerRoleLogModel.selectByCondition(map);
    }

    @Override
    public int selectByConditionCount(Map<String, Object> map) {
        return carManagerRoleLogModel.selectByConditionCount(map);
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int saveOrUpdate(CarManagerRoleLog record, CarManagerUser user) {
        List<CarManagerRoleLog> entityList = Lists.newArrayList();
        if (Objects.nonNull(record.getId())) {
            entityList =  carManagerRoleLogModel.selectByCondition(Collections.singletonMap("id", record.getId()));
            if (CollectionUtils.isNotEmpty(entityList)) {
                record.setId(entityList.get(0).getId());
            }
        }
        if (Objects.isNull(record.getId())) {
            record.setId(idWorker.nextId());
            record.setApplyId(user.getId());
            record.setApplyTime(new Date());
            record.setStoreId(user.getDepartmentId());
            record.setStatus(CarManagerRoleLogEnum.APPLY.getVal());
            record.setStatusCn(CarManagerRoleLogEnum.APPLY.getMsg());
            return this.insertSelective(record);
        }

        if(!CarManagerRoleLogEnum.CANCEL.getVal().equals(record.getStatus())){
            record.setCreateTime(new Date());
            record.setCreatePerson(user.getId());
        }
        record.setStatusCn(CarManagerRoleLogEnum.PASS.getEnum(record.getStatus()).getMsg());
        if (CarManagerRoleLogEnum.PASS.getVal().equals(record.getStatus())){
            CarManagerUser applyUser = carManagerUserService.selectByPrimaryKey(entityList.get(0).getApplyId(), false);
            JSONObject obj = JSONObject.parseObject(JSONObject.toJSONString(record));
            obj.put("userId", record.getApplyId());
            obj.put("userName", applyUser.getUserName());
            obj.put("storeId", entityList.get(0).getStoreId());
            Map<String, Object> result = carChaboshiLogService.searchForStore(obj).getResult();
            logger.info(String.format("json=%s",JSONObject.toJSONString(result)));
        }
        return this.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<CarManagerRoleLog> selectByConditionForPage(Map<String, Object> map) {
        return carManagerRoleLogModel.selectByConditionForPage(map);
    }

    @Override
    public Integer selectByConditionForCount(Map<String, Object> map) {
        return carManagerRoleLogModel.selectByConditionForCount(map);
    }
}
