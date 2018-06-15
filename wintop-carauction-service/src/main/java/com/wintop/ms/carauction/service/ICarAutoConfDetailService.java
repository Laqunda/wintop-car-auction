package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAutoConfDetail;
import com.wintop.ms.carauction.entity.Criteria;
import java.util.List;
import java.util.Map;

public interface ICarAutoConfDetailService {
    ServiceResult<Integer> countByExample(Map<String,Object> map);

    ServiceResult<CarAutoConfDetail> selectByPrimaryKey(Long id);

    ServiceResult<List<CarAutoConfDetail>> selectByExample(Map<String,Object> map);

    ServiceResult<Integer> deleteByPrimaryKey(Long id);

    ServiceResult<Integer> updateByPrimaryKeySelective(CarAutoConfDetail record);

    ServiceResult<Integer> updateByPrimaryKey(CarAutoConfDetail record);

    ServiceResult<Integer> insert(CarAutoConfDetail record);

    ServiceResult<Integer> insertSelective(CarAutoConfDetail record);

    Integer insertArr(List<CarAutoConfDetail> recordArr,Long autoId);
}