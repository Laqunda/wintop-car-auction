package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@RestController
@RequestMapping("/city")
public class WtCityApi {

    private static final Logger logger = LoggerFactory.getLogger(WtCityApi.class);

    private ResultModel resultModel;
    private final RestTemplate restTemplate;

    public WtCityApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @ApiOperation(value = "查询全部")
    @PostMapping(value = "/getAll", produces = "application/json; charset=UTF-8")
    public ResultModel getAll(@RequestBody Map<String, Object> map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT + "/city/getAll"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map), JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.LIST);
    }

    @ApiOperation(value = "通过id查询城市")
    @PostMapping(value = "/findById", produces = "application/json; charset=UTF-8")
    public ResultModel findById(@RequestBody Map<String, Object> map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT + "/city/findById"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map), JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    @ApiOperation(value = "通过id查询城市")
    @PostMapping(value = "/getByProvince", produces = "application/json; charset=UTF-8")
    public ResultModel getByProvince(@RequestBody Map<String, Object> map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT + "/city/getByProvince"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map), JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.LIST);
    }


}
