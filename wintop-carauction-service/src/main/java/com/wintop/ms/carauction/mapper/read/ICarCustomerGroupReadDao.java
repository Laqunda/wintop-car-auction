package com.wintop.ms.carauction.mapper.read;
import com.wintop.ms.carauction.entity.CarCustomerGroup;

import java.util.List;
import java.util.Map;

/**
 * 客户分组
 * @author zhangzijuan
 * @date 2018-02-26
 */
public interface ICarCustomerGroupReadDao {
    /**
     * 根据主键查询记录
     *@Author:zhangzijuan
     *@date 2018/2/26
     *@param:id
     */
    CarCustomerGroup selectByPrimaryKey(Long id);

    /**
     * 查询用户分组及对应的用户数量
     * @Author:zhangzijuan
     * @param map
     * @return
     */
    List<CarCustomerGroup> selectGroupAndNum(Map<String,Object> map);

    /**
     * 查询所有可以选择的用户分组
     * @param map
     * @return
     */
    List<CarCustomerGroup> selectGroupForSelect(Map<String,Object> map);
}