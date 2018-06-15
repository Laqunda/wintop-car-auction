package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarCustomerViewedAuto;
import com.wintop.ms.carauction.mapper.read.ICarCustomerViewedAutoReadDao;
import com.wintop.ms.carauction.mapper.write.ICarCustomerViewedAutoWriteDao;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by 12991 on 2018/3/12.
 */
@Repository
public class CarCustomerViewedAutoModel {
    @Resource
    private ICarCustomerViewedAutoReadDao readDao;
    @Resource
    private ICarCustomerViewedAutoWriteDao writeDao;


    /**
     * 查询用户浏览车辆数量
     */
    public Integer queryUserViewCount(Map<String,Object> map){
        return readDao.selectUserViewCount(map);
    }

    /**
     *根据条件查询浏览的车辆列表
     */
    public List<CarCustomerViewedAuto> selectUserViewList(Map<String,Object> map){
        return readDao.queryUserViewList(map);
    }
    /**
     *根据主键查询数据
     */
    public CarCustomerViewedAuto selectByPrimaryKey(Long id){
        return readDao.selectByPrimaryKey(id);
    }

    /**
     * 根据主键删除记录
     * @param id
     * @return
     */
    public int deleteByPrimaryKey(Long id){
        return writeDao.deleteById(id);
    }

    /**
     * 保存记录,不管记录里面的属性是否为空
     * @param record
     * @return
     */
    public int insert(CarCustomerViewedAuto record){
        return writeDao.insert(record);
    }

    public int insertSelective(CarCustomerViewedAuto record){
        return writeDao.insertSelective(record);
    }
    /**
     * 根据主键更新属性不为空的记录
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(CarCustomerViewedAuto record){
        return writeDao.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据主键更新记录
     * @param record
     * @return
     */
    public int updateByPrimaryKey(CarCustomerViewedAuto record){
        return writeDao.updateByPrimaryKey(record);
    }

    /**
     * 更新浏览记录时间
     * @param carId
     * @param customerId
     * @return
     */
    public int updateViewTime(Long carId, Long customerId){
        return writeDao.updateViewTime(carId, customerId);
    }

    /**
     * 查询是否存在浏览记录
     * @param carId
     * @param customerId
     * @return
     */
    public CarCustomerViewedAuto queryViewRecord(Long carId,  Long customerId){
        return readDao.queryViewRecord(carId, customerId);
    }

}
