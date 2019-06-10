package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarAutoDetectionData;
import com.wintop.ms.carauction.entity.Criteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ICarAutoDetectionDataReadDao {
    int countByExample(Criteria example);

    List<CarAutoDetectionData> selectByExample(Criteria example);

    /**
     * 主键查询
     */
    CarAutoDetectionData selectByPrimaryKey(Long id);

    /***
     *  根据车辆id获取车辆的质检报告损坏项数据
     * @param autoId
     * @return
     */
    List<CarAutoDetectionData> selectByAutoId(@Param("autoId")Long autoId);

    /**
     * 根据条件查询记录
     * @param param
     * @return
     */
    List<CarAutoDetectionData> selectByCondition(Map<String, Object> param);
}