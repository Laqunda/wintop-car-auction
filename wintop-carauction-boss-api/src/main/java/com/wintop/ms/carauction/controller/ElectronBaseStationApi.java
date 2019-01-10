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
     * 查询基站列表
     * @return
     */
    @AuthPublic
    @RequestMapping(value = "/selectBaseStationList",produces="application/json; charset=UTF-8")
    public ResultModel selectBaseStationList() {
        Map<String,Object> map = new HashMap();
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/electronBaseStation/selectBaseStationList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 保存基站
     * @param managerId
     * @param map
     * @return
     */
    @RequestMapping(value = "/saveBaseStation",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    @RequestAuth(false)
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
    @RequestMapping(value = "/updateBaseStation",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    @RequestAuth(false)
    public ResultModel updateBaseStation(@CurrentUserId Long managerId, @RequestBody Map<String,Object> map) {
        if(map.get("id")==null){
            return new ResultModel(false, ResultCode.NO_OBJECT.value(),ResultCode.NO_OBJECT.getRemark(),null);
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
    @RequestMapping(value = "/deleteBaseStation",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    @RequestAuth(false)
    public ResultModel deleteBaseStation(@CurrentUserId Long managerId, @RequestBody Map<String,Object> map) {
        if(map.get("id")==null){
            return new ResultModel(false, ResultCode.NO_OBJECT.value(),ResultCode.NO_OBJECT.getRemark(),null);
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
}
