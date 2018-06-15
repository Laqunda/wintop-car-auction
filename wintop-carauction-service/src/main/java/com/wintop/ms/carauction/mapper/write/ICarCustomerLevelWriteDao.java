package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarCustomerLevel;

import java.util.Map;

/**
 * 客户级别
 * @author zhangzijuan
 * @date 2018-02-26
 */
public interface ICarCustomerLevelWriteDao {
    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(CarCustomerLevel record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(CarCustomerLevel record);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(CarCustomerLevel record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(CarCustomerLevel record);

    /**
     * 将其他级别设置为不默认
     */
    int setNoDefault(Map<String,Object> map);
}