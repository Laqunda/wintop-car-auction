package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarCustomerSign;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/***
 * 签约主表
 */
public interface ICarCustomerSignReadDao {
    CarCustomerSign findById(Long id);

    CarCustomerSign findByCustomerId(@Param("customerId") Long customerId);

    List<CarCustomerSign> findByParams(Map params);

    CarCustomerSign querySignByUserId(@Param("customerId") Long customerId);
}
