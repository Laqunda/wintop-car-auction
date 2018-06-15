package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.entity.DepositDetail;

import java.util.List;
import java.util.Map;

/**
 * class_name: IDepositDetailService
 * package: com.wintop.ms.carauction.service
 * describe: 查询保证金明细列表
 * creat_user: lizhaoyang
 * creat_date-time: 2018/3/26/18:17
 **/
public interface IDepositDetailService {
    /**
     * 查询保证金缴纳明细细记录列表
     */
    List<DepositDetail> queryDepositDetailList(Map<String,Object> map);
    /**
     * 查询保证金缴纳明细记录的数量
     */
    Integer selectDepositDetailCount(Map<String,Object> map);
}
