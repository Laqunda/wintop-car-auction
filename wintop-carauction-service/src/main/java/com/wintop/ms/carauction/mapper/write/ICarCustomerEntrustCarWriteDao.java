package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarCustomerEntrustCar;
import org.apache.ibatis.annotations.Param;

/**
 * 买家委托车辆
 * @author zhangzijuan
 * @date 2018-02-27
 */
public interface ICarCustomerEntrustCarWriteDao {

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(CarCustomerEntrustCar record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(CarCustomerEntrustCar record);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(CarCustomerEntrustCar record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(CarCustomerEntrustCar record);

    /**
     * 修改车辆委托价
     * @param entrustCar
     * @return
     */
    public Integer updateCarEntrustPrice(CarCustomerEntrustCar entrustCar);

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

}