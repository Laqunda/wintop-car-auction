package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarBidRecord;
import com.wintop.ms.carauction.mapper.read.ICarBidRecordReadDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * class_name: CarBidRecordModel
 * package: com.wintop.ms.carauction.model
 * describe: 车辆出价记录模型
 * creat_user: lizhaoyang
 * creat_date-time: 2018/3/24/18:58
 **/
@Repository
public class CarBidRecordModel {
    @Autowired
    private ICarBidRecordReadDao readDao;
    /**
     * 查询委托记录列表
     */
    public List<CarBidRecord> queryCarBidRecordList(Map<String,Object> map){
        return  readDao.queryCarBidRecordList(map);
    }

    /**
     * 查询委托记录数量
     */
    public Integer selectCarBidRecordCount(Map<String,Object> map){
        return readDao.selectCarBidRecordCount(map);
    }
}
