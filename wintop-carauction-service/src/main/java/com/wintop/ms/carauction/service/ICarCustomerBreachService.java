package com.wintop.ms.carauction.service;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarCustomerBreach;

import java.util.List;
import java.util.Map;

/**
 * 客户违约信息
 * @author zhangzijuan
 * @date 2018-02-26
 */
public interface ICarCustomerBreachService {
    /**
     * 根据id查询客户违约信息
     * @param id
     * @return CarCustomerBreach
     */
    CarCustomerBreach selectByPrimaryKey(Long id);
    /**
     * 根据id删除客户违约信息
     * @param id
     * @return
     */
    Integer deleteByPrimaryKey(Long id);
    /**
     * 根据主键更新属性不为空的记录
     * @param record
     * @return
     */
    Integer updateByPrimaryKeySelective(CarCustomerBreach record);
    /**
     * 根据主键更新客户违约信息
     * @param record
     * @return
     */
    Integer updateByPrimaryKey(CarCustomerBreach record);
    /**
     * 存记录,不管记录里面的属性是否为空
     * @param record
     * @return
     */
    Integer insert(CarCustomerBreach record);

    /**
     * 查询订单争议列表
     * @param map
     * @return
     */
    List<CarCustomerBreach> selectBreachList(Map<String,Object> map);

    /**
     * 查询订单违约信息
     * @param map
     * @return
     */
    public CarCustomerBreach queryNewBreachInfo(Map<String,Object> map);

/**
 * 申请争议
 *@Author:zhangzijuan
 *@date 2018/3/27
 *@param:
 */
    Integer applyBreach(JSONObject object);

    /**
     * 争议审核
     * @param object
     * @return
     */
    public int breachApprove(JSONObject object);
}