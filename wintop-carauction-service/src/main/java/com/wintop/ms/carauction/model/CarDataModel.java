package com.wintop.ms.carauction.model;

import com.wintop.ms.carauction.entity.CarDataExcel;
import com.wintop.ms.carauction.mapper.write.ICarDataWriteDao;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * class_name: CarDataModel
 * package: com.wintop.ms.carauction.model
 * describe: 车辆信息模型
 * creat_user: lizhaoyang
 * creat_date-time: 2018/8/27/17:44
 **/
@Repository
public class CarDataModel {
    @Resource
    private ICarDataWriteDao writeDao;

    public Integer insertCarDataList(List<CarDataExcel> list){
        return  writeDao.insertCarDataList(list);
    }

    public Integer insertCarAutoDataList(Map<String,Object> paramMap){
        return writeDao.insertCarAutoDataList(paramMap);
    };

    public Integer insertCarAutoAuctionDataList(List<CarDataExcel> list){
        return writeDao.insertCarAutoAuctionDataList(list);
    };

    public Integer insertCarAutoInfoDetailDataList(List<CarDataExcel> list){
        return writeDao.insertCarAutoInfoDetailDataList(list);
    };

    public Integer insertCarAutoProceduresDataList(List<CarDataExcel> list){
        return writeDao.insertCarAutoProceduresDataList(list);
    };

    public Integer updateCarStoreName(){
        return writeDao.updateCarStoreName();
    };

    public Integer updateColor(Integer id){
        return writeDao.updateColor(id);
    };

    public Integer updateUseNature(Integer id){
        return writeDao.updateUseNature(id);
    };

}
