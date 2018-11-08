package com.wintop.ms.carauction.service.impl;

import com.wintop.core.util.IdWorker;
import com.wintop.ms.carauction.entity.*;
import com.wintop.ms.carauction.model.*;
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
    @Resource
    private CarPhotoTempModel carPhotoTempModel;
    private static final Logger logger = LoggerFactory.getLogger(CarDataServiceImpl.class);
    @Transactional
    @Override
    public Integer insertCarDataList(List<CarDataExcel> list,Long managerId,Long auctionId) throws RuntimeException{
        //用来计算导入持久层的时间
        Long startTime=System.currentTimeMillis();
        CarDataImportRecord carDataImportRecord=carDataImportRecordModel.selectCarDataImportRecord(auctionId);
        Integer idRecord=0;
        if(carDataImportRecord==null){
            return -1;
        }else {
            idRecord=carDataImportRecord.getIdRecord();
        }
        //用来判断是否第一次为某场次导入车辆信息,默认为true第一次
        Boolean checkFlag=true;
        //根据auctionId获取所有的场次拍卖车辆，
        // 如果为空或者0，则直接添加新的数据
        // 否则，先清空以前的
        List<CarLocaleAuctionCar> checkList=carLocaleAuctionCarModel.getAuctionCarList(auctionId);
        //存放当前拍卖场次车辆的Id集合
        List<Long> idList=new ArrayList<Long>();
        if (checkList!=null&&checkList.size()!=0){
            checkFlag=false;
            for (CarLocaleAuctionCar carLocaleAuctionCar:checkList){
                idList.add(carLocaleAuctionCar.getCarId());
            }
        }
        List<CarLocaleAuctionCar> carLocaleAuctionCars=new ArrayList<CarLocaleAuctionCar>();
        Map<String,Object> paramMap=new HashMap<String,Object>();
        Long regionId=1L;
        regionId=carLocaleAuctionModel.selectById(auctionId).getCityId();
        for (CarDataExcel carDataExcel:list){
            carDataExcel.setId(Integer.parseInt(carDataExcel.getId())+idRecord+"");
            CarLocaleAuctionCar carLocaleAuctionCar=new CarLocaleAuctionCar();
            carLocaleAuctionCar.setId(IdWorker.getInstance().nextId());
            carLocaleAuctionCar.setAuctionId(auctionId);
            carLocaleAuctionCar.setCarId(Long.parseLong(carDataExcel.getId()));
            carLocaleAuctionCar.setAutoAuctionId(Long.parseLong(carDataExcel.getId()));
            carLocaleAuctionCar.setAuctionCode(carDataExcel.getId().substring(carDataExcel.getId().length()-3));
            carLocaleAuctionCar.setAuctionStatus("0");
            carLocaleAuctionCar.setSort(Integer.parseInt(carLocaleAuctionCar.getAuctionCode()));
            carLocaleAuctionCar.setCreateTime(null);
            carLocaleAuctionCar.setCreatePerson(managerId);
            carLocaleAuctionCars.add(carLocaleAuctionCar);
        }
        paramMap.put("list",list);
        paramMap.put("regionId",regionId);
        if(!checkFlag){
            System.out.println("开始清空之前的拍卖车辆信息.........."+idRecord);
            carDataModel.deleteCarAutoById(idRecord);
            carDataModel.deleteCarAutoAuctionById(idRecord);
            carDataModel.deleteCarAutoInfoDetailById(idRecord);
            carDataModel.deleteCarAutoProceduresById(idRecord);
            carDataModel.deleteCarAutoPhotoById(idRecord);
            carDataModel.deleteCarLocaleAuctionCarById(auctionId);
        }
        Integer number=0;
            carDataModel.insertCarAutoDataList(paramMap);
            carDataModel.updateCarStoreName();
            carDataModel.insertCarAutoAuctionDataList(list);
            carDataModel.insertCarAutoInfoDetailDataList(list);
            carDataModel.insertCarAutoProceduresDataList(list);
            carDataModel.updateColor(idRecord);
            carDataModel.updateUseNature(idRecord);
            carDataModel.updateMainPhoto(idRecord);
            carDataModel.insertCarAutoPhoto(idRecord);
            number=carLocaleAuctionCarModel.insertCarLocaleAuctionCarList(carLocaleAuctionCars);
        Long endTime=System.currentTimeMillis()-startTime;
        System.out.println("导入持久层耗时："+endTime);
        return number;
    }
    @Transactional
    @Override
    public Integer insertCarPhoto(List<CarPhotoTemp> list, Long auctionId,Long timeCheck) throws RuntimeException{
        //首先根据auctionId查询是否有对应的导入记录
        CarDataImportRecord carDataImportRecord=carDataImportRecordModel.selectCarDataImportRecord(auctionId);
        Integer id=0;
        //如果没有，获取当前id为1的记录，以此为新的id新增导入记录
        if(carDataImportRecord==null){
            System.out.println("当前第一次导入车辆图片");
            id=carDataImportRecordModel.selectCarDataImportRecord(1L).getIdRecord();
            carDataImportRecordModel.insertCarDataImportRecord(new CarDataImportRecord(auctionId,id,timeCheck));
            carDataImportRecordModel.updateCarDataImportRecord(1L);
        }else {//如果有
            id=carDataImportRecord.getIdRecord();
        }
        for (CarPhotoTemp carPhotoTemp:list){
            carPhotoTemp.setId(carPhotoTemp.getId()+id);
        }
        Integer count=carPhotoTempModel.insertCarPhotoTemp(list);
        return count;
    }

    @Override
    public Integer deleteCarPhoto(Long auctionId) throws RuntimeException{
        CarDataImportRecord carDataImportRecord=carDataImportRecordModel.selectCarDataImportRecord(auctionId);
        Integer count=0;
        if(carDataImportRecord!=null){
            count=carPhotoTempModel.deleteCarPhotoTempById(carDataImportRecord.getIdRecord());
        }
        return count;
    }


}
