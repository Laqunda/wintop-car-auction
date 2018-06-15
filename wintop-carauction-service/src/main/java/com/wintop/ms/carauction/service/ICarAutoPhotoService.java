package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAutoPhoto;

import java.util.List;
import java.util.Map;

public interface ICarAutoPhotoService {

    ServiceResult<Integer> countByExample(Map<String,Object> map);

    ServiceResult<CarAutoPhoto> selectByPrimaryKey(Long id);

    ServiceResult<List<CarAutoPhoto>> selectByExample(Map<String,Object> map);

    List<CarAutoPhoto> selectByAutoId(Long autoId);

    ServiceResult<Integer> deleteByPrimaryKey(Long id);

    ServiceResult<Integer> updateByPrimaryKeySelective(CarAutoPhoto record);

    ServiceResult<Integer> updateByPrimaryKey(CarAutoPhoto record);

    ServiceResult<CarAutoPhoto> insert(CarAutoPhoto record);

    ServiceResult<Integer> insertSelective(CarAutoPhoto record);

    Integer insertArr(List<CarAutoPhoto> photoList,Long autoId);
}