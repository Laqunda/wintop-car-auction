package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAutoDetectionClass;
import com.wintop.ms.carauction.entity.CarAutoDetectionData;
import com.wintop.ms.carauction.entity.Criteria;
import java.util.List;

public interface ICarAutoDetectionDataService {

    ServiceResult<Integer> countByExample(Criteria example);

    ServiceResult<CarAutoDetectionData> selectByPrimaryKey(Long id);

    ServiceResult<List<CarAutoDetectionData>> selectByExample(Criteria example);

    ServiceResult<Integer> deleteByPrimaryKey(Long id);

    ServiceResult<Integer> updateByPrimaryKeySelective(CarAutoDetectionData record);

    ServiceResult<Integer> updateByPrimaryKey(CarAutoDetectionData record);

    ServiceResult<Integer> insert(CarAutoDetectionData record);

    ServiceResult<Integer> insertSelective(CarAutoDetectionData record);

    void saveDetectionData(CarAutoDetectionClass detectionClass,Long autoId);
}