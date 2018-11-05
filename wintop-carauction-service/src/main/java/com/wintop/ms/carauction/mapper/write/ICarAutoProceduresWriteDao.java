package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarAutoProcedures;
import com.wintop.ms.carauction.entity.Criteria;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ICarAutoProceduresWriteDao {

    /**
     */
    int deleteByExample(Criteria example);

    /**
     */
    int deleteByPrimaryKey(Long id);

    /**
     */
    int insert(CarAutoProcedures record);

    /**
     */
    int insertSelective(CarAutoProcedures record);

    /**
     */
    int updateByExampleSelective(@Param("record") CarAutoProcedures record, @Param("condition") Map<String, Object> condition);

    /**
     */
    int updateByExample(@Param("record") CarAutoProcedures record, @Param("condition") Map<String, Object> condition);

    /**
     */
    int updateByPrimaryKeySelective(CarAutoProcedures record);

    /**
     */
    int updateByPrimaryKey(CarAutoProcedures record);
}