package com.wintop.ms.carauction.service.impl;

import com.google.common.base.Predicates;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import com.google.common.primitives.Longs;
import com.wintop.ms.carauction.controller.BargainingAuditApi;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.AppUser;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.entity.*;
import com.wintop.ms.carauction.model.*;
import com.wintop.ms.carauction.service.IWtAppUserService;
import com.wintop.ms.carauction.util.utils.IdWorker;
import com.wintop.ms.carauction.util.utils.RandCodeUtil;
import com.wintop.ms.carauction.util.utils.RedisAppUserManager;
import com.wintop.ms.carauction.util.utils.RedisTokenManager;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by liangtingsen on 2018/2/5.
 * 客户端用户使用
 */
@Service
public class WtAppUserServiceImpl implements IWtAppUserService {
    private static final Logger logger = LoggerFactory.getLogger(BargainingAuditApi.class);
    @Resource
    private AppUserModel appUserModel;
    @Resource
    private CarCustomerDepositModel depositModel;
    @Resource
    private CarCustomerLogModel logModel;
    @Resource
    private CarCustomerGroupDetailModel groupDetailModel;
    @Resource
    private CarCustomerAuthModel authModel;
    @Resource
    private CarCustomerSignModel signModel;
    @Resource
    private CarFinancePayLogModel payLogModel;
    @Resource
    private  CarCustomerLevelModel levelModel;
    @Resource
    private CarCustomerDepositLogModel depositLogModel;
    @Resource
    private CarCustomerSignLogModel signLogModel;
    @Autowired
    private RedisAppUserManager appUserManager;
    @Autowired
    private RedisTokenManager tokenManager;
    private IdWorker idWorker = new IdWorker(10);

    /***
     * 根据用户名查询用户信息
     * @param userName
     * @return
     */
    @Override
    public ServiceResult<WtAppUser> findByUserByUsername(String userName) {
        ServiceResult<WtAppUser> serviceResult = new ServiceResult<>();
        try {
            WtAppUser appUser = appUserModel.findByUserName(userName);
            serviceResult.setResult(appUser);
            serviceResult.setSuccess("0","查询成功");
        }catch (Exception e){
            e.printStackTrace();
            serviceResult.setError("-1","异常");
        }finally {
            return serviceResult;
        }
    }

    @Override
    public ServiceResult<WtAppUser> findByUserNamePwd(String userName, String password) {
        ServiceResult<WtAppUser> serviceResult = new ServiceResult<>();
        try {
            WtAppUser appUser = appUserModel.findByUserName(userName);
            if (appUser!=null){
                if (appUser.getPassword().equals(password)){
                    serviceResult.setResult(appUser);
                    serviceResult.setSuccess("0","登陆成功");
                }else {
                    serviceResult.setError("1","密码错误");
                }
            }else {
                serviceResult.setError("2","登陆账号不存在");
            }
        }catch (Exception e){
            e.printStackTrace();
            serviceResult.setError("-1","异常");
        }finally {
            return serviceResult;
        }
    }
    /***
     * 根据用户ID查询用户详情
     * @param appUserId
     * @return
     */
    @Override
    public ServiceResult<WtAppUser> findById(Long appUserId) {
        ServiceResult<WtAppUser> serviceResult = new ServiceResult<>();
        try {
            WtAppUser appUser = appUserModel.findById(appUserId);
            if (appUser!=null){
                serviceResult.setResult(appUser);
                serviceResult.setSuccess("0","成功");
            }else {
                serviceResult.setError("1","用户不存在");
            }
        }catch (Exception e){
            e.printStackTrace();
            serviceResult.setError("-1","异常");
        }finally {
            return serviceResult;
        }
    }

    /***
     * 保存用户信息
     * @param appUser
     * @return
     */
    @Override
    @Transactional
    public ServiceResult<WtAppUser> saveUser(WtAppUser appUser) {
        ServiceResult<WtAppUser> result = new ServiceResult<>();
        try {
            CarCustomerLevel carCustomerLevel = levelModel.getDefaultLevel();
            appUser.setId(idWorker.nextId());
            appUser.setRegistTime(new Timestamp(System.currentTimeMillis()));
            appUser.setStatus("1");
            appUser.setMobile(appUser.getUserName());
            appUser.setUserLevelId(carCustomerLevel.getId());
            appUser.setUserNum(RandCodeUtil.getUserCode());
            appUserModel.saveUser(appUser);
            result.setResult(appUser);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.setError("-1","失败");
        }finally {
            return result;
        }
    }

    /***
     * 修改用户信息
     * @param appUser
     * @return
     */
    @Override
    @Transactional
    public ServiceResult<WtAppUser> updateUser(WtAppUser appUser) {
        ServiceResult<WtAppUser> result = new ServiceResult<>();
        try {
            appUser.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            appUserModel.updateUser(appUser);
            result.setResult(appUser);
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.setError("-1","失败");
        }finally {
            return result;
        }
    }

    /***
     * 修改用户密码
     * @param appUser
     * @return
     */
    @Override
    @Transactional
    public ServiceResult<WtAppUser> updatePwd(WtAppUser appUser) {
        ServiceResult<WtAppUser> result = new ServiceResult<>();
        try {
            appUser.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            appUserModel.updatePwd(appUser);
            appUser = appUserModel.findByUserName(appUser.getUserName());
            result.setResult(appUser);
            result.setSuccess("0", "成功");
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.setError("-1", "失败");
        } finally {
            return result;
        }
    }
    @Override
    public ServiceResult<WtAppUser> selectUserById(Long appUserId) {
        ServiceResult<WtAppUser> serviceResult = new ServiceResult<>();
        try {
            WtAppUser appUser = appUserModel.selectUserById(appUserId);
            if (appUser!=null){
                serviceResult.setResult(appUser);
                serviceResult.setSuccess("0","查询成功");
            }else {
                serviceResult.setError(ResultCode.NO_OBJECT.strValue(),ResultCode.NO_OBJECT.getRemark());
            }
        }catch (Exception e){
            e.printStackTrace();
            serviceResult.setError("-1","异常");
        }finally {
            return serviceResult;
        }
    }
    @Override
    public List<WtAppUser> selectListByParam(Map<String,Object> map){
        return appUserModel.selectListByParam(map);
    }
    @Override
    public Integer selectCountByParam(Map<String,Object> map){
        return appUserModel.selectCountByParam(map);
    }
    @Override
    @Transactional
    public ServiceResult<Map<String,Object>> userSignOut(JSONObject object){
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        try {
            Integer i=0;
            Long userId=object.getLong("userId");
            Long managerId=object.getLong("managerId");
            WtAppUser user=appUserModel.selectUserById(userId);
            //判断会员的状态是否是签约审核通过的
            if (user!=null && "7".equals(user.getStatus())){
                //设置会员状态为未实名认证
                user.setStatus("1");
                user.setUpdateTime(new Timestamp(System.currentTimeMillis()));
//                拍牌号置空
                user.setAuctionPlateNum(null);
                Map<String,Object> map=new HashMap<>();
                map.put("userId",userId);
                map.put("status",'2');
                //查询会员的可用保证金
                CarCustomerDeposit deposit=depositModel.selectDepositByUserId(map);
                if(appUserModel.updateUserInfo(user)>0){

                    //将会员的认证状态更改 1==不可用
                    CarCustomerAuth auth=authModel.getAuthInfoByUserId(userId);
                    if (auth!=null){
                        auth.setIsAvailable("1");
                        authModel.updateByPrimaryKeySelective(auth);
                    }

                    //将会员的签约状态更改为删除 1==已删除
                    CarCustomerSign sign=signModel.querySignByUserId(userId);
                    if (sign!=null){
                        sign.setIsDelete("1");
                        signModel.updateByPrimaryKeySelective(sign);
                    }
                    tokenManager.deleteCusAppUserToken(user.getId()+"");
                    appUserManager.cleanAppUser(user.getId()+"");

                    //保存客户操作日志
                    CarCustomerLog log=new CarCustomerLog();
                    log.setId(idWorker.nextId());
                    log.setUserId(userId);
                    log.setStatus("12");
                    log.setFile(object.getString("file"));
                    log.setMsg(object.getString("msg"));
                    log.setEditType("2");
                    log.setEditTime(new Date());

                    log.setEditUserId(managerId);
                    if (deposit!=null){
                        //将保证金金额设置为0
                        deposit.setDepositAmount(new BigDecimal(0));
                        //将保证金状态设置为 1：不可用
                        deposit.setUseStatus("1");
                        deposit.setUserId(userId);
                        depositModel.updateByPrimaryKeySelective(deposit);

                        //保存资金流水日志
                        CarFinancePayLog financePayLog=new CarFinancePayLog();
                        financePayLog.setId(idWorker.nextId());
                        financePayLog.setLogNo(RandCodeUtil.getOrderNumber());
//                支付类型 1保证金
                        financePayLog.setType("1");
                        financePayLog.setPayFee(deposit.getDepositAmount());
                        financePayLog.setPayTime(new Date());
                        financePayLog.setOrderId(deposit.getId());
                        financePayLog.setOrderNo(deposit.getDepositNo());
                        financePayLog.setUserId(userId);
//                支付状态 3退款
                        financePayLog.setStatus("3");
//                支付途径 2线下
                        financePayLog.setPayType("2");
                        financePayLog.setPayEvidence(object.getString("file"));
                        financePayLog.setRemark(object.getString("msg"));
                        financePayLog.setCreatePerson(managerId);
                        financePayLog.setCreatePersonType("2");
                        financePayLog.setCreateTime(new Date());
//                支付方式 5其他
                        financePayLog.setPayWay("5");
                        payLogModel.insert(financePayLog);

                        log.setLogId(financePayLog.getId());
                    }
                    i=logModel.insertSelective(log);


                }
            }
            Map<String,Object> map=new HashMap<>();
            map.put("count",i);
            if(i>0){
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            }else{
                result.setError(ResultCode.FAIL.strValue(),ResultCode.FAIL.getRemark());
            }
            result.setResult(map);
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 批量修改用户级别
     *@Author:zhangzijuan
     *@date 2018/3/17
     *@param:map
     */
    @Override
    public ServiceResult<Map<String,Object>> batchUpdateUserLevel(JSONObject object){
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        try {
            Map<String,Object> map=new HashMap<>();
            Integer a=0;
            Long managerId=object.getLong("managerId");
            map.put("userLevelId",object.getLong("userLevelId"));
            CarCustomerLevel level=levelModel.selectByPrimaryKey(object.getLong("userLevelId"));
            List<Long> userIdList = new ArrayList<Long>();
            if (level!=null){
                map.put("updateTime",new Date());
                String userIds=object.getString("userIds");
                if(StringUtils.isNotBlank(userIds)){
                    String[] userIdArray = userIds.split(",");
                    for(int i=0;i<userIdArray.length;i++){
                        Long userId=Long.valueOf(userIdArray[i]);
                        userIdList.add(userId);
                        // 保存会员操作日志
                        CarCustomerLog log=new CarCustomerLog();
                        log.setId(idWorker.nextId());
                        log.setUserId(userId);
                        log.setStatus("10");
                        log.setMsg(object.getString("msg"));
                        log.setEditType("2");
                        log.setEditTime(new Date());
                        log.setEditUserId(managerId);
                        logModel.insertSelective(log);
                    }
                    map.put("userIds",userIdList);
                    a=appUserModel.batchUpdateUserLevel(map);
                }
            }
            if (a>0){
                for (int i=0;i<userIdList.size();i++){
                    AppUser appUser= appUserManager.getAppUser(userIdList.get(i)+"");
                    if (appUser!=null){
                        appUser.setUserRankId(object.getLong("userLevelId"));
                        appUserManager.saveUser(appUser);
                    }
                }
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());;
            }else {
                result.setError(ResultCode.FAIL.strValue(),ResultCode.FAIL.getRemark());
            }
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }
    @Override
    @Transactional
    public ServiceResult<Map<String,Object>> updateUserGroup(JSONObject object){
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        try {
            Integer a=0;
            Long managerId=object.getLong("managerId");
            List<Long> userIds= Splitter.on(",").splitToList(object.getString("userId")).stream().map(v-> Longs.tryParse(v)).collect(Collectors.toList());
            String groupIds=object.getString("groupIds");
            String[] groupIdArray = groupIds.split(",");
                userIds.forEach(userId->{
                    groupDetailModel.deleteByUserId(userId);
                });
            for (int j = 0; j < userIds.size(); j++) {
                for(int i=0;i<groupIdArray.length;i++){
                    CarCustomerGroupDetail groupDetail=new CarCustomerGroupDetail();
                    groupDetail.setId(idWorker.nextId());
                    groupDetail.setCreateManager(managerId);
                    groupDetail.setCreateTime(new Date());
                    groupDetail.setCustomerId(userIds.get(j));
                    groupDetail.setGroupId(Long.valueOf(groupIdArray[i]));
                    a=groupDetailModel.insertSelective(groupDetail);
                }
                //保存会员操作日志
                CarCustomerLog log=new CarCustomerLog();
                log.setId(idWorker.nextId());
                log.setUserId(userIds.get(j));
                log.setStatus("9");
                log.setMsg(object.getString("msg"));
                log.setEditType("2");
                log.setEditTime(new Date());
                log.setEditUserId(managerId);
                logModel.insertSelective(log);
            }
            if (a>0){
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());;
            }else {
                result.setError(ResultCode.FAIL.strValue(),ResultCode.FAIL.getRemark());
            }
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /**
     * 修改会员
     * @Author:zhangzijuan
     * @param object
     * @return
     */
    @Transactional
    public ServiceResult<Map<String,Object>> editUser(JSONObject object){
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        try {
            Integer i=0;
            Long userId=object.getLong("userId");
//            判断车商号是否为空
            if(StringUtils.isNotBlank(object.getString("userNum"))){
                //根据用户id和车商号判断该车商号是已经存在
                i=appUserModel.selectUserNumIsRepeat(userId,object.getString("userNum"));
//                如果存在直接返回
                if(i>0){
                    result.setError(ResultCode.USERNUM_IS_REPEAT.strValue(),ResultCode.USERNUM_IS_REPEAT.getRemark());
                    return result;
                }
            }

            // 判断手机号是否为空
            if(StringUtils.isNotBlank(object.getString("mobile"))){
                //根据用户id和手机号判断该车商是否已经存在
                Map<String,Object> map=new HashMap<>();
                map.put("mobile",object.getString("mobile"));
                map.put("userId",userId);
                WtAppUser user=appUserModel.selectUserByMobile(map);
//                如果存在直接返回
                if(user!=null){
                    result.setError(ResultCode.REPEAT_MOBILE.strValue(),ResultCode.REPEAT_MOBILE.getRemark());
                    return result;
                }
            }

            // 判断拍牌号是否为空
            if(StringUtils.isNotBlank(object.getString("auctionPlateNum"))){
                Map<String,Object> map=new HashMap<>();
                map.put("auctionPlateNum",object.getString("auctionPlateNum"));
                map.put("userId",userId);
                //根据用户id和拍牌号判断该车商是否已经存在
                WtAppUser user=appUserModel.selectUserByAuctionPlateNum(map);
//                如果存在直接返回
                if(user!=null){
                    result.setError(ResultCode.REPEAT_AUCTION_PLATE.strValue(),ResultCode.REPEAT_AUCTION_PLATE.getRemark());
                    return result;
                }
            }
            WtAppUser user = JSONObject.toJavaObject(object,WtAppUser.class);
            user.setId(userId);
            user.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            //更新基础表数据
            appUserModel.updateUser(user);

            //查询该客户是否有认证信息
            CarCustomerAuth auth=authModel.getAuthInfoByUserId(userId);

            CarCustomerAuth auth1=JSONObject.toJavaObject(object,CarCustomerAuth.class);
            auth1.setUserId(userId);
            auth1.setRealName(object.getString("name"));

//            如果有,更新认证信息表信息
            if(auth!=null){
                auth1.setId(auth.getId());
               authModel.updateByPrimaryKeySelective(auth1);
            }else{
                auth1.setId(idWorker.nextId());
                //如果没有生成认证信息
                auth1.setAuthStatus("1");
                auth1.setSignPact("1");
                authModel.insertSelective(auth1);
            }

            if(StringUtils.isNotBlank(object.getString("groupIds"))){
                //更新用户分组
                String groupIds=object.getString("groupIds");
                String[] groupIdArray = groupIds.split(",");
                groupDetailModel.deleteByUserId(userId);
                for(int j=0;j<groupIdArray.length;j++){
                    CarCustomerGroupDetail groupDetail=new CarCustomerGroupDetail();
                    groupDetail.setId(idWorker.nextId());
                    //创建人
                     groupDetail.setCreateManager(object.getLong("managerId"));
                    groupDetail.setCreateTime(new Date());
                    groupDetail.setCustomerId(userId);
                    groupDetail.setGroupId(Long.valueOf(groupIdArray[j]));
                    groupDetailModel.insertSelective(groupDetail);
                }
            }
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /**
     * 查询会员车拍牌号是否重复
     *
     * @param mobile
     * @param auctionPlateNum
     * @return
     * @Author:zhangzijuan
     */
    @Override
    public Integer selectAuctionPlateNumIsRepeat(String mobile, String auctionPlateNum) {
        return appUserModel.selectAuctionPlateNumIsRepeat(mobile,auctionPlateNum);
    }

    /**
     * 查询会员详情信息
     * @Author:zhangzijuan
     * @param userId
     * @return
     */
    @Override
    public ServiceResult<WtAppUser> getUserInfoById(Long userId){
        ServiceResult<WtAppUser> result=new ServiceResult<>();
        try {
            WtAppUser user=appUserModel.getUserInfoById(userId);
            if (user!=null){
                user.setDepositAmount(depositModel.getDepositAmountByUserId(userId));
                result.setResult(user);
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());;
            }else {
                result.setError(ResultCode.NO_OBJECT.strValue(),ResultCode.NO_OBJECT.getRemark());
            }
        }catch (Exception e){
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
            e.printStackTrace();
        }
        return result;
    }

    @Override
    @Transactional
    public Integer approveSignInfo(JSONObject object){
        Integer result=0;
        Long userId=object.getLong("userId");
        //查询用户的签约信息
        CarCustomerSign sign=signModel.findById(object.getLong("signId"));
        //查询用户待审核的保证金
        Map<String,Object> map=new HashMap<>();
        map.put("userId",userId);
//        map.put("status","1");
        CarCustomerDeposit deposit=depositModel.selectDepositByUserId(map);
        if(sign==null || deposit==null){
            return  result;
        }
        String authStatus=object.getString("authStatus");
        System.out.println("----签约传入的状态---"+authStatus);
        String authMsg=object.getString("authMsg");
        Long managerId=object.getLong("managerId");
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

                //3 更新签约信息表
                sign.setAuthMsg(authMsg);
                sign.setAuthTime(new Date());
                sign.setAuthManager(managerId);

                // 4 更新保证金信息表
                deposit.setAuthManager(managerId);
                deposit.setAuthMsg(authMsg);
                deposit.setAuthTime(new Date());
//            2=签约审核通过
                if("2".equals(authStatus)){
//                    会员状态变为 7签约审核通过
                    user.setStatus("7");
//                    签约审核 2签约审核通过
                    sign.setStatus("2");
//                    保证金审核 2 审核通过
                    deposit.setStatus("2");

//               3=签约审核不通过，保证金通过
                }else if ("3".equals(authStatus)){
//                  会员状态变为 6签约审核不通过
                    user.setStatus("6");
//                    签约审核  3审核失败作废
                    sign.setStatus("3");
//                    保证金审核 2审核通过
                    deposit.setStatus("2");

//                 4=保证金审核不通过，签约通过
                }else if ("4".equals(authStatus)){
//              会员状态变为 4已签约-未缴纳保证金
                    user.setStatus("4");
//                    签约审核  2审核通过
                    sign.setStatus("2");
//                    保证金审核 -1不通过
                    deposit.setStatus("-1");

//              5=都不通过
                }else if ("5".equals(authStatus)) {
//             保证金审核不通过 会员状态变为 3已认证-未签约
                    user.setStatus("3");
//                    签约审核  3审核失败作废
                    sign.setStatus("3");
//                    保证金审核 -1不通过
                    deposit.setStatus("-1");

//              6=审核签约同意（保证金已经通过）
                }else if ("6".equals(authStatus)){
//                    会员状态 7=签约审核通过
                    user.setStatus("7");
//                    签约审核  2=审核通过
                    sign.setStatus("2");

                //7=审核签约不同意（保证金已经通过）
                }else if ("7".equals(authStatus)){
//                    会员状态 6=签约审核不通过
                    user.setStatus("6");
//                    签约审核  3审核失败作废
                    sign.setStatus("3");

//               8= 保证金审核通过（签约已经通过）
                }else if ("8".equals(authStatus)){
//                    会员状态 7=签约审核通过
                    user.setStatus("7");
//                    保证金审核  2=通过
                    deposit.setStatus("2");

//               9=  保证金审核不通过（签约已经通过）
                }else if ("9".equals(authStatus)){
//                    会员状态 4已签约-未缴纳保证金
                    user.setStatus("4");
//                    保证金审核  -1=不通过
                    deposit.setStatus("-1");
                }else{
                    return result;
                }
//              保存签约日志
                //2=全部通过，4=签约通过保证金不通过，6=签约通过（保证金已审核过）
                if ("2".equals(authStatus) || "4".equals(authStatus) || "6".equals(authStatus)){
                    CarCustomerSignLog carCustomerSignLog=new CarCustomerSignLog();
                    carCustomerSignLog.setId(idWorker.nextId());
                    carCustomerSignLog.setPdfFileUrl(object.getString("pdfUrl"));
                    carCustomerSignLog.setPicFileUrl(object.getString("picUrl"));
                    carCustomerSignLog.setLog(object.getString("log"));
                    carCustomerSignLog.setCreateTime(new Date());
                    carCustomerSignLog.setSignId(object.getLong("signId"));
                    carCustomerSignLog.setType("2");
                    signLogModel.insert(carCustomerSignLog);

                    log.setFile(object.getString("pdfUrl"));
                    log.setLogId(carCustomerSignLog.getId());
                }
                 CarFinancePayLog financePayLog=new CarFinancePayLog();

//                保证金金额设置0，状态设置为不可用
//                4=保证金不通过，签约通过 5=都不通过 9保证金不通过（签约已经审核通过）
                if ("4".equals(authStatus) || "5".equals(authStatus) || "9".equals(authStatus)){
                    //将保证金金额设置为0
                    deposit.setDepositAmount(new BigDecimal(0));
                    //将保证金状态设置为 1：不可用
                    deposit.setUseStatus("1");
                    deposit.setUserId(userId);

                    //保存资金流水日志
                    financePayLog.setId(idWorker.nextId());
                    financePayLog.setLogNo(RandCodeUtil.getOrderNumber());
//                支付类型 1保证金
                    financePayLog.setType("1");
                    financePayLog.setPayFee(deposit.getDepositAmount());
                    financePayLog.setPayTime(new Date());
                    financePayLog.setOrderId(deposit.getId());
                    financePayLog.setOrderNo(deposit.getDepositNo());
                    financePayLog.setUserId(userId);
//                支付状态 3退款
                    financePayLog.setStatus("3");
//                支付途径 2线下
                    financePayLog.setPayType("2");
                    financePayLog.setPayEvidence(object.getString("file"));
                    financePayLog.setRemark(object.getString("msg"));
                    financePayLog.setCreatePerson(managerId);
                    financePayLog.setCreatePersonType("2");
                    financePayLog.setCreateTime(new Date());
//                支付方式 5其他
                    financePayLog.setPayWay("5");
                    payLogModel.insert(financePayLog);
                }
                    signModel.updateByPrimaryKeySelective(sign);
                    depositModel.updateByPrimaryKeySelective(deposit);
                if(appUserModel.updateUser(user)>0){
                    appUserManager.updateUserStatus(user.getId()+"",user.getStatus());
                    log.setStatus(user.getStatus());
                    result=logModel.insertSelective(log);
                }
        return result;
    }

    @Transactional
    public Integer setUserFreeze(JSONObject object){
        Integer result=0;
        Long userId=object.getLong("userId");
        Long managerId=object.getLong("managerId");
        String msg=object.getString("msg");
        WtAppUser user=appUserModel.selectUserById(userId);
        if(user!=null && user.getUserStatus()!=null){
            CarCustomerLog customerLog=new CarCustomerLog();
            //保证金的冻结状态
            String freezeFlag="";
            //保证金操作类型
            String logType="";
            // 1.修改会员信息状态
            if("1".equals(user.getUserStatus())){
//               如果会员状态是正常的   设置为 2：冻结
                user.setUserStatus("2");
                //日志存储会员冻结的状态 8：冻结会员
                customerLog.setStatus("8");
//                -1:冻结
                freezeFlag="-1";
//                1冻结
                logType="1";
            }else if ("2".equals(user.getUserStatus())){
                //会员是状态是冻结的将其解冻，设置为1：正常
                user.setUserStatus("1");
                //日志存储会员解冻的状态  13：解冻会员
                customerLog.setStatus("13");
//                0 :不冻结
                freezeFlag="0";
//                2解冻
                logType="2";
            }else {
                return result;
            }
             user.setUpdateTime(new Timestamp(System.currentTimeMillis()));

            //2.如果有保证金信息，冻结保证金
            Map<String,Object> map=new HashMap<>();
            map.put("userId",userId);
            map.put("status",'2');
            CarCustomerDeposit deposit=depositModel.selectDepositByUserId(map);
            if (deposit!=null){
                deposit.setFreezeFlag(freezeFlag);
                deposit.setFreezeTime(new Date());
                if(depositModel.updateByPrimaryKeySelective(deposit)>0){
                    //3 保存保证金改变日志
                    CarCustomerDepositLog depositLog=new CarCustomerDepositLog();
                    depositLog.setId(idWorker.nextId());
                    depositLog.setUserId(userId);
                    depositLog.setDepositAmount(deposit.getDepositAmount());
                    depositLog.setDepositId(deposit.getId());
                    depositLog.setFileUrl(object.getString("file"));
                    depositLog.setCreatePerson(managerId);
                    depositLog.setCreateTime(new Date());
                    depositLog.setLogType(logType);
                    depositLog.setRemark(msg);
                    depositLog.setUserType("2");
                    depositLogModel.insert(depositLog);
                }else {
                    return result;
                }

                //日志中保存冻结/解冻的保证金id
                customerLog.setLogId(deposit.getId());
            }
            if(appUserModel.updateUser(user)>0){
                //3.保存会员信息日志
                customerLog.setId(idWorker.nextId());
                customerLog.setUserId(userId);
                customerLog.setMsg(msg);
                customerLog.setEditType("2");
                customerLog.setEditTime(new Date());
                customerLog.setEditUserId(managerId);
                customerLog.setFile(object.getString("file"));
               result= logModel.insert(customerLog);
                tokenManager.deleteCusAppUserToken(user.getId()+"");
                appUserManager.cleanAppUser(user.getId()+"");
            }
        }
        return result;
    }

    /**
     * 查询可以出价的用户
     * @Author:zhangzijuan
     * @return
     */
    public ServiceResult<List<CommonNameVo>>selectAllUserForSelect(){
        ServiceResult<List<CommonNameVo>> result=new ServiceResult<>();
        try {
            List<CommonNameVo> commonNameVos=appUserModel.selectAllUserForSelect();
            if (commonNameVos!=null && commonNameVos.size()>0){
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            }else {
                result.setError(ResultCode.NO_OBJECT.strValue(),ResultCode.NO_OBJECT.getRemark());
            }
            result.setResult(commonNameVos);
        }catch (Exception e){
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

@Transactional
    public Integer simpleSaveUser(JSONObject object){
        Integer result=0;
        CarCustomerLevel carCustomerLevel = levelModel.getDefaultLevel();
        Map<String,Object> map=new HashMap<>();
        map.put("auctionPlateNum",object.getString("auctionPlateNum"));
        map.put("mobile",object.getString("mobile"));
        WtAppUser user=appUserModel.selectUserByAuctionPlateNum(map);
        WtAppUser user1=appUserModel.selectUserByMobile(map);
        if (user!=null){
            return -1;
        }
        if (user1!=null){
            return -2;
        }
        if (user==null && user1==null){
            // 保存用户信息
            WtAppUser appUser=new WtAppUser();
            long appUserId = idWorker.nextId();
            appUser.setId(appUserId);
            appUser.setRegistTime(new Timestamp(System.currentTimeMillis()));
            appUser.setUpdateTime(new Timestamp(System.currentTimeMillis()));
//        7签约审核通过
            appUser.setStatus("7");
            appUser.setMobile(object.getString("mobile"));
            appUser.setName(object.getString("name"));
            if (carCustomerLevel!=null){
                appUser.setUserLevelId(carCustomerLevel.getId());
            }
            appUser.setAuctionPlateNum(object.getString("auctionPlateNum"));
            appUser.setUserNum(RandCodeUtil.getUserCode());
            appUser.setIsShare("2");
            appUser.setManualAdd("2");
            appUser.setUserName(object.getString("mobile"));
            appUser.setUserCode(RandCodeUtil.getOrderNumber());
            Long managerId=object.getLong("managerId");
            if (appUserModel.saveUser(appUser)>0){
                //保存保证金信息
                CarCustomerDeposit deposit=new CarCustomerDeposit();
                deposit.setId(idWorker.nextId());
                deposit.setUserId(appUser.getId());
                deposit.setDepositAmount(object.getBigDecimal("depositAmount"));
                deposit.setStatus("2");
                deposit.setAuthMsg("车商现场添加");
                deposit.setAuthManager(managerId);
                deposit.setAuthTime(new Date());
                deposit.setUseStatus("0");
                depositModel.insert(deposit);

                //保存认证信息
                CarCustomerAuth auth=new CarCustomerAuth();
                auth.setId(idWorker.nextId());
                auth.setUserId(appUser.getId());
                auth.setRealName(object.getString("name"));
                auth.setAuthStatus("2");
                auth.setAuthMsg("车商现场添加");
                auth.setAuthManager(managerId);
                auth.setAuthTime(new Date());
                auth.setApplyTime(new Date());
                result =authModel.insert(auth);

                //保存签约信息
                CarCustomerSign sign=new CarCustomerSign();
                sign.setId(idWorker.nextId());
                sign.setCustomerId(appUser.getId());
                sign.setIsDelete("0");
                sign.setStatus("2");
                sign.setAuthMsg("车商现场添加");
                sign.setAuthManager(managerId);
                sign.setAuthTime(new Date());
                signModel.insert(sign);

                // 保存关联组信息
                object.put("userId",appUserId);
                if (updateUserGroup(object).getSuccess()) {
                    logger.info("修改关联信息成功");
                }
            }
        }
        return result;
    }

    /**
     * 根据拍牌号查询用户
     * @param auctionPlateNum
     * @return
     */
    public WtAppUser selectUserByAuctionPlateNum(String auctionPlateNum){
        Map<String,Object> map=new HashMap<>();
        map.put("auctionPlateNum",auctionPlateNum);
        return appUserModel.selectUserByAuctionPlateNum(map);
    }


    /**
     * 修改会员拍牌号
     * @Author:zhangzijuan
     * @param object
     * @return
     */
    public ServiceResult<Map<String,Object>> editUserAuctionPlateNum(JSONObject object){
        ServiceResult<Map<String,Object>> result=new ServiceResult<>();
        try {
            Long userId=object.getLong("userId");
            String auctionPlateNum= object.getString("auctionPlateNum");
            Map<String,Object> map=new HashMap<>();
            map.put("auctionPlateNum",auctionPlateNum);
            map.put("userId",userId);
            //根据用户id和拍牌号判断该车商是否已经存在
            WtAppUser user=appUserModel.selectUserByAuctionPlateNum(map);
//                如果存在直接返回
            if(user!=null){
                result.setError(ResultCode.REPEAT_AUCTION_PLATE.strValue(),ResultCode.REPEAT_AUCTION_PLATE.getRemark());
            }else {
                WtAppUser appUser=new WtAppUser();
                appUser.setId(userId);
                appUser.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                appUser.setAuctionPlateNum(auctionPlateNum);
                //更新基础表数据
                if (appUserModel.updateUser(appUser)>0){
                    result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
                }else {
                    result.setError(ResultCode.FAIL.strValue(),ResultCode.FAIL.getRemark());
                };
            }
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }
}
