package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarAutoPhoto;
import com.wintop.ms.carauction.entity.Criteria;

import java.util.List;
import java.util.Map;

public interface ICarAutoPhotoReadDao {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    List<CarAutoPhoto> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    CarAutoPhoto selectByPrimaryKey(Long id);

    /**
     * 根据carId条件查询
     */
    List<CarAutoPhoto> selectByCarId(Long carId);
}