package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
import java.util.Map;

/**
 * class_name: CustomerQuitLogApi
 * package: com.wintop.ms.carauction.controller
 * describe: 用户退会记录相关
 * creat_user: lizhaoyang
 * creat_date-time: 2018/3/26/14:08
 **/
@RestController
@RequestMapping("/customerQuitLog")
public class CustomerQuitLogApi {
    private final RestTemplate restTemplate;
    CustomerQuitLogApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @ApiOperation(value = "根据用户Id查询退会记录")
    @PostMapping(value = "/getCustomerQuitLogList",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel getCustomerQuitLogList(@RequestBody Map<String,Object> map,@CurrentUserId Long userId) {
        map.put("userId",userId);
        if(map.get("page")==null || map.get("limit")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/customerQuitLogApi/queryCustomerQuitLogList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }
}
