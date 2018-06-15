package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUser;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.entity.CarManagerUser;
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
 * 中心发拍我的车辆列表接口
 */
@RestController
@RequestMapping("/centerRacketCar")
public class CenterRacketCarApi {
    private ResultModel resultModel;
    private final RestTemplate restTemplate;
    CenterRacketCarApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    /**
     * 获取发拍的车辆列表
     * @param map
     * @return
     */
    @RequestMapping(value = "/getCenterRacketCarList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResponseEntity<ResultModel> getCenterRacketCarList(@CurrentUser CarManagerUser carManagerUser, @RequestBody Map<String,Object> map) {
      /*  String authorization = headers.get(Constants.HEADER_AUTHORIZATION);
        System.out.println(authorization);
        if(StringUtils.isNotEmpty(authorization)){
            map.put("customerId",authorization.split("_")[0]);
        }*/
        map.put("customerId",carManagerUser.getId());
        map.put("auctionType","2");
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/centerRacketCar/selectCenterRacketCarList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.OBJECT);
    }
}
