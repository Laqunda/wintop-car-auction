package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarOrderRetail;
import org.apache.ibatis.annotations.Param;

public interface ICarOrderRetailWriteDao {
    int insertSelective(CarOrderRetail record);
}
