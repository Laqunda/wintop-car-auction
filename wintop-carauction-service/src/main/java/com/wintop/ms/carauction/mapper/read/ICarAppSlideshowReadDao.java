package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarAppSlideshow;

import java.util.List;
import java.util.Map;

/**
 * Created by 12991 on 2018/2/26.
 */
public interface ICarAppSlideshowReadDao {

    /**
     * 根据条件查询记录总数
     */
    int countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    List<CarAppSlideshow> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    CarAppSlideshow selectById(Long id);
}
