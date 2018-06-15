package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import com.wintop.ms.carauction.util.utils.RedisTokenManager;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
 * @Description:会员认证信息
 * @date 2018-03-20
 */
@RestController
@RequestMapping("/customerAuth")
public class CarCustomerAuthApi {
    @Autowired
    private RedisTokenManager tokenManager;
    private final RestTemplate restTemplate;
    CarCustomerAuthApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 获取用户的认证信息列表
     *@Author:zhangzijuan
     *@date 2018/3/19
     *@param:map
     */
    @ApiOperation(value = "获取用户的认证信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "searchName",value = "用户名或电话",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "page",value = "当前页数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "limit",value = "每页显示的条数",required = true,paramType = "query",dataType = "int")
    })
    @PostMapping(value = "/getUserAuthList",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel getUserAuthList(@RequestBody Map<String,Object> map) {
        if(map.get("page")==null || map.get("limit")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/customerAuth/selectUserAuthList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }
    /**
     * 根据id查询用户认证信息
     * @return
     */
    @ApiOperation(value = "根据id查询用户认证信息")
    @ApiImplicitParam(name = "id",value = "认证信息id",required = true,paramType = "query",dataType = "long")
    @PostMapping(value = "/getUserAuthById",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel getUserAuthById(@RequestBody Map<String,Object> map) {
        if(map.get("authId")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/customerAuth/selectByPrimaryKey"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map.get("authId")),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    @ApiOperation(value = "认证信息审核")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户信息Id，多个用逗号拼接",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "authStatus",value = "2审核通过，-1审核不通过",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "authMsg",value = "审批留言",required = true,paramType = "query",dataType = "string")
    })
    @PostMapping(value = "/approveUserAuth",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel approveUserAuth(@CurrentUserId Long managerId,@RequestBody Map<String,Object> map) {
        if(map.get("userId")==null || map.get("authStatus")==null || map.get("authMsg")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("managerId",managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/customerAuth/approveUserAuth"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        if(response.getStatusCode()== HttpStatus.OK) {
            JSONObject obj = response.getBody();
            boolean success = obj.getBoolean("success");
            if (success) {
                Map<String,Object> mapMessage=new HashMap<>();
                mapMessage.put("userId",map.get("userId"));
                //2=买家
                mapMessage.put("userType","1");
                //打开类型 7=认证界面
                mapMessage.put("openObjType","7");
                mapMessage.put("openObjId",map.get("userId"));
                mapMessage.put("title","认证信息审核");
                if ("2".equals(map.get("authStatus"))){
                    mapMessage.put("content","认证信息审核通过");
                }else if ("-1".equals(map.get("authStatus"))){
                    mapMessage.put("content","认证信息审核未通过，原因："+map.get("authMsg")+"，如有疑问，请联系客服。");
                }
                         this.restTemplate.exchange(
                            RequestEntity
                                .post(URI.create(Constants.ROOT+"/carMessage/sendMessageByUserId"))
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(mapMessage),JSONObject.class);
            }
        }
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }
}
