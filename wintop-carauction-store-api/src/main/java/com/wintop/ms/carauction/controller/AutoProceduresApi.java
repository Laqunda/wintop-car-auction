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
 * 车辆-----------手续信息
 */
@RestController
@RequestMapping("/autoProcedures")
public class AutoProceduresApi {
    private final RestTemplate restTemplate;
    private ResultModel resultModel = null;
    private static final Logger logger = LoggerFactory.getLogger(AutoProceduresApi.class);

    public static final String getAutoProceduresByCarId_URL = Constants.ROOT+"/service/procedures/getAutoProceduresByCarId";
    public static final String save_URL = Constants.ROOT+"/service/procedures/save";


    AutoProceduresApi(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }


    @PostMapping(value = "getInfo")
    @ApiOperation(value = "获取车辆手续信息",notes = "根据车辆id获取车辆手续信息")
    public ResponseEntity<ResultModel> getInfo(@ApiParam(value = "车辆id",required = true) @RequestParam Long autoId){
        logger.info("获取车辆手续信息");
        JSONObject object = new JSONObject();
        object.put("carId",autoId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(getAutoProceduresByCarId_URL))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(object),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }

    @PostMapping(value = "saveInfo")
    @ApiOperation(value = "保存车辆手续信息",notes = "保存车辆手续信息")
    @AuthUserToken
    public ResponseEntity<ResultModel> saveInfo(@CurrentUserId Long userId,
            @ApiParam(value = "车辆手续信息json参数",required = true) @RequestBody JSONObject object){
        logger.info("保存车辆手续信息");
        object.put("userId",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(save_URL))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(object),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }

}
