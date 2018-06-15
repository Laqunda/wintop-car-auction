package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.entity.AppVersion;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Map;

/**
 * 获取版本号接口
 */
@RestController
@RequestMapping("/version")
public class VersionApi {
    private ResultModel resultModel;

    private final RestTemplate restTemplate;
    String appVersion_url = Constants.ROOT + "/appVersion/getNewByAppType";

    VersionApi(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }
    @RequestMapping(value = "/selectVersion",
            method= RequestMethod.POST,
            produces="application/json; charset=UTF-8")
//    @AuthUserToken
    public ResponseEntity<ResultModel> selectVersion(@RequestBody Map<String,Object> map) throws MalformedURLException {

        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/version/selectVersion"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);

    }

    @ApiOperation(value = "获取卖家版最新版本")
    @PostMapping(value = "getNew")
    public ResponseEntity<ResultModel> getNew(){
        JSONObject object = new JSONObject();
        object.put("appType","2");
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(appVersion_url))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(object),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }

    @ApiOperation(value = "获取卖家版最新版本iOS")
    @PostMapping(value = "getNewIOS")
    public ResponseEntity<ResultModel> getNewIOS(){
        JSONObject object = new JSONObject();
        object.put("appType","3");
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(appVersion_url))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(object),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }
}
