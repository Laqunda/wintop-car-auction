package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.annotation.RequestAuth;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
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

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @author admin
 * @Description:地区配置信息
 * @date 2018-03-15
 */
@RestController
@RequestMapping("/regionSetting")
public class CarRegionSettingApi {
    private final RestTemplate restTemplate;
    CarRegionSettingApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private String rootUrl = Constants.ROOT+"/service/carRegionSetting/";

    /**
     * 获取所有地区
     *@Author:admin
     *@date 2018/3/21
     *@param:map
     */
    @ApiOperation(value = "获取所有地区")
    @RequestMapping(value = "/getAllRegion",
            consumes = "application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    @RequestAuth(false)
    public ResultModel getAllRegion() {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/wtRegion/getAll"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("1"),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.LIST);
    }

    /**
     * 查询地区拍卖设置列表
     *@Author:admin
     *@date 2018/3/21
     *@param:map
     */
    @ApiOperation(value = "查询地区拍卖设置列表")
    @RequestMapping(value = "/selectRegionSettingList",
            consumes = "application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel selectRegionSettingList(@RequestBody Map<String,Object> map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(rootUrl+"selectRegionSettingList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 保存地区拍卖设置
     *@Author:admin
     *@date 2018/3/21
     *@param:map
     */
    @ApiOperation(value = "保存地区拍卖设置")
    @RequestMapping(value = "/saveRegionSetting",
            consumes = "application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel saveRegionSetting(@RequestBody Map<String,Object> map, @CurrentUserId Long userId) {
        map.put("createPerson",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(rootUrl+"saveRegionSetting"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 修改地区拍卖设置
     *@Author:admin
     *@date 2018/3/21
     *@param:map
     */
    @ApiOperation(value = "修改地区拍卖设置")
    @RequestMapping(value = "/updateRegionSetting",
            consumes = "application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel updateRegionSetting(@RequestBody Map<String,Object> map, @CurrentUserId Long userId) {
        map.put("updatePerson",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(rootUrl+"updateRegionSetting"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 查看地区拍卖设置
     *@Author:admin
     *@date 2018/3/21
     *@param:map
     */
    @ApiOperation(value = "查看地区拍卖设置")
    @RequestMapping(value = "/selectRegionSetting",
            consumes = "application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel selectRegionSetting(@RequestBody Map<String,Object> map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(rootUrl+"selectRegionSetting"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /***
     * 查询地区拍卖设置
     * @param managerId
     * @param map
     * @return
     */
    @ApiOperation(value = "查询地区拍卖设置")
    @RequestMapping(value = "/getBossRegionSetting",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel getBossRegionSetting(@RequestBody Map<String,Object> map,@CurrentUserId String managerId) {
        map.put("managerId", managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(rootUrl + "getBossRegionSetting"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map), JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.LIST);
    }

    /***
     * 保存修改地区拍卖设置
     * @param map
     * @return
     */
    @RequestMapping(value = "/saveOrUpdateSetting",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    @ApiOperation(value = "保存修改地区拍卖设置")
    public ResultModel saveOrUpdateSetting(@RequestBody Map<String,Object> map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(rootUrl + "getBossSaveOrUpdate"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map), JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }
}
