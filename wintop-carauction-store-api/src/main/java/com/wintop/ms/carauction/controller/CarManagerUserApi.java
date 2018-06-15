package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultStatus;
import com.wintop.ms.carauction.core.entity.CarManagerUser;
import com.wintop.ms.carauction.core.entity.TokenManager;
import com.wintop.ms.carauction.core.entity.TokenSingleton;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.core.model.TokenModel;
import com.wintop.ms.carauction.util.utils.RedisStoreUserManager;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
import java.util.Map;

/**
 * 卖家登陆使用
 */
@RestController
@RequestMapping("/user")
public class CarManagerUserApi {

    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private RedisStoreUserManager storeUserManager;
    private final RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(CarManagerUserApi.class);

    private String loginUrl = Constants.ROOT+"/service/managerUser/userLogin";

    /**修改头像*/
    String updateHeadImgUrl = Constants.ROOT+"/service/managerUser/updateHeadImg";

//    private String userInfoUrl = Constants.ROOT+"/service/managerUser/queryUserInfo";

    CarManagerUserApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /***
     * 用户登陆使用接口
     * @param username
     * @param password
     * @return
     */
    @PostMapping(value = "/userLogin")
    @ApiOperation(value = "登录")
    public ResponseEntity<ResultModel> userLogin(@RequestHeader Map<String,String> headers,
                                 @ApiParam(name = "username",value = "用户名",required = true)@RequestParam String username,
                                 @ApiParam(name = "password",value = "密码",required = true)@RequestParam String password) {
        logger.info("用户登陆");
        ResponseEntity<ResultModel> modelResponseEntity = null;
        try {
            String appId = headers.get(Constants.HEADER_APPID);
            password = password.toUpperCase();
            Map map = new HashMap();
            map.put("appId", appId);
            map.put("userKey", username);
            map.put("userPassword", password);
            //1、通过http调用基础api获取当前登陆人对象
            ResponseEntity<JSONObject> resultResponseEntity = this.restTemplate.exchange(
                    RequestEntity
                            .post(URI.create(loginUrl))
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(map), JSONObject.class);
            //1、先判断返回网络状态码是否成功
            if (resultResponseEntity.getStatusCode() == HttpStatus.OK) {
                JSONObject result = resultResponseEntity.getBody();
                //2、判断业务处理结果是否成功
                if (result.getBoolean("success")) {
                    CarManagerUser user = result.getObject("result", CarManagerUser.class);
                    //生成一个token，保存用户登录状态
                    //用户登陆成功生成口令token返回客户端
                    TokenModel model = tokenManager.createToken(appId, user.getId());
//                    String redisKey = model.getRedisKey();

                    storeUserManager.saveUser(user);

                    user.setToken(model.getToken());
                    modelResponseEntity = new ResponseEntity<>(ResultModel.ok(user),HttpStatus.OK);
                } else {
                    //如果业务处理有问题，直接返回用户名密码错误状态信息 todo 需要在细化
                    modelResponseEntity = new ResponseEntity<>(ResultModel.error(ResultStatus.USERNAME_OR_PASSWORD_ERROR),HttpStatus.OK);
                }
            } else {
                modelResponseEntity = new ResponseEntity<>(ResultModel.error(ResultStatus.SERVICE_ERROR),HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            modelResponseEntity = new ResponseEntity<>(ResultModel.error(ResultStatus.SERVICE_ERROR),HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            if (modelResponseEntity==null){
                modelResponseEntity = new ResponseEntity<>(ResultModel.error(ResultStatus.SERVICE_ERROR),HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return modelResponseEntity;
        }
    }

    /***
     * 用户信息查询接口
     * @return
     */
    @RequestMapping(value = "/queryUserInfo",
            method = RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    @ApiOperation(value = "查询用户信息")
    public ResultModel queryUserInfo() {
        logger.info("查询用户信息");
        TokenModel model = TokenSingleton.getInstance().getTokenModel();
        CarManagerUser user = storeUserManager.getStoreUser(model.getUserId()+"");
        return new ResultModel(true,100,"",user);
   }

    /***
     * 退出登陆操作
     * @param headers
     * @return
     */
    @RequestMapping(value = "/logout",
            method = RequestMethod.DELETE)
    @AuthUserToken
    @ApiOperation(value = "退出登录")
    public ResponseEntity<ResultModel> logout(@RequestHeader Map<String,String> headers) {
        logger.info("退出登陆操作");
        ResponseEntity<ResultModel> responseEntity = null;
        try {
            /**从请求头中提取用户信息，进行token清空操作*/
            TokenModel tokenModel = tokenManager.getToken(headers.get(Constants.HEADER_APPID),headers.get(Constants.HEADER_AUTHORIZATION));
            tokenManager.deleteToken(tokenModel);
            storeUserManager.cleanStoreUser(tokenModel.getUserId()+"");
            responseEntity = new ResponseEntity<>(ResultModel.ok(),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(ResultModel.error(ResultStatus.UNSUCCESS),HttpStatus.INTERNAL_SERVER_ERROR);
        }finally {
            return responseEntity;
        }

    }

    /***
     * 修改头像
     * @param userId
     * @param headImg
     * @return
     */
    @RequestMapping(value = "/update_heaimg",method = RequestMethod.POST)
    @ApiOperation(value = "修改头像")
    @AuthUserToken
    public ResponseEntity<ResultModel> updateHeadImg(@CurrentUserId Long userId,
                                                     @RequestParam String headImg){
        logger.info("修改头像");
        ResponseEntity<ResultModel> responseEntity = null;
        ResultModel resultModel;
        try {
            Map userMap = new HashMap();
            userMap.put("userPhoto",headImg);
            userMap.put("id",userId);
            ResponseEntity<JSONObject> resultResponseEntity = this.restTemplate.exchange(
                    RequestEntity
                            .post(URI.create(updateHeadImgUrl))
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(userMap),JSONObject.class);
            JSONObject serviceResult = resultResponseEntity.getBody();
            //1、先判断返回网络状态码是否成功
            if(resultResponseEntity.getStatusCode()== HttpStatus.OK){
                if (serviceResult.get("success").toString()=="true") {
                    resultModel = new ResultModel(true, ResultStatus.SUCCESS);
                    responseEntity = new ResponseEntity<>(resultModel, HttpStatus.OK);
                    CarManagerUser managerUser = storeUserManager.getStoreUser(userId+"");
                    managerUser.setUserPhoto(headImg);
                    storeUserManager.saveUser(managerUser);
                }else {
                    resultModel = new ResultModel(true,ResultStatus.UNSUCCESS);
                    responseEntity = new ResponseEntity<>(resultModel,HttpStatus.OK);
                }
            }else {
                resultModel = new ResultModel(true, ResultStatus.ERROR);
                responseEntity = new ResponseEntity<>(resultModel, HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
            resultModel = new ResultModel(false,ResultStatus.ERROR);
            responseEntity = new ResponseEntity<>(resultModel,HttpStatus.OK);
        }finally {
            return responseEntity;
        }
    }

}


