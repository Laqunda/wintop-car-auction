package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.entity.CarManagerRole;
import com.wintop.ms.carauction.entity.CarManagerRoleType;

import java.util.List;
import java.util.Map;

public interface ICarManagerRoleService {
    /**
     * 根据条件查询记录集
     */
    public List<CarManagerRoleType> selectByExample(Map<String,Object> map);

    /**
     * 根据id查询角色
     * @param id
     * @return
     */
    public CarManagerRole getManagerRole(Long id);

    /**
     * 根据userId查询记录
     */
    public CarManagerRole selectByUserId(Long userId);

    /**
     * 根据Id查询记录
     */
    public CarManagerRole selectById(Long id);
}
