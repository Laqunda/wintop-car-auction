package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.entity.CarEntrustRecord;

import java.util.List;
import java.util.Map;

/**
 * class_name: ICarEntrustRecordService
 * package: com.wintop.ms.carauction.service
 * describe: 查询委托价设置记录
 * creat_user: lizhaoyang
 * creat_date-time: 2018/3/23/14:09
 **/
public interface ICarEntrustRecordService {
    /**
     * 查询委托记录列表
     */
    List<CarEntrustRecord> queryCarEntrustRecordList(Map<String,Object> map);

    /**
     * 查询委托记录数量
     */
    Integer selectEntrustRecordCount(Map<String,Object> map);
}
