package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.WtDistrict;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/***
 * 区县查询
 */
public interface IWtDistrictReadDao {
    WtDistrict findById(@Param("districtId") Long districtId);

    List<WtDistrict> findAll(Map map);
}
