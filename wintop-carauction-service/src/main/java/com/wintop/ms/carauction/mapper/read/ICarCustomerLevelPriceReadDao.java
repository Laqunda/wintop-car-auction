package com.wintop.ms.carauction.mapper.read;
import com.wintop.ms.carauction.entity.CarCustomerLevelPrice;

/**
 * 客户级别对应出价
 * @author zhangzijuan
 * @date 2018-02-26
 */
public interface ICarCustomerLevelPriceReadDao {
    /**
     * 根据主键查询记录
     *@Author:zhangzijuan
     *@date 2018/2/26
     *@param:id
     */
    CarCustomerLevelPrice selectByPrimaryKey(Long id);
}