package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.entity.CarBidRecord;
import com.wintop.ms.carauction.model.CarBidRecordModel;
import com.wintop.ms.carauction.service.ICarBidRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * class_name: CarBidRecordServiceImpl
 * package: com.wintop.ms.carauction.service.impl
 * describe: 车辆出价记录
 * creat_user: lizhaoyang
 * creat_date-time: 2018/3/24/19:03
 **/
@Service("carBidRecordService")
public class CarBidRecordServiceImpl implements ICarBidRecordService{
    @Resource
    private CarBidRecordModel carBidRecordModel;
    private static final Logger logger = LoggerFactory.getLogger(CarBidRecordServiceImpl.class);

    /**
     * 查询车辆出价记录列表
     */
    @Override
    public List<CarBidRecord> queryCarBidRecordRecordList(Map<String, Object> map) {
        List<CarBidRecord> list=carBidRecordModel.queryCarBidRecordList(map);
        return list;
    }

    /**
     * 查询车辆出价记录数量
     */
    @Override
    public Integer selectCarBidRecordCount(Map<String, Object> map) {
        return carBidRecordModel.selectCarBidRecordCount(map);
    }
}
