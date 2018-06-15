package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarCustomerViewedAuto;

import java.util.List;
import java.util.Map;

/**
 * Created by 12991 on 2018/3/12.
 */
public interface ICarCustomerViewedAutoService {

    ServiceResult<CarCustomerViewedAuto> selectByPrimaryKey(Long id);

    ServiceResult<Integer> deleteByPrimaryKey(Long id);

    ServiceResult<Integer> updateByPrimaryKeySelective(CarCustomerViewedAuto record);

    ServiceResult<Integer> updateByPrimaryKey(CarCustomerViewedAuto record);

    Integer insert(CarCustomerViewedAuto record);

    ServiceResult<Integer> insertSelective(CarCustomerViewedAuto record);

    /**
     * 根据条件查询用户浏览的车辆列表
     */
    List<CarCustomerViewedAuto> selectUserViewList(Map<String,Object> map);

    /**
     * 根据条件查询用户浏览的车辆数
     */
    int queryUserViewCount(Map<String,Object> map);

    /**
     * 更新浏览记录时间
     * @param carId
     * @param customerId
     * @return
     */
    public int updateViewTime(Long carId, Long customerId);

    /**
     * 查询是否存在浏览记录
     * @param carId
     * @param customerId
     * @return
     */
    CarCustomerViewedAuto queryViewRecord(Long carId,  Long customerId);
}
