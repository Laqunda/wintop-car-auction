package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarLocaleBoardRecord;
import com.wintop.ms.carauction.mapper.read.CarLocaleBoardRecordReadDao;
import com.wintop.ms.carauction.mapper.write.CarLocaleBoardRecordWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CarLocaleBoardRecordModel {
    @Autowired
    private CarLocaleBoardRecordReadDao recordReadDao;
    @Autowired
    private CarLocaleBoardRecordWriteDao recordWriteDao;

    /**
     * 根据条件查询记录总数
     */
    public int countByExample(Map<String,Object> map){
        return recordReadDao.countByExample(map);
    }

    /**
     * 根据条件查询记录集
     */
    public List<CarLocaleBoardRecord> selectByExample(Map<String,Object> map){
        return recordReadDao.selectByExample(map);
    }

    /**
     * 根据场次主键删除记录
     */
    public int deleteByAuctionId(Long localAuctionId){
        return recordWriteDao.deleteByAuctionId(localAuctionId);
    }

    /**
     * 保存记录,
     */
    public int saveLocaleBoardRecord(CarLocaleBoardRecord localeBoardRecord){
        return recordWriteDao.saveLocaleBoardRecord(localeBoardRecord);
    }

    /**
     * 批量保存记录,
     */
    public int saveBatchLocaleBoardRecord(List<CarLocaleBoardRecord> localeBoardRecords){
        return recordWriteDao.saveBatchLocaleBoardRecord(localeBoardRecords);
    }
}
