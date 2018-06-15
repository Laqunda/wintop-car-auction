package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarAutoTransfer;
import com.wintop.ms.carauction.entity.Criteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ICarAutoTransferReadDao {
    /**
     */
    int countByExample(Criteria example);

    /**
     */
    List<CarAutoTransfer> selectByExample(Criteria example);

    /**
     */
    CarAutoTransfer selectByPrimaryKey(Long id);

    /**
     * 根据order_id和auto_id查询有关数据
     */
    CarAutoTransfer selectByOrderId(@Param("orderId") Long orderId,@Param("autoId") Long autoId);
    /**
     * 根据条件查询过户流程信息
     */
    List<CarAutoTransfer> selectByParam(Map<String,Object> map);
    int selectCount(Map<String,Object> map);

}