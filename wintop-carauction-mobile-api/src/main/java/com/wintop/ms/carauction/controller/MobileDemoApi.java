package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthPublic;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUser;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultStatus;
import com.wintop.ms.carauction.core.entity.AppUser;
import com.wintop.ms.carauction.core.entity.TokenManager;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.core.model.TokenModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * mobile微服务Controller。
 *
 * @author mike
 *
 * @version 0.0.1
 *
 * @date 2017-10-19
 *
 */
@RestController
@RequestMapping("test")
public class MobileDemoApi {

    @Autowired
    private TokenManager tokenManager;

    private final RestTemplate restTemplate;

    private TokenModel tokenModel;

    private ResultModel resultModel;

    private int port = 8185;

    String loginUrl = Constants.ROOT + "/service/appuser/login";

    MobileDemoApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @RequestMapping(value = "/mobile/user/list",/*指定请求的实际地址，指定的地址可以是URI Template 模式（后面将会说明）；*/
            method=RequestMethod.POST,/*指定请求的method类型， GET、POST、PUT、DELETE等；*/
            headers="appid",/* 指定request中必须包含某些指定的header值，才能让该方法处理请求。*/
//			params="age=20",/*指定request中必须包含某些参数值是，才让该方法处理。*/
            consumes="application/json; charset=UTF-8",/*指定处理请求的提交内容类型（Content-Type），例如application/json, text/html;*/
            produces="application/json; charset=UTF-8")/*指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回；*/
    public  ResponseEntity<JSONObject> findUserList(@RequestBody Map<String,Object> carAgentUser) throws MalformedURLException {

//        if(isPass(headers)){
            ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                    RequestEntity
                            .post(URI.create(Constants.ROOT + "/service/user/list"))
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(carAgentUser),JSONObject.class);
            if(response.getStatusCode()== HttpStatus.OK){
            }else{
            }
            JSONObject RE = response.getBody();
            JSONArray array = RE.getJSONArray("result");
            array.getJSONObject(0).getString("");
        return response;

    }


    /***
     * app用户登陆使用接口
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/mobile/user/login",method = RequestMethod.POST)
    @ApiOperation(value = "登录")
    public ResponseEntity<ResultModel> login(@RequestHeader Map<String,String> headers,@RequestParam String username, @RequestParam String password) {
        Assert.notNull(username, "username can not be empty");
        Assert.notNull(password, "password can not be empty");

        String appId = headers.get(Constants.HEADER_APPID);
//        Long timestamp = Long.parseLong(headers.get(Constants.HEADER_TIMESTAMP).toString());

        Map map = new HashMap();
        map.put("username",username);
        map.put("password",password);
        map.put("appId",appId);
        //1、通过http调用基础api获取当前登陆人对象
        ResponseEntity<JSONObject> resultResponseEntity = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(loginUrl))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        JSONObject serviceResult = resultResponseEntity.getBody();
        ResultModel resultModel = null;
        //1、先判断返回网络状态码是否成功
        if(resultResponseEntity.getStatusCode()== HttpStatus.OK){
            //2、判断业务处理结果是否成功
            if (serviceResult.get("success").toString()=="true") {
                JSONObject appUser = serviceResult.getJSONObject("result");
                //生成一个token，保存用户登录状态
                Long userId = appUser.getLong("id");
                //用户登陆成功生成口令token返回客户端
                TokenModel model = tokenManager.createToken(appId, userId);
                resultModel = new ResultModel(true, ResultStatus.SUCCESS, model);
                return new ResponseEntity<>(resultModel, HttpStatus.OK);
            }else {
                //如果业务处理有问题，直接返回用户名密码错误状态信息 todo 需要在细化
                resultModel = new ResultModel(true,ResultStatus.USERNAME_OR_PASSWORD_ERROR);
                return new ResponseEntity<>(resultModel,HttpStatus.OK);
            }
        }else {
            resultModel = new ResultModel(false,ResultStatus.ERROR);
            return new ResponseEntity<>(resultModel,resultResponseEntity.getStatusCode());
        }
    }

    /***
     * 检查是否登陆操作
     *  注解解释：
     *      @AuthUserToken//需要登陆操作的方法添加此注解
     *      @CurrentUser AppUser appUser 当前方法需要获取当前登陆人的信息进行业务处理，添加此注解
     * @return
     */
    @RequestMapping(value = "/mobile/user/checkLogin",method = RequestMethod.POST)
    @AuthUserToken//需要登陆操作的方法添加此注解
    @ApiOperation(value = "检查登录")
    public ResponseEntity<ResultModel> checkLogin(@CurrentUser AppUser appUser) {
        System.out.println(appUser.getUserName());
        if (appUser!=null) {
            resultModel = new ResultModel(true,ResultStatus.SUCCESS, appUser);
        }else {
            resultModel = new ResultModel(false,ResultStatus.USER_NOT_LOGIN);
        }
        return new ResponseEntity<>(resultModel, HttpStatus.OK);
    }

    /***
     * 退出登陆操作
     * @param headers
     * @return
     */
    @RequestMapping(value = "/mobile/user/logout",
            method = RequestMethod.DELETE)
    @AuthUserToken
    @ApiOperation(value = "退出登录")
    public ResponseEntity<ResultModel> logout(@RequestHeader Map<String,String> headers) {
        /**从请求头中提取用户信息，进行token清空操作*/
        tokenModel = tokenManager.getToken(headers.get(Constants.HEADER_APPID),headers.get(Constants.HEADER_AUTHORIZATION));
        tokenManager.deleteToken(tokenModel);
        return new ResponseEntity<>(ResultModel.ok(), HttpStatus.OK);
    }

    /***
     * 添加公共注解方法例子
     * @return
     */
    @RequestMapping(value = "/mobile/public/example")
    @AuthPublic//无需授权直接可以调用的接口，需要此注解
    @ApiOperation("无需授权的公共接口例子")
    public ResponseEntity<ResultModel> publicExample(){
        return new ResponseEntity<>(ResultModel.ok(),HttpStatus.OK);
    }
}