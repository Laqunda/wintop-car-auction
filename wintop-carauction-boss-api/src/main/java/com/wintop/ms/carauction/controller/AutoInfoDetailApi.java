package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.config.ResultStatus;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/***
 * 车辆-----------基本信息
 */
@RestController
@RequestMapping("/autoDetail")
public class AutoInfoDetailApi {
    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(AutoInfoDetailApi.class);

    public static final String update_URL = Constants.ROOT+"/autoDetail/update";
    public static final String getAutoDetail_URL = Constants.ROOT+"/autoDetail/getByAutoId";
    public static final String updateRemark_URL = Constants.ROOT+"/autoDetail/updateRemark";
    //获取车辆全部配置项
    public static final String getAllConf_URL= Constants.ROOT + "/autoConfTitle/getAllApp";

    AutoInfoDetailApi(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }


    @PostMapping(value = "getBaseInfo")
    @ApiOperation(value = "获取车辆基础信息",notes = "根据车辆id获取车辆的基本信息")
    public ResultModel getBaseInfo(@ApiParam(value = "车辆id",required = true) @RequestParam Long autoId){
        logger.info("获取车辆基础信息");
        ResultModel resultModel=null;
        try {
            JSONObject object = new JSONObject();
            object.put("autoId",autoId);
            ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                    RequestEntity
                            .post(URI.create(getAutoDetail_URL))
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(object),JSONObject.class);
            resultModel = ApiUtil.getResultModel(response, ApiUtil.OBJECT);
        }catch (Exception e){
            e.printStackTrace();
            resultModel =new ResultModel(false, ResultCode.NET_EXCEPTION.value(),ResultCode.NET_EXCEPTION.getRemark());
        }finally {
            return resultModel;
        }
    }

    @PostMapping(value = "updateInfo")
    @ApiOperation(value = "修改车辆基本信息",notes = "修改保存车辆基本信息")
    @AuthUserToken
    public ResultModel updateInfo(@CurrentUserId Long userId,
                                                  @ApiParam(value = "车辆信息",required = true) @RequestBody JSONObject object){
        ResultModel resultModel=null;
        logger.info("修改车辆基本信息");
        try {
            ResponseEntity<JSONObject> response;
            if (object.get("autoStyle")!=null) {
                //获取当前车型在精友中的全称
                JSONObject object_ = new JSONObject();
                object_.put("id", object.getString("autoStyle"));
                response = this.restTemplate.exchange(
                        RequestEntity
                                .post(URI.create(Constants.ID_GET_VEHICLEDETAIL_URL))
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(object_), JSONObject.class);
                if (response.getStatusCode() == HttpStatus.OK) {
                    JSONObject body = response.getBody();
                    if (body != null && "000000".equals(body.getString("code"))) {
                        String commonName = body.getJSONObject("result").getJSONObject("vehConfig").getJSONObject("vcBase").getString("commonName");
                        resultModel = ResultModel.ok();
                        object.put("autoInfoName",commonName);
                    }
                }
            }

            object.put("updateUser",userId);
            response = this.restTemplate.exchange(
                    RequestEntity
                            .post(URI.create(update_URL))
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(object),JSONObject.class);
            resultModel = ApiUtil.getResultModel(response, ApiUtil.OBJECT);
        }catch (Exception e){
            e.printStackTrace();
            resultModel  =new ResultModel(false, ResultCode.NET_EXCEPTION.value(),ResultCode.NET_EXCEPTION.getRemark());
        }finally {
            return resultModel;
        }
    }

    @PostMapping(value = "updateRemark")
    @ApiOperation(value = "修改车辆备注信息",notes = "修改保存车辆备注信息")
    @AuthUserToken
    public ResultModel updateRemark(@CurrentUserId Long userId,
                                                    @ApiParam(value = "车辆id+备注信息",required = true) @RequestBody JSONObject object){
        logger.info("修改车辆备注信息");
        ResultModel resultModel= null;
        try {
            object.put("updateUser",userId);
            ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                    RequestEntity
                            .post(URI.create(updateRemark_URL))
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(object),JSONObject.class);
            resultModel = ApiUtil.getResultModel(response,ApiUtil.OBJECT);
        }catch (Exception e){
            e.printStackTrace();
            resultModel  =new ResultModel(false, ResultCode.NET_EXCEPTION.value(),ResultCode.NET_EXCEPTION.getRemark());
        }finally {
            return resultModel;
        }
    }


    @PostMapping(value = "getVehicleListByVin")
    @ApiOperation(value = "根据vin码匹配车型列表",notes = "根据vin码匹配车型列表")
    public ResultModel getDetailByVIN(@ApiParam(value = "车辆id",required = true) @RequestParam Long autoId,@ApiParam(value = "车辆vin码",required = true) @RequestParam String vin){
        logger.info("根据vin码匹配车型列表");
        ResultModel resultModel = null;
        try {
            JSONObject object = new JSONObject();
            object.put("autoId",autoId);

            ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                    RequestEntity
                            .post(URI.create(getAutoDetail_URL))
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(object),JSONObject.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                JSONObject object1 = response.getBody().getJSONObject("result");
                if (object1 != null) {
                    object.put("vin", vin);
                    response = this.restTemplate.exchange(
                            RequestEntity
                                    .post(URI.create(Constants.VIN_GET_VEHICLELIST_URL))
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .body(object), JSONObject.class);
                    if (response.getStatusCode() == HttpStatus.OK) {
                        JSONObject body = response.getBody();
                        if (body != null && "000000".equals(body.getString("code"))) {
                            JSONObject object2 = new JSONObject();
                            JSONArray returnStyleList = new JSONArray();
                            JSONArray styleList = body.getJSONObject("result").getJSONArray("vehicleList");
                            for (Object obj : styleList){
                                JSONObject styleObj = (JSONObject) obj;
                                JSONObject styleObj_1 = new JSONObject();
                                styleObj_1.put("autoBrand",styleObj.getString("brandName"));
                                styleObj_1.put("autoBrandCn",styleObj.getString("brandName"));
                                styleObj_1.put("autoStyle",styleObj.getString("vehicleId"));
                                styleObj_1.put("autoStyleCn",styleObj.getString("vehicleName"));
                                styleObj_1.put("autoSeriesCn",styleObj.getString("familyName"));
                                returnStyleList.add(styleObj_1);
                            }
                            object2.put("styleList",returnStyleList);
                            object2.put("autoId",autoId);
                            object2.put("id",object1.get("id"));

                            resultModel = ResultModel.ok(object2);
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            resultModel  =new ResultModel(false, ResultCode.NET_EXCEPTION.value(),ResultCode.NET_EXCEPTION.getRemark());
        }finally {
            return resultModel;
        }
    }

}