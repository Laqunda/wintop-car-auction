package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarAutoProcedures;
import com.wintop.ms.carauction.entity.Criteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ICarAutoProceduresReadDao {
    /**
     * ����������ѯ��¼����
     */
    int countByExample(Criteria example);

    /**
     * ����������ѯ��¼��
     */
    List<CarAutoProcedures> selectByExample(Criteria example);

    /**
     * ����������ѯ��¼
     */
    CarAutoProcedures selectByPrimaryKey(Long id);

    /**
     * 根据车辆d查询车辆手续信息
     * @param carId
     * @return
     */
    CarAutoProcedures getAutoProceduresByCarId(@Param(value = "carId") Long carId);
}