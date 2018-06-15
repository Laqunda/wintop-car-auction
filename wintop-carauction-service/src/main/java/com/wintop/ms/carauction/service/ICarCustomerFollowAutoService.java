package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarCustomerFollowAuto;

import java.util.List;
import java.util.Map;

/**
 * 用户关注车辆业务层
 * @author zhangzijuan
 * @date 2018-02-27
 */
public interface ICarCustomerFollowAutoService {
    /**
     * 根据id查询用户关注车辆
     * @param id
     * @return CarCustomerFollowAuto
     */
    ServiceResult<CarCustomerFollowAuto> selectByPrimaryKey(Long id);
    /**
     * 根据id删除用户关注车辆
     * @param id
     * @return
     */
    ServiceResult<Integer> deleteByPrimaryKey(Long id);
    /**
     * 根据主键更新属性不为空的记录
     * @param record
     * @return
     */
    ServiceResult<Integer> updateByPrimaryKeySelective(CarCustomerFollowAuto record);
    /**
     * 根据主键更新用户关注车辆
     * @param record
     * @return
     */
    ServiceResult<Integer> updateByPrimaryKey(CarCustomerFollowAuto record);
    /**
     * 存记录,不管记录里面的属性是否为空
     * @param record
     * @return
     */
    ServiceResult<Integer> insert(CarCustomerFollowAuto record);
    /**
     * 保存属性不为空的记录
     * @param record
     * @return
     */
    ServiceResult<Integer> insertSelective(CarCustomerFollowAuto record);

    /**
     * 收藏接口
     */
    ServiceResult<Integer> insertCustomerCollection(CarCustomerFollowAuto record);

    /**
     * 根据auto_id和user_id查询数据
     */
    ServiceResult<CarCustomerFollowAuto> selectCustomerFollow(Long autoId, Long userId);

    /**
     * 取消收藏接口
     */
    ServiceResult<Integer> deleteCustomerCollection(Map<String,Object> map);

    /**
     * 查询用户现场\线上关注车辆列表
     * @param map
     * @return
     */
    List<CarCustomerFollowAuto> queryUserFollowList(Map<String,Object> map);

    /**
     * 查询用户现场\线上关注车辆列表数量
     * @param map
     * @return
     */
    int queryUserFollowCount(Map<String,Object> map);
}