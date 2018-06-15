package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarCustomerLog;

import java.util.List;

/**
 * 客户变更日志
 * @author zhangzijuan
 * @date 2018-02-26
 */
public interface ICarCustomerLogReadDao {
    /**
     * 根据主键查询记录
     *@Author:zhangzijuan
     *@date 2018/2/26
     *@param:id
     */
    CarCustomerLog selectByPrimaryKey(Long id);

    /**
     * 根据用户Id查询用户日志
     *@Author:zhangzijuan
     *@date 2018/3/20
     *@param:userId
     */

    List<CarCustomerLog> selectUserLogByUserId(Long userId);
}