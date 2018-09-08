package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarPhotoTemp;

import java.util.List;

/**
 * class_name: ICarPhotoTempWriteDao
 * package: com.wintop.ms.carauction.mapper.write
 * creat_user: lizhaoyang
 * creat_date-time: 2018/9/8/11:28
 **/
public interface ICarPhotoTempWriteDao {
    Integer insertCarPhotoTemp(List<CarPhotoTemp> list);
    Integer clearCarPhotoTemp();
}
