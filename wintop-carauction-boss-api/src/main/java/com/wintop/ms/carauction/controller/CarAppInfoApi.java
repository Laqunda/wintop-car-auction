package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import io.swagger.annotations.Api;
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
 * @Description:app信息
 * @date 2018-03-15
 */
@RestController
@RequestMapping("/appInfo")
public class CarAppInfoApi {
    private final RestTemplate restTemplate;
    CarAppInfoApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private String rootUrl = Constants.ROOT+"/service/appInfo/";

    /**
     * 修改APP关于我们
     *@Author:admin
     *@date 2018/3/21
     *@param:map
     */
    @ApiOperation(value = "修改APP关于我们")
    @RequestMapping(value = "/updateAboutUs",
            consumes = "application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel updateAboutUs(@RequestBody Map<String,Object> map) {
        map.put("id",1l);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(rootUrl+"updateAboutUs"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 查看APP关于我们
     *@Author:admin
     *@date 2018/3/21
     *@param:map
     */
    @ApiOperation(value = "查看APP关于我们")
    @RequestMapping(value = "/selectAboutUs",
            consumes = "application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel selectAboutUs() {
        Map<String,Object> map = new HashMap<>();
        map.put("id",1l);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(rootUrl+"selectAboutUs"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

}
