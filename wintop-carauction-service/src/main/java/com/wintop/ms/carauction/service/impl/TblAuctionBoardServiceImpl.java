package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.entity.TblAuctionBoard;
import com.wintop.ms.carauction.model.TblAuctionBoardModel;
import com.wintop.ms.carauction.service.TblAuctionBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TblAuctionBoardServiceImpl implements TblAuctionBoardService {
    @Autowired
    private TblAuctionBoardModel tblAuctionBoardModel;

    @Override
    public int countByExample(Map<String, Object> map) {
        return tblAuctionBoardModel.countByExample(map);
    }

    @Override
    public List<TblAuctionBoard> selectByExample(Map<String, Object> map) {
        return tblAuctionBoardModel.selectByExample(map);
    }

    @Override
    public TblAuctionBoard selectByPrimaryKey(Integer id) {
        return tblAuctionBoardModel.selectByPrimaryKey(id);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return tblAuctionBoardModel.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TblAuctionBoard tblAuctionBoard) {
        return tblAuctionBoardModel.insert(tblAuctionBoard);
    }

    @Override
    public int updateByPrimaryKeySelective(TblAuctionBoard tblAuctionBoard) {
        return tblAuctionBoardModel.updateByPrimaryKeySelective(tblAuctionBoard);
    }
}
