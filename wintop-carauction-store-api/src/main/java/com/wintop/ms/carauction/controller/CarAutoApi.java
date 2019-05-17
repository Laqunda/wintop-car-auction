package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AppApiVersion;
import com.wintop.ms.carauction.core.annotation.AuthPublic;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.config.ResultStatus;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

/**
 * 车辆详情有关接口
 */
@RestController
@RequestMapping("/carAuto")
public class CarAutoApi {
    private ResultModel resultModel;
    private final RestTemplate restTemplate;
    CarAutoApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 查询车辆信息
     * @param map
     * @return
     */
    @RequestMapping(value = "/selectByCarId",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthPublic
    public ResponseEntity<ResultModel> selectByCarId(@RequestHeader Map<String,String> headers, @RequestBody Map<String,Object> map){
        String authorization = headers.get(Constants.HEADER_AUTHORIZATION);
        if(StringUtils.isNotEmpty(authorization) && authorization.indexOf("null")==-1){
            map.put("customerId",authorization.split("_")[0]);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carAuto/selectByCarIdForStore"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.OBJECT);
    }

    @RequestMapping(value = "/selectCarList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    @AppApiVersion(value = "2.0")
    public ResponseEntity<ResultModel> selectCarList(@RequestBody Map<String,Object> map) {
        if (map.get("type") == null || map.get("status") == null) {
            return new ResponseEntity<>(new ResultModel(false, 101, "缺少参数", null), HttpStatus.OK);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carAuto/selectCarList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.OBJECT);
    }

    @ApiOperation(value = "审批发拍的车辆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "carId",value = "车辆Id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "status",value = "审批状态 1 通过 2 不通过",required = true,dataType = "string"),
            @ApiImplicitParam(name = "msg",value = "审批留言",required = true,paramType = "query",dataType = "string")
    })
    @PostMapping(value = "/",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel approveCarAuto(@CurrentUserId Long managerId, @RequestBody Map<String,Object> map) {
        map.put("managerId",managerId);
        if(map.get("carId")==null || map.get("status")==null|| map.get("msg")==null|| map.get("managerId")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carAuto/approveCarAuto"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }
    @RequestMapping(value = "/retailOrderList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    @AppApiVersion(value = "2.0")
    public ResponseEntity<ResultModel> retailOrderlist(@RequestBody Map<String,Object> map) {
        if (map.get("page") == null) {
            return new ResponseEntity<>(new ResultModel(false, 101, "缺少参数", null), HttpStatus.OK);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carAuto/retailOrderList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.OBJECT);
    }
    /**
     * 查询车辆基本信息
     * @param map
     * @return
     */
    @RequestMapping(value = "/selectDetailByCarId",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthPublic
    public ResponseEntity<ResultModel> selectDetailByCarId(@RequestBody Map<String,Object> map){

        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carAuto/selectDetailByCarId"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.OBJECT);
    }

    /**
     *根据车辆id查询起拍价和保留价
     * @Author zhangzijiuan
     * @return
     */
    @ApiOperation(value = "根据车辆id查询起拍价和保留价")
    @ApiImplicitParam(name = "carId",value = "车辆Id",required = true,paramType = "query",dataType = "long")
    @PostMapping(value = "selectCarInfoById")
    public ResponseEntity<ResultModel> selectCarInfoById(@RequestBody Map<String,Object> map) {
        if (map.get("carId")==null){
            return new ResponseEntity<>(ResultModel.error(ResultStatus.PARAMETERS_ERROR), HttpStatus.OK);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carAuto/selectCarInfoById"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.OBJECT);
    }
}
