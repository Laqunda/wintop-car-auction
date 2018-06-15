package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CommonNameVo;
import com.wintop.ms.carauction.entity.CarStore;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ICarStoreReadDao {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    List<CarStore> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    CarStore selectByPrimaryKey(Long id);

    /**
     * 查询所有店铺
     * @return
     */
    List<CommonNameVo> selectAllStore();

    /**
     * 查询中心关联店铺
     */
    List<CommonNameVo> selectByCenter(@Param("centerId") Long centerId);

    /**
     * 查询所有店铺ID集合
     * @return
     */
    List<Long> selectAllStoreIds();
}