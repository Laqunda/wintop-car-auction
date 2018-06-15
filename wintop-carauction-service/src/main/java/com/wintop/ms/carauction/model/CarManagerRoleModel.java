package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarManagerRole;
import com.wintop.ms.carauction.mapper.read.ICarManagerRoleReadDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CarManagerRoleModel {
    @Autowired
    private ICarManagerRoleReadDao roleReadDao;

    /**
     * 根据条件查询记录集
     */
    public List<CarManagerRole> selectByExample(Map<String,Object> map){
        return roleReadDao.selectByExample(map);
    }

    /**
     * 根据id查询记录集
     */
    public CarManagerRole selectById(Long id){
        return roleReadDao.selectById(id);
    }

    /**
     * 根据userId查询记录
     */
    public CarManagerRole selectByUserId(Long userId){
        return roleReadDao.selectByUserId(userId);
    }
}
