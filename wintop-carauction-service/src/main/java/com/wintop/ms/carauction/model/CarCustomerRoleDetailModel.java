package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarCustomerRoleDetail;
import com.wintop.ms.carauction.mapper.read.ICarCustomerRoleDetailReadDao;
import com.wintop.ms.carauction.mapper.write.ICarCustomerRoleDetailWriteDao;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author zhangzijuan
 * @date 2018-02-26
 */
@Repository
public class CarCustomerRoleDetailModel {

    @Resource
    private ICarCustomerRoleDetailReadDao readDao;
    @Resource
    private ICarCustomerRoleDetailWriteDao writeDao;
    /**
     * 根据主键查询记录
     * @param id
     * @return
     */
    public CarCustomerRoleDetail selectByPrimaryKey(Long id){
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
    public int insert(CarCustomerRoleDetail record){
        return writeDao.insert(record);
    }

    /**
     * 保存属性不为空的记录
     * @param record
     * @return
     */
    public int insertSelective(CarCustomerRoleDetail record){
        return writeDao.insertSelective(record);
    }

    /**
     * 根据主键更新属性不为空的记录
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(CarCustomerRoleDetail record){
        return writeDao.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据主键更新记录
     * @param record
     * @return
     */
    public int updateByPrimaryKey(CarCustomerRoleDetail record){
        return writeDao.updateByPrimaryKey(record);
    }

}