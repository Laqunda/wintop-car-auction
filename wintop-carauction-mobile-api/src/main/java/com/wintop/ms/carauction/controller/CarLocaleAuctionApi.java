package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AppApiVersion;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/carAuction")
public class CarLocaleAuctionApi {
    private ResultModel resultModel;
    private final RestTemplate restTemplate;
    CarLocaleAuctionApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 获取场次列表
     * @param map
     * @return
     */
    @RequestMapping(value = "/selectAuctionList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ResponseEntity<ResultModel> selectAuctionList(@RequestBody Map<String,Object> map) {
        map.put("clientType","app");
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carLocaleAuction/selectAuctionList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.OBJECT);
    }

    /**
     * 获取场次列表
     * @param map
     * @return
     */
    @RequestMapping(value = "/selectAuctionTotalList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ResponseEntity<ResultModel> selectAuctionTotalList(@RequestBody Map<String,Object> map) {
        map.put("clientType","app");
        Object version = map.get("version");
        if ("2.0".equals(version + "")) {
            ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                    RequestEntity
                            .post(URI.create(Constants.ROOT+"/service/carLocaleAuction/selectAuctionTotalList2"))
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(map),JSONObject.class);
            return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.OBJECT);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carLocaleAuction/selectAuctionTotalList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.OBJECT);
    }

    /**
     * 获取场次车辆列表
     * @param map
     * @return
     */
    @RequestMapping(value = "/selectAuctionCarList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ResponseEntity<ResultModel> selectAuctionCarList(@RequestHeader Map<String,String> headers, @RequestBody Map<String,Object> map) {
        String authorization = headers.get(Constants.HEADER_AUTHORIZATION);
        if(StringUtils.isNotBlank(authorization)&&authorization.split("_").length>0&&StringUtils.isNotBlank(authorization.split("_")[0])){
            map.put("customerId",authorization.split("_")[0]);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carLocaleAuction/selectAuctionCarList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.OBJECT);
    }

}
