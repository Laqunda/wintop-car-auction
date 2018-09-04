package com.wintop.ms.carauction.service.impl;

import com.wintop.core.util.IdWorker;
import com.wintop.ms.carauction.entity.CarDataExcel;
import com.wintop.ms.carauction.entity.CarLocaleAuction;
import com.wintop.ms.carauction.entity.CarLocaleAuctionCar;
import com.wintop.ms.carauction.model.CarDataImportRecordModel;
import com.wintop.ms.carauction.model.CarDataModel;
import com.wintop.ms.carauction.model.CarLocaleAuctionCarModel;
import com.wintop.ms.carauction.model.CarLocaleAuctionModel;
import com.wintop.ms.carauction.service.ICarDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * class_name: CarDataServiceImpl
 * package: com.wintop.ms.carauction.service.impl
 * describe: 车辆信息逻辑层实现
 * creat_user: lizhaoyang
 * creat_date-time: 2018/8/27/17:47
 **/
@Service
public class CarDataServiceImpl implements ICarDataService{
    @Resource
    private CarDataModel carDataModel;
    @Resource
    private CarDataImportRecordModel carDataImportRecordModel;
    @Resource
    private CarLocaleAuctionModel carLocaleAuctionModel;
    @Resource
    private CarLocaleAuctionCarModel carLocaleAuctionCarModel;
    private static final Logger logger = LoggerFactory.getLogger(CarDataServiceImpl.class);
    @Transactional
    @Override
    public Integer insertCarDataList(List<CarDataExcel> list,Long managerId,Long auctionId) throws RuntimeException{
        Long startTime=System.currentTimeMillis();
        Integer id=carDataImportRecordModel.selectCarDataImportRecord(1);
        List<CarLocaleAuctionCar> carLocaleAuctionCars=new ArrayList<CarLocaleAuctionCar>();
        Map<String,Object> paramMap=new HashMap<String,Object>();
        Long regionId=1L;
        regionId=carLocaleAuctionModel.selectById(auctionId).getCityId();
        for (CarDataExcel carDataExcel:list){
            carDataExcel.setId(Integer.parseInt(carDataExcel.getId())+id+"");
            CarLocaleAuctionCar carLocaleAuctionCar=new CarLocaleAuctionCar();
            carLocaleAuctionCar.setId(IdWorker.getInstance().nextId());
            carLocaleAuctionCar.setAuctionId(auctionId);
            carLocaleAuctionCar.setCarId(Long.parseLong(carDataExcel.getId()));
            carLocaleAuctionCar.setAuctionCode(carDataExcel.getId().substring(carDataExcel.getId().length()-3));
            carLocaleAuctionCar.setAuctionStatus("0");
            carLocaleAuctionCar.setSort(Integer.parseInt(carLocaleAuctionCar.getAuctionCode()));
            carLocaleAuctionCar.setCreateTime(null);
            carLocaleAuctionCar.setCreatePerson(managerId);
            carLocaleAuctionCars.add(carLocaleAuctionCar);
        }
        paramMap.put("list",list);
        paramMap.put("regionId",regionId);
        Integer count=carDataImportRecordModel.updateCarDataImportRecord(1);
        Integer number=0;
        if (count>0){
            carDataModel.insertCarAutoDataList(paramMap);
            carDataModel.updateCarStoreName();
            carDataModel.insertCarAutoAuctionDataList(list);
            carDataModel.insertCarAutoInfoDetailDataList(list);
            carDataModel.insertCarAutoProceduresDataList(list);
            carDataModel.updateColor(id);
            carDataModel.updateUseNature(id);
            number=carLocaleAuctionCarModel.insertCarLocaleAuctionCarList(carLocaleAuctionCars);
        }
        Long endTime=System.currentTimeMillis()-startTime;
        System.out.println("导入持久层耗时："+endTime);
        return number;
    }
}
