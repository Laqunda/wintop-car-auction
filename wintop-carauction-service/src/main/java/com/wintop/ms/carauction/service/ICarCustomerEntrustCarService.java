package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarCustomerEntrustCar;

import java.util.List;
import java.util.Map;

/**
 * 买家委托车辆业务层
 * @author zhangzijuan
 * @date 2018-02-27
 */
public interface ICarCustomerEntrustCarService {
    /**
     * 根据id查询买家委托车辆
     * @param id
     * @return CarCustomerEntrustCar
     */
    ServiceResult<CarCustomerEntrustCar> selectByPrimaryKey(Long id);
    /**
     * 根据id删除买家委托车辆
     * @param id
     * @return
     */
    ServiceResult<Integer> deleteByPrimaryKey(Long id);
    /**
     * 根据主键更新属性不为空的记录
     * @param record
     * @return
     */
    ServiceResult<Integer> updateByPrimaryKeySelective(CarCustomerEntrustCar record);
    /**
     * 根据主键更新买家委托车辆
     * @param record
     * @return
     */
    ServiceResult<Integer> updateByPrimaryKey(CarCustomerEntrustCar record);
    /**
     * 存记录,不管记录里面的属性是否为空
     * @param record
     * @return
     */
    Integer insert(CarCustomerEntrustCar record);
    /**
     * 保存属性不为空的记录
     * @param record
     * @return
     */
    ServiceResult<Integer> insertSelective(CarCustomerEntrustCar record);

    /**
     * 查询车辆委托价
     * @param carId
     * @param customerId
     * @return
     */
    public CarCustomerEntrustCar selectEntrustByCarId(Long carId, Long customerId);

    /**
     * 修改车辆委托价
     * @param entrustCar
     * @return
     */
    Integer updateCarEntrustPrice(CarCustomerEntrustCar entrustCar);

    /**
     * 设置车辆委托价状态
     * @param entrustCar
     * @return
     */
    public Integer updateCarEntrustPriceStatus(CarCustomerEntrustCar entrustCar);

    /**
     * 删除委托价
     * @return
     */
    public Integer deleteCarEntrustPriceStatus(CarCustomerEntrustCar entrustCar);

    /**
     * 查询所有有效委托价列表
     * @param carId
     * @return
     */
    public List<CarCustomerEntrustCar> selectValidEntrustList(Long carId);
}