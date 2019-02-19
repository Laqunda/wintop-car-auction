package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarDataImportRecord;
import com.wintop.ms.carauction.mapper.read.ICarDataImportRecordReadDao;
import com.wintop.ms.carauction.mapper.write.ICarDataImportRecordWriteDao;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * class_name: CarDataImportRecordModel
 * package: com.wintop.ms.carauction.model
 * describe: 车辆数据导入model层
 * creat_user: lizhaoyang
 * creat_date-time: 2018/8/27/19:48
 **/
@Repository
public class CarDataImportRecordModel {
    @Resource
    private ICarDataImportRecordReadDao readDao;
    @Resource
    private ICarDataImportRecordWriteDao writeDao;
    public CarDataImportRecord selectCarDataImportRecord(Long id){
        return  readDao.selectCarDataImportRecord(id);
    }
    public Integer updateCarDataImportRecord(Long id){
        return writeDao.updateCarDataImportRecord(id);
    }
    public Integer updateCarDataImportRecordByTimeCheck(CarDataImportRecord carDataImportRecord){
        return writeDao.updateCarDataImportRecordByTimeCheck(carDataImportRecord);
    }
    public Integer insertCarDataImportRecord(CarDataImportRecord carDataImportRecord){
        return writeDao.insertCarDataImportRecord(carDataImportRecord);
    }
}
