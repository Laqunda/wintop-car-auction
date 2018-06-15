package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.AppUserStatusEnum;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultStatus;
import com.wintop.ms.carauction.core.entity.AppUser;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import com.wintop.ms.carauction.util.utils.RedisAppUserManager;
import com.wintop.ms.carauction.util.utils.RedisManagerTemplate;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @Description:用户认证
 * @date 2018-03-05
 */
@RestController
@RequestMapping("/auth")
public class CarCustomerAuthApi {
    private ResultModel resultModel;

    private Logger logger = LoggerFactory.getLogger(CarCustomerAuthApi.class);

    @Autowired
    private RedisAppUserManager appUserManager;
    private final RestTemplate restTemplate;
    //根据用户id查询用户认证信息
    String getAuthInfoByUserId="/service/customerAuth/getAuthInfoByUserId";
    //保存认证信息
    String saveAuthInfo="/service/customerAuth/saveAuthInfo";

    CarCustomerAuthApi(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

    /**
     * 根据用户id查询用户认证信息
     * @return
     */
    @PostMapping(value = "getAuthInfoByUserId", produces="application/json; charset=UTF-8")
    @AuthUserToken
    @ApiOperation(value = "获取当前登陆人认证信息")
    public ResponseEntity<ResultModel> getAuthInfoByUserId(@CurrentUserId Long userId){
        if (userId!=null) {
            ResponseEntity<JSONObject> response=this.restTemplate.exchange(
                    RequestEntity
                            .post(URI.create(Constants.ROOT+getAuthInfoByUserId))
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(userId),JSONObject.class);
            resultModel=new ResultModel(true, ResultStatus.SUCCESS);
            return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.OBJECT);
        }else{
            resultModel=new ResultModel(false, ResultStatus.USER_NOT_LOGIN);
            return new ResponseEntity<>(resultModel, HttpStatus.OK);
        }
    }


    @PostMapping(value = "saveAuthInfo", consumes="application/json; charset=UTF-8",produces="application/json; charset=UTF-8")
    @AuthUserToken
    @ApiOperation(value = "保存认证信息")
    public ResponseEntity<ResultModel> saveAuthInfo(@CurrentUserId Long userId, @RequestBody Map<String,Object> map){
        ResponseEntity<ResultModel> resultRsponse = null;
        boolean if_ok = false;
        try {
            if (map.get("realName")!=null && map.get("identityNumber")!=null
                    && StringUtils.isNotEmpty(map.get("realName").toString())
                    && StringUtils.isNotEmpty(map.get("identityNumber").toString())) {
                Map map1 = new HashMap();
                map1.put("idNo",map.get("identityNumber"));
                map1.put("name",map.get("realName"));
//先通过e签宝两要素接口验证用户提交的身份证信息真实性
                ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                        RequestEntity
                                .post(URI.create(Constants.PERSON_CARNO_VERIFICATION))
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(map1), JSONObject.class);
                JSONObject responseBody = response.getBody();
                logger.info(responseBody.toJSONString());
                if (response.getStatusCode() == HttpStatus.OK) {
                    if (responseBody.get("errcode")!=null && "0".equals(responseBody.get("errcode").toString())) {
                        //e签宝身份验证成功后，保存客户认证信息，否则直接返回客户端
                        map.put("userId", userId);
                        response = this.restTemplate.exchange(
                                RequestEntity
                                        .post(URI.create(Constants.ROOT + saveAuthInfo))
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .body(map), JSONObject.class);
                        if (response.getStatusCode() == HttpStatus.OK) {
                            JSONObject obj = response.getBody();
                            boolean success = obj.getBoolean("success");
                            if_ok = success;
                            if (success) {
                                appUserManager.updateUserStatus(userId+"", AppUserStatusEnum.AUTH_ING.value());
                            }
                        }
                    }
                }
                if (if_ok) {
                    resultRsponse = new ResponseEntity<>(ResultModel.ok(), HttpStatus.OK);
                } else {
                    resultRsponse = new ResponseEntity<>(ResultModel.error(ResultStatus.PERSON_CARDNO_ERROR), HttpStatus.OK);
                }
            }else {
                resultRsponse = new ResponseEntity<>(ResultModel.error(ResultStatus.PERSON_CARDNO_ERROR), HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
            resultRsponse = new ResponseEntity<>(ResultModel.error(ResultStatus.UNSUCCESS),HttpStatus.OK);
        }finally {
            return resultRsponse;
        }
    }
}