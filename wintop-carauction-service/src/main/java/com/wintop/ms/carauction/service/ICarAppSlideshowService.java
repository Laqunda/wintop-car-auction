package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAppSlideshow;

import java.util.List;
import java.util.Map;

/**
 * Created by 12991 on 2018/2/26.
 */
public interface ICarAppSlideshowService {
    /**
     * 根据条件查询记录总数
     */
    public Integer countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    List<CarAppSlideshow> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    CarAppSlideshow selectById(Long id);
    /**
     * 根据主键删除记录
     */
    Integer deleteById(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    Integer insert(CarAppSlideshow carAppSlideshow);

    /**
     * 根据主键更新属性不为空的记录
     */
    Integer updateByIdSelective(CarAppSlideshow carAppSlideshow);

    /**
     * 根据主键更新记录
     */
    Integer updateById(CarAppSlideshow carAppSlideshow);
}
