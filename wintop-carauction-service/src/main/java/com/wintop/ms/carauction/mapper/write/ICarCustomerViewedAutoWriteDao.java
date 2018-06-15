package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarCustomerViewedAuto;
import org.apache.ibatis.annotations.Param;

/**
 * Created by 12991 on 2018/3/12.
 */
public interface ICarCustomerViewedAutoWriteDao {
    /**
     * 根据主键删除记录
     */
    int deleteById(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(CarCustomerViewedAuto carCustomerViewedAuto);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insertSelective(CarCustomerViewedAuto carCustomerViewedAuto);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(CarCustomerViewedAuto carCustomerViewedAuto);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(CarCustomerViewedAuto carCustomerViewedAuto);

    /**
     * 更新浏览记录时间
     * @param carId
     * @param customerId
     * @return
     */
    Integer updateViewTime(@Param("carId") Long carId, @Param("customerId") Long customerId);
}
