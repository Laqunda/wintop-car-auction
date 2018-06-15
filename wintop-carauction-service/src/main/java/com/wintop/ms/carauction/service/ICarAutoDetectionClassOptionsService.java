package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAutoDetectionClassOptions;
import com.wintop.ms.carauction.entity.Criteria;
import java.util.List;

public interface ICarAutoDetectionClassOptionsService {
    ServiceResult<Integer> countByExample(Criteria example);

    ServiceResult<CarAutoDetectionClassOptions> selectByPrimaryKey(Long id);

    ServiceResult<List<CarAutoDetectionClassOptions>> selectByExample(Criteria example);

    ServiceResult<Integer> deleteByPrimaryKey(Long id);

    ServiceResult<Integer> updateByPrimaryKeySelective(CarAutoDetectionClassOptions record);

    ServiceResult<Integer> updateByPrimaryKey(CarAutoDetectionClassOptions record);

    ServiceResult<Integer> insert(CarAutoDetectionClassOptions record);

    ServiceResult<Integer> insertSelective(CarAutoDetectionClassOptions record);
}