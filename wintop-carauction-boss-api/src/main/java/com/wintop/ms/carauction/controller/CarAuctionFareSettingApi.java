package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.RequestAuth;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.wintop.ms.carauction.core.config.Constants;

import java.net.URI;
import java.util.Map;

/**
 * @author zhangzijuan
 * @Description:加价金额配置
 * @date 2018-03-16
 */
@RestController
@RequestMapping("/fareSetting")
public class CarAuctionFareSettingApi {
    private final RestTemplate restTemplate;
    private ResultModel resultModel;
    CarAuctionFareSettingApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 查询所有的价格
     *@Author:zhangzijuan
     *@date 2018/3/14
     *@param:map
     */
    @ApiOperation(value = "查询所有的价格")
    @PostMapping(value = "/selectAllFare",produces="application/json; charset=UTF-8")
    @AuthUserToken
    @RequestAuth(false)
    public ResultModel selectAllFare() {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/fareSetting/selectAllFare"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(null),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.LIST);
    }

}
