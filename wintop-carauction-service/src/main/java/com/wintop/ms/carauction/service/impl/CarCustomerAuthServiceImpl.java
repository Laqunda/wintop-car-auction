package com.wintop.ms.carauction.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.AppUser;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarCustomerAuth;
import com.wintop.ms.carauction.entity.CarCustomerLog;
import com.wintop.ms.carauction.entity.WtAppUser;
import com.wintop.ms.carauction.model.AppUserModel;
import com.wintop.ms.carauction.model.CarCustomerAuthModel;
import com.wintop.ms.carauction.model.CarCustomerLogModel;
import com.wintop.ms.carauction.service.ICarCustomerAuthService;
import com.wintop.ms.carauction.service.IWtAppUserService;
import com.wintop.ms.carauction.util.utils.IdWorker;
import com.wintop.ms.carauction.util.utils.RedisAppUserManager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.*;

/**
 * 用户认证信息业务层
 * @author zhangzijuan
 * @date 2018-02-26
 */
@Service("carCustomerAuthService")
public class CarCustomerAuthServiceImpl implements ICarCustomerAuthService {
    @Resource
    private CarCustomerAuthModel customerAuthModel;
    @Resource
    private AppUserModel userModel;
    @Resource
    private CarCustomerLogModel logModel;
    @Autowired
    private RedisAppUserManager appUserManager;
    IdWorker idWorker=new IdWorker(10);
    private static final Logger logger = LoggerFactory.getLogger(CarCustomerAuthServiceImpl.class);

    @Override
    public ServiceResult<CarCustomerAuth> selectByPrimaryKey(Long id) {
        ServiceResult<CarCustomerAuth> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(customerAuthModel.selectByPrimaryKey(id));
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
            result.setResult(customerAuthModel.deleteByPrimaryKey(id));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> updateByPrimaryKeySelective(CarCustomerAuth record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            Integer i=customerAuthModel.updateByPrimaryKeySelective(record);
            if(i>0){
                result.setSuccess("0","更新成功");
                result.setResult(i);
            }else{
                result.setError(ResultCode.FAIL.strValue(),ResultCode.FAIL.getRemark());
            }
        }catch (Exception e){
            result.setError("-1","异常");
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> updateByPrimaryKey(CarCustomerAuth record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            Integer i=customerAuthModel.updateByPrimaryKey(record);
            if(i>0){
                result.setSuccess("0","更新成功");
                result.setResult(i);
            }else{
                result.setError(ResultCode.FAIL.strValue(),ResultCode.FAIL.getRemark());
            }
        }catch (Exception e){
            result.setError("-1","异常");
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> insert(CarCustomerAuth record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            result.setSuccess(true);
            result.setResult(customerAuthModel.insert(record));
        }catch (Exception e){
            result.setSuccess(false);
            result.setResult(null);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> insertSelective(CarCustomerAuth record) {
        ServiceResult<Integer> result=new ServiceResult<>();
        try {
            Integer i=customerAuthModel.insertSelective(record);
            if(i>0){
                result.setSuccess("0","保存成功");
                result.setResult(i);
            }else{
                result.setError(ResultCode.FAIL.strValue(),ResultCode.FAIL.getRemark());
            }
        }catch (Exception e){
            result.setError("-1","异常");
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 根据用户Id查询用户认证信息
     * @param userId
     * @return CarCustomerAuth
     */
    @Override
    public ServiceResult<CarCustomerAuth> getAuthInfoByUserId(Long userId){
        ServiceResult<CarCustomerAuth> result=new ServiceResult<>();
        try {
            CarCustomerAuth customerAuth=customerAuthModel.getAuthInfoByUserId(userId);
            if (customerAuth!=null){
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
                result.setResult(customerAuth);
            }else {
                result.setError(ResultCode.NO_OBJECT.strValue(), ResultCode.NO_OBJECT.getRemark());
            }
        }catch (Exception e){
            result.setError("-1","异常");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 查询认证列表
     *@Author:zhangzijuan
     *@date 2018/3/20
     *@param:id
     */
    public List<CarCustomerAuth> selectUserAuthList(Map<String,Object> map){
        return  customerAuthModel.selectUserAuthList(map);
    }

    /**
     * 查询认证数
     *@Author:zhangzijuan
     *@date 2018/3/20
     *@param:id
     */
    public Integer selectUserAuthCount(Map<String,Object> map){
        return customerAuthModel.selectUserAuthCount(map);
    }

    @Override
    @Transactional
    public Integer approveUserAuth(JSONObject object){
        Integer result=0;
            String authStatus=object.getString("authStatus");
            String authMsg=object.getString("authMsg");
            String userIds=object.getString("userId");
            Long managerId=object.getLong("managerId");
            if(StringUtils.isNotBlank(userIds)){
                String[] userIdArray = userIds.split(",");
                for(int i=0;i<userIdArray.length;i++){
                    Long userId=Long.valueOf(userIdArray[i]);
                    //1.修改会员信息的状态
                    WtAppUser user=new WtAppUser();
                    user.setId(userId);
                    user.setUpdateTime(new Timestamp(System.currentTimeMillis()));

                    //2 保存会员操作日志
                    CarCustomerLog log=new CarCustomerLog();
                    log.setId(idWorker.nextId());
                    log.setUserId(userId);
                    log.setMsg(authMsg);
                    log.setEditType("2");
                    log.setEditTime(new Date());
                    log.setEditUserId(managerId);

                    //3 更新认证信息表
                    CarCustomerAuth auth=new CarCustomerAuth();
                    auth.setUserId(userId);
                    auth.setApplyTime(new Date());
                    auth.setAuthMsg(authMsg);
                    auth.setAuthStatus(authStatus);
                    auth.setAuthManager(managerId);

 //            审核通过 会员状态变为 3已认证--未签约
                    if("2".equals(authStatus)){
                        user.setStatus("3");
//                    会员操作日志  3已认证--未签约
                        log.setStatus("3");
                    }else if ("-1".equals(authStatus)){
//             审核不通过 会员状态变为 1未实名认证
                        user.setStatus("1");
//                     会员操作日志  11实名认证审核不通过
                        log.setStatus("11");
                    }else {
                        return result;
                    }

                    if(userModel.updateUser(user)>0 && customerAuthModel.updateByUserId(auth)>0){
                        appUserManager.updateUserStatus(user.getId()+"",user.getStatus());
                        result=logModel.insertSelective(log);
                    }

                }
            }
        return result;
    }

    @Override
    @Transactional
    public Integer saveAuthInfo(JSONObject object){
        Integer result=0;
        Long userId=object.getLong("userId");
        WtAppUser user=userModel.findById(userId);
        if(user!=null){
            CarCustomerAuth customerAuth=JSONObject.toJavaObject(object,CarCustomerAuth.class);

            //1 修改用户的状态 2-认证未审核
            user.setId(userId);
            user.setStatus("2");
            user.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            user.setAddress(customerAuth.getCityName()+customerAuth.getAddress());
            userModel.updateUser(user);
            //2 更新会员认证信息
            //1)提交审核时状态设置为待审核
            customerAuth.setAuthStatus("1");
            customerAuth.setApplyTime(new Date());
            customerAuth.setIsAvailable("0");
            //2)根据用户id查询该用户是否有认证信息
            CarCustomerAuth customerAuth1=customerAuthModel.getAuthInfoByUserId(userId);
            //如果有认证信息，则修改
            if(customerAuth1!=null){
                customerAuth.setId(customerAuth1.getId());
                result=customerAuthModel.updateByPrimaryKeySelective(customerAuth);
            }else {
                //如果没有认证信息，则添加认证信息
                customerAuth.setId(idWorker.nextId());
                result= customerAuthModel.insertSelective(customerAuth);
            }
                //3 保存会员操作日志
                CarCustomerLog log=new CarCustomerLog();
                log.setId(idWorker.nextId());
                log.setUserId(userId);
                log.setLogId(customerAuth.getId());
                log.setEditType("1");
            //        2-认证未审核
                log.setStatus("2");
                log.setMsg("会员提交认证信息");
                log.setEditTime(new Date());
                log.setEditUserId(userId);
            logModel.insertSelective(log);
        }
        return result;
    }
}