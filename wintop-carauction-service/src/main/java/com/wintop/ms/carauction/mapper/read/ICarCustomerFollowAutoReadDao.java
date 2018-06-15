package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarCustomerFollowAuto;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description 用户关注车辆
 * @author zhangzijuan
 * @date 2018-02-27
 */
public interface ICarCustomerFollowAutoReadDao {
    /**
     *@Author:zhangzijuan
     *@date 2018/2/27
     *@Description:根据主键查询记录
     *@param:id
     */
    CarCustomerFollowAuto selectByPrimaryKey(Long id);

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
    Integer queryUserFollowCount(Map<String,Object> map);

    /**
     * 根据条件查询关注的车辆数量
     */
    Integer selectFollowCount(Map<String,Object> map);

    /**
     * 根据auto_id和user_id查询数据
     */
    CarCustomerFollowAuto selectCustomerFollow(@Param("autoId") Long autoId, @Param("userId") Long userId);
}