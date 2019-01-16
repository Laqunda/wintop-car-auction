package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.entity.CarLocaleBoardRecord;

import java.util.List;
import java.util.Map;

public interface CarLocaleBoardRecordService {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    List<CarLocaleBoardRecord> selectByExample(Map<String,Object> map);

    /**
     * 根据场次主键删除记录
     */
    int deleteByAuctionId(Long localAuctionId);

    /**
     * 保存记录,
     */
    int saveLocaleBoardRecord(CarLocaleBoardRecord localeBoardRecord);

    /**
     * 批量保存记录,
     */
    int saveBatchLocaleBoardRecord(List<CarLocaleBoardRecord> localeBoardRecords);
}
