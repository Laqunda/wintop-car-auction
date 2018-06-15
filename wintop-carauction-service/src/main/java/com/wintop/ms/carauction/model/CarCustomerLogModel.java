package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarCustomerLog;
import com.wintop.ms.carauction.mapper.read.ICarCustomerLogReadDao;
import com.wintop.ms.carauction.mapper.write.ICarCustomerLogWriteDao;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhangzijuan
 * @date 2018-02-26
 */
@Repository
public class CarCustomerLogModel {

    @Resource
    private ICarCustomerLogReadDao readDao;
    @Resource
    private ICarCustomerLogWriteDao writeDao;
    /**
     * 根据主键查询记录
     * @param id
     * @return
     */
    public CarCustomerLog selectByPrimaryKey(Long id){
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
    public int insert(CarCustomerLog record){
        return writeDao.insert(record);
    }

    /**
     * 保存属性不为空的记录
     * @param record
     * @return
     */
    public int insertSelective(CarCustomerLog record){
        return writeDao.insertSelective(record);
    }

    /**
     * 根据主键更新属性不为空的记录
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(CarCustomerLog record){
        return writeDao.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据主键更新记录
     * @param record
     * @return
     */
    public int updateByPrimaryKey(CarCustomerLog record){
        return writeDao.updateByPrimaryKey(record);
    }

    /**
     * 根据用户Id查询用户日志
     *@Author:zhangzijuan
     *@date 2018/3/20
     *@param:userId
     */
    public List<CarCustomerLog> selectUserLogByUserId(Long userId){
        return readDao.selectUserLogByUserId(userId);
    }

}