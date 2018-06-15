package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.Constants;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
        map.put("userId",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/handleDispute/insertRevocationCar"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }


    /**
     *申请争议接口
     */
    @RequestMapping(value = "/insertApplicationDispute",
            method= RequestMethod.POST,
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResponseEntity<ResultModel> insertApplicationDispute(@RequestBody Map<String,Object> map, @CurrentUserId Long userId) throws MalformedURLException {
        map.put("userId",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/handleDispute/insertApplicationDispute"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }
}
