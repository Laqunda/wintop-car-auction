package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AppApiVersion;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;
import java.util.Objects;

/**
 * 查询查博士查询权限表
 */
@RestController
@RequestMapping(value = "carManagerRoleLog")
public class CarManagerRoleLogApi {

    private static final Logger logger = LoggerFactory.getLogger(CarManagerRoleLogApi.class);
    private final RestTemplate restTemplate;

    public CarManagerRoleLogApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 查询查博士查询权限商户审核列表
     */
    @ApiOperation(value = "查询查博士查询权限商户审核列表")
    @PostMapping(value = "/list",produces="application/json; charset=UTF-8")
    @AuthUserToken
    @AppApiVersion(value = "2.0")
    public ResultModel list(@RequestBody Map<String,Object> map, @CurrentUserId Long managerId) {
        if (Objects.isNull(map.get("storeId"))) {
            return new ResultModel(false, 101, "缺少参数", null);
        }
        map.put("managerId", managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carManagerRoleLog/list"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return  ApiUtil.getResultModel(response, ApiUtil.LIST);
    }

    /**
     * 保存查博士查询权限商户
     */
    @ApiOperation(value = "保存查博士查询权限商户")
    @PostMapping(value = "/queryAudit",produces="application/json; charset=UTF-8")
    @AuthUserToken
    @AppApiVersion(value = "2.0")
    public ResultModel queryAudit(@RequestBody Map<String,Object> map, @CurrentUserId Long managerId) {
        map.put("managerId", managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carManagerRoleLog/queryAudit"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return  ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 保存查博士查询权限商户
     */
    @ApiOperation(value = "保存查博士查询权限商户")
    @PostMapping(value = "/saveOrUpdate",produces="application/json; charset=UTF-8")
    @AuthUserToken
    @AppApiVersion(value = "2.0")
    public ResultModel saveOrUpdate(@RequestBody Map<String,Object> map, @CurrentUserId Long managerId) {
        map.put("managerId", managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carManagerRoleLog/saveOrUpdate"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return  ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }


}
