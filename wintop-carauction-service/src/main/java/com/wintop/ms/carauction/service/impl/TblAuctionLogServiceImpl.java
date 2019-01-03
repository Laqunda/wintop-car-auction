package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.entity.TblAuctionLog;
import com.wintop.ms.carauction.model.TblAuctionLogModel;
import com.wintop.ms.carauction.service.TblAuctionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TblAuctionLogServiceImpl implements TblAuctionLogService {
    @Autowired
    private TblAuctionLogModel tblAuctionLogModel;
    @Override
    public int countByExample(Map<String, Object> map) {
        return tblAuctionLogModel.countByExample(map);
    }

    @Override
    public List<TblAuctionLog> selectByExample(Map<String, Object> map) {
        return tblAuctionLogModel.selectByExample(map);
    }

    @Override
    public TblAuctionLog selectByPrimaryKey(Integer id) {
        return tblAuctionLogModel.selectByPrimaryKey(id);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return tblAuctionLogModel.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TblAuctionLog tblAuctionLog) {
        return tblAuctionLogModel.insert(tblAuctionLog);
    }

    @Override
    public int updateByPrimaryKeySelective(TblAuctionLog tblAuctionLog) {
        return tblAuctionLogModel.updateByPrimaryKeySelective(tblAuctionLog);
    }
}
