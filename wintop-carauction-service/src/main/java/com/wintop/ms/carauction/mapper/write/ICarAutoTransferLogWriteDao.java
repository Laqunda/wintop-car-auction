package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarAutoTransferLog;
import com.wintop.ms.carauction.entity.Criteria;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ICarAutoTransferLogWriteDao {

    int deleteByPrimaryKey(Long id);

    int insert(CarAutoTransferLog record);


}