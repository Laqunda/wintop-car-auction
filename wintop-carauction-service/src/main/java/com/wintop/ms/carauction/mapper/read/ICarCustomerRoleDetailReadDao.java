package com.wintop.ms.carauction.mapper.read;
import com.wintop.ms.carauction.entity.CarCustomerRole;
import com.wintop.ms.carauction.entity.CarCustomerRoleDetail;

/**
 * 客户角色信息详情
 * @author zhangzijuan
 * @date 2018-02-26
 */
public interface ICarCustomerRoleDetailReadDao {
    /**
     * 根据主键查询记录
     *@Author:zhangzijuan
     *@date 2018/2/26
     *@param:id
     */
    CarCustomerRoleDetail selectByPrimaryKey(Long id);
}