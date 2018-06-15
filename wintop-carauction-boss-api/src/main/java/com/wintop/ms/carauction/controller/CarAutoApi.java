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
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
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
     * 查询车辆信息
     * @param map
     * @return
     */
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
    public ResultModel selectDetailByCarId(@RequestBody Map<String,Object> map){
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carAuto/selectDetailByCarId"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

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
}
