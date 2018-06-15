package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultStatus;
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

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangzijuan
 * @Description:车辆的评级规则
 * @date 2018-03-07
 */
@RestController
@RequestMapping("/gradeRule")
public class CarGradeRuleApi {
    private ResultModel resultModel;
    private final RestTemplate restTemplate;
    String getCarGradeRule="/service/gradeRule/getCarGradeRule";
    CarGradeRuleApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    /**
     * 查询车辆评级规则
     * @return
     */
    @PostMapping(value = "getCarGradeRule", consumes="application/json; charset=UTF-8",produces="application/json; charset=UTF-8")
    @ApiOperation(value = "查询车辆评级规则")
    public ResponseEntity<ResultModel> getCarGradeRule(){
        ResponseEntity<JSONObject> response=this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+getCarGradeRule))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(null),JSONObject.class);
        resultModel=new ResultModel(true, ResultStatus.SUCCESS);
        return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.LIST);
    }
}
