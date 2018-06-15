package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarAutoTransferLog;
import com.wintop.ms.carauction.entity.Criteria;

import java.util.List;
import java.util.Map;

public interface ICarAutoTransferLogReadDao {
    /**
     *
     */
    int countByExample(Map<String,Object> map);

    /**
     *
     */
    List<CarAutoTransferLog> selectByExample(Map<String,Object> map);

    /**
     *
     */
    CarAutoTransferLog selectByPrimaryKey(Long id);

    /**
     * 当前订单过户流程标记
     * @param map
     * @return
     */
    List<CarAutoTransferLog> queryTransferFlowList(Map<String,Object> map);

    /**
     * 查询过户轨迹
     * @param map
     * @return
     */
    List<CarAutoTransferLog> queryTransferList(Map<String,Object> map);

    /**
     * 查询订单手续信息
     * @param orderId
     * @return
     */
    List<CarAutoTransferLog> selectTransferLogByOrderId(Long orderId);
}