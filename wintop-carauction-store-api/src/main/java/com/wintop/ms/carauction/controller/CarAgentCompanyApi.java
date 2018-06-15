package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUser;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.entity.CarManagerUser;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import com.wintop.ms.carauction.util.utils.IdWorker;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

/**
 * 查询可选的代办公司
 */
@RestController
@RequestMapping("/carAgentCompany")
public class CarAgentCompanyApi {
    private IdWorker idWorker = new IdWorker(10);
    private ResultModel resultModel;
    private final RestTemplate restTemplate;
    CarAgentCompanyApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @RequestMapping(value = "/getCarAgentCompanyList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResponseEntity<ResultModel> getCarAgentCompanyList(@CurrentUser CarManagerUser carManagerUser, @RequestBody Map<String,Object> map) {
        map.put("customerId",carManagerUser.getId());
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/agentCompany/selectCarAgentCompanyList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.LIST);
    }
}
