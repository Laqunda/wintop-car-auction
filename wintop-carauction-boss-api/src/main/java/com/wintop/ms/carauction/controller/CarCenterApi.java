package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.TokenManager;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.core.model.TokenModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

/**
 * @author admin
 * @Description:中心信息
 * @date 2018-03-15
 */
@RestController
@RequestMapping("/carCenter")
public class CarCenterApi {
    private final RestTemplate restTemplate;
    CarCenterApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    /**
     * 查询中心列表
     *@Author:admin
     *@date 2018/3/14
     *@param:map
     */
    @ApiOperation(value = "查询中心")
    @RequestMapping(value = "/selectCenterList",
            consumes = "application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel selectCenterList(@RequestBody Map<String,Object> map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carCenter/selectCenterList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 新增中心
     *@Author:admin
     *@date 2018/3/14
     *@param:map
     */
    @ApiOperation(value = "保存中心")
    @RequestMapping(value = "/saveCarCenter",
            consumes = "application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel saveCarCenter(@RequestBody Map<String,Object> map, @CurrentUserId Long userId) {
        if(map.get("regionId")==null){
            return new ResultModel(false, ResultCode.NO_REGION_ID.value(),ResultCode.NO_REGION_ID.getRemark(),null);
        }
        map.put("createPerson",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carCenter/saveCarCenter"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 修改中心
     *@Author:admin
     *@date 2018/3/14
     *@param:map
     */
    @ApiOperation(value = "修改中心")
    @RequestMapping(value = "/updateCarCenter",
            consumes = "application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel updateCarCenter( @RequestBody Map<String,Object> map, @CurrentUserId Long userId) {
        map.put("updatePerson",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carCenter/updateCarCenter"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 删除中心
     *@Author:admin
     *@date 2018/3/14
     *@param:map
     */
    @ApiOperation(value = "删除中心")
    @RequestMapping(value = "/deleteCarCenter",
            consumes = "application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel deleteCarCenter(@RequestBody Map<String,Object> map, @CurrentUserId Long userId) {
        map.put("delPerson",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carCenter/deleteCarCenter"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }


    /**
     * 保存或删除中心关联数据
     *@param:map
     */
    @ApiOperation(value = "保存或删除中心关联数据")
    @RequestMapping(value = "/saveOrDeleteRelation",
            consumes = "application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ResultModel saveOrDeleteRela(@RequestBody Map<String,Object> map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carCenter/saveOrDeleteRelation"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 查询中心
     *@Author:admin
     *@date 2018/3/14
     *@param:map
     */
    @ApiOperation(value = "查询中心")
    @RequestMapping(value = "/selectCarCenter",
            consumes = "application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel selectCarCenter(@RequestBody Map<String,Object> map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carCenter/selectCarCenter"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }
}
