package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarAutoInfoDetail;
import com.wintop.ms.carauction.entity.Criteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ICarAutoInfoDetailReadDao {

    int countByExample(Map<String,Object> map);

    List<CarAutoInfoDetail> selectByExample(Map<String,Object> map);

    CarAutoInfoDetail selectByPrimaryKey(Long id);

    /**
     * 查询车辆基本信息
     * @param carId
     * @return
     */
    CarAutoInfoDetail selectDetailByCarId(@Param("carId") Long carId);
}