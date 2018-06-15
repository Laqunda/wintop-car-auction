package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarAgentLog;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ICarAgentLogReadDao {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    List<CarAgentLog> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    CarAgentLog selectById(Long id);
}