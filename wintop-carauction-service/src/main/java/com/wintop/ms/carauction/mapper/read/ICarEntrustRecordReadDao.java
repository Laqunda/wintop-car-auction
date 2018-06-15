package com.wintop.ms.carauction.mapper.read;
import com.wintop.ms.carauction.entity.CarEntrustRecord;

import java.util.List;
import java.util.Map;

/**
 * class_name: ICarEntrustRecordReadDao
 * package: com.wintop.ms.carauction.mapper.read
 * describe: 查询委托价设置记录
 * creat_user: lizhaoyang
 * creat_date-time: 2018/3/23/10:50
 **/
public interface ICarEntrustRecordReadDao {
    /**
     * 查询委托价设置记录列表
     */
    List<CarEntrustRecord> queryCarEntrustRecordList(Map<String,Object> map);
    /**
     * 查询委托记录的数量
     */
    Integer selectEntrustRecordCount(Map<String,Object> map);
}
