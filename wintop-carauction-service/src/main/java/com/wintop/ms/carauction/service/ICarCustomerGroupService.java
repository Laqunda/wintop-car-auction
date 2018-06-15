package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarCustomerGroup;

import java.util.List;
import java.util.Map;

/**
 * 用户分组
 * @author zhangzijuan
 * @date 2018-02-26
 */
public interface ICarCustomerGroupService {
    /**
     * 根据id查询用户分组
     * @param id
     * @return CarCustomerGroup
     */
    ServiceResult<CarCustomerGroup> selectByPrimaryKey(Long id);
    /**
     * 根据id删除用户分组
     * @param id
     * @return
     */
    ServiceResult<Integer> deleteByPrimaryKey(Long id);
    /**
     * 根据主键更新属性不为空的记录
     * @param record
     * @return
     */
    ServiceResult<Integer> updateByPrimaryKeySelective(CarCustomerGroup record);
    /**
     * 根据主键更新用户分组
     * @param record
     * @return
     */
    ServiceResult<Integer> updateByPrimaryKey(CarCustomerGroup record);
    /**
     * 存记录,不管记录里面的属性是否为空
     * @param record
     * @return
     */
    ServiceResult<Integer> insert(CarCustomerGroup record);
    /**
     * 保存属性不为空的记录
     * @param record
     * @return
     */
    ServiceResult<Integer> insertSelective(CarCustomerGroup record);
    /**
     * 查询用户分组及对应的用户数量
     * @Author:zhangzijuan
     * @param map
     * @return
     */
    ServiceResult<List<CarCustomerGroup>> selectGroupAndNum(Map<String,Object> map);

    /**
     * 新建会分组
     * @Author:zhangzijuan
     * @param record
     * @return
     */
    ServiceResult<Map<String,Object>> saveGroup(CarCustomerGroup record);
    /**
     * 查询所有可以选择的用户分组
     * @Author:zhangzijuan
     * @return
     */
    ServiceResult<List<CarCustomerGroup>> selectGroupForSelect(Map<String,Object> map);


    /**
     * 删除会员分组
     * @Author:zhangzijuan
     * @param groupId
     * @return
     */
    ServiceResult<Map<String,Object>> deleteGroupById(Long groupId);
}