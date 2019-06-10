package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AppApiVersion;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/carAppSettingApi")
public class CarAppSettingApi {

    private ResultModel resultModel;
    private final RestTemplate restTemplate;

    public CarAppSettingApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 获取全部的首页设置
     */
    @PostMapping( value = "/getAppSetting",
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8" )
    @AuthUserToken
    public ResponseEntity<ResultModel> getAppSetting(@RequestBody Map<String, Object> map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/appSetting/getAppSetting"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }

    /**
     * 修改首面设置
     */
    @PostMapping( value = "/update",
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8" )
    @AuthUserToken
    public ResponseEntity<ResultModel> update(@RequestBody Map<String,Object> map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/appSetting/update"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }
}
