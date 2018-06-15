package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAgent;

import java.util.List;
import java.util.Map;

public interface ICarAgentService {

    /**
     * 根据条件查询记录总数
     */
    Integer countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    List<CarAgent> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    CarAgent selectById(Long id);

    /**
     * 根据主键删除记录
     */
    Integer deleteById(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    Integer insert(CarAgent carAgent);

    /**
     * 根据主键更新属性不为空的记录
     */
    Integer updateByIdSelective(CarAgent carAgent);

    /**
     * 根据主键更新记录
     */
    Integer updateById(CarAgent carAgent);

    /**
     * 查询代办列表
     *@Author:zhangzijuan
     *@date 2018/3/24
     *@param:
     */
    List<CarAgent> selectListByParam(Map<String,Object> map);
    /**
     * 查询代办总数
     *@Author:zhangzijuan
     *@date 2018/3/24
     *@param:
     */
    Integer countByParam(Map<String,Object> map);

     CarAgent selectByOrderId(Long orderId);
}
