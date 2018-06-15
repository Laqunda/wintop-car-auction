package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarCustomerAuth;
import com.wintop.ms.carauction.mapper.read.ICarCustomerAuthReadDao;
import com.wintop.ms.carauction.mapper.write.ICarCustomerAuthWriteDao;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author zhangzijuan
 * @date 2018-02-26
 */
@Repository
public class CarCustomerAuthModel {

    @Resource
    private ICarCustomerAuthReadDao readDao;
    @Resource
    private ICarCustomerAuthWriteDao writeDao;
    /**
     * 根据主键查询记录
     * @param id
     * @return
     */
    public CarCustomerAuth selectByPrimaryKey(Long id){
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
    public int insert(CarCustomerAuth record){
        return writeDao.insert(record);
    }

    /**
     * 保存属性不为空的记录
     * @param record
     * @return
     */
    public int insertSelective(CarCustomerAuth record){
        return writeDao.insertSelective(record);
    }

    /**
     * 根据主键更新属性不为空的记录
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(CarCustomerAuth record){
        return writeDao.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据主键更新记录
     * @param record
     * @return
     */
    public int updateByPrimaryKey(CarCustomerAuth record){
        return writeDao.updateByPrimaryKey(record);
    }


    /**
     * 根据用户Id查询用户信息
     * @param userId
     * @return
     */
    public CarCustomerAuth getAuthInfoByUserId(Long userId){
        return readDao.getAuthInfoByUserId(userId);
    }

    /**
     * 据用户id更新会员认证信息
     * @param record
     * @return
     */
    public int updateByUserId(CarCustomerAuth record){
        return writeDao.updateByUserId(record);
    }

    /**
     * 查询认证列表
     *@Author:zhangzijuan
     *@date 2018/3/20
     *@param:id
     */
    public List<CarCustomerAuth> selectUserAuthList(Map<String,Object> map){
        return  readDao.selectUserAuthList(map);
    }

    /**
     * 查询认证数
     *@Author:zhangzijuan
     *@date 2018/3/20
     *@param:id
     */
    public Integer selectUserAuthCount(Map<String,Object> map){
        return readDao.selectUserAuthCount(map);
    }
}