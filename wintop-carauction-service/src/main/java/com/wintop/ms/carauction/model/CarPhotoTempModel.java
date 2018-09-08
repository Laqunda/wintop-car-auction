package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarPhotoTemp;
import com.wintop.ms.carauction.mapper.write.ICarPhotoTempWriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * class_name: CarPhotoTempModel
 * package: com.wintop.ms.carauction.model
 * describe: 图片临时表Model层
 * creat_user: lizhaoyang
 * creat_date-time: 2018/9/8/13:47
 **/
@Repository
public class CarPhotoTempModel {
    @Autowired
    private ICarPhotoTempWriteDao writeDao;

    public Integer insertCarPhotoTemp(List<CarPhotoTemp> list){
        return writeDao.insertCarPhotoTemp(list);
    };
    public Integer clearCarPhotoTemp(){
        return writeDao.clearCarPhotoTemp();
    };
}
