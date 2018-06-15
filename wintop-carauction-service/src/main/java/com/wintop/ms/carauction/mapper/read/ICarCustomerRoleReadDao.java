package com.wintop.ms.carauction.mapper.read;
import com.wintop.ms.carauction.entity.CarCustomerRole;

/**
 * 客户角色
 * @author zhangzijuan
 * @date 2018-02-26
 */
public interface ICarCustomerRoleReadDao {
    /**
     * 根据主键查询记录
     *@Author:zhangzijuan
     *@date 2018/2/26
     *@param:id
     */
    CarCustomerRole selectByPrimaryKey(Long id);
}