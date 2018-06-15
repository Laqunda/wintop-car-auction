package com.wintop.ms.carauction.mapper.read;
import com.wintop.ms.carauction.entity.CarCustomerBreach;

import java.util.List;
import java.util.Map;

/**
 * 客户违约信息
 * @author zhangzijuan
 * @date 2018-02-26
 */
public interface ICarCustomerBreachReadDao {
    /**
     * 根据主键查询记录
     *@Author:zhangzijuan
     *@date 2018/2/26
     *@param:id
     */
    CarCustomerBreach selectByPrimaryKey(Long id);

    /**
     * 查询订单违约信息
     * @param map
     * @return
     */
    CarCustomerBreach queryNewBreachInfo(Map<String,Object> map);

    /**
     * 查询订单争议列表
     * @param map
     * @return
     */
    List<CarCustomerBreach> selectBreachList(Map<String,Object> map);
}