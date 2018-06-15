package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarCustomerLevelDetail;

/**
 * 客户级别信息详情
 * @author zhangzijuan
 * @date 2018-02-26
 */
public interface ICarCustomerLevelDetailWriteDao {
    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(CarCustomerLevelDetail record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(CarCustomerLevelDetail record);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(CarCustomerLevelDetail record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(CarCustomerLevelDetail record);
}