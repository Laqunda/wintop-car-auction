package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AppApiVersion;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultStatus;
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

import java.net.URI;
import java.util.Map;

/**
 * describe: 评价数据记录
 * @author mazg
 * @date 2019-5-7
 */
@RestController
@RequestMapping("/carEvaluateDataApi")
public class CarEvaluateDataApi {

    private ResultModel resultModel;
    private final RestTemplate restTemplate;

    public CarEvaluateDataApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 保存评价内容
     */
    @RequestMapping(value = "/insertCarEvaluateData",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    @AppApiVersion(value = "2.0")
    public ResponseEntity<ResultModel> insertCarEvaluateData(@RequestBody Map<String,Object> map){
        ResponseEntity<JSONObject> response=this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carEvaluateDataApi/insertCarEvaluateData"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        resultModel=new ResultModel(true, ResultStatus.SUCCESS);
        return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.OBJECT);
    }
}