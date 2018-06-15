package com.wintop.ms.carauction.mapper.read;
import com.wintop.ms.carauction.entity.CarCustomerLevel;

import java.util.List;
import java.util.Map;

/**
 * 客户级别
 * @author zhangzijuan
 * @date 2018-02-26
 */
public interface ICarCustomerLevelReadDao {
    /**
     * 根据主键查询记录
     *@Author:zhangzijuan
     *@date 2018/2/26
     *@param:id
     */
    CarCustomerLevel selectByPrimaryKey(Long id);

    /**
     * 根据参数查询会员等级列表
     *@Author:zhangzijuan
     *@date 2018/3/15
     *@param:
     */
    List<CarCustomerLevel> selectListByParam(Map<String,Object> map);

    /**
     *根据参数查询会员等级总数量
     *@Author:zhangzijuan
     *@date 2018/3/15
     *@param:
     */
    Integer selectCountByParam(Map<String,Object> map);

    /**
     * 根据级别id查询级别详情
     *@Author:zhangzijuan
     *@date 2018/3/17
     *@param:
     */
    CarCustomerLevel selectLevelById(Map<String,Object> map);
    /**
     * 查询默认级别
     *@Author:zhangzijuan
     *@date 2018/3/28
     *@param:
     */
    CarCustomerLevel getDefaultLevel();
}