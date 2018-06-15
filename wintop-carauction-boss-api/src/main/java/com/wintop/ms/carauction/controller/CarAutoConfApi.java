package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/***
 * 车辆---------配置信息
 */
@RestController
@RequestMapping("autoConf")
public class CarAutoConfApi {

    private final RestTemplate restTemplate;
    //获取车辆全部配置项
    String getAllConf_URL= Constants.ROOT + "/autoConfTitle/getAllApp";
    //保存配置选项
    String saveOption_URL= Constants.ROOT + "/autoConfTitle/saveOption";
    CarAutoConfApi(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }


    @PostMapping(value = "getAutoConfBase")
    @ApiOperation(value = "获取车辆配置信息-基础信息")
    @AuthUserToken
    public ResultModel getAutoConfBase(@RequestParam Long autoId){
        Map map = new HashMap();
        map.put("autoId",autoId);
        ResponseEntity<JSONObject> response=this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(getAllConf_URL))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.LIST);
    }

    @PostMapping(value = "saveOption")
    @ApiOperation(value = "保存车辆配置信息")
    @AuthUserToken
    public ResultModel saveOption(@ApiParam(value = "车辆配置json，车辆id+配置json",required = true) @RequestBody JSONObject object){
        ResponseEntity<JSONObject> response=this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(saveOption_URL))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(object),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.LIST);
    }

}