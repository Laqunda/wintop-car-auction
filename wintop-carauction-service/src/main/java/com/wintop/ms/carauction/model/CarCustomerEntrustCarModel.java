package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarCustomerEntrustCar;
import com.wintop.ms.carauction.mapper.read.ICarCustomerEntrustCarReadDao;
import com.wintop.ms.carauction.mapper.write.ICarCustomerEntrustCarWriteDao;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhangzijuan
 * @date 2018-02-27
 */
@Repository
public class CarCustomerEntrustCarModel {

    @Resource
    private ICarCustomerEntrustCarReadDao readDao;
    @Resource
    private ICarCustomerEntrustCarWriteDao writeDao;
    /**
     * 根据主键查询记录
     * @param id
     * @return
     */
    public CarCustomerEntrustCar selectByPrimaryKey(Long id){
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
    public int insert(CarCustomerEntrustCar record){
        return writeDao.insert(record);
    }

    /**
     * 保存属性不为空的记录
     * @param record
     * @return
     */
    public int insertSelective(CarCustomerEntrustCar record){
        return writeDao.insertSelective(record);
    }

    /**
     * 根据主键更新属性不为空的记录
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(CarCustomerEntrustCar record){
        return writeDao.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据主键更新记录
     * @param record
     * @return
     */
    public int updateByPrimaryKey(CarCustomerEntrustCar record){
        return writeDao.updateByPrimaryKey(record);
    }

    /**
     * 查询车辆委托价
     * @param carId
     * @param customerId
     * @return
     */
    public CarCustomerEntrustCar selectEntrustByCarId(Long carId,Long customerId){
        return readDao.selectEntrustByCarId(carId, customerId);
    }

    /**
     * 修改车辆委托价
     * @param entrustCar
     * @return
     */
    public Integer updateCarEntrustPrice(CarCustomerEntrustCar entrustCar){
        return writeDao.updateCarEntrustPrice(entrustCar);
    }

    /**
     * 设置车辆委托价状态
     * @param entrustCar
     * @return
     */
    public Integer updateCarEntrustPriceStatus(CarCustomerEntrustCar entrustCar){
        return writeDao.updateCarEntrustPriceStatus(entrustCar);
    }

    /**
     * 删除委托价
     * @return
     */
    public Integer deleteCarEntrustPriceStatus(CarCustomerEntrustCar entrustCar){
        return writeDao.deleteCarEntrustPriceStatus(entrustCar);
    }

    /**
     * 查询车辆最高委托价
     * @param carId
     * @return
     */
    public CarCustomerEntrustCar selectMaxEntrustByCarId(Long carId){
        return readDao.selectMaxEntrustByCarId(carId);
    }

    /**
     * 查询所有有效委托价列表
     * @param carId
     * @return
     */
    public List<CarCustomerEntrustCar> selectValidEntrustList(Long carId){
        return readDao.selectValidEntrustList(carId);
    }

}