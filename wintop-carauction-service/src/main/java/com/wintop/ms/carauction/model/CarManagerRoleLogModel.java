package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarManagerRoleLog;
import com.wintop.ms.carauction.mapper.read.ICarManagerRoleLogReadDao;
import com.wintop.ms.carauction.mapper.write.ICarManagerRoleLogWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CarManagerRoleLogModel {

    @Autowired
    private ICarManagerRoleLogReadDao readDao;

    @Autowired
    private ICarManagerRoleLogWriteDao writeDao;

    public CarManagerRoleLog selectByPrimaryKey(Long id){
        return readDao.selectByPrimaryKey(id);
    }

    public List<CarManagerRoleLog> selectByCondtion(Map<String,Object> map){
        return readDao.selectByCondtion(map);
    }

    public int deleteByPrimaryKey(Long id){
        return writeDao.deleteByPrimaryKey(id);
    }

    public int insert(CarManagerRoleLog record){
        return writeDao.insert(record);
    }

    public int insertSelective(CarManagerRoleLog record){
        return writeDao.insertSelective(record);
    }

    public int updateByPrimaryKeySelective(CarManagerRoleLog record){
        return writeDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(CarManagerRoleLog record){
        return writeDao.updateByPrimaryKey(record);
    }
}
