package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * 议价审核接口
 */
@RestController
@RequestMapping("/bargainingAudit")
public class BargainingAuditApi {
    private ResultModel resultModel;

    private final RestTemplate restTemplate;

    BargainingAuditApi(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

    /**
     *议价审核成功接口
     */
    @RequestMapping(value = "/insertBargainingAuditSuccess",
            method= RequestMethod.POST,
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResponseEntity<ResultModel> insertBargainingAuditSuccess(@RequestBody Map<String,Object> map, @CurrentUserId Long userId) throws MalformedURLException {
        map.put("userId",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/bargainingAudit/insertBargainingAuditSuccess"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }

    /**
     *议价审核失败接口
     */
    @RequestMapping(value = "/insertBargainingAuditFail",
            method= RequestMethod.POST,
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResponseEntity<ResultModel> insertBargainingAuditFail(@RequestBody Map<String,Object> map, @CurrentUserId Long userId) throws MalformedURLException {
        map.put("userId",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/bargainingAudit/insertBargainingAuditFail"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }
}
