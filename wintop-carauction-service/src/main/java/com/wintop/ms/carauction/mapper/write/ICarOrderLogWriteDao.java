package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarOrderLog;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ICarOrderLogWriteDao {

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(CarOrderLog carOrderLog);

}