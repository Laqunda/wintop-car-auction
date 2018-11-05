package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarAutoDetectionClassOptions;
import com.wintop.ms.carauction.entity.Criteria;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface ICarAutoDetectionClassOptionsWriteDao {

    /**
     */
    int deleteByExample(Criteria example);

    /**
     */
    int deleteByPrimaryKey(Long id);

    /**
     */
    int insert(CarAutoDetectionClassOptions record);

    /**
     */
    int insertSelective(CarAutoDetectionClassOptions record);

    /**
     */
    int updateByExampleSelective(@Param("record") CarAutoDetectionClassOptions record, @Param("condition") Map<String, Object> condition);

    /**
     */
    int updateByExample(@Param("record") CarAutoDetectionClassOptions record, @Param("condition") Map<String, Object> condition);

    /**
     */
    int updateByPrimaryKeySelective(CarAutoDetectionClassOptions record);

    /**
     */
    int updateByPrimaryKey(CarAutoDetectionClassOptions record);
}