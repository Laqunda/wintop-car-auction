package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarAutoDetectionData;
import com.wintop.ms.carauction.entity.Criteria;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ICarAutoDetectionDataWriteDao {

    int deleteByExample(Criteria example);

    int deleteByPrimaryKey(Long id);

    int insert(CarAutoDetectionData record);

    int insertSelective(CarAutoDetectionData record);

    int updateByExampleSelective(@Param("record") CarAutoDetectionData record, @Param("condition") Map<String, Object> condition);

    int updateByExample(@Param("record") CarAutoDetectionData record, @Param("condition") Map<String, Object> condition);

    int updateByPrimaryKeySelective(CarAutoDetectionData record);

    int updateByPrimaryKey(CarAutoDetectionData record);

    int insertArr(@Param("dataList") List<CarAutoDetectionData> dataList);

    int deleteByClassIdAutoId(Map map);
}