package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAutoConfTitle;
import com.wintop.ms.carauction.entity.Criteria;
import java.util.List;
import java.util.Map;

public interface ICarAutoConfTitleService {
    ServiceResult<Integer> countByExample(Map<String,Object> map);

    ServiceResult<CarAutoConfTitle> selectByPrimaryKey(Long id);

    ServiceResult<List<CarAutoConfTitle>> selectByExample(Map<String,Object> map);

    ServiceResult<Integer> deleteByPrimaryKey(Long id);

    ServiceResult<Integer> updateByPrimaryKeySelective(CarAutoConfTitle record);

    ServiceResult<Integer> updateByPrimaryKey(CarAutoConfTitle record);

    ServiceResult<Integer> insert(CarAutoConfTitle record);

    ServiceResult<Integer> insertSelective(CarAutoConfTitle record);

    List<CarAutoConfTitle> selectAll(Map map);
}