package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarCustomerLevelPrice;
import com.wintop.ms.carauction.mapper.read.ICarCustomerLevelPriceReadDao;
import com.wintop.ms.carauction.mapper.write.ICarCustomerLevelPriceWriteDao;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author zhangzijuan
 * @date 2018-02-26
 */
@Repository
public class CarCustomerLevelPriceModel {

    @Resource
    private ICarCustomerLevelPriceReadDao readDao;
    @Resource
    private ICarCustomerLevelPriceWriteDao writeDao;
    /**
     * 根据主键查询记录
     * @param id
     * @return
     */
    public CarCustomerLevelPrice selectByPrimaryKey(Long id){
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
    public int insert(CarCustomerLevelPrice record){
        return writeDao.insert(record);
    }

    /**
     * 保存属性不为空的记录
     * @param record
     * @return
     */
    public int insertSelective(CarCustomerLevelPrice record){
        return writeDao.insertSelective(record);
    }

    /**
     * 根据主键更新属性不为空的记录
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(CarCustomerLevelPrice record){
        return writeDao.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据主键更新记录
     * @param record
     * @return
     */
    public int updateByPrimaryKey(CarCustomerLevelPrice record){
        return writeDao.updateByPrimaryKey(record);
    }


   /**
    * 根据levelId删除记录
    *@Author:zhangzijuan
    *@date 2018/3/15
    *@param:
    */
    public int deleteByLevelId(Long levelId){
        return writeDao.deleteByLevelId(levelId);
    }



}