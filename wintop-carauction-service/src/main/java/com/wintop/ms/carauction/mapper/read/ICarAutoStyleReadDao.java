package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarAutoStyle;
import com.wintop.ms.carauction.entity.Criteria;

import java.util.List;

public interface ICarAutoStyleReadDao {
    /**
     */
    int countByExample(Criteria example);

    /**
     */
    List<CarAutoStyle> selectByExample(Criteria example);

    /**
     */
    CarAutoStyle selectByPrimaryKey(Long id);
}