package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAutoDetectionClass;
import com.wintop.ms.carauction.entity.CarAutoDetectionClassMap;
import com.wintop.ms.carauction.entity.Criteria;
import java.util.List;
import java.util.Map;

public interface ICarAutoDetectionClassService {
    ServiceResult<Integer> countByExample(Criteria example);

    ServiceResult<CarAutoDetectionClass> selectByPrimaryKey(Long id);

    ServiceResult<List<CarAutoDetectionClass>> selectByExample(Criteria example);

    ServiceResult<Integer> deleteByPrimaryKey(Long id);

    ServiceResult<Integer> updateByPrimaryKeySelective(CarAutoDetectionClass record);

    ServiceResult<Integer> updateByPrimaryKey(CarAutoDetectionClass record);

    ServiceResult<Integer> insert(CarAutoDetectionClass record);

    ServiceResult<Integer> insertSelective(CarAutoDetectionClass record);

    ServiceResult<List<CarAutoDetectionClassMap>> selectByDetectionData(Map map);

    ServiceResult<Map<String,Object>> getAutoDetectionDataClass(Long autoId);

    List<CarAutoDetectionClass> getAutoDetectionClass(CarAutoDetectionClass carAutoDetectionClass,Long autoId);

    CarAutoDetectionClass getAutoDetectionClassOption(Long classId,Long autoId);
}