package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/***
 * 车辆检测报告api
 */
@RestController
@RequestMapping("/autoDetection")
public class CarAutoDetectionApi {

    private Logger logger = LoggerFactory.getLogger(CarAutoDetectionApi.class);

    private final RestTemplate restTemplate;
    //保存检测报告
    private String saveReport_URL= Constants.ROOT + "/service/carAuto/saveReport";
    //获取车辆检测项顶层分类
    private String getPClass_URL= Constants.ROOT + "/autoDetection/getPClass";
    //根据顶层检测id获取子项检测类目
    private String getSonByPid_URL= Constants.ROOT + "/autoDetection/getSonByPid";
    //保存车辆检测损坏点数据
    private String saveDetection_URL= Constants.ROOT + "/autoDetection/saveDetection";
    //获取某车某检测项+损坏点数据
    private String getAutoDetectionClassOption_URL= Constants.ROOT + "/autoDetection/getAutoDetectionClassOption";


    CarAutoDetectionApi(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }


    @PostMapping(value = "saveReport")
    @ApiOperation(value = "保存车辆检测报告评级")
    @AuthUserToken
    public ResultModel saveReport(@CurrentUserId Long userId,
                                                  @ApiParam(value = "车辆id") @RequestParam Long autoId,
                                                  @ApiParam(value = "质检报告综合等级：A,B,C,D") @RequestParam String reportColligationRanks,
                                                  @ApiParam(value = "质检报告整备等级：++，+，-，--") @RequestParam String reportServicingRanks){
        JSONObject object = new JSONObject();
        object.put("updateUser",userId);
        object.put("autoId",autoId);
        object.put("reportColligationRanks",reportColligationRanks);
        object.put("reportServicingRanks",reportServicingRanks);

        ResponseEntity<JSONObject> response=this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(saveReport_URL))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(object),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    @PostMapping(value = "getPClass")
    @ApiOperation(value = "获取车辆检测大类")
    @AuthUserToken
    public ResultModel getPClass(){
        logger.info("获取车辆检测大类");
        ResponseEntity<JSONObject> response=this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(getPClass_URL))
                        .contentType(MediaType.APPLICATION_JSON)
                        .build(),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.LIST);
    }

    @PostMapping(value = "getSonClassByPid")
    @ApiOperation(value = "获取车辆检测项子类+损坏点")
    @AuthUserToken
    public ResultModel getSonClassByPid(@RequestParam Long pId,@RequestParam Long autoId){
        logger.info("获取车辆检测子类+损坏点");
        JSONObject object = new JSONObject();
        object.put("pId",pId);
        object.put("autoId",autoId);
        ResponseEntity<JSONObject> response=this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(getSonByPid_URL))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(object),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.LIST);
    }

    @PostMapping(value = "getClassOption")
    @ApiOperation(value = "获取车辆检测项子类+损坏点")
    @AuthUserToken
    public ResultModel getClassOption(@RequestParam Long classId,@RequestParam Long autoId){
        logger.info("获取车辆检测子类+损坏点");
        JSONObject object = new JSONObject();
        object.put("classId",classId);
        object.put("autoId",autoId);
        ResponseEntity<JSONObject> response=this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(getAutoDetectionClassOption_URL))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(object),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }


    @PostMapping(value = "saveDetection")
    @ApiOperation(value = "保存车辆监测损坏点信息")
    @AuthUserToken
    public ResultModel saveDetection(@CurrentUserId Long userId, @RequestBody JSONObject object){
        logger.info("保存车辆监测损坏点照片+损坏点");
        object.put("createManager",userId);
        ResponseEntity<JSONObject> response=this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(saveDetection_URL))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(object),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }
}