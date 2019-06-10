package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAutoLog;
import com.wintop.ms.carauction.entity.Criteria;
import java.util.List;
import java.util.Map;

public interface ICarAutoLogService {
    ServiceResult<Integer> countByExample(Criteria example);

    ServiceResult<CarAutoLog> selectByPrimaryKey(Long id);

    ServiceResult<List<CarAutoLog>> selectByExample(Criteria example);


    ServiceResult<Integer> insert(CarAutoLog record);


    /**
     * 查询车辆轨迹
     *@Author:zhangzijuan
     *@date 2018/3/23
     *@param:
     */
     List<CarAutoLog> selectCarLogByCarId(Map<String,Object> map);

     CarAutoLog selectCarLog(Map<String,Object> map);

    int selectCountEndByUserId(Long userId);

    List<CarAutoLog> selectWaitOrderList(Map<String,Object> map);

    List<CarAutoLog> selectEndOrderList(Map<String,Object> map);
}