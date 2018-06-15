package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarAutoDetectionClass;
import com.wintop.ms.carauction.entity.CarAutoDetectionClassMap;
import com.wintop.ms.carauction.entity.Criteria;

import java.util.List;
import java.util.Map;

public interface ICarAutoDetectionClassReadDao {

    int countByExample(Criteria example);


    List<CarAutoDetectionClass> selectByExample(Criteria example);


    CarAutoDetectionClass selectByPrimaryKey(Long id);

    /***
     * 条件筛选某车的检测项
     * @param map
     * @return
     */
    List<CarAutoDetectionClassMap> selectByDetectionDataType(Map map);

    List<CarAutoDetectionClass> getAutoDetectionClass(CarAutoDetectionClass carAutoDetectionClass);
}