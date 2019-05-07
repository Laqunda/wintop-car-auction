package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.entity.TblBaseStation;
import com.wintop.ms.carauction.model.TblBaseStationModel;
import com.wintop.ms.carauction.service.TblBaseStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TblBaseStationServiceImpl implements TblBaseStationService {
    @Autowired
    private TblBaseStationModel baseStationModel;
    @Override
    public int countByExample(Map<String, Object> map) {
        return baseStationModel.countByExample(map);
    }

    @Override
    public List<TblBaseStation> selectByExample(Map<String, Object> map) {
        return baseStationModel.selectByExample(map);
    }

    @Override
    public TblBaseStation selectByPrimaryKey(Long id) {
        return baseStationModel.selectByPrimaryKey(id);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return baseStationModel.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TblBaseStation tblBaseStation) {
        return baseStationModel.insert(tblBaseStation);
    }

    @Override
    public int updateByPrimaryKeySelective(TblBaseStation tblBaseStation) {
        return baseStationModel.updateByPrimaryKeySelective(tblBaseStation);
    }

    /**
     * 根据物理ID查询记录
     */
    @Override
    public TblBaseStation selectByRealId(String stationRealId){
        return baseStationModel.selectByRealId(stationRealId);
    }

    /**
     * 根据物理ID和token查询记录
     */
    @Override
    public TblBaseStation selectByRealIdAndToken(String stationRealId,String token){
        return baseStationModel.selectByRealIdAndToken(stationRealId, token);
    }

    /**
     * 逻辑删除基站
     * @param tblBaseStation
     * @return
     */
    @Override
    public int updateDeleteFlag(TblBaseStation tblBaseStation){
        return baseStationModel.updateDeleteFlag(tblBaseStation);
    }

    /**
     * 查询所有基站
     * @return
     */
    @Override
    public List<TblBaseStation> selectAllStationList(){
        return baseStationModel.selectAllStationList();
    }
}