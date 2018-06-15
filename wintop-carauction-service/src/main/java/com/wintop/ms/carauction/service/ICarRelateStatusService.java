package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarRelateStatus;

import java.util.List;
import java.util.Map;

/**
 * @author zhangzijuan
 * @Description:
 * @date 2018-03-26
 */
public interface ICarRelateStatusService {
    /**
     * 根据条件类型查询所有的状态
     *@Author:zhangzijuan
     *@date 2018/3/26
     *@param:
     */
    ServiceResult<List<CarRelateStatus>> selectAllStatus(Map<String,Object> map);
}
