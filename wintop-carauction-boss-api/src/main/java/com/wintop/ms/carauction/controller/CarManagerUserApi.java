package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.annotation.RequestAuth;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.CarManagerUser;
import com.wintop.ms.carauction.core.entity.TokenManager;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.core.model.TokenModel;
import com.wintop.ms.carauction.entity.ManagerRolePages;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import com.wintop.ms.carauction.util.utils.ParamValidUtil;
import com.wintop.ms.carauction.util.utils.RedisStoreUserManager;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台微服务Controller。
 *
 * @author admin
 *
 * @version 0.0.1
 *
 * @date 2018-03-15
 *
 */
@RestController
@RequestMapping("/managerUser")
public class CarManagerUserApi {

    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private RedisStoreUserManager storeUserManager;
    private final RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(CarManagerUserApi.class);

    private String rootUrl = Constants.ROOT+"/service/managerUser/";

    private String rolePageUrl = Constants.ROOT+"/service/rolePage/selectAllPages";

    CarManagerUserApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /***
     * 用户登陆使用接口
     * @param map
     * @return
     */
    @RequestMapping(value = "/userLogin",
            method = RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "登录")
    @RequestAuth(false)
    public ResultModel userLogin(@RequestHeader Map<String,String> headers, @RequestBody Map<String,Object> map) {
        String appId = headers.get(Constants.HEADER_APPID);
        //1、通过http调用基础api获取当前登陆人对象
        ResponseEntity<JSONObject> resultResponseEntity = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(rootUrl+"userLogin"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        //1、先判断返回网络状态码是否成功
        if(resultResponseEntity.getStatusCode()== HttpStatus.OK){
            JSONObject result = resultResponseEntity.getBody();
            String message = result.getString("message");
            //2、判断业务处理结果是否成功
            if (result.getBoolean("success")) {
                CarManagerUser user = result.getObject("result", CarManagerUser.class);
                //生成一个token，保存用户登录状态
                //用户登陆成功生成口令token返回客户端
                TokenModel model = tokenManager.createToken(appId, user.getId());
                /*String redisKey = model.getRedisKey();
                String value = JSONObject.toJSONString(user);
                redisManagerTemplate.add(redisKey,value);*/
                user.setToken(model.getToken());
                //***获取用户所拥有的权限
                map.clear();
                map.put("roleId",user.getRoleId());
                ResponseEntity<JSONObject> responseEntity = restTemplate.exchange(
                        RequestEntity
                                .post(URI.create(rolePageUrl))
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(map),JSONObject.class);
                String json = responseEntity.getBody().getJSONArray("result").toJSONString();
                List<String> pageList = JSONObject.parseArray(json,String.class);
                //System.out.println("----pageList.size()----"+pageList.size());
                if(pageList!=null && pageList.size()>0){
                    ManagerRolePages.getInstance().putData(user.getId(),pageList);
                }
                return new ResultModel(true,ResultCode.SUCCESS.value(),message, user);
            }else {
                return new ResultModel(false,ResultCode.BUSS_EXCEPTION.value(),message, null);
            }
        }else {
            return new ResultModel(false,ResultCode.NET_EXCEPTION.value(),ResultCode.NET_EXCEPTION.getRemark(), null);
        }
    }

    /***
     * 退出登陆操作
     * @param headers
     * @return
     */
    @RequestMapping(value = "/logout",
            method = RequestMethod.POST)
    @AuthUserToken
    @ApiOperation(value = "退出登录")
    @RequestAuth(false)
    public ResultModel logout(@RequestHeader Map<String,String> headers) {
        /**从请求头中提取用户信息，进行token清空操作*/
        TokenModel tokenModel = tokenManager.getToken(headers.get(Constants.HEADER_APPID),headers.get(Constants.HEADER_AUTHORIZATION));
        tokenManager.deleteStoreUserToken(tokenModel.getUserId()+"");
        return new ResultModel(true,ResultCode.SUCCESS.value(),ResultCode.SUCCESS.getRemark(),null);
    }

    /***
     * 修改用户密码
     * @param map
     * @return
     */
    @RequestMapping(value = "/updateUserPassword",
            method = RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    @ApiOperation(value = "修改用户密码")
    @RequestAuth(false)
    public ResultModel updateUserPassword(@RequestBody Map<String,Object> map, @CurrentUserId Long userId) {
        if(ParamValidUtil.invalidMapParam(map,"oldPassword") || ParamValidUtil.invalidMapParam(map,"newPassword")){
            new ResultModel(true, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("id",userId);
        ResponseEntity<JSONObject> responseEntity = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(rootUrl+"updateUserPassword"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(responseEntity,ApiUtil.OBJECT);
    }

    /***
     * 新增用户
     * @param map
     * @return
     */
    @RequestMapping(value = "/saveManagerUser",
            method = RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    @ApiOperation(value = "新增用户")
    public ResultModel saveManagerUser(@RequestBody Map<String,Object> map, @CurrentUserId Long userId) {
        map.put("createPerson",userId);
        ResponseEntity<JSONObject> responseEntity = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(rootUrl+"saveManagerUser"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(responseEntity,ApiUtil.OBJECT);
    }

    /***
     * 修改用户
     * @param map
     * @return
     */
    @RequestMapping(value = "/updateManagerUser",
            method = RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    @ApiOperation(value = "修改用户")
    public ResultModel updateManagerUser(@RequestBody Map<String,Object> map, @CurrentUserId Long userId) {
        map.put("modifyPerson",userId);
        ResponseEntity<JSONObject> responseEntity = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(rootUrl+"updateManagerUser"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(responseEntity,ApiUtil.OBJECT);
    }

    /**
     * 查询所有有效用户
     *@Author:admin
     *@date 2018/3/14
     *@param:map
     */
    @ApiOperation(value = "查询所有有效用户")
    @RequestMapping(value = "/selectAllManagerUser",
            consumes = "application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ResultModel selectAllManagerUser(@RequestBody Map<String,Object> map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(rootUrl+"selectAllManagerUser"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.LIST);
    }

    /***
     * 删除用户
     * @param map
     * @return
     */
    @RequestMapping(value = "/deleteManagerUser",
            method = RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    @ApiOperation(value = "删除用户")
    public ResultModel deleteManagerUser(@RequestBody Map<String,Object> map, @CurrentUserId Long userId) {
        String id = map.get("id").toString();
        if(Long.valueOf(id).longValue()==userId.longValue()){
            return new ResultModel(false,ResultCode.NO_ALLOW_DEL.value(),ResultCode.NO_ALLOW_DEL.getRemark(),null);
        }
        map.put("delPerson",userId);
        ResponseEntity<JSONObject> responseEntity = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(rootUrl+"deleteManagerUser"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        tokenManager.deleteStoreUserToken("用户id");
        storeUserManager.cleanStoreUser("用户id");
        return ApiUtil.getResultModel(responseEntity,ApiUtil.OBJECT);
    }

    /***
     * 查询用户
     * @param map
     * @return
     */
    @RequestMapping(value = "/selectManagerUser",
            method = RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    @ApiOperation(value = "查询用户")
    public ResultModel selectManagerUser(@RequestBody Map<String,Object> map) {
        ResponseEntity<JSONObject> responseEntity = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(rootUrl+"selectManagerUser"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(responseEntity,ApiUtil.OBJECT);
    }

    /***
     * 重置密码
     * @param map
     * @return
     */
    @RequestMapping(value = "/resetUserPassword",
            method = RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    @ApiOperation(value = "重置密码")
    public ResultModel resetUserPassword(@RequestBody Map<String,Object> map) {
        if(ParamValidUtil.invalidMapParam(map,"userId")){
            new ResultModel(true, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> responseEntity = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(rootUrl+"resetUserPassword"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(responseEntity,ApiUtil.OBJECT);
    }

    /**
     * 查询所有用户列表
     *@Author:admin
     *@date 2018/3/14
     *@param:map
     */
    @ApiOperation(value = "查询所有用户列表")
    @RequestMapping(value = "/selectManagerUserList",
            consumes = "application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel selectManagerUserList(@RequestBody Map<String,Object> map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(rootUrl+"selectManagerUserList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 查询评估师用户列表
     *@Author:admin
     *@date 2018/3/14
     *@param:map
     */
    @ApiOperation(value = "查询评估师用户列表")
    @RequestMapping(value = "/selectAssessUserList",
            consumes = "application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel selectAssessUserList(@CurrentUserId Long userId) {
        Map map = new HashMap();
        map.put("userId",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(rootUrl+"selectAssessUserList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

}


