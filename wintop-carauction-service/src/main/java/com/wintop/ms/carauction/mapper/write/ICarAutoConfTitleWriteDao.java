package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarAutoConfTitle;
import com.wintop.ms.carauction.entity.Criteria;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface ICarAutoConfTitleWriteDao {
    /**
     */
    int deleteByExample(Criteria example);

    /**
     */
    int deleteByPrimaryKey(Long id);

    /**
     */
    int insert(CarAutoConfTitle record);

    /**
     */
    int insertSelective(CarAutoConfTitle record);

    /**
     */
    int updateByExampleSelective(@Param("record") CarAutoConfTitle record, @Param("condition") Map<String, Object> condition);

    /**
     */
    int updateByExample(@Param("record") CarAutoConfTitle record, @Param("condition") Map<String, Object> condition);

    /**
     */
    int updateByPrimaryKeySelective(CarAutoConfTitle record);

    /**
     */
    int updateByPrimaryKey(CarAutoConfTitle record);
}