package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUser;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.entity.CarManagerUser;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统消息
 */
@RestController
@RequestMapping("message")
public class CarMessageApi {

    private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(CarMessageApi.class);

    private ResultModel resultModel;

    private final RestTemplate restTemplate;

    /**查询本人系统消息*/
    String getMyMessageUrl = Constants.ROOT + "/carMessage/getMyMessage";
    /**查询本人未读系统消息条数*/
    String getMyNoReadCountUrl = Constants.ROOT + "/carMessage/getMyCount";
    /**系统消息设置已读*/
    String readMsgUrl = Constants.ROOT + "/carMessage/readMsg";
    /**读取详情*/
    String findByIdForAppUrl = Constants.ROOT + "/carMessage/findByIdForApp";

    CarMessageApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /***
     * 查询系统消息
     * @param managerUser
     * @return
     */
    @PostMapping(value = "/me")
    @ApiOperation(value = "查询用户系统消息")
    @AuthUserToken
    public ResponseEntity<ResultModel> getMyMessage(@CurrentUser CarManagerUser managerUser,
                                                    @RequestBody JSONObject object){
        Logger.info("查询用户系统消息");
        Map map = new HashMap();
        map.put("userId", managerUser.getId());
        //根据不同的用户角色获取不同的用户消息
        if ("2".equals(managerUser.getRoleTypeId())){
            map.put("userType","2");
        }else {
            map.put("userType","3");
        }
        map.put("count",object.get("count"));
        map.put("page",object.get("page"));
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(getMyMessageUrl))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }

    /***
     * 查询未读系统消息数量
     * @param userId
     * @return
     */
    @RequestMapping(value = "/noReadCount",method = RequestMethod.POST)
    @ApiOperation(value = "查询未读系统消息数量")
    @AuthUserToken
    public ResponseEntity<ResultModel> noReadCount(@CurrentUserId Long userId){
        Map map = new HashMap();
        map.put("userId",userId);
        map.put("isRead","1");
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(getMyNoReadCountUrl))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }

    /***
     * 系统消息设置已读
     * @param object
     * @return
     */
    @RequestMapping(value = "/readMsg",method = RequestMethod.POST)
    @ApiOperation(value = "系统消息设置已读")
    @AuthUserToken
    public ResponseEntity<ResultModel> readMsg(@RequestBody JSONObject object){
        Map map = new HashMap();
        map.put("id",object.get("id"));
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(readMsgUrl))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }

    /***
     * 读取消息详情
     * @param object
     * @return
     */
    @RequestMapping(value = "/getDetail",method = RequestMethod.POST)
    @ApiOperation(value = "读取消息详情")
    @AuthUserToken
    public ResponseEntity<ResultModel> getDetail(@RequestBody JSONObject object){
        Map map = new HashMap();
        map.put("id",object.get("id"));
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(findByIdForAppUrl))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }
}