package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarCustomerGroupDetail;
import com.wintop.ms.carauction.mapper.read.ICarCustomerGroupDetailReadDao;
import com.wintop.ms.carauction.mapper.write.ICarCustomerGroupDetailWriteDao;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * 用户分组信息详情
 * @author zhangzijuan
 * @date 2018-02-26
 */
@Repository
public class CarCustomerGroupDetailModel {

    @Resource
    private ICarCustomerGroupDetailReadDao readDao;
    @Resource
    private ICarCustomerGroupDetailWriteDao writeDao;
    /**
     * 根据主键查询记录
     * @param id
     * @return
     */
    public CarCustomerGroupDetail selectByPrimaryKey(Long id){
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
    public int insert(CarCustomerGroupDetail record){
        return writeDao.insert(record);
    }

    /**
     * 保存属性不为空的记录
     * @param record
     * @return
     */
    public int insertSelective(CarCustomerGroupDetail record){
        return writeDao.insertSelective(record);
    }

    /**
     * 根据主键更新属性不为空的记录
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(CarCustomerGroupDetail record){
        return writeDao.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据主键更新记录
     * @param record
     * @return
     */
    public int updateByPrimaryKey(CarCustomerGroupDetail record){
        return writeDao.updateByPrimaryKey(record);
    }

    /**
     * 删除用户的分组
     * @param userId
     * @return
     */
    public Integer deleteByUserId(Long userId){
        return writeDao.deleteByUserId(userId);
    }

    /**
     * 删除用户的分组根据groupId
     * @param groupId
     * @return
     */
    public Integer deleteByGroupId(Long groupId){
        return writeDao.deleteByGroupId(groupId);
    }
}