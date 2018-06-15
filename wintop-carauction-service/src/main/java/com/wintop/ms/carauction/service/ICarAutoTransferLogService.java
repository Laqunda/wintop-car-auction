package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAutoTransferLog;
import com.wintop.ms.carauction.entity.Criteria;
import java.util.List;
import java.util.Map;

public interface ICarAutoTransferLogService {

    ServiceResult<Integer> countByExample(Map<String,Object> map);

    ServiceResult<CarAutoTransferLog> selectByPrimaryKey(Long id);

    ServiceResult<List<CarAutoTransferLog>> selectByExample(Map<String,Object> map);

    ServiceResult<Integer> deleteByPrimaryKey(Long id);


    ServiceResult<Integer> insert(CarAutoTransferLog record);

    ServiceResult<Integer> insertSelective(CarAutoTransferLog record);

    /**
     * 当前订单过户流程标记
     * @param map
     * @return
     */
    public List<CarAutoTransferLog> queryTransferFlowList(Map<String,Object> map);
        /**
         * 查询过户轨迹
         *@Author:zhangzijuan
         *@date 2018/3/26
         *@param:
         */
     ServiceResult<List<CarAutoTransferLog>> queryTransferList(Map<String,Object> map);

    /**
     * 查询订单手续信息
     * @param orderId
     * @return
     */
    List<CarAutoTransferLog> selectTransferLogByOrderId(Long orderId);
}