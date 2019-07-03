package com.wintop.ms.carauction.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.wintop.ms.carauction.core.config.CarManagerRoleLogEnum;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.ServiceResult;
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
    public ServiceResult<Map<String, Object>> saveOrUpdate(CarManagerRoleLog record, CarManagerUser user) {
        ServiceResult<Map<String, Object>> result = new ServiceResult<>();
        if (Objects.isNull(record.getId())) {
            record.setId(idWorker.nextId());
            record.setApplyId(user.getId());
            record.setApplyTime(new Date());
            record.setStoreId(user.getDepartmentId());
            record.setStatus(CarManagerRoleLogEnum.APPLY.getVal());
            record.setStatusCn(CarManagerRoleLogEnum.APPLY.getMsg());
            this.insertSelective(record);
            result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
            return result;
        }
        if(!CarManagerRoleLogEnum.CANCEL.getVal().equals(record.getStatus())){
            record.setCreateTime(new Date());
            record.setCreatePerson(user.getId());
        }
        record.setStatusCn(CarManagerRoleLogEnum.PASS.getEnum(record.getStatus()).getMsg());
        //审核的数据
        CarManagerRoleLog roleLog = carManagerRoleLogModel.selectByPrimaryKey(record.getId());
        if (CarManagerRoleLogEnum.PASS.getVal().equals(record.getStatus())){
            CarManagerUser applyUser = carManagerUserService.selectByPrimaryKey(roleLog.getApplyId(), false);
            JSONObject obj = JSONObject.parseObject(JSONObject.toJSONString(roleLog));
            obj.put("userId", roleLog.getApplyId());
            obj.put("userName", applyUser.getUserName());
            obj.put("storeId", roleLog.getStoreId());
            ServiceResult<Map<String, Object>> serviceResult = carChaboshiLogService.searchForStore(obj);
            if(!serviceResult.getCode().equals(ResultCode.SUCCESS.strValue())){
                return serviceResult;
            }
            logger.info(String.format("json=%s",JSONObject.toJSONString(serviceResult.getResult())));
        }
        this.updateByPrimaryKeySelective(record);
        result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());
        return result;
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
