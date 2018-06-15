package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarCustomerGroup;
import com.wintop.ms.carauction.model.AppUserModel;
import com.wintop.ms.carauction.model.CarCustomerGroupDetailModel;
import com.wintop.ms.carauction.model.CarCustomerGroupModel;
import com.wintop.ms.carauction.service.ICarCustomerGroupService;
import com.wintop.ms.carauction.util.utils.IdWorker;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.*;

/**
 * 用户分组
 * @author zhangzijuan
 * @date 2018-02-26
 */
@Service("carCustomerGroupService")
public class CarCustomerGroupServiceImpl implements ICarCustomerGroupService {
    @Resource
    private CarCustomerGroupModel groupModel;
    @Resource
    private AppUserModel appUserModel;

    @Resource
    private CarCustomerGroupDetailModel groupDetailModel;
    private static final Logger logger = LoggerFactory.getLogger(CarCustomerGroupServiceImpl.class);

    @Override
    public ServiceResult<CarCustomerGroup> selectByPrimaryKey(Long id) {
        ServiceResult<CarCustomerGroup> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(groupModel.selectByPrimaryKey(id));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> deleteByPrimaryKey(Long id) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(groupModel.deleteByPrimaryKey(id));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> updateByPrimaryKeySelective(CarCustomerGroup record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(groupModel.updateByPrimaryKeySelective(record));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> updateByPrimaryKey(CarCustomerGroup record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(groupModel.updateByPrimaryKey(record));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> insert(CarCustomerGroup record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(groupModel.insert(record));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> insertSelective(CarCustomerGroup record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(groupModel.insertSelective(record));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    public ServiceResult<List<CarCustomerGroup>> selectGroupAndNum(Map<String,Object> map){
        ServiceResult<List<CarCustomerGroup>> result=new ServiceResult<>();
        try {
            //  查询全部会员数
            CarCustomerGroup customerGroup=new CarCustomerGroup();
            customerGroup.setUserNum(appUserModel.selectCountByParam(map));
            customerGroup.setGroupName("全部会员");
            customerGroup.setId(0l);
            //查询没有分组的会员
            CarCustomerGroup noGroup=new CarCustomerGroup();
            noGroup.setId(1l);
            noGroup.setUserNum(appUserModel.selectCountNoGroup(map));
            noGroup.setGroupName("默认分组会员");
            List<CarCustomerGroup> carCustomerGroups=new ArrayList<>();
            carCustomerGroups.add(customerGroup);
            carCustomerGroups.add(noGroup);
            //查询会员分组及数量
            List<CarCustomerGroup> customerGroups=groupModel.selectGroupAndNum(map);
            carCustomerGroups.addAll(customerGroups);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            result.setResult(carCustomerGroups);
        }catch (Exception e){
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }
    /**
     * 新建会分组
     * @Author:zhangzijuan
     * @param record
     * @return
     */
    public ServiceResult<Map<String,Object>> saveGroup(CarCustomerGroup record){
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        try {
            if(StringUtils.isNotBlank(record.getGroupName())){
                IdWorker idWorker=new IdWorker(10);
                record.setId(idWorker.nextId());
                record.setStatus("1");
                record.setCreateTime(new Date());
                Integer i=groupModel.insertSelective(record);
                if(i>0){
                    result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
                }else {
                    result.setError(ResultCode.FAIL.strValue(),ResultCode.FAIL.getRemark());
                }
                Map<String,Object> map=new HashMap<>();
                map.put("count",i);
                result.setResult(map);
            }else {
                result.setError(ResultCode.NO_PARAM.strValue(),ResultCode.NO_PARAM.getRemark());
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    @Override
    public ServiceResult<List<CarCustomerGroup>> selectGroupForSelect(Map<String, Object> map) {
        ServiceResult<List<CarCustomerGroup>> result=new ServiceResult<>();
        try {
            List<CarCustomerGroup> groupList=groupModel.selectGroupForSelect(map);
            if(groupList!=null && groupList.size()!=0){
                result.setResult(groupList);
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            }else {
                result.setError(ResultCode.NO_OBJECT.strValue(),ResultCode.NO_OBJECT.getRemark());
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }
    @Transactional
    public ServiceResult<Map<String,Object>> deleteGroupById(Long groupId){
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        try {
            //删除该分组的信息
            Integer i=groupModel.deleteByPrimaryKey(groupId);
            //将输入该分组的人移除
            groupDetailModel.deleteByGroupId(groupId);
            if(i>0){
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            }else {
                result.setError(ResultCode.FAIL.strValue(),ResultCode.FAIL.getRemark());
            }
            Map<String,Object> map=new HashMap<>();
            map.put("count",i);
            result.setResult(map);
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }
}