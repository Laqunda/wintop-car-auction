package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.entity.CarCustomerDepositLog;
import com.wintop.ms.carauction.entity.DepositFreeze;

import java.util.List;
import java.util.Map;

/**
 * class_name: ICarCustomerDepositLogService
 * package: com.wintop.ms.carauction.service
 * describe: 保证金记录
 * creat_user: lizhaoyang
 * creat_date-time: 2018/3/27/20:00
 **/
public interface ICarCustomerDepositLogService {
    /**
     * 根据主键查询记录
     */
    CarCustomerDepositLog selectByPrimaryKey(Long id);
    /**
     * 查询保证金冻结记录列表
     */
    List<DepositFreeze> queryDepositFreezeList(Map<String,Object> map);
    /**
     * 查询保证金冻结记录的数量
     */
    Integer selectDepositFreezeCount(Map<String,Object> map);
}
