package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
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

/**
 * 发拍车辆列表接口
 */
@RestController
@RequestMapping("/hairShotCar")
public class HairShotCarApi {
    private ResultModel resultModel;
    private final RestTemplate restTemplate;
    HairShotCarApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    /**
     * 获取发拍的车辆列表
     * @param map
     * @return
     */
    @RequestMapping(value = "/getHairShotCarList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ResponseEntity<ResultModel> getHairShotCarList(@RequestHeader Map<String,String> headers, @RequestBody Map<String,Object> map) {
        String authorization = headers.get(Constants.HEADER_AUTHORIZATION);
        System.out.println(authorization);
        if(StringUtils.isNotEmpty(authorization)){
            map.put("customerId",authorization.split("_")[0]);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/hairShotCar/selectHairShotCarList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.OBJECT);
    }
}
