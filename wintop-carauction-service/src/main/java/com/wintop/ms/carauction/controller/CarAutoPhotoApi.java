package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.config.ResultStatus;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAutoPhoto;
import com.wintop.ms.carauction.service.ICarAutoPhotoService;
import com.wintop.ms.carauction.util.utils.IdWorker;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/***
 * 车辆图片服务接口
 */
@RestController
@RequestMapping("autoPhoto")
public class CarAutoPhotoApi {

    IdWorker idWorker = new IdWorker(10);

    @Autowired
    private ICarAutoPhotoService carAutoPhotoService;
    Logger logger = LoggerFactory.getLogger(CarAutoPhotoApi.class);

    @PostMapping(value = "/getByAutoId")
    @ApiOperation(value = "查询车辆照片",notes = "查询车辆照片")
    public ServiceResult<List<CarAutoPhoto>> getByAutoId(@RequestBody Long autoId){
        ServiceResult<List<CarAutoPhoto>> result = new ServiceResult<>();
        result.setResult(carAutoPhotoService.selectByAutoId(autoId));
        result.setSuccess(ResultStatus.SUCCESS.getCode()+"",ResultStatus.SUCCESS.getMessage());
        return result;
    }

    @PostMapping(value = "/savePhoto")
    @ApiOperation(value = "保存车辆照片",notes = "保存车辆照片")
    public ServiceResult<Integer> savePhoto(@RequestBody JSONObject object){
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            List<CarAutoPhoto> photoList = new ArrayList<>();
            CarAutoPhoto def_carAutoPhoto = new CarAutoPhoto();
            def_carAutoPhoto.setAutoId(Long.parseLong(object.get("autoId").toString()));
            def_carAutoPhoto.setCreateTime(new Date());
            def_carAutoPhoto.setCreateUser(object.getString("userId"));
            def_carAutoPhoto.setUpdateUser(object.getString("userId"));
            def_carAutoPhoto.setCreateTime(new Date());

            CarAutoPhoto carAutoPhoto = new CarAutoPhoto();
            carAutoPhoto.setId(idWorker.nextId());
            if (object.get("frontLeft") != null && !object.getString("frontLeft").isEmpty()) {
                carAutoPhoto.setPhotoUrl(object.getString("frontLeft"));
                carAutoPhoto.setSort(1);
                carAutoPhoto.setPhotoType("1");
                carAutoPhoto.setAutoId(Long.parseLong(object.get("autoId").toString()));
                carAutoPhoto.setCreateTime(new Date());
                carAutoPhoto.setCreateUser(object.getString("userId"));
                carAutoPhoto.setUpdateUser(object.getString("userId"));
                carAutoPhoto.setCreateTime(new Date());
                photoList.add(carAutoPhoto);
            }

            carAutoPhoto =  new CarAutoPhoto();
            carAutoPhoto.setId(idWorker.nextId());
            if (object.get("left") != null && !object.getString("left").isEmpty()) {
                carAutoPhoto.setPhotoUrl(object.getString("left"));
                carAutoPhoto.setSort(2);
                carAutoPhoto.setPhotoType("2");
                carAutoPhoto.setAutoId(Long.parseLong(object.get("autoId").toString()));
                carAutoPhoto.setCreateTime(new Date());
                carAutoPhoto.setCreateUser(object.getString("userId"));
                carAutoPhoto.setUpdateUser(object.getString("userId"));
                carAutoPhoto.setCreateTime(new Date());
                photoList.add(carAutoPhoto);
            }
            carAutoPhoto = new CarAutoPhoto();
            carAutoPhoto.setId(idWorker.nextId());
            if (object.get("rightRear") != null && !object.getString("rightRear").isEmpty()) {
                carAutoPhoto.setPhotoUrl(object.getString("rightRear"));
                carAutoPhoto.setSort(3);
                carAutoPhoto.setPhotoType("3");
                carAutoPhoto.setAutoId(Long.parseLong(object.get("autoId").toString()));
                carAutoPhoto.setCreateTime(new Date());
                carAutoPhoto.setCreateUser(object.getString("userId"));
                carAutoPhoto.setUpdateUser(object.getString("userId"));
                carAutoPhoto.setCreateTime(new Date());
                photoList.add(carAutoPhoto);
            }
            carAutoPhoto = new CarAutoPhoto();
            carAutoPhoto.setId(idWorker.nextId());
            if (object.get("frontSeat") != null && !object.getString("frontSeat").isEmpty()) {
                carAutoPhoto.setPhotoUrl(object.getString("frontSeat"));
                carAutoPhoto.setSort(4);
                carAutoPhoto.setPhotoType("4");
                carAutoPhoto.setAutoId(Long.parseLong(object.get("autoId").toString()));
                carAutoPhoto.setCreateTime(new Date());
                carAutoPhoto.setCreateUser(object.getString("userId"));
                carAutoPhoto.setUpdateUser(object.getString("userId"));
                carAutoPhoto.setCreateTime(new Date());
                photoList.add(carAutoPhoto);
            }
            carAutoPhoto = new CarAutoPhoto();
            carAutoPhoto.setId(idWorker.nextId());
            if (object.get("instrumentPanel") != null && !object.getString("instrumentPanel").isEmpty()) {
                carAutoPhoto.setPhotoUrl(object.getString("instrumentPanel"));
                carAutoPhoto.setSort(5);
                carAutoPhoto.setPhotoType("5");
                carAutoPhoto.setAutoId(Long.parseLong(object.get("autoId").toString()));
                carAutoPhoto.setCreateTime(new Date());
                carAutoPhoto.setCreateUser(object.getString("userId"));
                carAutoPhoto.setUpdateUser(object.getString("userId"));
                carAutoPhoto.setCreateTime(new Date());
                photoList.add(carAutoPhoto);
            }
            carAutoPhoto = new CarAutoPhoto();
            carAutoPhoto.setId(idWorker.nextId());
            if (object.get("backSeat") != null && !object.getString("backSeat").isEmpty()) {
                carAutoPhoto.setPhotoUrl(object.getString("backSeat"));
                carAutoPhoto.setSort(6);
                carAutoPhoto.setPhotoType("6");
                carAutoPhoto.setAutoId(Long.parseLong(object.get("autoId").toString()));
                carAutoPhoto.setCreateTime(new Date());
                carAutoPhoto.setCreateUser(object.getString("userId"));
                carAutoPhoto.setUpdateUser(object.getString("userId"));
                carAutoPhoto.setCreateTime(new Date());
                photoList.add(carAutoPhoto);
            }
            carAutoPhoto = new CarAutoPhoto();
            carAutoPhoto.setId(idWorker.nextId());
            if (object.get("controlBoard") != null && !object.getString("controlBoard").isEmpty()) {
                carAutoPhoto.setPhotoUrl(object.getString("controlBoard"));
                carAutoPhoto.setSort(7);
                carAutoPhoto.setPhotoType("7");
                carAutoPhoto.setAutoId(Long.parseLong(object.get("autoId").toString()));
                carAutoPhoto.setCreateTime(new Date());
                carAutoPhoto.setCreateUser(object.getString("userId"));
                carAutoPhoto.setUpdateUser(object.getString("userId"));
                carAutoPhoto.setCreateTime(new Date());
                photoList.add(carAutoPhoto);
            }
            carAutoPhoto = new CarAutoPhoto();
            carAutoPhoto.setId(idWorker.nextId());
            if (object.get("carTail") != null && !object.getString("carTail").isEmpty()) {
                carAutoPhoto.setPhotoUrl(object.getString("carTail"));
                carAutoPhoto.setSort(8);
                carAutoPhoto.setPhotoType("8");
                carAutoPhoto.setAutoId(Long.parseLong(object.get("autoId").toString()));
                carAutoPhoto.setCreateTime(new Date());
                carAutoPhoto.setCreateUser(object.getString("userId"));
                carAutoPhoto.setUpdateUser(object.getString("userId"));
                carAutoPhoto.setCreateTime(new Date());
                photoList.add(carAutoPhoto);
            }
            carAutoPhoto = new CarAutoPhoto();
            carAutoPhoto.setId(idWorker.nextId());
            if (object.get("trunkFloor") != null && !object.getString("trunkFloor").isEmpty()) {
                carAutoPhoto.setPhotoUrl(object.getString("trunkFloor"));
                carAutoPhoto.setSort(9);
                carAutoPhoto.setPhotoType("9");
                carAutoPhoto.setAutoId(Long.parseLong(object.get("autoId").toString()));
                carAutoPhoto.setCreateTime(new Date());
                carAutoPhoto.setCreateUser(object.getString("userId"));
                carAutoPhoto.setUpdateUser(object.getString("userId"));
                carAutoPhoto.setCreateTime(new Date());
                photoList.add(carAutoPhoto);
            }
            carAutoPhoto = new CarAutoPhoto();
            carAutoPhoto.setId(idWorker.nextId());
            if (object.get("engineNacelle") != null && !object.getString("engineNacelle").isEmpty()) {
                carAutoPhoto.setPhotoUrl(object.getString("engineNacelle"));
                carAutoPhoto.setSort(10);
                carAutoPhoto.setPhotoType("10");
                carAutoPhoto.setAutoId(Long.parseLong(object.get("autoId").toString()));
                carAutoPhoto.setCreateTime(new Date());
                carAutoPhoto.setCreateUser(object.getString("userId"));
                carAutoPhoto.setUpdateUser(object.getString("userId"));
                carAutoPhoto.setCreateTime(new Date());
                photoList.add(carAutoPhoto);
            }
            carAutoPhoto = new CarAutoPhoto();
            carAutoPhoto.setId(idWorker.nextId());
            if (object.get("theFactoryBrand") != null && !object.getString("theFactoryBrand").isEmpty()) {
                carAutoPhoto.setPhotoUrl(object.getString("theFactoryBrand"));
                carAutoPhoto.setSort(11);
                carAutoPhoto.setPhotoType("11");
                carAutoPhoto.setAutoId(Long.parseLong(object.get("autoId").toString()));
                carAutoPhoto.setCreateTime(new Date());
                carAutoPhoto.setCreateUser(object.getString("userId"));
                carAutoPhoto.setUpdateUser(object.getString("userId"));
                carAutoPhoto.setCreateTime(new Date());
                photoList.add(carAutoPhoto);
            }
            carAutoPhoto = new CarAutoPhoto();
            carAutoPhoto.setId(idWorker.nextId());
            if (object.get("drivingLicense") != null && !object.getString("drivingLicense").isEmpty()) {
                carAutoPhoto.setPhotoUrl(object.getString("drivingLicense"));
                carAutoPhoto.setSort(12);
                carAutoPhoto.setPhotoType("12");
                carAutoPhoto.setAutoId(Long.parseLong(object.get("autoId").toString()));
                carAutoPhoto.setCreateTime(new Date());
                carAutoPhoto.setCreateUser(object.getString("userId"));
                carAutoPhoto.setUpdateUser(object.getString("userId"));
                carAutoPhoto.setCreateTime(new Date());
                photoList.add(carAutoPhoto);
            }

            JSONArray otherPhotoArr = object.getJSONArray("otherPhoto");
            if (otherPhotoArr!=null && otherPhotoArr.size()>0){
                int i=12;
                for (Object url:otherPhotoArr) {
                    i++;
                    carAutoPhoto = new CarAutoPhoto();
                    carAutoPhoto.setId(idWorker.nextId());
                    carAutoPhoto.setPhotoUrl((String) url);
                    carAutoPhoto.setSort(i);
                    carAutoPhoto.setPhotoType("13");
                    carAutoPhoto.setAutoId(Long.parseLong(object.get("autoId").toString()));
                    carAutoPhoto.setCreateTime(new Date());
                    carAutoPhoto.setCreateUser(object.getString("userId"));
                    carAutoPhoto.setUpdateUser(object.getString("userId"));
                    carAutoPhoto.setCreateTime(new Date());
                    photoList.add(carAutoPhoto);
                }
            }

            if (carAutoPhotoService.insertArr(photoList,Long.parseLong(object.get("autoId").toString()))>0){
                result.setSuccess("0","成功");
            }else {
                result.setError("-1","保存失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","保存失败");
        }finally {
            return result;
        }
    }

    @PostMapping(value = "/delPhoto")
    @ApiOperation(value = "删除车辆照片",notes = "删除车辆照片")
    @AuthUserToken
    public ServiceResult<Integer> delPhoto(@RequestBody Long photoId){
        return carAutoPhotoService.deleteByPrimaryKey(photoId);
    }
}
