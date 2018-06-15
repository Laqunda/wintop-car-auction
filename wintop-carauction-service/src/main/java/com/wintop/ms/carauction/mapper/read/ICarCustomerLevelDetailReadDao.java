package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarCustomerLevelDetail;

/**
 * 客户级别信息详情
 * @author zhangzijuan
 * @date 2018-02-26
 */
public interface ICarCustomerLevelDetailReadDao {
    /**
     * 根据主键查询记录
     *@Author:zhangzijuan
     *@date 2018/2/26
     *@param:id
     */
    CarCustomerLevelDetail selectByPrimaryKey(Long id);
}