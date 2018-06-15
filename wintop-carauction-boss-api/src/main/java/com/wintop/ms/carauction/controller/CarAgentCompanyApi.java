package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.annotation.RequestAuth;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.TokenManager;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.core.model.TokenModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

/**
 * @author admin
 * @Description:代办公司信息
 * @date 2018-03-15
 */
@RestController
@RequestMapping("/agentCompany")
public class CarAgentCompanyApi {
    @Autowired
    private TokenManager tokenManager;
    private final RestTemplate restTemplate;
    CarAgentCompanyApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    /**
     * 查询所有有效代办公司
     *@Author:admin
     *@date 2018/3/14
     *@param:map
     */
    @ApiOperation(value = "查询所有有效代办公司")
    @RequestMapping(value = "/selectAllAgentCompany",
            consumes = "application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")

    @AuthUserToken
    @RequestAuth(false)
    public ResultModel selectAllAgentCompany() {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/agentCompany/selectAllAgentCompany"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(null),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.LIST);
    }

    /**
     * 查询代办公司
     *@Author:admin
     *@date 2018/3/14
     *@param:map
     */
    @ApiOperation(value = "查询代办公司")
    @RequestMapping(value = "/selectAgentCompanyList",
            consumes = "application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel selectAgentCompanyList(@RequestBody Map<String,Object> map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/agentCompany/selectAgentCompanyList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 新增代办公司
     *@Author:admin
     *@date 2018/3/14
     *@param:map
     */
    @ApiOperation(value = "保存代办公司")
    @RequestMapping(value = "/saveAgentCompany",
            consumes = "application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel saveAgentCompany(@RequestBody Map<String,Object> map, @CurrentUserId Long userId) {
        map.put("createPerson",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/agentCompany/saveAgentCompany"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 修改代办公司
     *@Author:admin
     *@date 2018/3/14
     *@param:map
     */
    @ApiOperation(value = "修改代办公司")
    @RequestMapping(value = "/updateAgentCompany",
            consumes = "application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel updateAgentCompany( @RequestBody Map<String,Object> map, @CurrentUserId Long userId) {
        map.put("updatePerson",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/agentCompany/updateAgentCompany"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 删除代办公司
     *@Author:admin
     *@date 2018/3/14
     *@param:map
     */
    @ApiOperation(value = "删除代办公司")
    @RequestMapping(value = "/deleteAgentCompany",
            consumes = "application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel deleteAgentCompany( @RequestBody Map<String,Object> map, @CurrentUserId Long userId) {
        map.put("delPerson",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/agentCompany/deleteAgentCompany"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 查询代办公司
     *@Author:admin
     *@date 2018/3/14
     *@param:map
     */
    @ApiOperation(value = "查询代办公司")
    @RequestMapping(value = "/selectAgentCompany",
            consumes = "application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel selectAgentCompany( @RequestBody Map<String,Object> map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/agentCompany/selectAgentCompany"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 修改代办公司负责人信息
     *@Author:admin
     *@date 2018/3/14
     *@param:map
     */
    @ApiOperation(value = "修改代办公司负责人信息")
    @RequestMapping(value = "/updateAgentCompanyHandler",
            consumes = "application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel updateAgentCompanyHandler(@RequestBody Map<String,Object> map) {
        if(map.get("id")==null || map.get("userId")==null){
            return new ResultModel(true, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/agentCompany/updateAgentCompanyHandler"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }
}
