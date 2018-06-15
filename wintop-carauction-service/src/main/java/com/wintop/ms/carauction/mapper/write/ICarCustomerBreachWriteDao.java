package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarCustomerBreach;

/**
 * 客户违约信息
 * @author zhangzijuan
 * @date 2018-02-26
 */
public interface ICarCustomerBreachWriteDao {
    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(CarCustomerBreach record);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(CarCustomerBreach record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(CarCustomerBreach record);
}