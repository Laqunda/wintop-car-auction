package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUser;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultStatus;
import com.wintop.ms.carauction.core.entity.CarManagerUser;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/***
 * 车辆管理------
 * 发车
 * 修改
 * 删除
 * 复制
 */
@RestController
@RequestMapping("autoManager")
public class AutoManagerApi {

    private final RestTemplate restTemplate;
    private ResultModel resultModel;
    private static final Logger logger = LoggerFactory.getLogger(AutoManagerApi.class);

    public static final String initAuto_URL = Constants.ROOT+"/service/carAuto/initAuto";
    public static final String submitAuth_URL = Constants.ROOT+"/service/carAuto/submitAuth";
    public static final String delAuto_URL = Constants.ROOT+"/service/carAuto/del";
    public static final String getLastAutoDraft_URL = Constants.ROOT+"/service/carAuto/getLastAutoDraft";
    public static final String getAutoProcedurefo_URL = Constants.ROOT+"/service/procedures/getProcedurefo";
    public static final String getAutoAuction_URL = Constants.ROOT+"/service/carAutoAuction/getByAutoId";
    /**查询车辆发布完成情况*/
    public static final String getAutoPublishStatus_URL = Constants.ROOT+"/service/carAuto/getAutoPublishStatus";




    AutoManagerApi(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }


    @PostMapping(value = "getLastAutoDraft")
    @ApiOperation(value = "查询用户最进编辑的草稿车辆信息",notes = "查询客户最新发布的车辆信息，是否需要扫描行驶本还是提醒客户是否需要继续上次发布")
    @AuthUserToken
    public ResponseEntity<ResultModel> getLastAutoDraft(@CurrentUserId Long userId){
        logger.info("查询用户最新草稿车辆信息");
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(getLastAutoDraft_URL))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(userId),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }

    @PostMapping(value = "initAuto")
    @AuthUserToken
    @ApiOperation(value = "初始化一辆新车记录" ,notes = "初始化一辆车辆信息")
    public ResponseEntity<ResultModel> initAuto(@CurrentUser CarManagerUser managerUser,
                                                @ApiParam(value = "二手车、新车",required = true) @RequestParam String ifNew){
        logger.info("初始化一辆新车记录");

        String auctionType;
        if (managerUser==null || managerUser.getRoleTypeId()==null){
            //如果不是中心或经销店人员，则无权限新增车辆
            return new ResponseEntity<>(ResultModel.error(ResultStatus.UN_AUTHORIZED),HttpStatus.OK);
        }else if (managerUser.getRoleTypeId()==2L){
            //中心--线下
            auctionType = "2";
        }else if (managerUser.getRoleTypeId()==3L){
            //经销店--线上
            auctionType = "1";
        }else {
            //如果不是中心或经销店人员，则无权限新增车辆
            return new ResponseEntity<>(ResultModel.error(ResultStatus.UN_AUTHORIZED),HttpStatus.OK);
        }

        JSONObject object = new JSONObject();
        object.put("ifNew",ifNew);
        object.put("userId",managerUser.getId());
        object.put("auctionType",auctionType);
        object.put("userMobile",managerUser.getUserPhone());
        object.put("userName",managerUser.getUserName());
        object.put("regionId",managerUser.getRegionId());
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(initAuto_URL))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(object),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }


    @PostMapping(value = "submitAuth")
    @AuthUserToken
    @ApiOperation(value = "提交审核车辆",notes = "提交车辆为待审核状态")
    public ResponseEntity<ResultModel> submitAuth(@CurrentUser CarManagerUser managerUser, @ApiParam(value = "车辆id") @RequestParam Long autoId){
        ResponseEntity responseEntity = null;
        try {
            logger.info("提交车辆为待审核状态");

            JSONObject object = new JSONObject();
            object.put("updateUser",managerUser.getId());
            object.put("autoId",autoId);
            object.put("userMobile",managerUser.getUserPhone());
            object.put("userName",managerUser.getUserName());
            ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                    RequestEntity
                            .post(URI.create(submitAuth_URL))
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(object),JSONObject.class);
            responseEntity = ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
        }catch (Exception e){
            e.printStackTrace();
            responseEntity = new ResponseEntity(ResultModel.error(ResultStatus.SERVICE_ERROR),HttpStatus.INTERNAL_SERVER_ERROR);
        }finally {
            return responseEntity;
        }
    }

    @PostMapping(value = "del")
    @AuthUserToken
    @ApiOperation(value = "删除车辆")
    public ResponseEntity<ResultModel> del(@CurrentUser CarManagerUser managerUser, @ApiParam(value = "车辆id") @RequestParam Long autoId){
        ResponseEntity responseEntity = null;
        try {
            logger.info("删除车辆");

            JSONObject object = new JSONObject();
            object.put("updateUser",managerUser.getId());
            object.put("autoId",autoId);
            object.put("userMobile",managerUser.getUserPhone());
            object.put("userName",managerUser.getUserName());
            ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                    RequestEntity
                            .post(URI.create(delAuto_URL))
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(object),JSONObject.class);
            responseEntity = ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
        }catch (Exception e){
            e.printStackTrace();
            responseEntity = new ResponseEntity(ResultModel.error(ResultStatus.SERVICE_ERROR),HttpStatus.INTERNAL_SERVER_ERROR);
        }finally {
            return responseEntity;
        }
    }


    @PostMapping(value = "getProcedurefo")
    @AuthUserToken
    @ApiOperation(value = "获取车辆手续信息",notes = "根据车辆id获取车辆的手续信息")
    public ResponseEntity<ResultModel> getProcedurefo(@ApiParam(value = "车辆id",required = true) @RequestParam Long autoId){
        logger.info("获取车辆手续信息");
        JSONObject object = new JSONObject();
        object.put("carId",autoId);

        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(getAutoProcedurefo_URL))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(object),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }

    @PostMapping(value = "getAuctionInfo")
    @ApiOperation(value = "获取车辆拍卖信息",notes = "根据车辆id获取车辆的拍卖信息")
    public ResponseEntity<ResultModel> getAuctionInfo(@ApiParam(value = "车辆id",required = true) @RequestParam Long autoId){

        logger.info("获取车辆拍卖信息");
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(getAutoAuction_URL))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(autoId),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }

    @PostMapping(value = "getPulishStatus")
    @ApiOperation(value = "查询车辆的发车进度完成情况",notes = "根据车辆id查询车辆的发车进度完成情况")
    @AuthUserToken
    public ResponseEntity<ResultModel> getPulishStatus(@CurrentUser CarManagerUser managerUser, @ApiParam(value = "车辆id",required = true) @RequestParam String autoId){

        logger.info("查询车辆的发车进度完成情况");
        JSONObject object = new JSONObject();
        object.put("autoId",autoId);
        object.put("roleTypeId",managerUser.getRoleTypeId());
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(getAutoPublishStatus_URL))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(object),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }
}
