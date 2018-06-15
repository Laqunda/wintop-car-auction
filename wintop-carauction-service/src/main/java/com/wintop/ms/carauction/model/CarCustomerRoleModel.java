package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarCustomerRole;
import com.wintop.ms.carauction.mapper.read.ICarCustomerRoleReadDao;
import com.wintop.ms.carauction.mapper.write.ICarCustomerRoleWriteDao;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author zhangzijuan
 * @date 2018-02-26
 */
@Repository
public class CarCustomerRoleModel {

    @Resource
    private ICarCustomerRoleReadDao readDao;
    @Resource
    private ICarCustomerRoleWriteDao writeDao;
    /**
     * 根据主键查询记录
     * @param id
     * @return
     */
    public CarCustomerRole selectByPrimaryKey(Long id){
        return readDao.selectByPrimaryKey(id);
    }

    /**
     * 根据主键删除记录
     * @param id
     * @return
     */
    public int deleteByPrimaryKey(Long id){
        return writeDao.deleteByPrimaryKey(id);
    }

    /**
     * 保存记录,不管记录里面的属性是否为空
     * @param record
     * @return
     */
    public int insert(CarCustomerRole record){
        return writeDao.insert(record);
    }

    /**
     * 保存属性不为空的记录
     * @param record
     * @return
     */
    public int insertSelective(CarCustomerRole record){
        return writeDao.insertSelective(record);
    }

    /**
     * 根据主键更新属性不为空的记录
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(CarCustomerRole record){
        return writeDao.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据主键更新记录
     * @param record
     * @return
     */
    public int updateByPrimaryKey(CarCustomerRole record){
        return writeDao.updateByPrimaryKey(record);
    }

}