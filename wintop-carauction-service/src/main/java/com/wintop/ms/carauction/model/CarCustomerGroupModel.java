package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarCustomerGroup;
import com.wintop.ms.carauction.mapper.read.ICarCustomerGroupReadDao;
import com.wintop.ms.carauction.mapper.write.ICarCustomerGroupWriteDao;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 用户分组
 * @author zhangzijuan
 * @date 2018-02-26
 */
@Repository
public class CarCustomerGroupModel {

    @Resource
    private ICarCustomerGroupReadDao readDao;
    @Resource
    private ICarCustomerGroupWriteDao writeDao;
    /**
     * 根据主键查询记录
     * @param id
     * @return
     */
    public CarCustomerGroup selectByPrimaryKey(Long id){
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
    public int insert(CarCustomerGroup record){
        return writeDao.insert(record);
    }

    /**
     * 保存属性不为空的记录
     * @param record
     * @return
     */
    public int insertSelective(CarCustomerGroup record){
        return writeDao.insertSelective(record);
    }

    /**
     * 根据主键更新属性不为空的记录
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(CarCustomerGroup record){
        return writeDao.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据主键更新记录
     * @param record
     * @return
     */
    public int updateByPrimaryKey(CarCustomerGroup record){
        return writeDao.updateByPrimaryKey(record);
    }
    /**
     * 查询用户分组及对应的用户数量
     * @Author:zhangzijuan
     * @param map
     * @return
     */
    public List<CarCustomerGroup> selectGroupAndNum(Map<String,Object> map){
        return  readDao.selectGroupAndNum(map);
    }

    /**
     * 查询所有可以选择的用户分组
     *@Author:zhangzijuan
     *@date 2018/3/17
     *@param:
     */
    public List<CarCustomerGroup> selectGroupForSelect(Map<String,Object> map){
        return readDao.selectGroupForSelect(map);
    }

}