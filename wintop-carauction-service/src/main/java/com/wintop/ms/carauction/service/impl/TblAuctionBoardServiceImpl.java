package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.entity.TblAuctionBoard;
import com.wintop.ms.carauction.entity.TblBoardStation;
import com.wintop.ms.carauction.model.TblAuctionBoardModel;
import com.wintop.ms.carauction.service.TblAuctionBoardService;
import com.wintop.ms.carauction.util.utils.IdWorker;
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
        List<TblAuctionBoard> boards = tblAuctionBoardModel.selectByExample(map);
        for(TblAuctionBoard board:boards){
            List<TblBoardStation> stations = tblAuctionBoardModel.selectStationListByBoardRealId(board.getBoardRealId());
            board.setBaseStations(stations);
        }
        return boards;
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
        String[] stationRealIds = tblAuctionBoard.getStationRealIds().split(",");
        tblAuctionBoardModel.deleteBoardStation(tblAuctionBoard.getBoardRealId());
        for(String stationRealId:stationRealIds){
            TblBoardStation boardStation = new TblBoardStation();
            boardStation.setId(IdWorker.getInstance().nextId());
            boardStation.setBoardRealId(tblAuctionBoard.getBoardRealId());
            boardStation.setStationRealId(stationRealId);
            tblAuctionBoardModel.saveBoardStation(boardStation);
        }
        return tblAuctionBoardModel.insert(tblAuctionBoard);
    }

    @Override
    public int updateByPrimaryKeySelective(TblAuctionBoard tblAuctionBoard) {
        String[] stationRealIds = tblAuctionBoard.getStationRealIds().split(",");
        tblAuctionBoardModel.deleteBoardStation(tblAuctionBoard.getBoardRealId());
        for(String stationRealId:stationRealIds){
            TblBoardStation boardStation = new TblBoardStation();
            boardStation.setId(IdWorker.getInstance().nextId());
            boardStation.setBoardRealId(tblAuctionBoard.getBoardRealId());
            boardStation.setStationRealId(stationRealId);
            tblAuctionBoardModel.saveBoardStation(boardStation);
        }
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
     * @param boardRealId
     * @return
     */
    @Override
    public TblAuctionBoard selectByRealId(String boardRealId){
        return tblAuctionBoardModel.selectByRealId(boardRealId);
    }

    /**
     * 查询同一个拍卖场是否存在调价器
     * @param stationRealId
     * @param cuttingSign
     * @return
     */
    public TblAuctionBoard selectCuttingSignByBsId(String stationRealId,String cuttingSign){
        if("0".equals(cuttingSign)){
            return null;
        }
        Map<String,Object> map = new HashMap<>();
        map.put("stationRealId",stationRealId);
        map.put("cuttingSign","1");
        List<TblAuctionBoard> boards = tblAuctionBoardModel.selectByExample(map);
        return boards.size()>0?boards.get(0):null;
    }

}
