package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarCustomerLevel;
import com.wintop.ms.carauction.mapper.read.ICarCustomerLevelReadDao;
import com.wintop.ms.carauction.mapper.write.ICarCustomerLevelWriteDao;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author zhangzijuan
 * @date 2018-02-26
 */
@Repository
public class CarCustomerLevelModel {

    @Resource
    private ICarCustomerLevelReadDao readDao;
    @Resource
    private ICarCustomerLevelWriteDao writeDao;
    /**
     * 根据主键查询记录
     * @param id
     * @return
     */
    public CarCustomerLevel selectByPrimaryKey(Long id){
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
    public int insert(CarCustomerLevel record){
        return writeDao.insert(record);
    }

    /**
     * 保存属性不为空的记录
     * @param record
     * @return
     */
    public int insertSelective(CarCustomerLevel record){
        return writeDao.insertSelective(record);
    }

    /**
     * 根据主键更新属性不为空的记录
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(CarCustomerLevel record){
        return writeDao.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据主键更新记录
     * @param record
     * @return
     */
    public int updateByPrimaryKey(CarCustomerLevel record){
        return writeDao.updateByPrimaryKey(record);
    }

    /**
     * 根据参数查询会员等级列表
     *@Author:zhangzijuan
     *@date 2018/3/15
     *@param:
     */
    public List<CarCustomerLevel> selectListByParam(Map<String,Object> map){
        return readDao.selectListByParam(map);
    }

    /**
     *根据参数查询会员等级总数量
     *@Author:zhangzijuan
     *@date 2018/3/15
     *@param:
     */
    public Integer selectCountByParam(Map<String,Object> map){
        return readDao.selectCountByParam(map);
    }

    /**
     * 将其他级别设置为不默认
     */
    public int setNoDefault(Map<String,Object> map){
        return writeDao.setNoDefault(map);
    }

    /**
     * 根据级别id查询级别详情
     */
    public CarCustomerLevel selectLevelById(Map<String,Object> map){
        return  readDao.selectLevelById(map);
    }

    /**
     * 查询默认级别
     *@Author:zhangzijuan
     *@date 2018/3/28
     *@param:
     */
    public CarCustomerLevel getDefaultLevel(){
        return readDao.getDefaultLevel();
    }

}