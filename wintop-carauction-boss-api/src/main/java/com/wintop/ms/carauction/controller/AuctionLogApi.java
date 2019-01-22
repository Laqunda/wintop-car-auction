package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

/**
 * @author zhangzijuan
 * @Description:电子竞价器相关api
 * @date 2019-01-15
 */
@RestController
@RequestMapping("/auction")
public class AuctionLogApi {
    private final RestTemplate restTemplate;
    AuctionLogApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 根据参数查询出价记录列表
     *@Author:zhangzijuan
     *@date 2019/1/15
     *@param:map
     */
    @ApiOperation(value = "根据参数查询出价记录列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "auctionTimesName",value = "场次名称",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "boardName",value = "拍牌号",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "carInfo",value = "车辆编号/车牌号",required = false,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "carId",value = "车辆id",required = false,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "page",value = "当前页数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "limit",value = "每页显示的条数",required = true,paramType = "query",dataType = "int")
    })
    @PostMapping(value = "/getAuctionLogList",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel getAuctionLogList(@CurrentUserId Long userId,@RequestBody Map<String,Object> map) {
        if(map.get("page")==null || map.get("limit")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("userId",userId);
        map.put("priceType",'0');
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/electronAuction/selectLogList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }


    /**
     * 根据参数查询加价记录列表
     *@Author:zhangzijuan
     *@date 2019/1/15
     *@param:map
     */
    @ApiOperation(value = "根据参数查询加价记录列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "auctionTimesName",value = "场次名称",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "carInfo",value = "车辆编号/车牌号",required = false,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "carId",value = "车辆id",required = false,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "page",value = "当前页数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "limit",value = "每页显示的条数",required = true,paramType = "query",dataType = "int")
    })
    @PostMapping(value = "/getAddLogList",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel getAddLogList(@CurrentUserId Long userId,@RequestBody Map<String,Object> map) {
        if(map.get("page")==null || map.get("limit")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("userId",userId);
        map.put("priceType",'2');
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/electronAuction/selectLogList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 根据参数查询加价幅度调整列表
     *@Author:zhangzijuan
     *@date 2019/1/15
     *@param:map
     */
    @ApiOperation(value = "根据参数查询加价幅度调整列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "auctionTimesName",value = "场次名称",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "carInfo",value = "车辆编号/车牌号",required = false,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "carId",value = "车辆id",required = false,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "page",value = "当前页数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "limit",value = "每页显示的条数",required = true,paramType = "query",dataType = "int")
    })
    @PostMapping(value = "/getRangeLogList",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel getRangeLogList(@CurrentUserId Long userId,@RequestBody Map<String,Object> map) {
        if(map.get("page")==null || map.get("limit")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("userId",userId);
        map.put("priceType","3");
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/electronAuction/selectLogList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }
}
