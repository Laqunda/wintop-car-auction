package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.entity.CarLocaleBoardRecord;
import com.wintop.ms.carauction.model.CarLocaleBoardRecordModel;
import com.wintop.ms.carauction.service.CarLocaleBoardRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CarLocaleBoardRecordServiceImpl implements CarLocaleBoardRecordService {
    @Autowired
    private CarLocaleBoardRecordModel recordModel;
    @Override
    public int countByExample(Map<String, Object> map) {
        return recordModel.countByExample(map);
    }

    @Override
    public List<CarLocaleBoardRecord> selectByExample(Map<String, Object> map) {
        return recordModel.selectByExample(map);
    }

    @Override
    public int deleteByAuctionId(Long localAuctionId) {
        return recordModel.deleteByAuctionId(localAuctionId);
    }

    @Override
    public int saveLocaleBoardRecord(CarLocaleBoardRecord localeBoardRecord) {
        return recordModel.saveLocaleBoardRecord(localeBoardRecord);
    }

    /**
     * 批量保存记录,
     */
    public int saveBatchLocaleBoardRecord(List<CarLocaleBoardRecord> localeBoardRecords){
        return recordModel.saveBatchLocaleBoardRecord(localeBoardRecords);
    }
}
