package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AppApiVersion;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * 评估采购单 信息操作处理
 *
 * @author ruoyi
 * @date 2019-05-05
 */
@RestController
@RequestMapping(value = "carAssessOrder")
public class CarAssessOrderApi {
    private static final Logger logger = LoggerFactory.getLogger(CarAssessOrderApi.class);
    private final RestTemplate restTemplate;

    CarAssessOrderApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    /**
     * 查询评估采购单列表
     */
    @ApiOperation(value = "查询评估采购单列表")
    @RequestMapping(value = "/list",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    @AppApiVersion(value = "2.0")
    public ResultModel list(@RequestBody Map map) {
        if (map.get("page") == null || map.get("limit") == null) {
            return new ResultModel(false, ResultCode.NO_PARAM.value(), ResultCode.NO_PARAM.getRemark(), null);
        }
//        map.put("managerId",managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT + "/service/carAssessOrder/list"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map), JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }


    /**
     * 新增保存评估采购单
     */
    @ApiOperation(value = "新增保存评估采购单")
    @RequestMapping(value = "/add",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    @AppApiVersion(value = "2.0")
    public ResultModel addSave(@CurrentUserId Long managerId, @RequestBody Map map) {
        map.put("managerId",managerId);

        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT + "/service/carAssessOrder/add"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map), JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }


    /**
     * 修改保存评估采购单
     */
    @ApiOperation(value = "修改保存评估采购单")
    @RequestMapping(value = "/edit",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    @AppApiVersion(value = "2.0")
    public ResultModel editSave(@CurrentUserId Long managerId, @RequestBody Map map) {
        map.put("managerId",managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT + "/service/carAssessOrder/edit"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map), JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 采购单审核通过/不通过
     */
    @ApiOperation(value = "采购单审核通过/不通过")
    @RequestMapping(value = "/editStatus",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    @AppApiVersion(value = "2.0")
    public ResultModel editStatus(@CurrentUserId Long managerId, @RequestBody Map map) {
        map.put("managerId",managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT + "/service/carAssessOrder/editStatus"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map), JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 删除评估采购单
     */
    @ApiOperation(value = "删除评估采购单")
    @RequestMapping(value = "/remove",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    @AppApiVersion(value = "2.0")
    public ResultModel remove(@CurrentUserId Long managerId, @RequestBody Map map) {
        map.put("managerId",managerId);

        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT + "/service/carAssessOrder/remove"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map), JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }
    @ApiOperation(value = "根据条件查询采购申请")
    @RequestMapping(value = "/selectListByType",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    //@AuthUserToken
    @AppApiVersion(value = "2.0")
    public ResultModel selectListByType( @RequestBody Map map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT + "/service/carAssessOrder/selectListByType"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map), JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }


}
