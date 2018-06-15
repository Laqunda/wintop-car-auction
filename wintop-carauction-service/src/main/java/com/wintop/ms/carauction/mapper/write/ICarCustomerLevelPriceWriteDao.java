package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarCustomerLevelPrice;

/**
 * 客户级别对应出价
 * @author zhangzijuan
 * @date 2018-02-26
 */
public interface ICarCustomerLevelPriceWriteDao {
    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(CarCustomerLevelPrice record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(CarCustomerLevelPrice record);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(CarCustomerLevelPrice record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(CarCustomerLevelPrice record);

    int deleteByLevelId(Long levelId);
}