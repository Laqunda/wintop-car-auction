package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAutoDetectionDataPhoto;
import com.wintop.ms.carauction.entity.Criteria;
import java.util.List;

public interface ICarAutoDetectionDataPhotoService {
    ServiceResult<Integer> countByExample(Criteria example);

    ServiceResult<CarAutoDetectionDataPhoto> selectByPrimaryKey(Long id);

    ServiceResult<List<CarAutoDetectionDataPhoto>> selectByExample(Criteria example);

    ServiceResult<Integer> deleteByPrimaryKey(Long id);

    ServiceResult<Integer> updateByPrimaryKeySelective(CarAutoDetectionDataPhoto record);

    ServiceResult<Integer> updateByPrimaryKey(CarAutoDetectionDataPhoto record);

    ServiceResult<Integer> insert(CarAutoDetectionDataPhoto record);

    ServiceResult<Integer> insertSelective(CarAutoDetectionDataPhoto record);
}