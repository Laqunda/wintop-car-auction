package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.wintop.ms.carauction.core.annotation.AuthPublic;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.annotation.RequestAuth;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import com.wintop.ms.carauction.util.utils.ExcelUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangzijuan
 * @Description:PC车辆相关
 * @date 2018-03-22
 */
@RestController
@RequestMapping("/carAuto")
public class CarAutoApi {
    private final RestTemplate restTemplate;
    CarAutoApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    /**
     * 根据参数查询车辆列表
     *@Author:zhangzijuan
     *@date 2018/3/22
     *@param:map
     */
    @ApiOperation(value = "根据参数查询车辆列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "当前页数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "limit",value = "每页显示的条数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "storeId",value = "店铺id",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "sourceType",value = "车辆来源：1个人车源，2公务车，3 4S店置换，4店铺车，5试乘试驾车",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "ifNew",value = "车辆类型 1二手车，2新车",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "status",value = "状态id",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "auctionType",value = "竞拍类型 拍卖类型（1线上、2线下)",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "startTime",value = "发车开始时间  2018-03-13",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "endTime",value = "结束发车时间  2018-03-13",required = false,paramType = "query",dataType = "int")
    })
    @PostMapping(value = "/getCarAutoListByParam",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel getCarAutoListByParam(@CurrentUserId Long managerId,@RequestBody Map<String,Object> map) {
        if(map.get("page")==null || map.get("limit")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("managerId",managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carAuto/getCarAutoListByParam"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }


    /**
     * 根据参数查询草稿车辆列表
     *@Author:zhangzijuan
     *@date 2018/3/22
     *@param:map
     */
    @ApiOperation(value = "根据参数查询草稿车辆列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "当前页数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "limit",value = "每页显示的条数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "storeId",value = "店铺id",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "sourceType",value = "车辆来源：1个人车源，2公务车，3 4S店置换，4店铺车，5试乘试驾车",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "ifNew",value = "车辆类型 1二手车，2新车",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "status",value = "状态id",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "auctionType",value = "竞拍类型 拍卖类型（1线上、2线下)",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "startTime",value = "发车开始时间  2018-03-13",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "endTime",value = "结束发车时间  2018-03-13",required = false,paramType = "query",dataType = "int")
    })
    @PostMapping(value = "/getDraftCarAutoList",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel getDraftCarAutoList(@CurrentUserId Long managerId, @RequestBody Map<String,Object> map) {
        if(map.get("page")==null || map.get("limit")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("isEdit","1");
        map.put("managerId",managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carAuto/getCarAutoListByParam"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }


      /**
     * 根据参数查询交易关闭车辆列表
     *@Author:zhangzijuan
     *@date 2018/3/22
     *@param:map
     */
    @ApiOperation(value = "根据参数查询交易关闭车辆列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "当前页数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "limit",value = "每页显示的条数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "storeId",value = "店铺id",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "sourceType",value = "车辆来源：1个人车源，2公务车，3 4S店置换，4店铺车，5试乘试驾车",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "ifNew",value = "车辆类型 1二手车，2新车",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "auctionType",value = "竞拍类型 拍卖类型（1线上、2线下)",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "startTime",value = "发车开始时间  2018-03-13",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "endTime",value = "结束发车时间  2018-03-13",required = false,paramType = "query",dataType = "int")
    })
    @PostMapping(value = "/getCloseCarAutoList",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel getCloseCarAutoList(@CurrentUserId Long managerId,@RequestBody Map<String,Object> map) {
        if(map.get("page")==null || map.get("limit")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("status","18");
        map.put("managerId",managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carAuto/getCarAutoListByParam"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }
    /**
     * 根据参数查询流拍车辆列表
     *@Author:zhangzijuan
     *@date 2018/3/22
     *@param:map
     */
    @ApiOperation(value = "根据参数查询流拍车辆列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "当前页数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "limit",value = "每页显示的条数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "storeId",value = "店铺id",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "sourceType",value = "车辆来源：1个人车源，2公务车，3 4S店置换，4店铺车，5试乘试驾车",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "ifNew",value = "车辆类型 1二手车，2新车",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "auctionType",value = "竞拍类型 拍卖类型（1线上、2线下)",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "startTime",value = "发车开始时间  2018-03-13",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "endTime",value = "结束发车时间  2018-03-13",required = false,paramType = "query",dataType = "int")
    })
    @PostMapping(value = "/getFlowWayCarAutoList",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel getFlowWayCarAutoList(@CurrentUserId Long managerId,@RequestBody Map<String,Object> map) {
        if(map.get("page")==null || map.get("limit")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("status","19");
        map.put("managerId",managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carAuto/getCarAutoListByParam"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }
    /**
     * 根据参数查询待议价车辆列表
     *@Author:zhangzijuan
     *@date 2018/3/22
     *@param:map
     */
    @ApiOperation(value = "根据参数查询待议价车辆列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "当前页数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "limit",value = "每页显示的条数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "storeId",value = "店铺id",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "sourceType",value = "车辆来源：1个人车源，2公务车，3 4S店置换，4店铺车，5试乘试驾车",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "ifNew",value = "车辆类型 1二手车，2新车",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "auctionType",value = "竞拍类型 拍卖类型（1线上、2线下)",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "startTime",value = "发车开始时间  2018-03-13",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "endTime",value = "结束发车时间  2018-03-13",required = false,paramType = "query",dataType = "int")
    })
    @PostMapping(value = "/getBargainCarAutoList",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel getBargainCarAutoList(@CurrentUserId Long managerId,@RequestBody Map<String,Object> map) {
        if(map.get("page")==null || map.get("limit")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("status","9");
        map.put("managerId",managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carAuto/getCarAutoListByParam"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }
    /**
     * 查询车辆的所有状态
     *@Author:zhangzijuan
     *@date 2018/3/23
     *@param:map
     */
    @ApiOperation(value = "查询车辆的所有状态")
    @PostMapping(value = "/selectAllCarStatus",produces="application/json; charset=UTF-8")
    @AuthUserToken
    @RequestAuth(false)
    public ResultModel selectAllCarStatus() {
        Map<String,Object> map=new HashMap<>();
        map.put("type","2");
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/relateStatus/selectAllStatus"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.LIST);
    }


    /**
     * 根据参数查询待议价车辆列表
     *@Author:zhangzijuan
     *@date 2018/3/22
     *@param:map
     */
    @ApiOperation(value = "根据参数查询撤回审核车辆列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "当前页数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "limit",value = "每页显示的条数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "storeId",value = "店铺id",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "sourceType",value = "车辆来源：1个人车源，2公务车，3 4S店置换，4店铺车，5试乘试驾车",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "ifNew",value = "车辆类型 1二手车，2新车",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "auctionType",value = "竞拍类型 拍卖类型（1线上、2线下)",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "startTime",value = "发车开始时间  2018-03-13",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "endTime",value = "结束发车时间  2018-03-13",required = false,paramType = "query",dataType = "int")
    })
    @PostMapping(value = "/getRevokeApproveCarAutoList",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel getRevokeApproveCarAutoList(@CurrentUserId Long managerId,@RequestBody Map<String,Object> map) {
        if(map.get("page")==null || map.get("limit")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("status","4");
        map.put("managerId",managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carAuto/getCarAutoListByParam"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 根据参数查询发车待审核车辆列表
     *@Author:zhangzijuan
     *@date 2018/3/22
     *@param:map
     */
    @ApiOperation(value = "根据参数查询发车待审核车辆列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "当前页数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "limit",value = "每页显示的条数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "storeId",value = "店铺id",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "sourceType",value = "车辆来源：1个人车源，2公务车，3 4S店置换，4店铺车，5试乘试驾车",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "ifNew",value = "车辆类型 1二手车，2新车",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "auctionType",value = "竞拍类型 拍卖类型（1线上、2线下)",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "startTime",value = "发车开始时间  2018-03-13",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "endTime",value = "结束发车时间  2018-03-13",required = false,paramType = "query",dataType = "int")
    })
    @PostMapping(value = "/getApproveCarAutoList",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel getApproveCarAutoList(@CurrentUserId Long managerId,@RequestBody Map<String,Object> map) {
        if(map.get("page")==null || map.get("limit")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("status","2");
        map.put("managerId",managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carAuto/getCarAutoListByParam"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }


    /**
     * 审核撤回审批的车辆
     *@Author:zhangzijuan
     *@date 2018/3/22
     *@param:map
     */
    @ApiOperation(value = "审核撤回审批的车辆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "carId",value = "车辆Id",required = true,dataType = "long"),
            @ApiImplicitParam(name = "status",value = "审批状态 1 通过 2 不通过",required = true,dataType = "string"),
            @ApiImplicitParam(name = "msg",value = "审批留言",required = false,paramType = "query",dataType = "string")
    })
    @PostMapping(value = "/revokeApprove",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel revokeApprove(@CurrentUserId Long managerId,@RequestBody Map<String,Object> map) {
        map.put("managerId",managerId);
        if(map.get("carId")==null || map.get("status")==null|| map.get("msg")==null|| map.get("managerId")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carAuto/revokeApprove"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 审批发拍的车辆
     *@Author:zhangzijuan
     *@date 2018/3/22
     *@param:map
     */
    @ApiOperation(value = "审批发拍的车辆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "carId",value = "车辆Id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "status",value = "审批状态 1 通过 2 不通过",required = true,dataType = "string"),
            @ApiImplicitParam(name = "msg",value = "审批留言",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "auctionStartTime",value = "开拍时间 格式如：2018-03-23 12:00:00",required = false,dataType = "string"),
            @ApiImplicitParam(name = "openLimit",value = "开放范围id拼接",required = false,dataType = "string"),
            @ApiImplicitParam(name = "openLimitCn",value = "开放范围，空则全部开放",required = false,dataType = "string")
    })
    @PostMapping(value = "/approveCarAuto",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel approveCarAuto(@CurrentUserId Long managerId,@RequestBody Map<String,Object> map) {
        map.put("managerId",managerId);
        if(map.get("carId")==null || map.get("status")==null|| map.get("msg")==null|| map.get("managerId")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carAuto/approveCarAuto"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }
    /**
     * 通过车辆id查询车辆信息
     * @param map
     * @return
     */
    @ApiOperation(value ="通过车辆id查询车辆信息")
    @RequestMapping(value = "/selectByCarId",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthPublic
    @RequestAuth(false)
    public ResultModel selectByCarId(@RequestBody Map<String,Object> map){
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carAuto/selectByCarId"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }
    /**
     * 查询车辆信息
     * @param map
     * @return
     */
    @ApiOperation(value ="查询车辆信息")
    @RequestMapping(value = "/detail",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthPublic
    @RequestAuth(false)
    public ResultModel detail(@RequestBody Map<String,Object> map){
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carAuto/detail"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 查询车辆基本信息
     * @param map
     * @return
     */
    @RequestMapping(value = "/selectDetailByCarId",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthPublic
    @RequestAuth(false)
    @ApiOperation(value ="查询车辆基本信息")
    public ResultModel selectDetailByCarId(@RequestBody Map<String,Object> map){
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carAuto/selectDetailByCarId"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    @ApiOperation(value ="查询车辆手续信息")
    @RequestMapping(value = "/selectProcedureByCarId",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ResultModel selectProcedureByCarId(@RequestBody Map<String,Object> map){
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/procedures/getAutoProceduresByCarId"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 线上车辆导出
     * @param request
     * @param rep
     * @param carAutoNo
     * @param autoInfoName
     * @param licenseNumber
     * @param createUserName
     * @param vin
     * @param storeId
     * @param carType
     * @param startTime
     * @param endTime
     */
    @ApiOperation(value = "线上车辆导出")
    @AuthPublic
    @PostMapping( value = "/exportOnlineCarList" )
    public void exportOnlineCarList(HttpServletRequest request, HttpServletResponse rep,
                                    @RequestParam("carAutoNo") String carAutoNo,
                                    @RequestParam("autoInfoName") String autoInfoName,
                                    @RequestParam("licenseNumber") String licenseNumber,
                                    @RequestParam("createUserName") String createUserName,
                                    @RequestParam("vin") String vin,
                                    @RequestParam("storeId") String storeId,
                                    @RequestParam("carType") String carType,
                                    @RequestParam("startTime") String startTime,
                                    @RequestParam("endTime") String endTime,
                                    @RequestParam("authorization") String authorization) {
        String[] headers = {"车辆编号", "车辆名称", "车牌号", "上拍数", "车辆归属城市", "初次上牌","公里数","VIN","所属店铺","车辆类型","车辆来源","价格信息","竞拍类型","开拍信息","成交价","服务费","是否代办","车辆状态","发车时间","发车人"};
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-mm-dd HH:mm:ss");
        Map<String, Object> map = Maps.newHashMap();
        map.put("carAutoNo", carAutoNo);
        map.put("autoInfoName", autoInfoName);
        map.put("licenseNumber", licenseNumber);
        map.put("createUserName", createUserName);
        map.put("vin", vin);
        map.put("storeId", storeId);
        map.put("carType", carType);
        if (StringUtils.isNotEmpty(startTime)) {
            map.put("startTime", sdf.format(startTime));
        }
        if (StringUtils.isNotEmpty(endTime)) {
            map.put("endTime", sdf.format(endTime));
        }
        if (StringUtils.isNotEmpty(authorization)) {
            map.put("managerId", StringUtils.split(authorization,"_")[0]);
        }
        HSSFWorkbook workbook = ExcelUtil.createStartExcel("在线车辆记录", headers);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT + "/service/carAuto/getCarAutoAllListByParam"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map), JSONObject.class);
        ApiUtil.getResultModel(response, ApiUtil.OBJECT);
        HSSFSheet sheet = workbook.getSheetAt(0);
        if (response.getStatusCode() == HttpStatus.OK) {
            JSONObject obj = response.getBody();
            JSONArray result = obj.getJSONObject("result").getJSONArray("list");
            if (result != null && result.size() > 0) {
                for (int i = 0; i < result.size(); i++) {
                    JSONObject object = result.getJSONObject(i);
                    HSSFRow itemRow = sheet.createRow(i + 2);
                    HSSFCell c0 = itemRow.createCell(0);
                    c0.setCellValue(object.getString("carAutoNo"));

                    HSSFCell c12 = itemRow.createCell(1);
                    c12.setCellValue(object.getString("autoInfoName"));

                    HSSFCell c1 = itemRow.createCell(2);
                    c1.setCellValue(object.getString("licenseNumber"));

                    HSSFCell c2 = itemRow.createCell(3);
                    c2.setCellValue(object.getString("auctionNum"));

                    HSSFCell c3 = itemRow.createCell(4);
                    c3.setCellValue(object.getString("vehicleAttributionCity"));

                    String beginRegisterDate = "";
                    if (object.getString("beginRegisterDate") != null) {
                        beginRegisterDate = StringUtils.left(sdf.format(new Date(object.getLong("beginRegisterDate"))),4);
                    }

                    HSSFCell c4 = itemRow.createCell(5);
                    c4.setCellValue(beginRegisterDate);

                    HSSFCell c5 = itemRow.createCell(6);
                    c5.setCellValue(object.getString("mileage"));

                    HSSFCell c6 = itemRow.createCell(7);
                    c6.setCellValue(object.getString("vin"));

                    HSSFCell c7 = itemRow.createCell(8);
                    c7.setCellValue(object.getString("carStoreNme"));

                    String ifNew = "";
                    if (object.getString("ifNew") != null) {
                        ifNew = object.getString("ifNew").equals("1") ? "二手车" : "新车";
                    }
                    HSSFCell c9 = itemRow.createCell(9);
                    c9.setCellValue(ifNew);

                    String sourceType = "";
                    if (object.getString("sourceType") != null) {
                        switch (object.getString("sourceType")) {
                            case "1":
                                sourceType = "个人车源";
                                break;
                            case "2":
                                sourceType = "公务车";
                                break;
                            case "3":
                                sourceType = "4S店置换";
                                break;
                            case "4":
                                sourceType = "店铺车";
                                break;
                            case "5":
                                sourceType = "试乘试驾车";
                                break;
                        }
                    }
                    HSSFCell c10 = itemRow.createCell(10);
                    c10.setCellValue(sourceType);

                    HSSFCell c11 = itemRow.createCell(11);
                    c11.setCellValue(object.getString("startingPrice"));

                    String auctionType = "";
                    if (object.getString("auctionType") != null) {
                        auctionType = object.getString("auctionType").equals("1") ? "线上" : "线下";
                    }
                    HSSFCell c14 = itemRow.createCell(12);
                    c14.setCellValue(auctionType);

                    HSSFCell c15 = itemRow.createCell(13);
                    c15.setCellValue(object.getString("auctionStartTime"));

                    HSSFCell c17 = itemRow.createCell(14);
                    c17.setCellValue(object.getString("transactionFee"));

                    HSSFCell c18 = itemRow.createCell(15);
                    c18.setCellValue(object.getString("serviceFee"));

                    String ifAgent = "";
                    if (object.getString("ifAgent") != null) {
                        ifAgent = object.getString("ifAgent").equals("1") ? "二手车" : "新车";
                    }
                    HSSFCell c19 = itemRow.createCell(16);
                    c19.setCellValue(ifAgent);

                    HSSFCell c20 = itemRow.createCell(17);
                    c20.setCellValue(object.getString("statusName"));

                    String createTime = "";
                    if (object.getString("createTime") != null) {
                        createTime = sdf.format(new Date(object.getLong("createTime")));
                    }
                    HSSFCell c21 = itemRow.createCell(18);
                    c21.setCellValue(createTime);

                    HSSFCell c22 = itemRow.createCell(19);
                    c22.setCellValue(object.getString("createUserName"));
                }
            }
        }
        String filename = String.valueOf(ExcelUtil.processFileName(request, "在线车辆信息列表")).concat(".xls");
        rep.setContentType("application/vnd.ms-excel;charset=utf-8");
        rep.setHeader("Content-disposition", "attachment;filename=" + filename);
        try {
            ServletOutputStream ouputStream = rep.getOutputStream();
            workbook.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param request
     * @param rep
     * @param saleFlag
     * @param auctionType
     * @param title
     * @param cityId
     * @param auctionStartTimeBegin
     * @param auctionStartTimeEnd
     */
    @ApiOperation(value = "现场车辆导出")
    @AuthPublic
    @PostMapping( value = "/exportUnderCarList" )
    public void exportUnderCarList(HttpServletRequest request, HttpServletResponse rep,
                                   @RequestParam("saleFlag") String saleFlag,
                                   @RequestParam("auctionType") String auctionType,
                                   @RequestParam("title") String title,
                                   @RequestParam("cityId") String cityId,
                                   @RequestParam("auctionStartTimeBegin") String auctionStartTimeBegin,
                                   @RequestParam("auctionStartTimeEnd") String auctionStartTimeEnd,
                                   @RequestParam("authorization") String authorization) {
        String[] headers = {"辆编号","车辆标题","车牌号","车所属店铺","车辆来源","开拍时间","开拍地点","场次主题","起拍价","保留价","最后出价","车商号","状态","初登日期","竞拍次数","发车人","发车人账号"};
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-mm-dd HH:mm:ss");
        Map<String, Object> map = Maps.newHashMap();
        map.put("saleFlag",saleFlag);
        map.put("auctionType",auctionType);
        map.put("title",title);
        map.put("cityId",cityId);

        if (StringUtils.isNotEmpty(auctionStartTimeBegin)) {
            map.put("auctionStartTimeBegin", sdf.format(auctionStartTimeBegin));
        }
        if (StringUtils.isNotEmpty(auctionStartTimeEnd)) {
            map.put("auctionStartTimeEnd", sdf.format(auctionStartTimeEnd));
        }
        if (StringUtils.isNotEmpty(authorization)) {
            map.put("managerId", StringUtils.split(authorization,"_")[0]);
        }
        HSSFWorkbook workbook = ExcelUtil.createStartExcel("线下车辆记录", headers);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT + "/service/carAuto/getCarAutoAllListByParam"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map), JSONObject.class);
        ApiUtil.getResultModel(response, ApiUtil.OBJECT);
        HSSFSheet sheet = workbook.getSheetAt(0);
        if (response.getStatusCode() == HttpStatus.OK) {
            JSONObject obj = response.getBody();
            JSONArray result = obj.getJSONObject("result").getJSONArray("list");
            if (result != null && result.size() > 0) {
                for (int i = 0; i < result.size(); i++) {
                    JSONObject object = result.getJSONObject(i);
                    HSSFRow itemRow = sheet.createRow(i + 2);

                    HSSFCell c0 = itemRow.createCell(0);
                    c0.setCellValue(object.getString("carAutoNo"));

                    HSSFCell c1 = itemRow.createCell(1);
                    c1.setCellValue(object.getString("autoInfoName"));

                    HSSFCell c2 = itemRow.createCell(2);
                    c2.setCellValue(object.getString("licenseNumber"));

                    HSSFCell c3 = itemRow.createCell(3);
                    c3.setCellValue(object.getString("'carStoreNme'"));

                    String sourceType = "";
                    if (object.getString("sourceType") != null) {
                        switch (object.getString("sourceType")) {
                            case "1":
                                sourceType = "个人车源";
                                break;
                            case "2":
                                sourceType = "公务车";
                                break;
                            case "3":
                                sourceType = "4S店置换";
                                break;
                            case "4":
                                sourceType = "店铺车";
                                break;
                            case "5":
                                sourceType = "试乘试驾车";
                                break;
                        }
                    }
                    HSSFCell c4 = itemRow.createCell(4);
                    c4.setCellValue(sourceType);

                    String auctionStartTime = "";
                    if (object.getString("auctionStartTime") != null) {
                        auctionStartTime = sdf.format(new Date(object.getLong("auctionStartTime")));
                    }
                    HSSFCell c5 = itemRow.createCell(5);
                    c5.setCellValue(auctionStartTime);

                    HSSFCell c6 = itemRow.createCell(6);
                    c6.setCellValue(object.getString("cityName"));

                    HSSFCell c7 = itemRow.createCell(7);
                    c7.setCellValue(object.getString("title"));

                    HSSFCell c8 = itemRow.createCell(8);
                    c8.setCellValue(object.getString("startingPrice"));

                    HSSFCell c9 = itemRow.createCell(9);
                    c9.setCellValue(object.getString("reservePrice"));

                    HSSFCell c10 = itemRow.createCell(10);
                    c10.setCellValue(object.getString("maxBidFee"));

                    HSSFCell c11 = itemRow.createCell(11);
                    c11.setCellValue(object.getString("userNum"));

                    HSSFCell c12 = itemRow.createCell(12);
                    c12.setCellValue(object.getString("statusName"));

                    HSSFCell c13 = itemRow.createCell(13);
                    String beginRegisterDate = "";
                    if (object.getString("beginRegisterDate") != null) {
                        beginRegisterDate = sdf.format(new Date(object.getLong("beginRegisterDate")));
                    }
                    c13.setCellValue(beginRegisterDate);

                    HSSFCell c14 = itemRow.createCell(14);
                    c14.setCellValue(object.getString("auctionNum"));

                    HSSFCell c15 = itemRow.createCell(15);
                    c15.setCellValue(object.getString("publishUserName"));

                    HSSFCell c16 = itemRow.createCell(16);
                    c16.setCellValue(object.getString("publishUserMobile"));
                }
            }
        }
        String filename = String.valueOf(ExcelUtil.processFileName(request, "线下车辆信息列表")).concat(".xls");
        rep.setContentType("application/vnd.ms-excel;charset=utf-8");
        rep.setHeader("Content-disposition", "attachment;filename=" + filename);
        try {
            ServletOutputStream ouputStream = rep.getOutputStream();
            workbook.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param request
     * @param rep
     * @param saleFlag
     * @param salesStartDate
     * @param salesEndDate
     * @param carAutoNo
     * @param autoInfoName
     * @param licenseNumber
     * @param createUserName
     * @param vin
     */
    @ApiOperation(value = "零售车辆导出")
    @AuthPublic
    @PostMapping( value = "/exportRetailCarList" )
    public void exportRetailCarList(HttpServletRequest request, HttpServletResponse rep,
                                    @RequestParam("saleFlag") String saleFlag,
                                    @RequestParam("salesStartDate") String salesStartDate,
                                    @RequestParam("salesEndDate") String salesEndDate,
                                    @RequestParam("carAutoNo") String carAutoNo,
                                    @RequestParam("autoInfoName") String autoInfoName,
                                    @RequestParam("licenseNumber") String licenseNumber,
                                    @RequestParam("createUserName") String createUserName,
                                    @RequestParam("vin") String vin,
                                    @RequestParam("authorization") String authorization) {
        String[] headers = {"车辆编号", "车辆名称", "车牌号", "上拍数", "车辆归属城市", "初次上牌","公里数","VIN","所属店铺","销售人员","车辆类型","车辆来源","成交价","付款方式","是否代办","发车时间","发车人"};
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-mm-dd HH:mm:ss");
        Map<String, Object> map = Maps.newHashMap();
        map.put("saleFlag",saleFlag);

        map.put("carAutoNo",carAutoNo);
        map.put("autoInfoName",autoInfoName);
        map.put("licenseNumber",licenseNumber);
        map.put("createUserName",createUserName);
        map.put("vin",vin);

        if (StringUtils.isNotEmpty(salesStartDate)) {
            map.put("auctionStartTimeBegin", sdf.format(salesStartDate));

        }
        if (StringUtils.isNotEmpty(salesEndDate)) {
            map.put("auctionStartTimeEnd", sdf.format(salesEndDate));
        }
        if (StringUtils.isNotEmpty(authorization)) {
            map.put("managerId", StringUtils.split(authorization,"_")[0]);
        }
        HSSFWorkbook workbook = ExcelUtil.createStartExcel("零售车辆记录", headers);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT + "/service/carAuto/getCarAutoAllListByParam"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map), JSONObject.class);
        ApiUtil.getResultModel(response, ApiUtil.OBJECT);
        HSSFSheet sheet = workbook.getSheetAt(0);
        if (response.getStatusCode() == HttpStatus.OK) {
            JSONObject obj = response.getBody();
            JSONArray result = obj.getJSONObject("result").getJSONArray("list");
            if (result != null && result.size() > 0) {
                for (int i = 0; i < result.size(); i++) {
                    JSONObject object = result.getJSONObject(i);
                    HSSFRow itemRow = sheet.createRow(i + 2);
                    HSSFCell c0 = itemRow.createCell(0);
                    c0.setCellValue(object.getString("carAutoNo"));

                    HSSFCell c12 = itemRow.createCell(1);
                    c12.setCellValue(object.getString("autoInfoName"));

                    HSSFCell c1 = itemRow.createCell(2);
                    c1.setCellValue(object.getString("licenseNumber"));

                    HSSFCell c2 = itemRow.createCell(3);
                    c2.setCellValue(object.getString("auctionNum"));

                    HSSFCell c3 = itemRow.createCell(4);
                    c3.setCellValue(object.getString("vehicleAttributionCity"));

                    String beginRegisterDate = "";
                    if (object.getString("beginRegisterDate") != null) {
                        beginRegisterDate = StringUtils.left(sdf.format(new Date(object.getLong("beginRegisterDate"))),4);
                    }
                    HSSFCell c4 = itemRow.createCell(5);
                    c4.setCellValue(beginRegisterDate);

                    HSSFCell c5 = itemRow.createCell(6);
                    c5.setCellValue(object.getString("mileage"));

                    HSSFCell c6 = itemRow.createCell(7);
                    c6.setCellValue(object.getString("vin"));

                    HSSFCell c7 = itemRow.createCell(8);
                    c7.setCellValue(object.getString("carStoreNme"));

                    HSSFCell c8 = itemRow.createCell(9);
                    c8.setCellValue(object.getString("salesConsultant"));

                    HSSFCell c9 = itemRow.createCell(10);
                    String ifNew = "";
                    if (object.getString("ifNew") != null) {
                        ifNew = object.getString("ifNew").equals("1") ? "二手车" : "新车";
                    }
                    c9.setCellValue(ifNew);

                    String sourceType = "";
                    if (object.getString("sourceType") != null) {
                        switch (object.getString("sourceType")) {
                            case "1":
                                sourceType = "个人车源";
                                break;
                            case "2":
                                sourceType = "公务车";
                                break;
                            case "3":
                                sourceType = "4S店置换";
                                break;
                            case "4":
                                sourceType = "店铺车";
                                break;
                            case "5":
                                sourceType = "试乘试驾车";
                                break;
                        }
                    }
                    HSSFCell c10 = itemRow.createCell(11);
                    c10.setCellValue(sourceType);

                    HSSFCell c11 = itemRow.createCell(12);
                    c11.setCellValue(object.getString("transactionFee"));

                    HSSFCell c13 = itemRow.createCell(13);
                    String paymentType = "";
                    if (object.getString("paymentType") != null) {
                        paymentType = object.getString("paymentType").equals("1") ? "全款" : "按揭";
                    }
                    c13.setCellValue(paymentType);

                    HSSFCell c14 = itemRow.createCell(14);
                    String ifAgent = "";
                    if (object.getString("ifAgent") != null) {
                        ifAgent = object.getString("ifAgent").equals("1") ? "需要" : "不需要";
                    }
                    c14.setCellValue(ifAgent);

                    String publishTime = "";
                    if (object.getString("publishTime") != null) {
                        publishTime = sdf.format(new Date(object.getLong("publishTime")));
                    }
                    HSSFCell c15 = itemRow.createCell(15);
                    c15.setCellValue(publishTime);

                    HSSFCell c16 = itemRow.createCell(16);
                    c16.setCellValue(object.getString("publishUserName"));
                }
            }
        }
        String filename = String.valueOf(ExcelUtil.processFileName(request, "零售车辆信息列表")).concat(".xls");
        rep.setContentType("application/vnd.ms-excel;charset=utf-8");
        rep.setHeader("Content-disposition", "attachment;filename=" + filename);
        try {
            ServletOutputStream ouputStream = rep.getOutputStream();
            workbook.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
