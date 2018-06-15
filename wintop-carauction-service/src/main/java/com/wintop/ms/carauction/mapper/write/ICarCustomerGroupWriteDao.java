package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarCustomerGroup;

/**
 * 客户分组
 * @author zhangzijuan
 * @date 2018-02-26
 */
public interface ICarCustomerGroupWriteDao {
    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(CarCustomerGroup record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(CarCustomerGroup record);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(CarCustomerGroup record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(CarCustomerGroup record);
}