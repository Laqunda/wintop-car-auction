package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarManagerRolePublish;
import com.wintop.ms.carauction.mapper.read.CarManagerRolePublishReadDao;
import com.wintop.ms.carauction.mapper.write.CarManagerRolePublishWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author mazg
 * @since 用户审车权限关联表
 */
@Repository
public class CarManagerRolePublishModel {

    @Autowired
    private CarManagerRolePublishReadDao readDao;

    @Autowired
    private CarManagerRolePublishWriteDao writeDao;

    public CarManagerRolePublish selectByPrimaryKey(Long id){
        return readDao.selectByPrimaryKey(id);
    }

    public List<CarManagerRolePublish> selectByCondition(Map<String,Object> param){
        return readDao.selectByCondition(param);
    }

    public int deleteByPrimaryKey(Long id){
        return writeDao.deleteByPrimaryKey(id);
    }

    public int insertSelective(CarManagerRolePublish record){
        return writeDao.insertSelective(record);
    }

    public int insert(CarManagerRolePublish record){
        return writeDao.insert(record);
    }

    public int deleteByCondition(Map<String, Object> map){
        return writeDao.deleteByCondition(map);
    }

    public int updateByPrimaryKeySelective(CarManagerRolePublish record){
        return writeDao.updateByPrimaryKeySelective(record);
    }
}
