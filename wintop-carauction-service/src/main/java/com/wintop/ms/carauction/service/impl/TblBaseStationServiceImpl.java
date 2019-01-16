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
    private TblBaseStationModel tblBaseStationModel;
    @Override
    public int countByExample(Map<String, Object> map) {
        return tblBaseStationModel.countByExample(map);
    }

    @Override
    public List<TblBaseStation> selectByExample(Map<String, Object> map) {
        return tblBaseStationModel.selectByExample(map);
    }

    @Override
    public TblBaseStation selectByPrimaryKey(Long id) {
        return tblBaseStationModel.selectByPrimaryKey(id);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return tblBaseStationModel.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TblBaseStation tblBaseStation) {
        return tblBaseStationModel.insert(tblBaseStation);
    }

    @Override
    public int updateByPrimaryKeySelective(TblBaseStation tblBaseStation) {
        return tblBaseStationModel.updateByPrimaryKeySelective(tblBaseStation);
    }

    /**
     * 根据物理ID查询记录
     */
    @Override
    public TblBaseStation selectByRealId(String stationRealId){
        return tblBaseStationModel.selectByRealId(stationRealId);
    }

    /**
     * 逻辑删除基站
     * @param tblBaseStation
     * @return
     */
    @Override
    public int updateDeleteFlag(TblBaseStation tblBaseStation){
        return tblBaseStationModel.updateDeleteFlag(tblBaseStation);
    }
}
