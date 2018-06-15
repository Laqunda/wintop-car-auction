package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAutoFamily;
import com.wintop.ms.carauction.entity.Criteria;
import java.util.List;

public interface ICarAutoFamilyService {

    ServiceResult<Integer> countByExample(Criteria example);

    ServiceResult<CarAutoFamily> selectByPrimaryKey(Long id);

    ServiceResult<List<CarAutoFamily>> selectByExample(Criteria example);

    ServiceResult<Integer> deleteByPrimaryKey(Long id);

    ServiceResult<Integer> updateByPrimaryKeySelective(CarAutoFamily record);

    ServiceResult<Integer> updateByPrimaryKey(CarAutoFamily record);

    ServiceResult<Integer> insert(CarAutoFamily record);

    ServiceResult<Integer> insertSelective(CarAutoFamily record);
}