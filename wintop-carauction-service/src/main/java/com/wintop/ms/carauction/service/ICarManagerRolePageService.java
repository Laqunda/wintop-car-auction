package com.wintop.ms.carauction.service;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.entity.CarManagerRolePage;

import java.util.List;

public interface ICarManagerRolePageService {
    /**
     * 根据roleId查询所有数据
     */
    List<CarManagerRolePage> selectAll(Long roleId);

    /**
     * 根据roleId查询pageId
     */
    List<String> selectAllPages(Long roleId);

    /**
     * 根据roleId删除记录
     */
    int deleteByRoleId(Long roleId);

    /**
     * 保存记录,
     */
    int insert(CarManagerRolePage record);
    /**
     * 修改角色权限
     *@Author:zhangzijuan
     *@date 2018/4/11
     *@param:
     */
     int editRolePage(JSONObject object);
}
