package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarAutoTransfer;
import com.wintop.ms.carauction.entity.Criteria;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ICarAutoTransferWriteDao {

    /**
     */
    int deleteByPrimaryKey(Long id);

    /**
     */
    int insert(CarAutoTransfer record);

    /**
     */
    int updateByPrimaryKeySelective(CarAutoTransfer record);

    /**
     */
    int updateByPrimaryKey(CarAutoTransfer record);

    /**
     * 根据orderId更新
     * @param autoTransfer
     * @return
     */
    int updateByOrderId(CarAutoTransfer autoTransfer);

}