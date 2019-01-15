package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.entity.TblAuctionTimes;

import java.util.List;
import java.util.Map;

public interface TblAuctionTimesService {

    /**
     * 根据条件查询对象
     */
    TblAuctionTimes saveBidding(Map<String,Object> map);
}
