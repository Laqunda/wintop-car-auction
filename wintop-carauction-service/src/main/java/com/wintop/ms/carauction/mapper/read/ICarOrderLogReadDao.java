package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarOrderLog;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ICarOrderLogReadDao {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Map<String,Object> map);

    /**
     * 根据条件查询记录集
     */
    List<CarOrderLog> selectByExample(Map<String,Object> map);

    /**
     * 根据主键查询记录
     */
    CarOrderLog selectById(Long id);

}