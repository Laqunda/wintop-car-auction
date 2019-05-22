package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.entity.CommonNameVo;
import com.wintop.ms.carauction.entity.WtAppUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by liangtingsen on 2018/2/5.
 * 用户管理
 */
public interface IWtAppUserService {

    /**
     * 查询用户名是否已存在
     * @param userName
     * @return
     */
    ServiceResult<WtAppUser> findByUserByUsername(String userName);

    /**
     * 根据用户名密码查询注册用户
     * @param userName
     * @param password
     * @return
     */
    ServiceResult<WtAppUser> findByUserNamePwd(String userName, String password);

    /***
     * 根据用户ID查询用户信息
     * @param appUserId
     * @return
     */
    ServiceResult<WtAppUser> findById(Long appUserId);

    /***
     * 保存用户信息
     * @param appUser
     * @return
     */
    ServiceResult<WtAppUser> saveUser(WtAppUser appUser);

    /***
     * 修改用户信息
     * @param appUser
     * @return
     */
    ServiceResult<WtAppUser> updateUser(WtAppUser appUser);

    /***
     * 修改用户密码
     * @param appUser
     * @return
     */
    ServiceResult<WtAppUser> updatePwd(WtAppUser appUser);
    /**
     * 根据用户ID查询用户信息
     * @param appUserId
     * @return
     */
    ServiceResult<WtAppUser> selectUserById(Long appUserId);
    /**
     * 根据参数查询用户信息列表
     *@Author:zhangzijuan
     *@date 2018/3/14
     *@param:map
     */
    List<WtAppUser> selectListByParam(Map<String,Object> map);
    /**
     * 根据参数查询用户数量
     *@Author:zhangzijuan
     *@date 2018/3/14
     *@param:map
     */
    Integer selectCountByParam(Map<String,Object> map);

    /**
     * 退会
     *@Author:zhangzijuan
     *@date 2018/3/14
     *@param:map
     */
    ServiceResult<Map<String,Object>> userSignOut(JSONObject object);

    /**
     * 批量修改用户级别
     *@Author:zhangzijuan
     *@date 2018/3/17
     *@param:map
     */
     ServiceResult<Map<String,Object>> batchUpdateUserLevel(JSONObject object);

    /**
     * 修改用户级别
     * @Author:zhangzijuan
     * @param object
     * @return
     */
     ServiceResult<Map<String,Object>> updateUserGroup(JSONObject object);

    /**
     * 新增会员
     * @Author:zhangzijuan
     * @param object
     * @return
     */
    ServiceResult<Map<String,Object>> editUser(JSONObject object);

    /**
     * 查询会员详情信息
     * @Author:zhangzijuan
     * @param userId
     * @return
     */
    ServiceResult<WtAppUser> getUserInfoById(Long userId);
      /**
     * 审批签约信息
     * @Author:zhangzijuan
     * @param object
     * @return
             */
     Integer approveSignInfo(JSONObject object);
    /**
     * 设置会员冻结解冻
     * @Author:zhangzijuan
     * @param object
     * @return
     */
     Integer setUserFreeze(JSONObject object);

    /**
     * 查询可以出价的用户
     * @Author:zhangzijuan
     * @return
     */
     ServiceResult<List<CommonNameVo>> selectAllUserForSelect();

    /***
     * 简单存储用户信息
     * @param object
     * @return
     */
     Integer simpleSaveUser(JSONObject object);
    /**
     * 根据拍牌号查询用户
     * @param auctionPlateNum
     * @return
     */
     WtAppUser selectUserByAuctionPlateNum(String auctionPlateNum);

    /**
     * 修改会员拍牌号
     * @Author:zhangzijuan
     * @param object
     * @return
     */
    ServiceResult<Map<String,Object>> editUserAuctionPlateNum(JSONObject object);

    /**
     * 查询会员车拍牌号是否重复
     *
     * @param mobile
     * @param auctionPlateNum
     * @return
     * @Author:zhangzijuan
     */
    Integer selectAuctionPlateNumIsRepeat(String mobile, String auctionPlateNum);
}
