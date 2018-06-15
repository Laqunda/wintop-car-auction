package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAutoDetectionClass;
import com.wintop.ms.carauction.entity.CarAutoDetectionData;
import com.wintop.ms.carauction.entity.CarAutoDetectionDataPhoto;
import com.wintop.ms.carauction.service.ICarAutoDetectionClassService;
import com.wintop.ms.carauction.service.ICarAutoDetectionDataService;
import com.wintop.ms.carauction.util.utils.IdWorker;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
import java.util.Map;

/***
 * 车辆检测报告api
 */
@RestController
@RequestMapping("/autoDetection")
public class CarAutoDetectionApi {
    @Autowired
    private ICarAutoDetectionClassService iCarAutoDetectionClassService;

    @Autowired
    private ICarAutoDetectionDataService detectionDataService;

    private IdWorker idWorker = new IdWorker(10);
    private static final Logger logger = LoggerFactory.getLogger(CarAutoDetectionApi.class);


    /**
     * 根据车辆d查询车辆检测报告
     * @return
     */
    @PostMapping(value = "getAutoDetection")
    public ServiceResult<Map<String,Object>> getAutoDetection(@RequestBody JSONObject autoMap){
        logger.info("查询车辆检测报告");
        Long autoId = Long.parseLong(autoMap.get("autoId").toString());
        return iCarAutoDetectionClassService.getAutoDetectionDataClass(autoId);
    }

    @PostMapping(value = "getPClass")
    @ApiOperation(value = "获取车辆检测项顶层分类")
    public ServiceResult<List<CarAutoDetectionClass>> getPClass(){
        logger.info("获取车辆检测项顶层分类");
        ServiceResult<List<CarAutoDetectionClass>> result = new ServiceResult<>();
        try {
            CarAutoDetectionClass carAutoDetectionClass = new CarAutoDetectionClass();
            carAutoDetectionClass.setpId(0L);
            result.setResult(iCarAutoDetectionClassService.getAutoDetectionClass(carAutoDetectionClass,null));
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","获取失败");
        }finally {
            return result;
        }
    }

    @PostMapping(value = "getSonByPid")
    @ApiOperation(value = "根据顶层检测id获取子项检测类目")
    public ServiceResult<List<CarAutoDetectionClass>> getSonByPid(@RequestBody JSONObject object){
        logger.info("根据顶层检测id获取子项检测类目");
        ServiceResult<List<CarAutoDetectionClass>> result = new ServiceResult<>();
        try {
            CarAutoDetectionClass carAutoDetectionClass = new CarAutoDetectionClass();
            carAutoDetectionClass.setpId(object.getLong("pId"));
            result.setResult(iCarAutoDetectionClassService.getAutoDetectionClass(carAutoDetectionClass,object.getLong("autoId")));
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","获取失败");
        }finally {
            return result;
        }
    }

    @PostMapping(value = "getAutoDetectionClassOption")
    @ApiOperation(value = "根据检测项id和车辆id查询，某检测项的数据")
    public ServiceResult<CarAutoDetectionClass> getAutoDetectionClassOption(@RequestBody JSONObject object){
        logger.info("根据检测项id和车辆id查询，某检测项的数据");
        ServiceResult<CarAutoDetectionClass> result = new ServiceResult<>();
        try {
            result.setResult(iCarAutoDetectionClassService.getAutoDetectionClassOption(object.getLong("classId"),object.getLong("autoId")));
            result.setSuccess("0","成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","获取失败");
        }finally {
            return result;
        }
    }

    @PostMapping(value = "saveDetection")
    @ApiOperation(value = "保存车辆监测信息")
    public ServiceResult saveDetection(@ApiParam(value = "车辆检测信息：图片+损坏点数据") @RequestBody JSONObject object){
        logger.info("保存车辆监测信息");
        ServiceResult result = new ServiceResult();
        try {
            //解析封装损坏点数据
            JSONArray dataList = object.getJSONArray("dataList");
            CarAutoDetectionClass detectionClass = new CarAutoDetectionClass();
            List<CarAutoDetectionData> detectionDataList = new ArrayList<>();
            CarAutoDetectionData detectionData;
            if (dataList != null && dataList.size() > 0) {
                for (Object obj : dataList) {
                    JSONObject jsonObject = (JSONObject) obj;
                    detectionData = new CarAutoDetectionData();
                    detectionData.setId(idWorker.nextId());
                    detectionData.setClassId(object.getLong("classId"));
                    detectionData.setAutoId(object.getLong("autoId"));
                    detectionData.setCreateManager(object.getString("createManager"));
                    detectionData.setCreateTime(new Date());
                    detectionData.setDecreasedScore(jsonObject.getLong("decreasedScore"));
                    detectionData.setProblemDescription(jsonObject.getString("problemDescription"));
                    detectionData.setOptionsId(jsonObject.getLong("optionsId"));

                    detectionDataList.add(detectionData);
                }
                detectionClass.setDataList(detectionDataList);
            }
            //解析封装检测项照片
            List<CarAutoDetectionDataPhoto> dataPhotoList = new ArrayList<>();
            JSONArray photoList = object.getJSONArray("photoList");
            CarAutoDetectionDataPhoto dataPhoto;
            if (photoList != null && photoList.size() > 0) {
                for (Object photoObj : photoList) {
                    JSONObject photoJOB = (JSONObject) photoObj;
                    dataPhoto = new CarAutoDetectionDataPhoto();
                    dataPhoto.setId(idWorker.nextId());
                    dataPhoto.setAutoId(object.getLong("autoId"));
                    dataPhoto.setClassId(object.getLong("classId"));
                    dataPhoto.setPhotoUrl(photoJOB.getString("photoUrl"));

                    dataPhotoList.add(dataPhoto);
                }
                detectionClass.setPhotoList(dataPhotoList);
            }
            detectionClass.setId(object.getLong("classId"));
            detectionDataService.saveDetectionData(detectionClass, object.getLong("autoId"));
            result.setSuccess("0", "保存成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setError("-1","保存失败");
        }finally {
            return result;
        }
    }
}