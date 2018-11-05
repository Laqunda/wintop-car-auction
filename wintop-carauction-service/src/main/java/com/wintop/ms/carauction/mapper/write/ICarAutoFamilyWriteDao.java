package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarAutoFamily;
import com.wintop.ms.carauction.entity.Criteria;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ICarAutoFamilyWriteDao {

    /**
     */
    int deleteByExample(Criteria example);

    /**
     */
    int deleteByPrimaryKey(Long id);

    /**
     */
    int insert(CarAutoFamily record);

    /**
     */
    int insertSelective(CarAutoFamily record);

    /**
     */
    int updateByExampleSelective(@Param("record") CarAutoFamily record, @Param("condition") Map<String, Object> condition);

    /**
     */
    int updateByExample(@Param("record") CarAutoFamily record, @Param("condition") Map<String, Object> condition);

    /**
     */
    int updateByPrimaryKeySelective(CarAutoFamily record);

    /**
     */
    int updateByPrimaryKey(CarAutoFamily record);
}