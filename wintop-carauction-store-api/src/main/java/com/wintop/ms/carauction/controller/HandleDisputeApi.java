package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Map;

/**
 * 处理发拍的车详情有关问题接口
 */
@RestController
@RequestMapping("/handleDispute")
public class HandleDisputeApi {
    private ResultModel resultModel;

    private final RestTemplate restTemplate;

    HandleDisputeApi(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

    /**
     *申请撤回车辆接口
     */
    @RequestMapping(value = "/insertRevocationCar",
            method= RequestMethod.POST,
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResponseEntity<ResultModel> insertRevocationCar(@RequestBody Map<String,Object> map, @CurrentUserId Long userId) throws MalformedURLException {
        if (map.get("carId")==null || map.get("msg")==null){
            return new ResponseEntity<>(ResultModel.error(ResultStatus.PARAMETERS_ERROR), HttpStatus.OK);
        }
        map.put("userId",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/handleDispute/insertRevocationCar"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }


    /**
     *申请二拍接口
     */
    @ApiOperation(value = "申请二拍")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "carId",value = "车辆Id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "userId",value = "用户id",required = true,dataType = "string"),
            @ApiImplicitParam(name = "startAmount",value = "起拍价",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "reserveAmount",value = "保留价",required = false,dataType = "string"),
    })
    @RequestMapping(value = "/insertForTwo",
            method= RequestMethod.POST,
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResponseEntity<ResultModel> insertForTwo(@RequestBody Map<String,Object> map, @CurrentUserId Long userId) {
        if (map.get("carId")==null || map.get("startAmount")==null || map.get("reserveAmount")==null){
            return new ResponseEntity<>(ResultModel.error(ResultStatus.PARAMETERS_ERROR), HttpStatus.OK);
        }
        map.put("userId",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carAuto/setAgainAuction"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }

    /**
     *申请争议接口
     */
    @ApiOperation(value = "申请争议")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId",value = "订单id",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "breachObjType",value = "争议类型",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "msg",value = "争议原因",required = true,paramType = "query",dataType = "int")
    })
    @PostMapping(value = "/insertApplicationDispute",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel insertApplicationDispute(@RequestBody Map<String,Object> map, @CurrentUserId Long managerId) throws MalformedURLException {
        if(map.get("orderId")==null || map.get("breachObjType")==null || map.get("msg")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("managerId",managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/customerBreach/applyBreach"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     *确定过户
     */
    @RequestMapping(value = "/saveDetermine",
            method= RequestMethod.POST,
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResponseEntity<ResultModel> saveDetermine(@RequestBody Map<String,Object> map, @CurrentUserId Long userId) throws MalformedURLException {
        map.put("userId",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/handleDispute/saveDetermine"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }

    /**
     *确定收款
     */
    @RequestMapping(value = "/saveGathering",
            method= RequestMethod.POST,
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResponseEntity<ResultModel> saveGathering(@RequestBody Map<String,Object> map, @CurrentUserId Long userId) throws MalformedURLException {
        map.put("createPerson",userId);
        if (map.get("orderId")==null){
            return new ResponseEntity<>(ResultModel.error(ResultStatus.PARAMETERS_ERROR), HttpStatus.OK);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carOrderBoss/saveOfflineOrderPay"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }
}
