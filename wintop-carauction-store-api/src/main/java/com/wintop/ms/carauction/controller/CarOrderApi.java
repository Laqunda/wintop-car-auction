package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.annotation.InfoCleanNotify;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultStatus;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/carOrder")
public class CarOrderApi {
    private ResultModel resultModel;
    private final RestTemplate restTemplate;
    CarOrderApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 根据条件查询车辆详情信息
     */
    @RequestMapping(value = "/selectCarDetail",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResponseEntity<ResultModel> selectCarDetail(@RequestBody Map<String,Object> map, @CurrentUserId Long userId) {
        map.put("customerId",userId);
        if (map==null || map.get("carId")==null){
            return new ResponseEntity<>(ResultModel.error(ResultStatus.PARAMETERS_ERROR),HttpStatus.OK);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carOrder/selectCarDetail"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.OBJECT);
    }

    /**
     * 查询历史车辆列表
     */
    @RequestMapping(value = "/selectHistoryCarList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    @InfoCleanNotify
    public ResultModel selectHistoryCarList(@RequestBody Map<String,Object> map, @CurrentUserId Long userId) {
        map.put("customerId",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carOrder/selectHistoryCarList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 根据条件查询历史车辆详情
     */
    @RequestMapping(value = "/selectHistoryCarDetail",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResponseEntity<ResultModel> selectHistoryCarDetail(@RequestBody Map<String,Object> map, @CurrentUserId Long userId) {
        map.put("customerId",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carOrder/selectHistoryCarDetail"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.OBJECT);
    }

    /**
     * 根据订单id查询订单的支付金额
     */
        @PostMapping(value = "/selectOrderById",
                consumes="application/json; charset=UTF-8",
                produces="application/json; charset=UTF-8")
        @AuthUserToken
        public ResponseEntity<ResultModel> selectOrderById(@RequestBody Map<String,Object> map) {
            if (map.get("orderId")==null){
                return new ResponseEntity<>(ResultModel.error(ResultStatus.PARAMETERS_ERROR), HttpStatus.OK);
            }
            ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                    RequestEntity
                            .post(URI.create(Constants.ROOT+"/service/carOrder/selectOrderById"))
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(map),JSONObject.class);
            return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.OBJECT);
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
    public ResponseEntity<ResultModel> saveProcedurePassback(@RequestBody Map<String,Object> map, @CurrentUserId Long userId) {
        if(map.get("orderId")==null){
            return new ResponseEntity<>(ResultModel.error(ResultStatus.PARAMETERS_ERROR), HttpStatus.OK);
        }
        map.put("userId",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carOrderBoss/saveProcedurePassback"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.OBJECT);
    }
    }
