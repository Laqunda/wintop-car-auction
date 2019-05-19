package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.*;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.CarManagerUser;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

/**
 * 查博士日志 信息操作处理
 *
 * @author ruoyi
 * @date 2019-05-05
 */
@RestController
@RequestMapping(value = "carChaboshiLog")
public class CarChaboshiLogApi {

    private static final Logger logger = LoggerFactory.getLogger(CarChaboshiLogApi.class);

    private ResultModel resultModel;
    private final RestTemplate restTemplate;
    CarChaboshiLogApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 查询查博士日志列表
     */
    @ApiOperation(value = "查询查博士日志列表")
    @PostMapping(value = "/list",produces="application/json; charset=UTF-8")
    @AuthUserToken
    @RequestAuth(value = false)
    public ResultModel list(@RequestBody Map<String,Object> map) {
        if(map.get("page")==null || map.get("limit")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carChaboshiLog/list"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return  ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 查询查博士日志列表
     */
    @ApiOperation(value = "查询查博士日志详情")
    @PostMapping(value = "/detail",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel detail(@RequestBody Map<String,Object> map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carChaboshiLog/detail"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return  ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 新增保存查博士日志
     */
    @ApiOperation(value = "新增保存查博士日志")
    @RequestMapping(value = "/add",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel addSave(@CurrentUserId Long managerId, @RequestBody Map<String,Object> map) {

        map.put("managerId",managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carChaboshiLog/add"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }


    /**
     * 修改保存查博士日志
     */
    @ApiOperation(value = "修改查博士日志")
    @RequestMapping(value = "/edit",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel editSave(@CurrentUserId Long managerId, @RequestBody Map<String,Object> map) {
        map.put("managerId",managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carChaboshiLog/edit"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 删除查博士日志
     */
    @ApiOperation(value = "删除查博士日志")
    @RequestMapping(value = "/remove",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel remove(@CurrentUserId Long managerId,@RequestBody Map<String,Object> map) {
        map.put("managerId",managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carChaboshiLog/remove"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 查博士查询
     */
    @ApiOperation(value = "查博士查询")
    @RequestMapping(value = "/vinSearch",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel vinSearch(@CurrentUser CarManagerUser managerUser, @RequestBody Map<String,Object> map) {
        map.put("userId",managerUser.getId());
        map.put("userName",managerUser.getUserName());
//        map.put("storeId",managerUser.getDepartmentId());
        map.put("userType","2");//店铺人员
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carChaboshiLog/vinSearch"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }


}
