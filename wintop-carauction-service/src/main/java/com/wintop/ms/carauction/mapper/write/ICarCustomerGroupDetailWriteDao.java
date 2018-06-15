package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarCustomerGroupDetail;

/**
 * 客户分组信息详情
 * @author zhangzijuan
 * @date 2018-02-26
 */
public interface ICarCustomerGroupDetailWriteDao {
    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(CarCustomerGroupDetail record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(CarCustomerGroupDetail record);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(CarCustomerGroupDetail record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(CarCustomerGroupDetail record);

    /**
     * 删除用户的分组
     * @param userId
     * @return
     */
    Integer deleteByUserId(Long userId);
    /**
     * 删除用户的分组根据userId
     * @param groupId
     * @return
     */
    Integer deleteByGroupId(Long groupId);
}