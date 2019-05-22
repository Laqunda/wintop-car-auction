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

    @AuthPublic
    @PostMapping( value = "/exportOnlineCarList" )
    public void exportOnlineCarList(HttpServletRequest request, HttpServletResponse rep,@RequestBody Map<String,Object> map) {
        String[] headers = {"车辆编号", "车辆名称", "车牌号", "上拍数", "车辆归属城市", "初次上牌","公里数","VIN","所属店铺","车辆类型","车辆来源","价格信息","竞拍类型","开拍信息","竞拍结果","是否代办","车辆状态","发车信息"};
        HSSFWorkbook workbook = ExcelUtil.createStartExcel("在线车辆记录", headers);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT + "/service/managerUser/selectManagerUserAllList"))
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

                    HSSFCell c4 = itemRow.createCell(5);
                    c4.setCellValue(StringUtils.split(object.getString("beginRegisterDate"),"-")[0]);

                    HSSFCell c5 = itemRow.createCell(6);
                    c5.setCellValue(object.getString("mileage"));

                    HSSFCell c6 = itemRow.createCell(7);
                    c6.setCellValue(object.getString("vin"));

                    HSSFCell c7 = itemRow.createCell(8);
                    c7.setCellValue(object.getString("carStoreNme"));

                    HSSFCell c8 = itemRow.createCell(9);
                    c8.setCellValue(object.getString("carStoreNme"));

                    HSSFCell c9 = itemRow.createCell(10);
                    c9.setCellValue(object.getString("ifNew"));

                    HSSFCell c10 = itemRow.createCell(11);
                    c10.setCellValue(object.getString("sourceType"));

                    HSSFCell c11 = itemRow.createCell(12);
                    c11.setCellValue(object.getString("startingPrice"));

                    HSSFCell c13 = itemRow.createCell(13);
                    c13.setCellValue(object.getString("reservePrice"));

                    HSSFCell c14 = itemRow.createCell(14);
                    c14.setCellValue(object.getString("auctionType"));

                    HSSFCell c15 = itemRow.createCell(15);
                    c15.setCellValue(object.getString("auctionStartTime"));

                    HSSFCell c16 = itemRow.createCell(16);
                    c16.setCellValue(object.getString("title"));

                    HSSFCell c17 = itemRow.createCell(17);
                    c17.setCellValue(object.getString("transactionFee"));

                    HSSFCell c18 = itemRow.createCell(18);
                    c18.setCellValue(object.getString("serviceFee"));

                    HSSFCell c19 = itemRow.createCell(19);
                    c19.setCellValue(object.getString("ifAgent"));

                    HSSFCell c20 = itemRow.createCell(20);
                    c20.setCellValue(object.getString("statusName"));

                    HSSFCell c21 = itemRow.createCell(21);
                    c21.setCellValue(object.getString("createTime"));

                    HSSFCell c22 = itemRow.createCell(22);
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

}
