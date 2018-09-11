package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarDataImportRecord;

/**
 * class_name: ICarDataImportRecordWriteDao
 * package: com.wintop.ms.carauction.mapper.write
 * describe: 车辆数据导入的id记录表持久层
 * creat_user: lizhaoyang
 * creat_date-time: 2018/8/27/18:58
 **/
public interface ICarDataImportRecordWriteDao {
    Integer updateCarDataImportRecord(Long id);
    Integer updateCarDataImportRecordByTimeCheck(CarDataImportRecord carDataImportRecord);
    Integer insertCarDataImportRecord(CarDataImportRecord carDataImportRecord);
}
