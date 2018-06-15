package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarAutoDetectionDataPhoto;
import com.wintop.ms.carauction.entity.Criteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ICarAutoDetectionDataPhotoReadDao {

    int countByExample(Criteria example);


    List<CarAutoDetectionDataPhoto> selectByExample(Criteria example);


    CarAutoDetectionDataPhoto selectByPrimaryKey(Long id);

    /**
     * 获取车辆质检报告图片
     * @param autoId
     * @return
     */
    List<CarAutoDetectionDataPhoto> selectByAutoId(@Param("autoId") Long autoId);
}