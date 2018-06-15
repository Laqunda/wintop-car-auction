package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarAutoFamily;
import com.wintop.ms.carauction.entity.Criteria;

import java.util.List;

public interface ICarAutoFamilyReadDao {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Criteria example);

    /**
     * 根据条件查询记录集
     */
    List<CarAutoFamily> selectByExample(Criteria example);

    /**
     * 根据主键查询记录
     */
    CarAutoFamily selectByPrimaryKey(Long id);
}