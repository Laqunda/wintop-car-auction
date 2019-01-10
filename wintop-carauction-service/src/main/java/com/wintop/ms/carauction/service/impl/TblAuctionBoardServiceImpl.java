package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.entity.TblAuctionBoard;
import com.wintop.ms.carauction.model.TblAuctionBoardModel;
import com.wintop.ms.carauction.service.TblAuctionBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
    public TblAuctionBoard selectByPrimaryKey(Long id) {
        return tblAuctionBoardModel.selectByPrimaryKey(id);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
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

    /**
     * 逻辑删除拍牌
     * @param tblAuctionBoard
     * @return
     */
    @Override
    public int updateDeleteFlag(TblAuctionBoard tblAuctionBoard){
        return tblAuctionBoardModel.updateDeleteFlag(tblAuctionBoard);
    }

    /**
     * 根据拍牌物理ID查询
     * @param realId
     * @return
     */
    @Override
    public TblAuctionBoard selectByRealId(String realId){
        return tblAuctionBoardModel.selectByRealId(realId);
    }

    /**
     * 查询同一个拍卖场是否存在调价器
     * @param bsId
     * @param cuttingSign
     * @return
     */
    public TblAuctionBoard selectCuttingSignByBsId(Long bsId,String cuttingSign){
        if("0".equals(cuttingSign)){
            return null;
        }
        Map<String,Object> map = new HashMap<>();
        map.put("bsId",bsId);
        map.put("cuttingSign","1");
        List<TblAuctionBoard> boards = tblAuctionBoardModel.selectByExample(map);
        return boards.size()>0?boards.get(0):null;
    }
}
