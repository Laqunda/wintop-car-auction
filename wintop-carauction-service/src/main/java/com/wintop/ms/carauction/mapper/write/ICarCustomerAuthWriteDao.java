package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarCustomerAuth;

/**
 * 客户认证信息
 * @author zhangzijuan
 * @date 2018-02-26
 */
public interface ICarCustomerAuthWriteDao {
    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(CarCustomerAuth record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(CarCustomerAuth record);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(CarCustomerAuth record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(CarCustomerAuth record);

    /**
     * 据用户id更新会员认证信息
     * @param record
     * @return
     */
    int updateByUserId(CarCustomerAuth record);

}