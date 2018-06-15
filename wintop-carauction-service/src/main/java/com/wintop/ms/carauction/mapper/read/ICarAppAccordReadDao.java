package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarAppAccord;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by 12991 on 2018/2/26.
 */
public interface ICarAppAccordReadDao {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    List<CarAppAccord> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    CarAppAccord selectById(Long id);

    /***
     * 根据编号查询协议内容
     * @param code
     * @return
     */
    CarAppAccord findByCode(@Param("code")String code);
}