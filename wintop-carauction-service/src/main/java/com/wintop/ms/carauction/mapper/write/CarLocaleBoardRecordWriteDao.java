package com.wintop.ms.carauction.mapper.write;

import java.util.List;

import com.wintop.ms.carauction.entity.CarLocaleBoardRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarLocaleBoardRecordWriteDao {

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
    int saveBatchLocaleBoardRecord(@Param("localeBoardRecords") List<CarLocaleBoardRecord> localeBoardRecords);
}