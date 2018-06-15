package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.WtRegion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by liangtingsen on 2018/3/6.
 * 地区区域
 */
public interface IWtRegionReadDao {
    /***
     * 根据主键查询详情
     * @param id
     * @return
     */
    WtRegion findById(@Param("id")Long id);

    /***
     * 根据地区编号查询详情
     * @param code
     * @return
     */
    WtRegion findByCode(@Param("code")String code);

    /***
     * 查询全部地区信息
     * @param status
     * @return
     */
    List<WtRegion> findAll(@Param("status") String status);
}
