package com.wintop.ms.carauction.mapper.read;


import com.wintop.ms.carauction.entity.CarCustomerAuth;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 客户认证信息
 * @author zhangzijuan
 * @date 2018-02-26
 */
public interface ICarCustomerAuthReadDao {
    /**
     * 根据主键查询记录
     *@Author:zhangzijuan
     *@date 2018/2/26
     *@param:id
     */
    CarCustomerAuth selectByPrimaryKey(Long id);

    /**
     * 根据用户Id查询用户信息
     *@Author:zhangzijuan
     *@date 2018/3/5
     *@param:id
     */
    CarCustomerAuth getAuthInfoByUserId(@Param(value = "userId") Long userId);

    /**
     * 查询认证列表
     *@Author:zhangzijuan
     *@date 2018/3/20
     *@param:id
     */
    List<CarCustomerAuth> selectUserAuthList(Map<String,Object> map);
    /**
     * 查询认证数
     *@Author:zhangzijuan
     *@date 2018/3/20
     *@param:id
     */
   Integer selectUserAuthCount(Map<String,Object> map);
}