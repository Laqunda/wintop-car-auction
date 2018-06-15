package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthPublic;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.annotation.RequestAuth;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @author admin
 * @Description:app轮播信息
 * @date 2018-03-15
 */
@RestController
@RequestMapping("/appSlideshow")
public class CarAppSlideshowApi {
    private final RestTemplate restTemplate;
    CarAppSlideshowApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private String rootUrl = Constants.ROOT+"/service/carAppSlideshow/";

    /**
     * 查询所有有效场次
     * @return
     */
    @ApiOperation(value = "查询所有有效场次")
    @RequestMapping(value = "/selectAllValidAuction",
            consumes = "application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    @RequestAuth(false)
    public ResultModel selectAllValidAuction() {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carLocaleAuction/selectAllValidAuction"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(null),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.LIST);
    }

    /**
     * 查询所有有效竞拍车辆
     * @return
     */
    @ApiOperation(value = "查询所有有效竞拍车辆")
    @RequestMapping(value = "/selectAllValidAuto",
            consumes = "application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    @RequestAuth(false)
    public ResultModel selectAllValidAuto() {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carAutoAuction/selectAllValidAuto"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(null),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.LIST);
    }

    /**
     * 保存APP轮播图或播报
     * @return
     */
    @ApiOperation(value = "保存APP轮播图或播报")
    @RequestMapping(value = "/saveAppSlideshow",
            consumes = "application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel saveAppSlideshow(@RequestBody Map<String,Object> map, @CurrentUserId Long userId) {
        map.put("editor",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(rootUrl+"saveAppSlideshow"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 修改APP轮播图或播报
     * @return
     */
    @ApiOperation(value = "修改APP轮播图或播报")
    @RequestMapping(value = "/updateAppSlideshow",
            consumes = "application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel updateAppSlideshow(@RequestBody Map<String,Object> map, @CurrentUserId Long userId) {
        map.put("editor",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(rootUrl+"updateAppSlideshow"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 查询APP轮播图或播报
     * @return
     */
    @ApiOperation(value = "查询APP轮播图或播报")
    @RequestMapping(value = "/selectAppSlideshow",
            consumes = "application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel selectAppSlideshow(@RequestBody Map<String,Object> map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(rootUrl+"selectAppSlideshow"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 查询APP轮播图或播报列表
     * @return
     */
    @ApiOperation(value = "查询APP轮播图或播报列表")
    @RequestMapping(value = "/selectAppSlideshowList",
            consumes = "application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel selectAppSlideshowList(@RequestBody Map<String,Object> map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(rootUrl+"selectAppSlideshowList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.LIST);
    }
}
