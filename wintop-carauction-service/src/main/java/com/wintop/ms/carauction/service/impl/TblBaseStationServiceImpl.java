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
    public TblBaseStation selectByPrimaryKey(Integer id) {
        return tblBaseStationModel.selectByPrimaryKey(id);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
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
}
