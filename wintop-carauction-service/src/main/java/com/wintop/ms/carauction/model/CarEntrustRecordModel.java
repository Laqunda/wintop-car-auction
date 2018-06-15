package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarEntrustRecord;
import com.wintop.ms.carauction.mapper.read.ICarEntrustRecordReadDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * class_name: CarEntrustRecordModel
 * package: com.wintop.ms.carauction.model
 * describe: 查询委托价设置记录
 * creat_user: lizhaoyang
 * creat_date-time: 2018/3/23/10:54
 **/
@Repository
public class CarEntrustRecordModel {
    @Autowired
    private ICarEntrustRecordReadDao readDao;

    /**
     * 查询委托记录列表
     */
    public List<CarEntrustRecord> queryCarEntrustRecordList(Map<String,Object> map){
        return  readDao.queryCarEntrustRecordList(map);
    }

    /**
     * 查询委托记录数量
     */
    public Integer selectEntrustRecordCount(Map<String,Object> map){
        return readDao.selectEntrustRecordCount(map);
    }
}
