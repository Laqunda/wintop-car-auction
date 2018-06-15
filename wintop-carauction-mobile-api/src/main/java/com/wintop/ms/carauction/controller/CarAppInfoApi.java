package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

/**
 * @author zhangzijuan
 * @Description:app相关信息
 * @date 2018-03-06
 */
@RestController
@RequestMapping("/appInfo")
public class CarAppInfoApi {
    private ResultModel resultModel;

    private final RestTemplate restTemplate;
//获取app的版本号
    String getVersionByAppId="/service/appInfo/getVersionByAppId";
    CarAppInfoApi(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }
    /**
     * 根据用户id查询用户认证信息
     * @return
     */
    @PostMapping(value = "getAppInfo", consumes="application/json; charset=UTF-8",produces="application/json; charset=UTF-8")
    @ApiOperation(value = "查询版本信息")
    public ResponseEntity<ResultModel> getVersionByAppId(@RequestHeader Map<String,String> headers){
        String appId = headers.get(Constants.HEADER_APPID);
        ResponseEntity<JSONObject> response=this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+getVersionByAppId))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(appId),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.OBJECT);
    }

}
