package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarCustomerEntrustCar;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 买家委托车辆
 * @author zhangzijuan
 * @date 2018-02-27
 */
public interface ICarCustomerEntrustCarReadDao {
    /**
     *@Author:zhangzijuan
     *@date 2018/2/27
     *@Description:根据主键查询记录
     *@param:id
     */
    CarCustomerEntrustCar selectByPrimaryKey(Long id);

    /**
     * 查询车辆委托价
     * @param carId
     * @param customerId
     * @return
     */
    CarCustomerEntrustCar selectEntrustByCarId(@Param("carId") Long carId, @Param("customerId") Long customerId);

    /**
     * 查询车辆最高委托价
     * @param carId
     * @return
     */
    CarCustomerEntrustCar selectMaxEntrustByCarId(Long carId);

    /**
     * 查询所有有效委托价列表
     * @param carId
     * @return
     */
    List<CarCustomerEntrustCar> selectValidEntrustList(Long carId);
}