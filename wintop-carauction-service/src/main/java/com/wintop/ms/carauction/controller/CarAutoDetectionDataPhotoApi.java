package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.primitives.Longs;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.entity.CarAutoDetectionClass;
import com.wintop.ms.carauction.entity.CarAutoDetectionClassMap;
import com.wintop.ms.carauction.entity.CarAutoDetectionDataPhoto;
import com.wintop.ms.carauction.service.ICarAutoDetectionClassService;
import com.wintop.ms.carauction.service.ICarAutoDetectionDataPhotoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/***
 * 车辆故障检测照片api
 */
@RestController
@RequestMapping("/service/carAutoDetectionDataPhotoApi")
public class CarAutoDetectionDataPhotoApi {
    private static Logger logger = LoggerFactory.getLogger(CarAutoInfoDetailApi.class);
    @Autowired
    private ICarAutoDetectionClassService carAutoDetectionClassService;

    @Autowired
    private ICarAutoDetectionDataPhotoService carAutoDetectionDataPhotoService;

    private ResultModel resultModel;
    /***
     * 车辆故障检测照片
     * @param obj
     * @return
     */
    @RequestMapping(value = "/getCarDetectionPhoto",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<List<Map<String,Object>>> getCarDetectionPhoto(@RequestBody JSONObject obj) {
        ServiceResult<List<Map<String,Object>>> result = new ServiceResult<>();
        try {
            logger.info("查询车辆故障检测照片");
            List<Map<String, Object>> resultList = Lists.newArrayList();
            Map<String, Object> recordMap = Maps.newHashMap();
            Long carId = Longs.tryParse(obj.getString("carId"));
            List<CarAutoDetectionClassMap> classMapList = carAutoDetectionClassService.selectByDetectionData(Collections.emptyMap()).getResult();
            List<CarAutoDetectionDataPhoto> photoList = carAutoDetectionDataPhotoService.selectByAutoId(carId).getResult();
            for (CarAutoDetectionDataPhoto record : photoList) {
                recordMap = Maps.newHashMap();
                String title = classMapList.stream().filter(c -> c.getClassId().equals(record.getClassId())).findFirst().get().getName();
                recordMap.put("title", title);
                recordMap.put("url", record.getPhotoUrl());
                resultList.add(recordMap);
            }
            result.setSuccess("0","成功");
            result.setResult(resultList);
            logger.info("查询车辆故障检测照片成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess("-1","失败");
            logger.info("查询车辆故障检测照片失败");
        }
        return result;
    }
}
