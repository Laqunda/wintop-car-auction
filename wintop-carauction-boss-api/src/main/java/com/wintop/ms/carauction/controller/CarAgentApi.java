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
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangzijuan
 * @Description:代办相关
 * @date 2018-03-24
 */
@RestController
@RequestMapping("/carAgent")
public class CarAgentApi {
    private final RestTemplate restTemplate;
    CarAgentApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    /**
     * 根据参数获取代办列表
     *@Author:zhangzijuan
     *@date 2018/3/24
     *@param:map
     */
    @ApiOperation(value = "根据参数获取代办列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "当前页数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "limit",value = "每页显示的条数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "auctionType",value = "拍卖类型（1线上 2线下）",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "moveToWhere",value = "迁往何处（1本市外迁均可，2只能外迁，3只能本市'）",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "startTime",value = "开始时间 例如：2018-03-24",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "endTime",value = "结束时间 例如：2018-03-24",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "status",value = "代办状态（1过户事宜确定中、2出牌确认中、3交档确认中、4提档确认中、5手续上传中、6手续回传确认中 7手续回传不通过）",required = false,paramType = "query",dataType = "string"),
    })
    @PostMapping(value = "/getCarAgentList",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel getCarAgentList(@CurrentUserId Long managerId,@RequestBody Map<String,Object> map) {
        if(map.get("page")==null || map.get("limit")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("managerId",managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carAgent/selectListByParam"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }
    /**
     * 查询代办的所有状态
     *@Author:zhangzijuan
     *@date 2018/3/23
     *@param:map
     */
    @ApiOperation(value = "查询代办的所有状态")
    @PostMapping(value = "/selectAllAgentStatus",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel selectAllAgentStatus() {
        Map<String,Object> map=new HashMap<>();
        map.put("type","3");
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/relateStatus/selectAllStatus"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.LIST);
    }


    /**
     * 根据参数获取代办违约处理列表
     *@Author:zhangzijuan
     *@date 2018/3/24
     *@param:map
     */
    @ApiOperation(value = "根据参数获取代办违约处理列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "当前页数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "limit",value = "每页显示的条数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "auctionType",value = "拍卖类型（1线上 2线下）",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "moveToWhere",value = "迁往何处（1本市外迁均可，2只能外迁，3只能本市'）",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "startTime",value = "开始时间 例如：2018-03-24",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "endTime",value = "结束时间 例如：2018-03-24",required = false,paramType = "query",dataType = "string"),
    })
    @PostMapping(value = "/getBreachCarAgentList",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel getBreachCarAgentList(@CurrentUserId Long managerId,@RequestBody Map<String,Object> map) {
        if(map.get("page")==null || map.get("limit")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("noStatus","9");
        map.put("isBreach","1");
        map.put("managerId",managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carAgent/selectListByParam"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }
}
