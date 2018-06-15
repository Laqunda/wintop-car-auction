package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarAutoConfDetail;
import com.wintop.ms.carauction.entity.Criteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ICarAutoConfDetailWriteDao {

    int deleteByExample(Criteria example);


    int deleteByPrimaryKey(Long id);


    int insert(CarAutoConfDetail record);


    int insertSelective(CarAutoConfDetail record);


    int updateByExampleSelective(@Param("record") CarAutoConfDetail record, @Param("condition") Map<String, Object> condition);


    int updateByExample(@Param("record") CarAutoConfDetail record, @Param("condition") Map<String, Object> condition);


    int updateByPrimaryKeySelective(CarAutoConfDetail record);


    int updateByPrimaryKey(CarAutoConfDetail record);

    int insertArr(@Param("optionList") List<CarAutoConfDetail> recordArr);

    int deleteByAutoId(@Param("autoId") Long autoId);
}