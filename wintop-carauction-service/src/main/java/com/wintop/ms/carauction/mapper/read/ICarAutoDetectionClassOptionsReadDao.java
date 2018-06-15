package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarAutoDetectionClassOptions;
import com.wintop.ms.carauction.entity.Criteria;

import java.util.List;
import java.util.Map;

public interface ICarAutoDetectionClassOptionsReadDao {

    int countByExample(Criteria example);


    List<CarAutoDetectionClassOptions> selectByExample(Criteria example);


    CarAutoDetectionClassOptions selectByPrimaryKey(Long id);

    List<CarAutoDetectionClassOptions> selectByClassIdAutoId(Map map);
}