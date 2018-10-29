package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.CarDataExcel;
import com.wintop.ms.carauction.entity.ListEntity;

import java.util.List;
import java.util.Map;

/**
 * class_name: ICarDataWriteDao
 * package: com.wintop.ms.carauction.mapper.write
 * describe: 车辆信息持久层接口
 * creat_user: lizhaoyang
 * creat_date-time: 2018/8/27/17:42
 **/
public interface ICarDataWriteDao {
    //批量存储车辆数据信息
    Integer insertCarDataList(List<CarDataExcel> list);

    Integer insertCarAutoDataList(Map<String,Object> paramMap);

    Integer insertCarAutoAuctionDataList(List<CarDataExcel> list);

    Integer insertCarAutoInfoDetailDataList(List<CarDataExcel> list);

    Integer insertCarAutoProceduresDataList(List<CarDataExcel> list);

    Integer insertCarAutoPhoto(Integer id);

    Integer updateCarStoreName();

    Integer updateColor(Integer id);

    Integer updateUseNature(Integer id);

    Integer updateMainPhoto(Integer id);

    Integer deleteCarDataById(List<Long> idList);

    Integer deleteCarAutoById(List<Long> idList);

    Integer deleteCarAutoAuctionById(List<Long> idList);

    Integer deleteCarAutoInfoDetailById(List<Long> idList);

    Integer deleteCarAutoProceduresById(List<Long> idList);

    Integer deleteCarAutoPhotoById(List<Long> idList);

    Integer deleteCarLocaleAuctionCarById(Long auctionId);
}