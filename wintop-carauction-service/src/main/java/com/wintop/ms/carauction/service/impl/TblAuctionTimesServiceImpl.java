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
    @Override
    public int countByExample(Map<String, Object> map) {
        return tblAuctionTimesModel.countByExample(map);
    }

    @Override
    public List<TblAuctionTimes> selectByExample(Map<String, Object> map) {
        return tblAuctionTimesModel.selectByExample(map);
    }

    @Override
    public TblAuctionTimes selectByPrimaryKey(Integer id) {
        return tblAuctionTimesModel.selectByPrimaryKey(id);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return tblAuctionTimesModel.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TblAuctionTimes tblAuctionTimes) {
        return tblAuctionTimesModel.insert(tblAuctionTimes);
    }

    @Override
    public int updateByPrimaryKeySelective(TblAuctionTimes tblAuctionTimes) {
        return tblAuctionTimesModel.updateByPrimaryKeySelective(tblAuctionTimes);
    }

    /**
     * 根据条件查询对象
     */
    @Override
    @Transactional
    public TblAuctionTimes saveBidding(Map<String,Object> map){
        TblAuctionTimes auctionTimes = tblAuctionTimesModel.selectByParam(map);
        if(auctionTimes!=null){
            TblAuctionLog auctionLog = new TblAuctionLog();
            auctionLog.setId(IdWorker.getInstance().nextId());
            auctionLog.setStationRealCode(auctionTimes.getStationRealCode());
            auctionLog.setBoardRealId((String)map.get("pp"));
            auctionLog.setToken((String)map.get("mm"));
            auctionLog.setLocalAuctionId(auctionTimes.getLocalAuctionId());
            //TODO,读取当前拍卖车辆
            auctionLog.setAuctionCarId(null);
            auctionLog.setBidFee(auctionTimes.getInitPrice());
            auctionLog.setAuctionTimesId(auctionTimes.getId());
            auctionLog.setAuctionTimesName(auctionTimes.getAuctionTimesName());
            auctionLog.setBsId(auctionTimes.getBsId());
            auctionLog.setBoardRealName(auctionTimes.getBoardRealName());
            auctionLog.setAuctionTime(new Date());
            auctionLog.setCuttingSign(auctionTimes.getCuttingSign());
            tblAuctionLogModel.insert(auctionLog);
        }
        return auctionTimes;
    }
}
