package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import io.swagger.annotations.ApiOperation;
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
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 12991 on 2018/3/19.
 */
@RestController
@RequestMapping("/carStoreLogoMessageCount")
public class CarStoreLogoMessageCountApi {
    private final RestTemplate restTemplate;
    private ResultModel resultModel;
    CarStoreLogoMessageCountApi(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

    @ApiOperation(value = "查询当前登陆人的所在部门信息")
    @RequestMapping(value = "/selectCarStoreLogoMessageCount",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResponseEntity<ResultModel> selectCarStoreLogoMessageCount(@CurrentUserId Long userId) {
        Map map = new HashMap();
        map.put("userId",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carStoreLogoMessageCount/selectCarStoreLogoMessageCount"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }
}
