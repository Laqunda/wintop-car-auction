package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import com.wintop.ms.carauction.util.utils.ParamValidUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
 * @Description:
 * @date 2018-03-19
 */
@RestController
@RequestMapping("/order")
public class CarOrderApi {
    private final RestTemplate restTemplate;
    CarOrderApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 根据用户Id查询用的订单
     *@Author:zhangzijuan
     *@date 2018/3/19
     *@param:map
     */
    @ApiOperation(value = "根据用户Id查询用的订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户Id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "page",value = "当前页数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "limit",value = "每页显示的条数",required = true,paramType = "query",dataType = "int")
    })
    @PostMapping(value = "/getUserOrder",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel getUserOrder(@RequestBody Map<String,Object> map) {
        if(map.get("page")==null || map.get("limit")==null ||map.get("userId")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carOrderBoss/selectOrderByUserId"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 订单列表
     *@date 2018/3/19
     *@param:map
     */
    @ApiOperation(value = "订单列表")
    @RequestMapping(value = "/selectOrderList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel selectOrderList(@RequestBody Map<String,Object> map,@CurrentUserId Long userId) {
        map.put("userId",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carOrderBoss/selectOrderList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 争议订单列表
     *@date 2018/3/26
     *@param:map
     */
    @ApiOperation(value = "争议订单列表")
    @RequestMapping(value = "/selectBreachOrderList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel selectBreachOrderList(@RequestBody Map<String,Object> map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carOrderBoss/selectBreachOrderList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 查询订单详情信息
     *@date 2018/3/19
     *@param:map
     */
    @ApiOperation(value = "查询订单详情信息")
    @RequestMapping(value = "/selectOrderDetailById",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel selectOrderDetailById(@RequestBody Map<String,Object> map) {
        if(map.get("id")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carOrderBoss/selectOrderDetailById"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 查询订单争议信息
     *@date 2018/3/19
     *@param:map
     */
    @ApiOperation(value = "查询订单争议信息")
    @RequestMapping(value = "/selectOrderBreach",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel selectOrderBreach(@RequestBody Map<String,Object> map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carOrderBoss/selectOrderBreach"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 查询订单轨迹
     *@date 2018/3/19
     *@param:map
     */
    @ApiOperation(value = "查询订单轨迹")
    @RequestMapping(value = "/selectOrderLogList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel selectOrderLogList(@RequestBody Map<String,Object> map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carOrderBoss/selectOrderLogList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.LIST);
    }

    /**
     * 保存线下订单支付确认
     *@date 2018/3/26
     *@param:map
     */
    @ApiOperation(value = "保存线下订单支付确认")
    @RequestMapping(value = "/saveOfflineOrderPay",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel saveOfflineOrderPay(@RequestBody Map<String,Object> map, @CurrentUserId Long userId) {
        if(map.get("orderId")==null || map.get("payFee")==null ||map.get("payWay")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("createPerson",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carOrderBoss/saveOfflineOrderPay"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 争议需支付违约金订单列表
     *@date 2018/3/26
     *@param:map
     */
    @ApiOperation(value = "争议需支付违约金订单列表")
    @RequestMapping(value = "/selectBreachPayOrderList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel selectBreachPayOrderList(@RequestBody Map<String,Object> map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carOrderBoss/selectBreachPayOrderList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 保存订单违约金支付确认
     *@date 2018/3/26
     *@param:map
     */
    @ApiOperation(value = "保存订单违约金支付确认")
    @RequestMapping(value = "/saveBreachOrderPay",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel saveBreachOrderPay(@RequestBody Map<String,Object> map, @CurrentUserId Long userId) {
        if(ParamValidUtil.invalidMapParam(map,"orderId") || ParamValidUtil.invalidMapParam(map,"breachId") || ParamValidUtil.invalidMapParam(map,"payWay")){
            new ResultModel(true, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("createPerson",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carOrderBoss/saveBreachOrderPay"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 手续回传待确认订单列表
     *@date 2018/3/26
     *@param:map
     */
    @ApiOperation(value = "手续回传待确认订单列表")
    @RequestMapping(value = "/selectProcedurePassbackOrderList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel selectProcedurePassbackOrderList(@RequestBody Map<String,Object> map,@CurrentUserId Long userId) {
        map.put("userId",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carOrderBoss/selectProcedurePassbackOrderList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 查询订单手续信息
     *@date 2018/3/26
     *@param:map
     */
    @ApiOperation(value = "查询订单手续信息")
    @RequestMapping(value = "/selectProcedurePassback",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel selectProcedurePassback(@RequestBody Map<String,Object> map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carOrderBoss/selectProcedurePassback"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.LIST);
    }

    /**
     * 审核回传手续信息
     *@date 2018/3/26
     *@param:map
     */
    @ApiOperation(value = "审核回传手续信息")
    @RequestMapping(value = "/saveProcedurePassback",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel saveProcedurePassback(@RequestBody Map<String,Object> map, @CurrentUserId Long userId) {
        if(map.get("orderId")==null){
            new ResultModel(true, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("userId",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carOrderBoss/saveProcedurePassback"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    @ApiOperation(value = "查询订单打印信息")
    @RequestMapping(value = "/selectOrderPrintInfo",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel selectOrderPrintInfo(@RequestBody Map<String,Object> map) {
        if(map.get("id")==null){
            return new ResultModel(true, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carOrderBoss/selectOrderPrintInfo"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    @ApiOperation(value = "订单用户信息补全")
    @RequestMapping(value = "/updateOrderUser",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel updateOrderUser(@RequestBody Map<String,Object> map) {
        if(map.get("userId")==null || map.get("id")==null){
            return new ResultModel(true, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carOrderBoss/updateOrderUser"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }
}
