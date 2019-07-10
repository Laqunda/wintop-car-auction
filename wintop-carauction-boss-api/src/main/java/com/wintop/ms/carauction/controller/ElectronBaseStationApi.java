package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthPublic;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.annotation.RequestAuth;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import com.wintop.ms.carauction.util.utils.IdWorker;
import com.wintop.ms.carauction.util.utils.ParamValidUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/electronBaseStation")
public class ElectronBaseStationApi {

    private final RestTemplate restTemplate;
    ElectronBaseStationApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 查询基站分页列表
     * @return
     */
    @ApiOperation(value = "查询基站分页列表")
    @RequestMapping(value = "/selectBaseStationList",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel selectBaseStationList(@CurrentUserId Long managerId, @RequestBody Map<String,Object> map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/electronBaseStation/selectBaseStationList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 查询所有基站列表
     * @return
     */
    @ApiOperation(value = "查询所有基站列表")
    @RequestMapping(value = "/selectAllStationList",produces="application/json; charset=UTF-8")
    @AuthUserToken
    @RequestAuth(false)
    public ResultModel selectAllStationList() {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/electronBaseStation/selectAllStationList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(new HashMap()),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.LIST);
    }

    /**
     * 保存基站
     * @param managerId
     * @param map
     * @return
     */
    @ApiOperation(value = "保存基站")
    @RequestMapping(value = "/saveBaseStation",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel saveBaseStation(@CurrentUserId Long managerId, @RequestBody Map<String,Object> map) {
        map.put("createPerson",managerId);
        map.put("createTime",new Date());
        map.put("delFlag","1");
        map.put("id", IdWorker.getInstance().nextId());
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/electronBaseStation/saveBaseStation"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 更新基站
     * @param managerId
     * @param map
     * @return
     */
    @ApiOperation(value = "更新基站")
    @RequestMapping(value = "/updateBaseStation",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel updateBaseStation(@CurrentUserId Long managerId, @RequestBody Map<String,Object> map) {
        if(map.get("id")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("modifyPerson",managerId);
        map.put("modifyTime",new Date());
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/electronBaseStation/updateBaseStation"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 删除基站
     * @param managerId
     * @param map
     * @return
     */
    @ApiOperation(value = "删除基站")
    @RequestMapping(value = "/deleteBaseStation",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel deleteBaseStation(@CurrentUserId Long managerId, @RequestBody Map<String,Object> map) {
        if(map.get("id")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("delPerson",managerId);
        map.put("delTime",new Date());
        map.put("delFlag","2");
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/electronBaseStation/deleteBaseStation"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 查询基站
     * @param map
     * @return
     */
    @ApiOperation(value = "查询基站")
    @RequestMapping(value = "/selectBaseStation",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel selectBaseStation(@RequestBody Map<String,Object> map) {
        if(map.get("id")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/electronBaseStation/selectBaseStation"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }
}
