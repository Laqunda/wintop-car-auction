package com.wintop.ms.carauction.mapper.read;

import java.util.List;
import java.util.Map;

import com.wintop.ms.carauction.entity.TblAuctionTimes;
import org.apache.ibatis.annotations.Param;

public interface TblAuctionTimesReadDao {
    /**
     * 根据条件查询对象
     */
    TblAuctionTimes selectAuctionCar(Map<String,Object> map);

}