package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.WtCity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IWtCityReadDao {
    WtCity findById(@Param("cityId") Long cityId);

    List<WtCity> findAll(Map map);
}
