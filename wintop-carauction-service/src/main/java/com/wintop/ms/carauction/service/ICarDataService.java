package com.wintop.ms.carauction.service;

import com.wintop.ms.carauction.entity.CarDataExcel;

import java.util.List;

/**
 * class_name: ICarDataService
 * package: com.wintop.ms.carauction.service
 * describe: 车辆信息逻辑处理层
 * creat_user: lizhaoyang
 * creat_date-time: 2018/8/27/17:46
 **/
public interface ICarDataService {
    Integer insertCarDataList(List<CarDataExcel> list,Long managerId,Long auctionId);
}
