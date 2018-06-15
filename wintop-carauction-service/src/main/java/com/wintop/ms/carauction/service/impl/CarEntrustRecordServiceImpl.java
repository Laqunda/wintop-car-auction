package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.entity.CarEntrustRecord;
import com.wintop.ms.carauction.model.CarEntrustRecordModel;
import com.wintop.ms.carauction.service.ICarEntrustRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * class_name: CarEntrustRecordServiceImpl
 * package: com.wintop.ms.carauction.service.impl
 * describe: 查询委托价设置记录
 * creat_user: lizhaoyang
 * creat_date-time: 2018/3/23/14:10
 **/
@Service("carEntrustRecordService")
public class CarEntrustRecordServiceImpl implements ICarEntrustRecordService{

    @Resource
    private CarEntrustRecordModel carEntrustRecordModel;
    private static final Logger logger = LoggerFactory.getLogger(CarEntrustRecordServiceImpl.class);

    /**
     * 查询委托价设置记录列表
     */
    @Override
    public List<CarEntrustRecord> queryCarEntrustRecordList(Map<String, Object> map) {
        List<CarEntrustRecord> list=carEntrustRecordModel.queryCarEntrustRecordList(map);
        return list;
    }

    /**
     * 查询委托记录数量
     */
    @Override
    public Integer selectEntrustRecordCount(Map<String, Object> map) {
        return carEntrustRecordModel.selectEntrustRecordCount(map);
    }
}
