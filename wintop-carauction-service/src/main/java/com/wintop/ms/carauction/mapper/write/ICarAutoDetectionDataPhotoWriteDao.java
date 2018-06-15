package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarAutoDetectionDataPhoto;
import com.wintop.ms.carauction.entity.Criteria;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ICarAutoDetectionDataPhotoWriteDao {

    int deleteByExample(Criteria example);

    int deleteByPrimaryKey(Long id);

    int insert(CarAutoDetectionDataPhoto record);

    int insertSelective(CarAutoDetectionDataPhoto record);

    int updateByExampleSelective(@Param("record") CarAutoDetectionDataPhoto record, @Param("condition") Map<String, Object> condition);

    int updateByExample(@Param("record") CarAutoDetectionDataPhoto record, @Param("condition") Map<String, Object> condition);

    int updateByPrimaryKeySelective(CarAutoDetectionDataPhoto record);

    int updateByPrimaryKey(CarAutoDetectionDataPhoto record);

    int insertArr(@Param("photoList") List<CarAutoDetectionDataPhoto> photoList);

    int deleteByClassIdAutoId(Map map);
}