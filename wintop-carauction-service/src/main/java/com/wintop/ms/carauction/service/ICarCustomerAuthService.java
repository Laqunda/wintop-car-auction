package com.wintop.ms.carauction.service;
import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarCustomerAuth;

import java.util.List;
import java.util.Map;

/**
 * 用户认证信息业务层
 * @author zhangzijuan
 * @date 2018-02-26
 */
public interface ICarCustomerAuthService {
    /**
     * 根据id查询用户认证信息
     * @param id
     * @return CarCustomerAuth
     */
    ServiceResult<CarCustomerAuth> selectByPrimaryKey(Long id);
    /**
     * 根据id删除用户认证信息
     * @param id
     * @return
     */
    ServiceResult<Integer> deleteByPrimaryKey(Long id);
    /**
     * 根据主键更新属性不为空的记录
     * @param record
     * @return
     */
    ServiceResult<Integer> updateByPrimaryKeySelective(CarCustomerAuth record);
    /**
     * 根据主键更新用户认证信息
     * @param record
     * @return
     */
    ServiceResult<Integer> updateByPrimaryKey(CarCustomerAuth record);
    /**
     * 存记录,不管记录里面的属性是否为空
     * @param record
     * @return
     */
    ServiceResult<Integer> insert(CarCustomerAuth record);
    /**
     * 保存属性不为空的记录
     * @param record
     * @return
     */
    ServiceResult<Integer> insertSelective(CarCustomerAuth record);

    /**
     * 根据用户Id查询用户认证信息
     * @param userId
     * @return CarCustomerAuth
     */
    ServiceResult<CarCustomerAuth> getAuthInfoByUserId(Long userId);

    /**
     * 查询认证列表
     *@Author:zhangzijuan
     *@date 2018/3/20
     *@param:id
     */
    List<CarCustomerAuth> selectUserAuthList(Map<String,Object> map);

     /*
     *查询认证数
     *@Author:zhangzijuan
     *@date 2018/3/20
     *@param:id
     */
    Integer selectUserAuthCount(Map<String,Object> map);
    /**
     * 认证信息审核
     *@Author:zhangzijuan
     *@date 2018/3/20
     *@param:object
     */
    Integer approveUserAuth(JSONObject object);
    /**
     * 提交认证接口
     *@Author:zhangzijuan
     *@date 2018/3/20
     *@param:id
     */
    Integer saveAuthInfo(JSONObject object);
}