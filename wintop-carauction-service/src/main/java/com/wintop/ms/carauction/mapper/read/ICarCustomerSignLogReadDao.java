package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarCustomerSignLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/***
 * 签约日志表
 */
public interface ICarCustomerSignLogReadDao {

    CarCustomerSignLog findById(Long id);

    List<CarCustomerSignLog> findBySignId(Map<String,Object> map);
}