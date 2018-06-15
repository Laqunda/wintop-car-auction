package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarCustomerGroupDetail;

/**
 * 用户分组信息详情
 * @author zhangzijuan
 * @date 2018-02-26
 */
public interface ICarCustomerGroupDetailService {
    /**
     * 根据id查询用户分组信息详情
     * @param id
     * @return CarCustomerGroupDetail
     */
    ServiceResult<CarCustomerGroupDetail> selectByPrimaryKey(Long id);
    /**
     * 根据id删除用户分组信息详情
     * @param id
     * @return
     */
    ServiceResult<Integer> deleteByPrimaryKey(Long id);
    /**
     * 根据主键更新属性不为空的记录
     * @param record
     * @return
     */
    ServiceResult<Integer> updateByPrimaryKeySelective(CarCustomerGroupDetail record);
    /**
     * 根据主键更新用户分组信息详情
     * @param record
     * @return
     */
    ServiceResult<Integer> updateByPrimaryKey(CarCustomerGroupDetail record);
    /**
     * 存记录,不管记录里面的属性是否为空
     * @param record
     * @return
     */
    ServiceResult<Integer> insert(CarCustomerGroupDetail record);
    /**
     * 保存属性不为空的记录
     * @param record
     * @return
     */
    ServiceResult<Integer> insertSelective(CarCustomerGroupDetail record);
}