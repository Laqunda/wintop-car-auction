package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUser;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultStatus;
import com.wintop.ms.carauction.core.entity.AppUser;
import com.wintop.ms.carauction.core.entity.TokenManager;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.core.model.TokenModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import com.wintop.ms.carauction.util.utils.RedisAppUserManager;
import com.wintop.ms.carauction.util.utils.RedisManager;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by liangtingsen on 2018/3/1.
 * app用户接口
 */
@RestController
@RequestMapping("user")
public class AppUserApi {
    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private RedisManager redisManager;
    @Autowired
    private RedisAppUserManager appUserManager;

    private final RestTemplate restTemplate;

    private TokenModel tokenModel;

    private ResultModel resultModel;

    private static final Logger logger = LoggerFactory.getLogger(AppUserApi.class);
    /**登陆*/
    String loginUrl = Constants.ROOT + "/service/appuser/login";
    /**用户名查询用户*/
    String findByUsernameUrl = Constants.ROOT + "/service/appuser/findByUsername";
    /**新增用户*/
    String saveUserUrl = Constants.ROOT +"/service/appuser/saveuser";
    /**修改密码*/
    String updatePasswordUrl = Constants.ROOT +"/service/appuser/updatePassword";
    /**修改头像*/
    String updateHeadImgUrl = Constants.ROOT +"/service/appuser/updateHeadImg";
    /**修改用户信息*/
    String updateUserUrl = Constants.ROOT +"/service/appuser/updateUser";
    /**获取用户信息*/
    String getUserInfoDetail= Constants.ROOT+ "/service/appuser/getUserDetail";


    AppUserApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /***
     * 修改用户信息
     * @param userId
     * @param userMap
     * @return
     */
    @RequestMapping(value = "/update_user",method = RequestMethod.POST)
    @ApiOperation(value = "修改用户信息")
    @AuthUserToken
    public ResponseEntity<ResultModel> updateUser(@CurrentUserId Long userId,
                                                  @RequestBody Map userMap
    ){
        ResponseEntity<ResultModel> responseEntity = null;
        ResultModel resultModel;
        try {
            //1、验证验证码是否正确
            //第一步先判断验证码是否正确
            userMap.put("id",userId);
            //2、调用serviceapi 保存用户信息
            ResponseEntity<JSONObject> resultResponseEntity = this.restTemplate.exchange(
                    RequestEntity
                            .post(URI.create(updateUserUrl))
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(userMap),JSONObject.class);
            JSONObject serviceResult = resultResponseEntity.getBody();
            //1、先判断返回网络状态码是否成功
            if(resultResponseEntity.getStatusCode()== HttpStatus.OK){
                if (serviceResult.get("success").toString()=="true") {
                    resultModel = new ResultModel(true, ResultStatus.SUCCESS);
                    responseEntity = new ResponseEntity<>(resultModel, HttpStatus.OK);
                }else {
                    resultModel = new ResultModel(true,ResultStatus.UNSUCCESS);
                    responseEntity = new ResponseEntity<>(resultModel,HttpStatus.OK);
                }
            }else {
                resultModel = new ResultModel(true,ResultStatus.ERROR);
                responseEntity = new ResponseEntity<>(resultModel,HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
            resultModel = new ResultModel(false,ResultStatus.ERROR);
            responseEntity = new ResponseEntity<>(resultModel,HttpStatus.OK);
        }finally {
            return responseEntity;
        }
    }

    /***
     * 修改登陆密码
     * @param headers
     * @param username
     * @param code
     * @param password
     * @return
     */
    @RequestMapping(value = "/update_password",method = RequestMethod.POST)
    @ApiOperation(value = "修改忘记密码")
    public ResponseEntity<ResultModel> updatePassword(@RequestHeader Map<String,String> headers,
                                                      @RequestParam String username,
                                                      @RequestParam String code,
                                                      @RequestParam String password){
        logger.info("修改登陆密码");
        ResponseEntity<ResultModel> responseEntity = null;
        ResultModel resultModel;
        try {
            //1、验证验证码是否正确
            //第一步先判断验证码是否正确
            String validate_code = redisManager.getKeyValue(Constants.UPD_PWD_VALIDATE_CODE_KEY+username);
            if (validate_code!=null && validate_code.equals(code)){
                String appId = headers.get(Constants.HEADER_APPID);
                Map userMap = new HashMap();
                password = password.toUpperCase();
                userMap.put("username",username);
                userMap.put("password",password);
                //2、调用serviceapi 保存用户信息
                ResponseEntity<JSONObject> resultResponseEntity = this.restTemplate.exchange(
                        RequestEntity
                                .post(URI.create(updatePasswordUrl))
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(userMap),JSONObject.class);
                JSONObject serviceResult = resultResponseEntity.getBody();
                //1、先判断返回网络状态码是否成功
                if(resultResponseEntity.getStatusCode()== HttpStatus.OK){
                    if (serviceResult.get("success").toString()=="true") {
                        JSONObject appUser = serviceResult.getJSONObject("result");
                        //3、保存成功后自动完成登陆，并返回客户端token信息
                        Long userId = appUser.getLong("id");
                        //用户登陆成功生成口令token返回客户端
                        TokenModel model = tokenManager.createToken(appId, userId);
                        resultModel = new ResultModel(true, ResultStatus.SUCCESS, model);
                        responseEntity = new ResponseEntity<>(resultModel, HttpStatus.OK);
                    }else {
                        resultModel = new ResultModel(true,ResultStatus.UNSUCCESS);
                        responseEntity = new ResponseEntity<>(resultModel,HttpStatus.OK);
                    }
                }else {
                    resultModel = new ResultModel(true,ResultStatus.ERROR);
                    responseEntity = new ResponseEntity<>(resultModel,HttpStatus.OK);
                }
            }else {
                resultModel = new ResultModel(true,ResultStatus.VALIDATE_CODE_ERROR);
                responseEntity = new ResponseEntity<>(resultModel,HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
            resultModel = new ResultModel(false,ResultStatus.ERROR);
            responseEntity = new ResponseEntity<>(resultModel,HttpStatus.OK);
        }finally {
            return responseEntity;
        }
    }

    /***
     * 用户注册
     * @param headers
     * @param username
     * @param code
     * @param password
     * @param name
     * @return
     */
    @RequestMapping(value = "/regist",method = RequestMethod.POST)
    @ApiOperation(value = "注册")
    public ResponseEntity<ResultModel> regist(@RequestHeader Map<String,String> headers,
                                              @RequestParam String username,
                                              @RequestParam String code,
                                              @RequestParam String password,
                                              @RequestParam String name){
        logger.info("用户注册");
        ResponseEntity<ResultModel> responseEntity = null;
        ResultModel resultModel;
        try {

            //0、先验证下手机号是否已存在
            ResponseEntity<JSONObject> resultResponseEntity = this.restTemplate.exchange(
                    RequestEntity
                            .post(URI.create(findByUsernameUrl))
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(username),JSONObject.class);
            JSONObject serviceResult = resultResponseEntity.getBody();
            if(resultResponseEntity.getStatusCode()== HttpStatus.OK){
                if (serviceResult.get("success").toString()=="true") {
                    JSONObject appUser = serviceResult.getJSONObject("result");
                    if (appUser!=null){//已存在
                        resultModel = new ResultModel(true,ResultStatus.PHONE_EXISTS,appUser);
                        responseEntity = new ResponseEntity<>(resultModel,HttpStatus.OK);
                        return responseEntity;
                    }
                }
            }

//------------------------------以下进入注册-------------------------------------

            //1、验证验证码是否正确
            //第一步先判断验证码是否正确
            String validate_code = redisManager.getKeyValue(Constants.LOGIN_VALIDATE_CODE_KEY+username);
            if (validate_code!=null && validate_code.equals(code)){
                String appId = headers.get(Constants.HEADER_APPID);
                password = password.toUpperCase();
                Map userMap = new HashMap();
                userMap.put("username",username);
                userMap.put("password",password);
                userMap.put("name",name);
                //2、调用serviceapi 保存用户信息
                resultResponseEntity = this.restTemplate.exchange(
                        RequestEntity
                                .post(URI.create(saveUserUrl))
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(userMap),JSONObject.class);
                serviceResult = resultResponseEntity.getBody();
                //1、先判断返回网络状态码是否成功
                if(resultResponseEntity.getStatusCode()== HttpStatus.OK){
                    if (serviceResult.get("success").toString()=="true") {
                        JSONObject appUserJson = serviceResult.getJSONObject("result");
                        //3、保存成功后自动完成登陆，并返回客户端token信息
                        Long userId = appUserJson.getLong("id");
                        //用户登陆成功生成口令token返回客户端
                        TokenModel model = tokenManager.createToken(appId, userId);
                        //将登陆成功后的用户对象以key json形式保存到redis中
//                    String redisKey = model.getRedisKey();

                        AppUser appUser = new AppUser();
                        appUser.setId(appUserJson.getLong("id"));
                        appUser.setName(appUserJson.getString("name"));
                        appUser.setUserName(appUserJson.getString("userName"));
                        appUser.setHeadImg(appUserJson.getString("headImg"));
                        appUser.setMobile(appUserJson.getString("mobile"));
                        appUser.setUserNum(appUserJson.getString("userNum"));
                        appUser.setStatus(appUserJson.getString("status"));
                        appUser.setUserRankId(appUserJson.getLong("userLevelId"));
                        appUserManager.saveUser(appUser);
                        resultModel = new ResultModel(true, ResultStatus.SUCCESS, model);
                        responseEntity = new ResponseEntity<>(resultModel, HttpStatus.OK);
                    }else {
                        resultModel = new ResultModel(true,ResultStatus.UNSUCCESS);
                        responseEntity = new ResponseEntity<>(resultModel,HttpStatus.OK);
                    }
                }else {
                    resultModel = new ResultModel(true,ResultStatus.ERROR);
                    responseEntity = new ResponseEntity<>(resultModel,HttpStatus.OK);
                }
            }else {
                resultModel = new ResultModel(true,ResultStatus.VALIDATE_CODE_ERROR);
                responseEntity = new ResponseEntity<>(resultModel,HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
            resultModel = new ResultModel(false,ResultStatus.ERROR);
            responseEntity = new ResponseEntity<>(resultModel,HttpStatus.OK);
        }finally {
            return responseEntity;
        }
    }

    /***
     * app用户获取手机号验证码
     * @param mobile    手机号
     * @param type      类型，1登陆，2找回修改密码
     * @return
     */
    @RequestMapping(value = "/get_validate_code",method = RequestMethod.POST)
    @ApiOperation(value = "获取手机号验证码")
    public ResponseEntity<ResultModel> getLoginValidateCode(@RequestParam String mobile,@RequestParam String type) {
        Assert.notNull(mobile, "mobile can not be empty");
        ResponseEntity<ResultModel> responseEntity = null;
        ResultModel resultModel;
        try {
            //生成一个验证码
            int num = (int) ((Math.random() * 9 + 1) * 100000);
            String msgContext = "您的验证码为：" + num + "，有效期60秒，请勿泄露验证码【柠檬竞价】";
            Map map = new HashMap();
            map.put("mobile",mobile);
            map.put("content",msgContext);
            logger.info(msgContext);
            //调用短信发送工具类进行短信发送
//            TransmissionDevice transmissionDevice=new TransmissionDevice();
//            Message message=new Message("您本次的验证码是："+num+"，打死也不要告诉别人【运通汇】",mobile);
//            MessageSendResult messageSendResult = MessageSendAdapter.sendMessage(transmissionDevice,message);
//            System.out.println(messageSendResult.getMessage());
            //1、通过http调用基础api获取当前登陆人对象
            ResponseEntity<JSONObject> resultResponseEntity = this.restTemplate.exchange(
                    RequestEntity
                            .post(URI.create(Constants.SENDSMS_URL))
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(map),JSONObject.class);
            JSONObject serviceResult = resultResponseEntity.getBody();
            //短信发送成功
            if (serviceResult.getBoolean("success")){
                //将成功发送的短信验证码存入到redis中,并设置有效期为1分钟
                if (type!=null && type.equals("1")){
                    redisManager.setKeyValue(Constants.LOGIN_VALIDATE_CODE_KEY + mobile, num + "", 1, TimeUnit.MINUTES);
                }else if (type!=null && type.equals("2")){
                    redisManager.setKeyValue(Constants.UPD_PWD_VALIDATE_CODE_KEY + mobile, num + "", 1, TimeUnit.MINUTES);
                }
                resultModel = new ResultModel(true, ResultStatus.SUCCESS,"发送成功！");
                responseEntity = new ResponseEntity<>(resultModel, HttpStatus.OK);
            }else {
                resultModel = new ResultModel(false, ResultStatus.ERROR);
                responseEntity = new ResponseEntity<>(resultModel, HttpStatus.OK);
            }

        } catch (Exception e) {
            e.printStackTrace();
            resultModel = new ResultModel(false, ResultStatus.ERROR);
            responseEntity = new ResponseEntity<>(resultModel, HttpStatus.OK);
        } finally {
            return responseEntity;
        }
    }

    /***
     * app用户登陆使用接口
     * @param object
     * @return
     */
    @RequestMapping(value = "/check_mobile",method = RequestMethod.POST)
    @ApiOperation(value = "验证手机号是否存在")
    public ResponseEntity<ResultModel> checkMobile(@RequestBody JSONObject object) {
        String mobile = object.getString("mobile");
        /**如果客户端传递过来验证码，需要验证一下验证码是否正确*/
        if (object.get("validateCode")!=null && StringUtils.isNotEmpty(object.getString("validateCode"))){
            String validate_code = redisManager.getKeyValue(Constants.LOGIN_VALIDATE_CODE_KEY+mobile);
            if (validate_code==null || !validate_code.equals(validate_code)) {
                return new ResponseEntity<>(ResultModel.error(ResultStatus.VALIDATE_CODE_ERROR),HttpStatus.OK);
            }
        }
        //1、通过http调用基础api获取当前登陆人对象
        ResponseEntity<JSONObject> resultResponseEntity = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(findByUsernameUrl))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mobile),JSONObject.class);
        JSONObject serviceResult = resultResponseEntity.getBody();
        ResultModel resultModel = null;
        //1、先判断返回网络状态码是否成功
        if(resultResponseEntity.getStatusCode()== HttpStatus.OK){
            //2、判断业务处理结果是否成功
            if (serviceResult.get("success").toString()=="true") {

                JSONObject appUser = serviceResult.getJSONObject("result");
                if (appUser!=null){//已存在
                    resultModel = new ResultModel(true,ResultStatus.USERNAME_EXISTS,appUser);
                }else {//不存在
                    resultModel = new ResultModel(true,ResultStatus.USERNAME_UNKNOWN);
                }
                return new ResponseEntity<>(resultModel, HttpStatus.OK);
            }else {//异常情况
                resultModel = new ResultModel(true,ResultStatus.ERROR);
                return new ResponseEntity<>(resultModel,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else {
            resultModel = new ResultModel(false,ResultStatus.ERROR);
            return new ResponseEntity<>(resultModel,resultResponseEntity.getStatusCode());
        }
    }

    /***
     * app用户登陆使用接口
     * @param username
     * @param code
     * @return
     */
    @RequestMapping(value = "/login_validate",method = RequestMethod.POST)
    @ApiOperation(value = "验证码登录")
    public ResponseEntity<ResultModel> loginValidate(@RequestHeader Map<String,String> headers, @RequestParam String username, @RequestParam String code) {
        Assert.notNull(username, "username can not be empty");
        Assert.notNull(code, "code can not be empty");
        //第一步先判断验证码是否正确
        String validate_code = redisManager.getKeyValue(Constants.LOGIN_VALIDATE_CODE_KEY+username);
        if (validate_code!=null && validate_code.equals(code)){
            //第二步验证码正确则查询用户信息并返回token
            String appId = headers.get(Constants.HEADER_APPID);
            //1、通过http调用基础api获取当前登陆人对象
            ResponseEntity<JSONObject> resultResponseEntity = this.restTemplate.exchange(
                    RequestEntity
                            .post(URI.create(findByUsernameUrl))
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(username),JSONObject.class);
            JSONObject serviceResult = resultResponseEntity.getBody();
            ResultModel resultModel;
            //1、先判断返回网络状态码是否成功
            if(resultResponseEntity.getStatusCode()== HttpStatus.OK){
                //2、判断业务处理结果是否成功
                if (serviceResult.get("success").toString()=="true") {
                    JSONObject wtAppUser = serviceResult.getJSONObject("result");
                    //生成一个token，保存用户登录状态
                    Long userId = wtAppUser.getLong("id");
                    //用户登陆成功生成口令token返回客户端
                    TokenModel model = tokenManager.createToken(appId, userId);
                    resultModel = ResultModel.ok(model);


                    AppUser appUser = new AppUser();
                    appUser.setId(wtAppUser.getLong("id"));
                    appUser.setName(wtAppUser.getString("name"));
                    appUser.setUserName(wtAppUser.getString("userName"));
                    appUser.setHeadImg(wtAppUser.getString("headImg"));
                    appUser.setMobile(wtAppUser.getString("mobile"));
                    appUser.setUserNum(wtAppUser.getString("userNum"));
                    appUser.setStatus(wtAppUser.getString("status"));
                    appUser.setUserRankId(wtAppUser.getLong("userLevelId"));
                    //将登陆成功后的用户对象以key json形式保存到redis中
//                    String redisKey = model.getRedisKey();
                    appUserManager.saveUser(appUser);

                    return new ResponseEntity<>(resultModel, HttpStatus.OK);
                }else {
                    //如果业务处理有问题，直接返回用户名密码错误状态信息
                    resultModel = ResultModel.error(ResultStatus.SERVICE_ERROR);
                    return new ResponseEntity<>(resultModel,HttpStatus.OK);
                }
            }else {
                resultModel = ResultModel.error(ResultStatus.SERVICE_ERROR);
                return new ResponseEntity<>(resultModel,resultResponseEntity.getStatusCode());
            }
        }else {
            //验证码错误
            resultModel = ResultModel.error(ResultStatus.VALIDATE_CODE_ERROR);
            return new ResponseEntity<>(resultModel, HttpStatus.OK);
        }
    }

    /***
     * app用户登陆使用接口
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/login_password",method = RequestMethod.POST)
    @ApiOperation(value = "登录")
    public ResponseEntity<ResultModel> loginPassword(@RequestHeader Map<String,String> headers, @RequestParam String username, @RequestParam String password) {
        Assert.notNull(username, "username can not be empty");
        Assert.notNull(password, "password can not be empty");

        String appId = headers.get(Constants.HEADER_APPID);
//        Long timestamp = Long.parseLong(headers.get(Constants.HEADER_TIMESTAMP).toString());
        password = password.toUpperCase();
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
        //1、先判断返回网络状态码是否成功
        if(resultResponseEntity.getStatusCode()== HttpStatus.OK){
            //2、判断业务处理结果是否成功
            if (serviceResult.get("success").toString()=="true") {
                JSONObject wtAppUser = serviceResult.getJSONObject("result");
                //生成一个token，保存用户登录状态
                Long userId = wtAppUser.getLong("id");
                //用户登陆成功生成口令token返回客户端
                TokenModel model = tokenManager.createToken(appId, userId);

                //将登陆成功后的用户对象以key json形式保存到redis中
                AppUser appUser = new AppUser();
                appUser.setId(wtAppUser.getLong("id"));
                appUser.setName(wtAppUser.getString("name"));
                appUser.setUserName(wtAppUser.getString("userName"));
                appUser.setHeadImg(wtAppUser.getString("headImg"));
                appUser.setMobile(wtAppUser.getString("mobile"));
                appUser.setUserNum(wtAppUser.getString("userNum"));
                appUser.setStatus(wtAppUser.getString("status"));
                appUser.setUserRankId(wtAppUser.getLong("userLevelId"));
                //将登陆成功后的用户对象以key json形式保存到redis中
                appUserManager.saveUser(appUser);
                resultModel = ResultModel.ok(model);
                return new ResponseEntity<>(resultModel, HttpStatus.OK);
            }else {
                //如果业务处理有问题，直接返回用户名密码错误状态信息
                resultModel = ResultModel.error(ResultStatus.USERNAME_OR_PASSWORD_ERROR);
                return new ResponseEntity<>(resultModel,HttpStatus.OK);
            }
        }else {
            resultModel = ResultModel.error(ResultStatus.VALIDATE_CODE_ERROR);
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
    @RequestMapping(value = "/get_user_info",method = RequestMethod.POST)
    @AuthUserToken//需要登陆操作的方法添加此注解
    @ApiOperation(value = "获取当前登陆人信息")
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
    @RequestMapping(value = "/logout",
            method = RequestMethod.POST)
    @AuthUserToken
    @ApiOperation(value = "退出登录")
    public ResponseEntity<ResultModel> logout(@RequestHeader Map<String,String> headers) {
        /**从请求头中提取用户信息，进行token清空操作*/
        tokenModel = tokenManager.getToken(headers.get(Constants.HEADER_APPID),headers.get(Constants.HEADER_AUTHORIZATION));
        tokenManager.deleteToken(tokenModel);
        appUserManager.cleanAppUser(tokenModel.getUserId()+"");

        return new ResponseEntity<>(ResultModel.ok(), HttpStatus.OK);
    }



    /***
     * 获取登陆人信息
     * @return
     */
    @PostMapping(value = "/getUserInfoDetail",produces="application/json; charset=UTF-8")
    @AuthUserToken//需要登陆操作的方法添加此注解
    @ApiOperation(value = "获取当前登陆人信息")
    public ResponseEntity<ResultModel> getUserInfoDetail(@CurrentUserId Long userId) {
        if (userId!=null) {
            ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                    RequestEntity
                            .post(URI.create(getUserInfoDetail))
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(userId),JSONObject.class);
            return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
        }else {
            resultModel = new ResultModel(false,ResultStatus.USER_NOT_LOGIN);
            return  new ResponseEntity<>(resultModel, HttpStatus.OK);
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
        ResponseEntity<ResultModel> responseEntity = null;
        try {
            Map userMap = new HashMap();
            userMap.put("headImg",headImg);
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
                    responseEntity = new ResponseEntity<>(ResultModel.ok(), HttpStatus.OK);
                    AppUser appUser = appUserManager.getAppUser(userId+"");
                    appUser.setHeadImg(headImg);
                    //====更新redis中的用户信息
                    appUserManager.saveUser(appUser);
                }else {
                    responseEntity = new ResponseEntity<>(ResultModel.error(ResultStatus.UNSUCCESS),HttpStatus.OK);
                }
            }else {
                responseEntity = new ResponseEntity<>(ResultModel.error(ResultStatus.ERROR), HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(ResultModel.error(ResultStatus.ERROR),HttpStatus.OK);
        }finally {
            return responseEntity;
        }
    }
}
