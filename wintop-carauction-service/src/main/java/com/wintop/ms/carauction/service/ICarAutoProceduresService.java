package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAutoProcedures;
import com.wintop.ms.carauction.entity.Criteria;
import java.util.List;

public interface ICarAutoProceduresService {

    ServiceResult<Integer> countByExample(Criteria example);

    ServiceResult<CarAutoProcedures> selectByPrimaryKey(Long id);

    ServiceResult<List<CarAutoProcedures>> selectByExample(Criteria example);

    ServiceResult<Integer> deleteByPrimaryKey(Long id);

    ServiceResult<Integer> updateByPrimaryKeySelective(CarAutoProcedures record);

    ServiceResult<Integer> updateByPrimaryKey(CarAutoProcedures record);

    Integer insert(CarAutoProcedures record);

    ServiceResult<Integer> insertSelective(CarAutoProcedures record);

     /*
     *根据车辆d查询车辆手续信息
     * @param carId
     * @return CarAutoProcedures
     */
    ServiceResult<CarAutoProcedures> getAutoProceduresByCarId(Long carId);
}