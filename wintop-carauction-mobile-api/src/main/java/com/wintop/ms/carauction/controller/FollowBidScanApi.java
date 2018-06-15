package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 12991 on 2018/3/8.
 */
@RestController
@RequestMapping("/followBidScan")
public class FollowBidScanApi {
    private ResultModel resultModel;

    private final RestTemplate restTemplate;

    FollowBidScanApi(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }
    @RequestMapping(value = "/selectFollowBidScan",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResponseEntity<ResultModel> insertCustomerCollection(@CurrentUserId Long userId) throws MalformedURLException {
        Map<String,Object> map=new HashMap<>();
        map.put("customerId",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/followBidScan/selectFollowBidScan"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }
}
