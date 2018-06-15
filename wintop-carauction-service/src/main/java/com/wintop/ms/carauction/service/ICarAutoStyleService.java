package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAutoStyle;
import com.wintop.ms.carauction.entity.Criteria;
import java.util.List;

public interface ICarAutoStyleService {

    ServiceResult<Integer> countByExample(Criteria example);

    ServiceResult<CarAutoStyle> selectByPrimaryKey(Long id);

    ServiceResult<List<CarAutoStyle>> selectByExample(Criteria example);

    ServiceResult<Integer> deleteByPrimaryKey(Long id);

    ServiceResult<Integer> updateByPrimaryKeySelective(CarAutoStyle record);

    ServiceResult<Integer> updateByPrimaryKey(CarAutoStyle record);

    ServiceResult<Integer> insert(CarAutoStyle record);

    ServiceResult<Integer> insertSelective(CarAutoStyle record);
}