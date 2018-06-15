package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarCustomerLevelDetail;

/**
 * 客户级别信息详情
 * @author zhangzijuan
 * @date 2018-02-26
 */
public interface ICarCustomerLevelDetailService {
    /**
     * 根据id查询客户级别信息详情
     * @param id
     * @return CarCustomerLevelDetail
     */
    ServiceResult<CarCustomerLevelDetail> selectByPrimaryKey(Long id);
    /**
     * 根据id删除客户级别信息详情
     * @param id
     * @return
     */
    ServiceResult<Integer> deleteByPrimaryKey(Long id);
    /**
     * 根据主键更新属性不为空的记录
     * @param record
     * @return
     */
    ServiceResult<Integer> updateByPrimaryKeySelective(CarCustomerLevelDetail record);
    /**
     * 根据主键更新客户级别信息详情
     * @param record
     * @return
     */
    ServiceResult<Integer> updateByPrimaryKey(CarCustomerLevelDetail record);
    /**
     * 存记录,不管记录里面的属性是否为空
     * @param record
     * @return
     */
    ServiceResult<Integer> insert(CarCustomerLevelDetail record);
    /**
     * 保存属性不为空的记录
     * @param record
     * @return
     */
    ServiceResult<Integer> insertSelective(CarCustomerLevelDetail record);
}