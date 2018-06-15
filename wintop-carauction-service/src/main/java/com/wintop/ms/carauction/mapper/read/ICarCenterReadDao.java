package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarCenter;
import com.wintop.ms.carauction.entity.CommonNameVo;

import java.util.List;
import java.util.Map;

public interface ICarCenterReadDao {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    List<CarCenter> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    CarCenter selectByPrimaryKey(Long id);

    /**
     * 查询所有中心
     */
    List<CommonNameVo> selectAllCenter();

}