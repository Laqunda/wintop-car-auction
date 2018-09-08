package com.wintop.ms.carauction.mapper.read;

import com.wintop.ms.carauction.entity.CarDataImportRecord;

/**
 * class_name: ICarDataImportRecordReadDao
 * package: com.wintop.ms.carauction.mapper.read
 * describe: 车辆数据导入的id记录表持久层
 * creat_user: lizhaoyang
 * creat_date-time: 2018/8/27/18:58
 **/
public interface ICarDataImportRecordReadDao {
    CarDataImportRecord selectCarDataImportRecord(Long id);
}
