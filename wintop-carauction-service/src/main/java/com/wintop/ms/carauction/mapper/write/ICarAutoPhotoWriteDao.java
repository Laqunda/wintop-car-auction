package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarAutoPhoto;
import com.wintop.ms.carauction.entity.Criteria;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ICarAutoPhotoWriteDao {


    int deleteByExample(Criteria example);


    int deleteByPrimaryKey(Long id);


    int insert(CarAutoPhoto record);


    int insertSelective(CarAutoPhoto record);


    int updateByExampleSelective(@Param("record") CarAutoPhoto record, @Param("condition") Map<String, Object> condition);


    int updateByExample(@Param("record") CarAutoPhoto record, @Param("condition") Map<String, Object> condition);


    int updateByPrimaryKeySelective(CarAutoPhoto record);

    int updateByPrimaryKey(CarAutoPhoto record);

    int insertArr(@Param("photoList") List<CarAutoPhoto> list);

    void deleteByAutoId(@Param("autoId") Long autoId);
}