package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarCustomerRole;

/**
 * 客户角色
 * @author zhangzijuan
 * @date 2018-02-26
 */
public interface ICarCustomerRoleWriteDao {
    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(CarCustomerRole record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(CarCustomerRole record);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(CarCustomerRole record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(CarCustomerRole record);
}