package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthPublic;
import com.wintop.ms.carauction.core.annotation.CurrentPossibleUserId;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

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
                        .post(URI.create(Constants.ROOT+"/service/carAuto/selectByCarId"))
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
     * 获取线上拍的车辆列表
     * @param map
     * @return
     */
    @RequestMapping(value = "/getOnlineCarList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ResponseEntity<ResultModel> selectAuctionCarList(@CurrentPossibleUserId Long customerId, @RequestBody Map<String,Object> map) {
        map.put("customerId",customerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carAuto/selectOnlineCarList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.OBJECT);
    }
}
