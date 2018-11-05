package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarAutoDetectionClass;
import com.wintop.ms.carauction.entity.Criteria;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ICarAutoDetectionClassWriteDao {
    /**
     */
    int deleteByExample(Criteria example);

    /**
     */
    int deleteByPrimaryKey(Long id);

    /**
     */
    int insert(CarAutoDetectionClass record);

    /**
     */
    int insertSelective(CarAutoDetectionClass record);

    /**
     */
    int updateByExampleSelective(@Param("record") CarAutoDetectionClass record, @Param("condition") Map<String, Object> condition);

    /**
     */
    int updateByExample(@Param("record") CarAutoDetectionClass record, @Param("condition") Map<String, Object> condition);

    /**
     */
    int updateByPrimaryKeySelective(CarAutoDetectionClass record);

    /**
     */
    int updateByPrimaryKey(CarAutoDetectionClass record);
}