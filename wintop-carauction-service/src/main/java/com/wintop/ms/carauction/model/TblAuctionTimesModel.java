package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.TblAuctionTimes;
import com.wintop.ms.carauction.mapper.read.TblAuctionTimesReadDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class TblAuctionTimesModel {
    @Autowired
    private TblAuctionTimesReadDao tblAuctionTimesReadDao;

    /**
     * 根据条件查询对象
     */
    public TblAuctionTimes selectAuctionCar(Map<String,Object> map){
        return tblAuctionTimesReadDao.selectAuctionCar(map);
    }
}
