package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarManagerRolePage;
import com.wintop.ms.carauction.mapper.read.ICarManagerRolePageReadDao;
import com.wintop.ms.carauction.mapper.write.ICarManagerRolePageWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarManagerRolePageModel {
    @Autowired
    private ICarManagerRolePageReadDao rolePageReadDao;
    @Autowired
    private ICarManagerRolePageWriteDao rolePageWriteDao;

    /**
     * 根据roleId查询所有数据
     */
    public List<CarManagerRolePage> selectAll(Long roleId){
        return rolePageReadDao.selectAll(roleId);
    }

    /**
     * 根据roleId查询pageId
     */
    public List<String> selectAllPages(Long roleId){
        return rolePageReadDao.selectAllPages(roleId);
    }

    /**
     * 根据roleId删除记录
     */
    public int deleteByRoleId(Long roleId){
        return rolePageWriteDao.deleteByRoleId(roleId);
    }

    /**
     * 保存记录,
     */
    public int insert(CarManagerRolePage record){
        return rolePageWriteDao.insert(record);
    }

    public int batchInsert(List<CarManagerRolePage> rolePages){
        return rolePageWriteDao.batchInsert(rolePages);
    }

}
