package com.wintop.ms.carauction.mapper.read;
import com.wintop.ms.carauction.entity.CarCustomerGroupDetail;

/**
 * 客户分组信息详情
 * @author zhangzijuan
 * @date 2018-02-26
 */
public interface ICarCustomerGroupDetailReadDao {
    /**
     * 根据主键查询记录
     *@Author:zhangzijuan
     *@date 2018/2/26
     *@param:id
     */
    CarCustomerGroupDetail selectByPrimaryKey(Long id);
}