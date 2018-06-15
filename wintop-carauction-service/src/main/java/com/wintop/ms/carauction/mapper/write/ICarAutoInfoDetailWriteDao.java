package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarAutoInfoDetail;
import com.wintop.ms.carauction.entity.Criteria;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ICarAutoInfoDetailWriteDao {


    int deleteByExample(Criteria example);

    int deleteByPrimaryKey(Long id);


    int insert(CarAutoInfoDetail record);


    int insertSelective(CarAutoInfoDetail record);


    int updateByExampleSelective(@Param("record") CarAutoInfoDetail record, @Param("condition") Map<String, Object> condition);


    int updateByExample(@Param("record") CarAutoInfoDetail record, @Param("condition") Map<String, Object> condition);


    int updateByPrimaryKeySelective(CarAutoInfoDetail record);

    int updateByPrimaryKey(CarAutoInfoDetail record);

    /***
     * 根据车辆id修改车辆对应的基本信息
     * @param detail
     * @return
     */
    int updateRemarkByautoId(CarAutoInfoDetail detail);
}