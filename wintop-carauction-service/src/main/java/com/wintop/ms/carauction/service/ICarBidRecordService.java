package com.wintop.ms.carauction.service;


import com.wintop.ms.carauction.entity.CarBidRecord;

import java.util.List;
import java.util.Map;

/**
 * class_name: ICarBidRecordService
 * package: com.wintop.ms.carauction.service
 * describe: 查询车辆出价记录
 * creat_user: lizhaoyang
 * creat_date-time: 2018/3/24/19:01
 **/
public interface ICarBidRecordService {
    /**
     * 查询车辆出价记录列表
     */
    List<CarBidRecord> queryCarBidRecordRecordList(Map<String,Object> map);

    /**
     * 查询车辆出价记录数量
     */
    Integer selectCarBidRecordCount(Map<String,Object> map);
}
