package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarBidRecord;

import java.util.List;
import java.util.Map;

/**
 * class_name: ICarBidRecordReadDao
 * package: com.wintop.ms.carauction.mapper.read
 * describe: 查询车辆出价记录
 * creat_user: lizhaoyang
 * creat_date-time: 2018/3/24/18:44
 **/
public interface ICarBidRecordReadDao {
    /**
     * 查询车辆出价记录列表
     */
    List<CarBidRecord> queryCarBidRecordList(Map<String,Object> map);
    /**
     * 查询车辆出价记录的数量
     */
    Integer selectCarBidRecordCount(Map<String,Object> map);
}
