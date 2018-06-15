package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthPublic;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultStatus;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * @author zhangzijuan
 * @Description:车辆的评级规则
 * @date 2018-03-07
 */
@RestController
@RequestMapping("/gradeRule")
public class CarGradeRuleApi {
    private final RestTemplate restTemplate;
    String getCarGradeRule=Constants.ROOT+"/service/gradeRule/getCarGradeRule";
    String getCarGradeRuleAuto_URL=Constants.ROOT+"/service/gradeRule/getCarGradeRuleAuto";

    CarGradeRuleApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    /**
     * 查询车辆评级规则
     * @return
     */
    @PostMapping(value = "getCarGradeRule")
    @ApiOperation(value = "查询车辆评级规则")
    @AuthPublic
    public ResultModel getCarGradeRule(){
        ResponseEntity<JSONObject> response=this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(getCarGradeRule))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(null),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.LIST);
    }

}
