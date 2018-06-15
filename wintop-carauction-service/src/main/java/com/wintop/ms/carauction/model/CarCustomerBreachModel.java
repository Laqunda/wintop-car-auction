package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarCustomerBreach;
import com.wintop.ms.carauction.mapper.read.ICarCustomerBreachReadDao;
import com.wintop.ms.carauction.mapper.write.ICarCustomerBreachWriteDao;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author zhangzijuan
 * @date 2018-02-26
 */
@Repository
public class CarCustomerBreachModel {

    @Resource
    private ICarCustomerBreachReadDao readDao;
    @Resource
    private ICarCustomerBreachWriteDao writeDao;
    /**
     * 根据主键查询记录
     * @param id
     * @return
     */
    public CarCustomerBreach selectByPrimaryKey(Long id){
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
    public int insert(CarCustomerBreach record){
        return writeDao.insert(record);
    }

    /**
     * 根据主键更新属性不为空的记录
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(CarCustomerBreach record){
        return writeDao.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据主键更新记录
     * @param record
     * @return
     */
    public int updateByPrimaryKey(CarCustomerBreach record){
        return writeDao.updateByPrimaryKey(record);
    }

    /**
     * 查询订单违约信息
     * @param map
     * @return
     */
    public CarCustomerBreach queryNewBreachInfo(Map<String,Object> map){
        return readDao.queryNewBreachInfo(map);
    }

    /**
     * 查询订单争议列表
     * @param map
     * @return
     */
    public List<CarCustomerBreach> selectBreachList(Map<String,Object> map){
        return readDao.selectBreachList(map);
    }

}