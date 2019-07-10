package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarManagerRoleData;
import com.wintop.ms.carauction.mapper.read.ICarManagerRoleDataReadDao;
import com.wintop.ms.carauction.mapper.write.ICarManagerRoleDataWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CarManagerRoleDataModel {

    @Autowired
    private ICarManagerRoleDataReadDao readDao;

    @Autowired
    private ICarManagerRoleDataWriteDao writeDao;

    public CarManagerRoleData selectByPrimaryKey(Long id) {
        return readDao.selectByPrimaryKey(id);
    }

    public List<CarManagerRoleData> selectForCondition(Map<String, Object> map) {
        return readDao.selectAll(map);
    }

    public int deleteByPrimaryKey(Long id) {
        return writeDao.deleteByPrimaryKey(id);
    }

    public int deleteForCondition(Map<String, Object> map) {
        return writeDao.deleteForCondition(map);
    }

    public int insert(CarManagerRoleData record) {
        return writeDao.insert(record);
    }

    public int insertSelective(CarManagerRoleData record) {
        return writeDao.insertSelective(record);
    }

    public int updateByPrimaryKeySelective(CarManagerRoleData record) {
        return writeDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(CarManagerRoleData record) {
        return writeDao.updateByPrimaryKey(record);
    }
}
