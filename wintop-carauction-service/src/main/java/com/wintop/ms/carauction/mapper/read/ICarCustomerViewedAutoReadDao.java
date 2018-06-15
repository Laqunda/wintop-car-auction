package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarCustomerViewedAuto;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by 12991 on 2018/3/12.
 */
public interface ICarCustomerViewedAutoReadDao {

    /**
     * 根据主键查询数据
     */
    CarCustomerViewedAuto selectByPrimaryKey(Long id);
    /**
     * 根据条件查询浏览的车辆数量
     */
    Integer selectUserViewCount(Map<String,Object> map);

    /**
     * 查询用户浏览的车辆列表
     */
    List<CarCustomerViewedAuto> queryUserViewList(Map<String,Object> map);

    /**
     * 查询是否存在浏览记录
     * @param carId
     * @param customerId
     * @return
     */
    CarCustomerViewedAuto queryViewRecord(@Param("carId") Long carId, @Param("customerId") Long customerId);
}
