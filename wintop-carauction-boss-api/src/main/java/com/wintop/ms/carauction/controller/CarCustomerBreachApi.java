package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.annotation.RequestAuth;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import com.wintop.ms.carauction.util.utils.ParamValidUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
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
 * @Description:违约信息
 * @date 2018-03-27
 */
@RestController
@RequestMapping("/customerBreach")
public class CarCustomerBreachApi {
    private final RestTemplate restTemplate;
    CarCustomerBreachApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 申请争议
     *@Author:zhangzijuan
     *@date 2018/3/27
     *@param:map
     */
    @ApiOperation(value = "申请争议")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId",value = "订单id",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "breachObjType",value = "争议类型",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "msg",value = "争议原因",required = true,paramType = "query",dataType = "int")
    })
    @PostMapping(value = "/applyBreach",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel applyBreach(@CurrentUserId Long managerId,@RequestBody Map<String,Object> map) {
        if(map.get("orderId")==null || map.get("breachObjType")==null || map.get("msg")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("managerId",managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/customerBreach/applyBreach"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 查询争议类型
     *@Author:zhangzijuan
     *@date 2018/3/27
     *@param:map
     */
    @ApiOperation(value = "查询争议类型")
    @PostMapping(value = "/getBreachTypeList",produces="application/json; charset=UTF-8")
    @RequestAuth(false)
    public ResultModel getBreachTypeList() {
        Map<String,Object> map=new HashMap<>();
        map.put("type","2");
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/customerBreach/getBreachStatusList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.LIST);
    }

    /**
     * 查询争议状态
     *@Author:zhangzijuan
     *@date 2018/3/27
     *@param:map
     */
    @ApiOperation(value = "查询争议状态")
    @PostMapping(value = "/getBreachStatusList",produces="application/json; charset=UTF-8")
    @RequestAuth(false)
    public ResultModel getBreachStatusList() {
        Map<String,Object> map=new HashMap<>();
        map.put("type","1");
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/customerBreach/getBreachStatusList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.LIST);
    }

    /**
     * 审核争议
     *@date 2018/3/27
     *@param:map
     */
    @ApiOperation(value = "审核争议")
    @PostMapping(value = "/breachApprove",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel breachApprove(@RequestBody Map<String,Object> map, @CurrentUserId Long userId) {
        if(ParamValidUtil.invalidMapParam(map,"orderId") || ParamValidUtil.invalidMapParam(map,"breachId") || ParamValidUtil.invalidMapParam(map,"status")){
            new ResultModel(true, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        String status = (String)map.get("status");
        if("356".contains(status)){
            if(ParamValidUtil.invalidMapParam(map,"amount")){
                new ResultModel(true, ResultCode.AMOUNT_ERROR.value(),ResultCode.AMOUNT_ERROR.getRemark()+"-amount",null);
            }
        }
        map.put("initiatAuthManager",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/customerBreach/breachApprove"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }
}
