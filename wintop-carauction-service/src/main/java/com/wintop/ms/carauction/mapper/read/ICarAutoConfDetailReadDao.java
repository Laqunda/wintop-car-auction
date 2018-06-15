package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarAutoConfDetail;
import com.wintop.ms.carauction.entity.Criteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ICarAutoConfDetailReadDao {
    /**
     *
     */
    int countByExample(Map<String,Object> map);

    /**
     *
     */
    List<CarAutoConfDetail> selectByExample(Map<String,Object> map);

    /**
     *
     */
    CarAutoConfDetail selectByPrimaryKey(Long id);

    /**
     * 根据车辆id查询配置信息
     * @param carId
     * @return
     */
    List<CarAutoConfDetail> selectConfigsByCarId(@Param("carId") Long carId);
}