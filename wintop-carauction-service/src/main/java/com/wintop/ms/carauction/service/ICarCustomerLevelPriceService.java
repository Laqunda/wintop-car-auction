package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarCustomerLevelPrice;

/**
 * 用户客户级别对应出价业务层
 * @author zhangzijuan
 * @date 2018-02-26
 */
public interface ICarCustomerLevelPriceService {
    /**
     * 根据id查询用户客户级别对应出价
     * @param id
     * @return CarCustomerLevelPrice
     */
    ServiceResult<CarCustomerLevelPrice> selectByPrimaryKey(Long id);
    /**
     * 根据id删除用户客户级别对应出价
     * @param id
     * @return
     */
    ServiceResult<Integer> deleteByPrimaryKey(Long id);
    /**
     * 根据主键更新属性不为空的记录
     * @param record
     * @return
     */
    ServiceResult<Integer> updateByPrimaryKeySelective(CarCustomerLevelPrice record);
    /**
     * 根据主键更新用户客户级别对应出价
     * @param record
     * @return
     */
    ServiceResult<Integer> updateByPrimaryKey(CarCustomerLevelPrice record);
    /**
     * 存记录,不管记录里面的属性是否为空
     * @param record
     * @return
     */
    ServiceResult<Integer> insert(CarCustomerLevelPrice record);
    /**
     * 保存属性不为空的记录
     * @param record
     * @return
     */
    ServiceResult<Integer> insertSelective(CarCustomerLevelPrice record);
}