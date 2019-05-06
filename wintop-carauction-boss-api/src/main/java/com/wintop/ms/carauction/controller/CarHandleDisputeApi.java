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
public class CarHandleDisputeApi {
    private final RestTemplate restTemplate;

    CarHandleDisputeApi(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

    /**
     *申请撤回车辆接口
     */
    @RequestMapping(value = "/insertRevocationCar",
            method= RequestMethod.POST,
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public  ResultModel insertRevocationCar(@RequestBody Map<String,Object> map, @CurrentUserId Long userId) throws MalformedURLException {
        if(map.get("carId")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("userId",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/handleDispute/insertRevocationCar"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 线上转线下渠道
     */
    @RequestMapping(value = "/transferChannelCar",
            method= {RequestMethod.POST,RequestMethod.GET},
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel transferChannelCar(@RequestBody Map<String,Object> map,@CurrentUserId Long userId)  throws MalformedURLException {
        if(map.get("carId")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("userId",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/handleDispute/transferChannelCar"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);    }

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
    public ResultModel insertForTwo(@RequestBody Map<String,Object> map, @CurrentUserId Long userId) {
        if (map.get("carId")==null || map.get("startAmount")==null || map.get("reserveAmount")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("userId",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carAuto/setAgainAuction"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }


    /**
     *确定收款
     */
    @RequestMapping(value = "/saveGathering",
            method= RequestMethod.POST,
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel saveGathering(@RequestBody Map<String,Object> map, @CurrentUserId Long userId) {
        if(map.get("orderId")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("createPerson",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carOrderBoss/saveOfflineOrderPay"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }
}
