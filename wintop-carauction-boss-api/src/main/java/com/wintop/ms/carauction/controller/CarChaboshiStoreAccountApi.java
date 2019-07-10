package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AppApiVersion;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;
import java.util.Objects;

/**
 * 查博士店铺资金流水 信息操作处理
 *
 * @author ruoyi
 * @date 2019-05-05
 */
@RestController
@RequestMapping(value = "carChaboshiStoreAccount")
public class CarChaboshiStoreAccountApi {

    private static final Logger logger = LoggerFactory.getLogger(CarChaboshiStoreAccountApi.class);

    private ResultModel resultModel;
    private final RestTemplate restTemplate;
    CarChaboshiStoreAccountApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 查询查博士店铺资金流水列表
     */
    @ApiOperation(value = "查询查博士店铺资金流水列表")
    @PostMapping(value = "/list",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel list(@RequestBody Map<String,Object> map) {
        if(map.get("page")==null || map.get("limit")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carChaboshiStoreAccount/list"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return  ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 查询查博士店铺资金流水列表
     */
    @ApiOperation(value = "查询查博士店铺资金流水列表")
    @PostMapping(value = "/allList",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel allList(@RequestBody Map<String,Object> map) {
        if(map.get("storeId")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carChaboshiStoreAccount/allList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return  ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 查询查博士店铺资金流水列表
     */
    @ApiOperation(value = "查询查博士店铺资金流水详情")
    @PostMapping(value = "/detail",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel detail(@RequestBody Map<String,Object> map) {
        if (MapUtils.isNotEmpty(map) && Objects.isNull(map.get("storeId"))){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carChaboshiStoreAccount/detail"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return  ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 新增保存查博士店铺资金流水
     */
    @ApiOperation(value = "新增保存查博士店铺资金流水")
    @RequestMapping(value = "/add",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel addSave(@CurrentUserId Long managerId, @RequestBody Map<String,Object> map) {

        map.put("managerId",managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carChaboshiStoreAccount/add"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }


    /**
     * 修改保存查博士店铺资金流水
     */
    @ApiOperation(value = "修改查博士店铺资金流水")
    @RequestMapping(value = "/edit",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel editSave(@CurrentUserId Long managerId, @RequestBody Map<String,Object> map) {
        map.put("managerId",managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carChaboshiStoreAccount/edit"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 删除查博士店铺资金流水
     */
    @ApiOperation(value = "删除查博士店铺资金流水")
    @RequestMapping(value = "/remove",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel remove(@CurrentUserId Long managerId,@RequestBody Map<String,Object> map) {
        map.put("managerId",managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carChaboshiStoreAccount/remove"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

}
