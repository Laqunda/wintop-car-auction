package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarCustomerRole;

/**
 * 用户角色
 * @author zhangzijuan
 * @date 2018-02-26
 */
public interface ICarCustomerRoleService {
    /**
     * 根据id查询用户角色
     * @param id
     * @return CarCustomerRole
     */
    ServiceResult<CarCustomerRole> selectByPrimaryKey(Long id);
    /**
     * 根据id删除用户角色
     * @param id
     * @return
     */
    ServiceResult<Integer> deleteByPrimaryKey(Long id);
    /**
     * 根据主键更新属性不为空的记录
     * @param record
     * @return
     */
    ServiceResult<Integer> updateByPrimaryKeySelective(CarCustomerRole record);
    /**
     * 根据主键更新用户角色
     * @param record
     * @return
     */
    ServiceResult<Integer> updateByPrimaryKey(CarCustomerRole record);
    /**
     * 存记录,不管记录里面的属性是否为空
     * @param record
     * @return
     */
    ServiceResult<Integer> insert(CarCustomerRole record);
    /**
     * 保存属性不为空的记录
     * @param record
     * @return
     */
    ServiceResult<Integer> insertSelective(CarCustomerRole record);
}