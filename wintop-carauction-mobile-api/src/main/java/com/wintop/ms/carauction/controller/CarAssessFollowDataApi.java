package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AppApiVersion;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

/**
 * 车辆评估跟进 信息操作处理
 *
 * @author ruoyi
 * @date 2019-05-05
 */
@RestController
@RequestMapping(value = "carAssessFollowData")
public class CarAssessFollowDataApi {
    private static final Logger logger = LoggerFactory.getLogger(CarAssessFollowDataApi.class);
    private final RestTemplate restTemplate;
    CarAssessFollowDataApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 查询车辆评估跟进列表
     */
    @ApiOperation(value = "查询车辆评估跟进列表")
    @PostMapping(value = "/list",produces="application/json; charset=UTF-8")
    @AuthUserToken
    @AppApiVersion(value = "2.0")
    public ResultModel list(@RequestBody Map map) {
        if(map.get("page")==null || map.get("limit")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carAssessFollowData/list"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }


    /**
     * 新增保存车辆评估跟进
     */
    @ApiOperation(value = "新增车辆评估跟进")
    @RequestMapping(value = "/add",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    @AppApiVersion(value = "2.0")
    public ResultModel addSave(@RequestBody Map map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carAssessFollowData/add"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }


    /**
     * 修改保存车辆评估跟进
     */
    @ApiOperation(value = "修改车辆评估跟进")
    @RequestMapping(value = "/edit",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    @AppApiVersion(value = "2.0")
    public ResultModel editSave(@RequestBody Map map) {

        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carAssessFollowData/edit"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 删除车辆评估跟进
     */
    @ApiOperation(value = "删除车辆评估跟进")
    @RequestMapping(value = "/remove",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    @AppApiVersion(value = "2.0")
    public ResultModel remove(@RequestBody Map map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carAssessFollowData/remove"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

}
