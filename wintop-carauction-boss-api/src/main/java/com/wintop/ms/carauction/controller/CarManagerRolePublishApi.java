package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/publish")
public class CarManagerRolePublishApi {

    private static final Logger logger = LoggerFactory.getLogger(CarAssessApi.class);


    private final RestTemplate restTemplate;

    public CarManagerRolePublishApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 查询用户审车权限关联表
     */
    @ApiOperation(value = "查询用户审车权限关联表")
    @PostMapping(value = "/list",
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ResultModel list(@RequestBody Map<String,Object> map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT + "/service/publish/list"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map), JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 保存用户审车权限关联表
     */
    @ApiOperation(value = "保存用户审车权限关联表")
    @PostMapping(value = "/saveOrUpdateList",
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ResultModel saveOrUpdateList(@RequestBody Map<String,Object> map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT + "/service/publish/saveOrUpdateList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map), JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }
}
