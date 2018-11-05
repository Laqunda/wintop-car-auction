package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarAutoStyle;
import com.wintop.ms.carauction.entity.Criteria;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ICarAutoStyleWriteDao {

    /**
     */
    int deleteByExample(Criteria example);

    /**
     */
    int deleteByPrimaryKey(Long id);

    /**
     */
    int insert(CarAutoStyle record);

    /**
     */
    int insertSelective(CarAutoStyle record);

    /**
     */
    int updateByExampleSelective(@Param("record") CarAutoStyle record, @Param("condition") Map<String, Object> condition);

    /**
     */
    int updateByExample(@Param("record") CarAutoStyle record, @Param("condition") Map<String, Object> condition);

    /**
     */
    int updateByPrimaryKeySelective(CarAutoStyle record);

    /**
     */
    int updateByPrimaryKey(CarAutoStyle record);
}