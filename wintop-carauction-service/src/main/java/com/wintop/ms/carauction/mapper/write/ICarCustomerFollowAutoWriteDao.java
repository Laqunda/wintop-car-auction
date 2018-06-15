package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarCustomerFollowAuto;

import java.util.Map;

/**
 * 用户关注车辆
 * @author zhangzijuan
 * @date 2018-02-27
 */
public interface ICarCustomerFollowAutoWriteDao {

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(CarCustomerFollowAuto record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(CarCustomerFollowAuto record);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(CarCustomerFollowAuto record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(CarCustomerFollowAuto record);

    /**
     * 收藏接口
     */
    int insertCustomerCollection(CarCustomerFollowAuto record);
    /**
     * 取消收藏接口
     */
    int deleteCustomerCollection(Map<String,Object> map);
}