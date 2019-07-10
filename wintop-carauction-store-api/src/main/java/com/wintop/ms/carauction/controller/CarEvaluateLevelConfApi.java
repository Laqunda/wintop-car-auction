package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AppApiVersion;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.HashMap;

/**
 * 获取分享车辆内容[分享前调用]接口
 */
@RestController
@RequestMapping("/carEvaluateLevelConfApi")
public class CarEvaluateLevelConfApi {

    private ResultModel resultModel;

    private final RestTemplate restTemplate;

    public CarEvaluateLevelConfApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(value = "/queryCarEvaluateLevelConfList",
            method = RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    @AppApiVersion(value = "2.0")
    public ResponseEntity<ResultModel> selectCarContentSharing() throws MalformedURLException {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT + "/service/carEvaluateLevelConfApi/queryCarEvaluateLevelConfList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(new HashMap<String,Object>()), JSONObject.class);
        return ApiUtil.getResponseEntity(response, resultModel, ApiUtil.LIST);
    }
}
