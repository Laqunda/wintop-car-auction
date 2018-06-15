package com.wintop.ms.carauction.controller;

/**
 * @author zhangzijuan
 * @Description:保证金的接口
 * @date 2018-03-20
 */

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import io.swagger.annotations.Api;
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

@RestController
@RequestMapping(value = "/deposit")
@Api(tags = "保证金相关接口",value = "保证金接口")
public class CarCustomerDepositApi {
    private final RestTemplate restTemplate;
    CarCustomerDepositApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 获取用户的保证金缴纳记录
     *@Author:zhangzijuan
     *@date 2018/3/20
     *@param:map
     */
    @ApiOperation(value = "获取用户的保证金缴纳记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户Id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "page",value = "当前页数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "limit",value = "每页显示的条数",required = true,paramType = "query",dataType = "int")
    })
    @PostMapping(value = "/getUserDepositRecord",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel getUserDepositRecord(@RequestBody Map<String,Object> map) {
        if(map.get("page")==null || map.get("limit")==null ||map.get("userId")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carCustomerDeposit/queryUserDepositByUserId"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }


    /**
     * @Author: lizhaoyang
     * @Description: 获取保证金缴费记录列表
     * @Date:2018/3/26/18:50
     **/
    @ApiOperation(value = "获取用户的保证金缴费记录")
    @PostMapping(value = "/getDepositDetailList",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel getCarBidRecordList(@RequestBody Map<String,Object> map,@CurrentUserId Long userId) {
        map.put("userId",userId);
        if(map.get("page")==null || map.get("limit")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/depositDetailApi/queryDepositDetailList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * @Author: lizhaoyang
     * @Description: 获取保证金冻结记录列表
     * @Date:2018/3/27/20:15
     **/
    @ApiOperation(value = "获取用户的获取保证金冻结记录")
    @PostMapping(value = "/getDepositFreezeList",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel getDepositFreezeList(@RequestBody Map<String,Object> map,@CurrentUserId Long userId) {
        map.put("userId",userId);
        if(map.get("page")==null || map.get("limit")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/depositDetailApi/queryDepositFreezeList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }
}
