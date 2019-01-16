package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.entity.TblAuctionLog;
import com.wintop.ms.carauction.entity.TblAuctionTimes;
import com.wintop.ms.carauction.model.TblAuctionLogModel;
import com.wintop.ms.carauction.model.TblAuctionTimesModel;
import com.wintop.ms.carauction.service.TblAuctionTimesService;
import com.wintop.ms.carauction.util.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class TblAuctionTimesServiceImpl implements TblAuctionTimesService {
    @Autowired
    private TblAuctionTimesModel tblAuctionTimesModel;
    @Autowired
    private TblAuctionLogModel tblAuctionLogModel;

    /**
     * 根据条件查询对象
     */
    @Override
    @Transactional
    public TblAuctionTimes saveBidding(Map<String,Object> map){
        TblAuctionTimes auctionTimes = tblAuctionTimesModel.selectAuctionCar(map);
        if(auctionTimes!=null){
            TblAuctionLog auctionLog = new TblAuctionLog();
            auctionLog.setId(IdWorker.getInstance().nextId());
            auctionLog.setBoardRealId((String)map.get("pp"));
            auctionLog.setToken((String)map.get("mm"));
            auctionLog.setStationRealId(auctionTimes.getStationRealId());
            auctionLog.setLocalAuctionId(auctionTimes.getLocalAuctionId());
            //TODO,读取当前拍卖车辆
            auctionLog.setAuctionCarId(auctionTimes.getAuctionCarId());
            auctionLog.setCarId(auctionTimes.getCarId());
            auctionLog.setBidFee(null);
            auctionLog.setAuctionTimesName(auctionTimes.getAuctionTimesName());
            auctionLog.setBoardName(auctionTimes.getBoardName());
            auctionLog.setAuctionTime(new Date());
            auctionLog.setPriceType(auctionTimes.getCuttingSign());
            auctionLog.setEnable("0");
            tblAuctionLogModel.insert(auctionLog);
        }
        return auctionTimes;
    }
}