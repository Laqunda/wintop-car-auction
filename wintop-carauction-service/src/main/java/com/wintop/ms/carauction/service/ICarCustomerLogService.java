package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarCustomerLog;

import java.util.List;

/**
 * 客户变更日志业务层
 * @author zhangzijuan
 * @date 2018-02-26
 */
public interface ICarCustomerLogService {
    /**
     * 根据id查询客户变更日志
     * @param id
     * @return CarCustomerLog
     */
    ServiceResult<CarCustomerLog> selectByPrimaryKey(Long id);
    /**
     * 根据id删除客户变更日志
     * @param id
     * @return
     */
    ServiceResult<Integer> deleteByPrimaryKey(Long id);
    /**
     * 根据主键更新属性不为空的记录
     * @param record
     * @return
     */
    ServiceResult<Integer> updateByPrimaryKeySelective(CarCustomerLog record);
    /**
     * 根据主键更新客户变更日志
     * @param record
     * @return
     */
    ServiceResult<Integer> updateByPrimaryKey(CarCustomerLog record);
    /**
     * 存记录,不管记录里面的属性是否为空
     * @param record
     * @return
     */
    ServiceResult<Integer> insert(CarCustomerLog record);
    /**
     * 保存属性不为空的记录
     * @param record
     * @return
     */
    ServiceResult<Integer> insertSelective(CarCustomerLog record);

    /**
     * 根据用户Id查询用户日志
     *@Author:zhangzijuan
     *@date 2018/3/20
     *@param:userId
     */
    ServiceResult<List<CarCustomerLog>> selectUserLogByUserId(Long userId);

}