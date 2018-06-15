package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.WtProvince;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/***
 * 查询省直辖市数据
 */
public interface IWtProvinceReadDao {
    WtProvince findById(@Param("provinceId") Long provinceId);

    List<WtProvince> findAll(Map map);
}
