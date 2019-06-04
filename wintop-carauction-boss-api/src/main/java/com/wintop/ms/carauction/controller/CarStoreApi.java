package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.annotation.RequestAuth;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.TokenManager;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.core.model.TokenModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @author admin
 * @Description:店铺信息
 * @date 2018-03-15
 */
@RestController
@RequestMapping("/carStore")
public class CarStoreApi {
    @Autowired
    private TokenManager tokenManager;
    private final RestTemplate restTemplate;
    CarStoreApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    /**
     * 查询所有有效店铺
     *@Author:admin
     *@date 2018/3/14
     *@param:map
     */
    @ApiOperation(value = "查询所有店铺")
    @RequestMapping(value = "/selectAllStore",
            consumes = "application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    @RequestAuth(false)
    public ResultModel selectAllStore() {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carStore/selectAllStore"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(new HashMap<String,Object>()),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.LIST);
    }

    /**
     * 查询店铺列表
     *@Author:admin
     *@date 2018/3/14
     *@param:map
     */
    @ApiOperation(value = "查询店铺列表")
    @RequestMapping(value = "/selectStoreList",
            consumes = "application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel selectStoreList(@RequestBody Map<String,Object> map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carStore/selectStoreList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 新增店铺
     *@Author:admin
     *@date 2018/3/14
     *@param:map
     */
    @ApiOperation(value = "保存店铺")
    @RequestMapping(value = "/save",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel saveCarStore(@RequestBody Map<String,Object> map, @CurrentUserId Long userId) {
        if(map.get("regionId")==null){
            return new ResultModel(false, ResultCode.NO_REGION_ID.value(),ResultCode.NO_REGION_ID.getRemark(),null);
        }
        map.put("createPerson",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carStore/saveCarStore"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 修改店铺
     *@Author:admin
     *@date 2018/3/14
     *@param:map
     */
    @ApiOperation(value = "修改店铺")
    @RequestMapping(value = "/updateCarStore",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel updateCarStore(@RequestHeader Map<String,String> headers, @RequestBody Map<String,Object> map) {
        TokenModel tokenModel = tokenManager.getToken(headers.get(Constants.HEADER_APPID),headers.get(Constants.HEADER_AUTHORIZATION));
        map.put("updatePerson",tokenModel.getUserId());
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carStore/updateCarStore"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 删除店铺
     *@Author:admin
     *@date 2018/3/14
     *@param:map
     */
    @ApiOperation(value = "删除店铺")
    @RequestMapping(value = "/deleteCarStore",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel deleteCarStore(@RequestHeader Map<String,String> headers, @RequestBody Map<String,Object> map) {
        TokenModel tokenModel = tokenManager.getToken(headers.get(Constants.HEADER_APPID),headers.get(Constants.HEADER_AUTHORIZATION));
        map.put("delPerson",tokenModel.getUserId());
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carStore/deleteCarStore"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 查询店铺
     *@Author:admin
     *@date 2018/3/14
     *@param:map
     */
    @ApiOperation(value = "查询店铺")
    @RequestMapping(value = "/selectCarStore",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel selectCarStore(@RequestBody Map<String,Object> map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carStore/selectCarStore"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }
}
