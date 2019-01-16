package com.wintop.ms.carauction.mapper.read;

import java.util.List;
import java.util.Map;

import com.wintop.ms.carauction.entity.CarLocaleBoardRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface CarLocaleBoardRecordReadDao {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    List<CarLocaleBoardRecord> selectByExample(Map<String,Object> map);

}