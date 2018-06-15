package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarManagerRoleType;
import com.wintop.ms.carauction.mapper.read.ICarManagerRoleTypeReadDao;
import com.wintop.ms.carauction.mapper.write.ICarManagerRoleTypeWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CarManagerRoleTypeModel {
    @Autowired
    private ICarManagerRoleTypeReadDao typeReadDao;

    /**
     * 根据条件查询记录集
     */
    public List<CarManagerRoleType> selectByExample(Map<String,Object> map){
        return typeReadDao.selectByExample(map);
    }
}
