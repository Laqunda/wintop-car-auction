package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarCustomerRoleDetail;

/**
 * 客户角色信息详情
 * @author zhangzijuan
 * @date 2018-02-26
 */
public interface ICarCustomerRoleDetailService {
    /**
     * 根据id查询客户角色信息详情
     * @param id
     * @return CarCustomerRoleDetail
     */
    ServiceResult<CarCustomerRoleDetail> selectByPrimaryKey(Long id);
    /**
     * 根据id删除客户角色信息详情
     * @param id
     * @return
     */
    ServiceResult<Integer> deleteByPrimaryKey(Long id);
    /**
     * 根据主键更新属性不为空的记录
     * @param record
     * @return
     */
    ServiceResult<Integer> updateByPrimaryKeySelective(CarCustomerRoleDetail record);
    /**
     * 根据主键更新客户角色信息详情
     * @param record
     * @return
     */
    ServiceResult<Integer> updateByPrimaryKey(CarCustomerRoleDetail record);
    /**
     * 存记录,不管记录里面的属性是否为空
     * @param record
     * @return
     */
    ServiceResult<Integer> insert(CarCustomerRoleDetail record);
    /**
     * 保存属性不为空的记录
     * @param record
     * @return
     */
    ServiceResult<Integer> insertSelective(CarCustomerRoleDetail record);
}