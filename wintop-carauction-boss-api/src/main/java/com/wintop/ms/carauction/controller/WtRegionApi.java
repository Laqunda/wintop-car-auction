package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.annotation.RequestAuth;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultStatus;
import com.wintop.ms.carauction.core.model.ResultModel;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liangtingsen on 2018/3/6.
 * 地区城市api
 */
@RestController
@RequestMapping("wtRegion")
public class WtRegionApi {
    private final RestTemplate restTemplate;
    private ResultModel resultModel;

    private int port = 8185;
    private static final Logger logger = LoggerFactory.getLogger(WtRegionApi.class);
    /**
     * 根据id查询地区城市
     */
    String getByIdUrl = Constants.ROOT + "/wtRegion/getById";
    /**
     * 根据编号查询地区城市
     */
    String getByCodeUrl = Constants.ROOT + "/wtRegion/getByCode";
    /**
     * 查询全部城市地区列表
     */
    String getAllRegionUrl = Constants.ROOT + "/wtRegion/getAll";
    /**
     * 根据用户查询全部城市地区列表
     */
    String getAllRegionByUserUrl = Constants.ROOT + "/wtRegion/getAllByUser";


    WtRegionApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    /***
     * 根据城市编号获取城市信息
     * @param code
     * @return
     */
    @RequestMapping(value = "/getByCode", method = RequestMethod.POST)
    @ApiOperation(value = "根据code查询地区城市")
    @AuthUserToken
    @RequestAuth(value = false)
    public ResponseEntity<ResultModel> getByCode(@RequestParam String code) {
        ResponseEntity<JSONObject> resultResponseEntity = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(getByCodeUrl))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(code), JSONObject.class);
        JSONObject serviceResult = resultResponseEntity.getBody();
        ResultModel resultModel = null;
        //1、先判断返回网络状态码是否成功
        if (resultResponseEntity.getStatusCode() == HttpStatus.OK) {
            //2、判断业务处理结果是否成功
            if (serviceResult.get("success").toString() == "true") {
                JSONObject object = serviceResult.getJSONObject("result");
                if (object != null) {//已存在
                    resultModel = new ResultModel(true, ResultStatus.SUCCESS, object);
                } else {//不存在
                    resultModel = new ResultModel(true, ResultStatus.OBJECT_NOT_EXIST);
                }
                return new ResponseEntity<>(resultModel, HttpStatus.OK);
            } else {//异常情况
                resultModel = new ResultModel(true, ResultStatus.ERROR);
                return new ResponseEntity<>(resultModel, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            resultModel = new ResultModel(false, ResultStatus.ERROR);
            return new ResponseEntity<>(resultModel, resultResponseEntity.getStatusCode());
        }
    }

    /***
     * 获取全部城市地区信息
     * @return
     */
    @RequestMapping(value = "/getAllRegion", method = RequestMethod.POST)
    @ApiOperation(value = "查询全部地区城市")
    @AuthUserToken
    @RequestAuth(value = false)
    public ResponseEntity<ResultModel> getAllRegion() {
        String status = "1";
        ResponseEntity<JSONObject> resultResponseEntity = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(getAllRegionUrl))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(status), JSONObject.class);
        JSONObject serviceResult = resultResponseEntity.getBody();
        ResultModel resultModel = null;
        //1、先判断返回网络状态码是否成功
        if (resultResponseEntity.getStatusCode() == HttpStatus.OK) {
            //2、判断业务处理结果是否成功
            if (serviceResult.get("success").toString() == "true") {
                JSONArray object = serviceResult.getJSONArray("result");
                if (object != null) {//已存在
                    resultModel = new ResultModel(true, ResultStatus.SUCCESS, object);
                } else {//不存在
                    resultModel = new ResultModel(true, ResultStatus.OBJECT_NOT_EXIST);
                }
                return new ResponseEntity<>(resultModel, HttpStatus.OK);
            } else {//异常情况
                resultModel = new ResultModel(true, ResultStatus.ERROR);
                return new ResponseEntity<>(resultModel, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            resultModel = new ResultModel(false, ResultStatus.ERROR);
            return new ResponseEntity<>(resultModel, resultResponseEntity.getStatusCode());
        }
    }

    /***
     * 根据用户获取全部城市地区信息
     * 管理员 全部的地区
     * 非管理员 只能获取到自己地区的信息
     * @return
     */
    @RequestMapping(value = "/getAllRegionByUser", method = RequestMethod.POST)
    @ApiOperation(value = "根据用户--查询全部地区城市")
    @AuthUserToken
    @RequestAuth(value = false)
    public ResponseEntity<ResultModel> getAllRegionByUser(@CurrentUserId Long userId) {
        Map data = new HashMap();
        data.put("status", "1");
        data.put("userId", userId);
        ResponseEntity<JSONObject> resultResponseEntity = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(getAllRegionByUserUrl))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(data), JSONObject.class);
        JSONObject serviceResult = resultResponseEntity.getBody();
        ResultModel resultModel = null;
        //1、先判断返回网络状态码是否成功
        if (resultResponseEntity.getStatusCode() == HttpStatus.OK) {
            //2、判断业务处理结果是否成功
            if (serviceResult.get("success").toString() == "true") {
                JSONArray object = serviceResult.getJSONArray("result");
                if (object != null) {//已存在
                    resultModel = new ResultModel(true, ResultStatus.SUCCESS, object);
                } else {//不存在
                    resultModel = new ResultModel(true, ResultStatus.OBJECT_NOT_EXIST);
                }
                return new ResponseEntity<>(resultModel, HttpStatus.OK);
            } else {//异常情况
                resultModel = new ResultModel(true, ResultStatus.ERROR);
                return new ResponseEntity<>(resultModel, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            resultModel = new ResultModel(false, ResultStatus.ERROR);
            return new ResponseEntity<>(resultModel, resultResponseEntity.getStatusCode());
        }
    }
}
