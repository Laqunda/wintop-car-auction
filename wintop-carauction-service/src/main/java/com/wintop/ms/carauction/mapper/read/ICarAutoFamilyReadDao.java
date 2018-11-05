package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarAutoFamily;
import com.wintop.ms.carauction.entity.Criteria;

import java.util.List;

public interface ICarAutoFamilyReadDao {
    /**
     * ??????????????????
     */
    int countByExample(Criteria example);

    /**
     * ????????????????
     */
    List<CarAutoFamily> selectByExample(Criteria example);

    /**
     * ??????????????
     */
    CarAutoFamily selectByPrimaryKey(Long id);
}