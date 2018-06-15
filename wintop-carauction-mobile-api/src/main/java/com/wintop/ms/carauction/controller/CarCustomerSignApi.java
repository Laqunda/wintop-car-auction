package com.wintop.ms.carauction.controller;


import com.alibaba.fastjson.JSONObject;
import com.wintop.core.util.UtilDate;
import com.wintop.ms.carauction.core.annotation.AppUserRequestAuth;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUser;
import com.wintop.ms.carauction.core.config.AppUserStatusEnum;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultStatus;
import com.wintop.ms.carauction.core.entity.AppUser;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import com.wintop.ms.carauction.util.utils.RedisAppUserManager;
import com.wintop.ms.carauction.util.utils.RedisManagerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.*;

/***
 * 签约服务
 */
@RestController
@RequestMapping("/sign")
public class CarCustomerSignApi {

    @Autowired
    private RedisAppUserManager appUserManager;
    private Logger logger = LoggerFactory.getLogger(CarCustomerSignApi.class);
    private ResultModel resultModel;
    private final RestTemplate restTemplate;
    private static String getById_URL = Constants.ROOT+"/sign/getById";
    private static String getByCustomerId_URL = Constants.ROOT+"/sign/getByCustomerId";
    private static String saveSignature_URL = Constants.ROOT+"/sign/saveSignature";
    /**获取用户实名认证信息*/
    private static String getAuthInfoByUserId_URL = Constants.ROOT + "/service/customerAuth/getAuthInfoByUserId";
    /**创建签约账户*/
    private static String createSignUser_URL = Constants.COMMON_MODULE_ROOT + "/signature/account/user/add";
    /**生成个人签名的合同*/
    private static String createSignature_URL =  Constants.COMMON_MODULE_ROOT + "/signature/gosign/single";
    CarCustomerSignApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }




    /**
     * 查询某个签约详情
     */
    @PostMapping(value = "/getById")
    @AuthUserToken
    @AppUserRequestAuth({
            AppUserStatusEnum.AUTH_SUCCESS,
            AppUserStatusEnum.SIG_SUCCESS,
            AppUserStatusEnum.SIG_ING,
            AppUserStatusEnum.SIG_ERROR,
            AppUserStatusEnum.SIG_OK
    })
    public ResponseEntity<ResultModel> getById(@RequestParam Long id) {
        logger.info("查询某个签约详情");
        HashMap map = new HashMap();
        map.put("id",id);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(getById_URL))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }

    /**
     * 查询某人的签约数据
     */
    @PostMapping(value = "/getByCustomerId")
    @AuthUserToken
    @AppUserRequestAuth({
            AppUserStatusEnum.AUTH_SUCCESS,
            AppUserStatusEnum.SIG_SUCCESS,
            AppUserStatusEnum.SIG_ING,
            AppUserStatusEnum.SIG_ERROR,
            AppUserStatusEnum.SIG_OK
    })
    public ResponseEntity<ResultModel> getByCustomerId(@CurrentUser AppUser appUser) {
        logger.info("查询某人的签约数据");
        ResponseEntity<ResultModel> responseEntity = null;
        try {
            HashMap map = new HashMap();
            map.put("customerId", appUser.getId());
            ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                    RequestEntity
                            .post(URI.create(getByCustomerId_URL))
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(map), JSONObject.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                JSONObject object = response.getBody();
                JSONObject signObj = object.getJSONObject("result");
                if (signObj != null) {
                    Map hashMap = new HashMap();
                    //status 签约状态：0未签约，1客户签字，2签约审核通过，3审核失败作废',
                    hashMap.put("status",signObj.get("status"));
                    hashMap.put("authMsg",signObj.get("authMsg"));
                    hashMap.put("userStatus",signObj.get("userStatus"));
                    hashMap.put("depositStatus",signObj.get("depositStatus"));
                    //个人签约--待审核--返回待盖章的合同
                    if ("1".equals(signObj.get("status"))) {
                        JSONObject signLog = signObj.getJSONObject("signLog");
                        hashMap.put("pdf_url", signLog.get("pdfFileUrl"));
                        hashMap.put("img_url",signLog.get("picFileUrl"));
                    } else if ("2".equals(signObj.get("status"))) {
                        //个人签约审核成功--返回盖章的合同
                        JSONObject signLog = signObj.getJSONObject("signLog");
                        hashMap.put("pdf_url", signLog.get("pdfFileUrl"));
                        hashMap.put("img_url",signLog.get("picFileUrl"));
                    } else if ("3".equals(signObj.get("status"))) {
                        //个人签约审核失败--返回空白合同
                        hashMap.put("pdf_url", Constants.SIGNATURE_TEMP_BASEPDF_URL);
                        hashMap.put("img_url",Constants.SIGNATURE_BASEPDF_URL_IMG);
                    } else {
                        hashMap.put("pdf_url", Constants.SIGNATURE_TEMP_BASEPDF_URL);
                        hashMap.put("img_url",Constants.SIGNATURE_BASEPDF_URL_IMG);
                    }
                    if ("3".equals(signObj.get("status")) && "-1".equals(signObj.get("depositStatus"))){
                        hashMap.put("userStatus","8");
                    }else if ("2".equals(signObj.get("status")) && "-1".equals(signObj.get("depositStatus"))){
                        hashMap.put("userStatus","9");
                    }
                    resultModel = ResultModel.ok(hashMap);
                } else {
                    Map hashMap = new HashMap();
                    hashMap.put("status","0");
                    hashMap.put("authMsg","未签约");
                    hashMap.put("userStatus",appUser.getStatus());
                    hashMap.put("depositStatus",null);
                    hashMap.put("pdf_url", Constants.SIGNATURE_TEMP_BASEPDF_URL);
                    hashMap.put("img_url",Constants.SIGNATURE_BASEPDF_URL_IMG);
                    resultModel = ResultModel.ok(ResultStatus.SUCCESS, hashMap);
                }
            }else {
                resultModel = ResultModel.error(ResultStatus.ERROR);
            }
            responseEntity = new ResponseEntity(resultModel,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resultModel = ResultModel.error(ResultStatus.SERVICE_ERROR);
            responseEntity = new ResponseEntity(resultModel,HttpStatus.INTERNAL_SERVER_ERROR);

        } finally {
            return responseEntity;
        }
    }

    /**
     * 保存签字,生成甲方签字合同
     */
    @PostMapping(value = "/saveSignature")
    @AuthUserToken
    @AppUserRequestAuth({
            AppUserStatusEnum.AUTH_SUCCESS,
            AppUserStatusEnum.SIG_SUCCESS,
            AppUserStatusEnum.SIG_ING,
            AppUserStatusEnum.SIG_ERROR,
            AppUserStatusEnum.SIG_OK
    })
    public ResponseEntity<ResultModel> saveSignature(@CurrentUser AppUser user,
                                                     @RequestParam String signatureImg) {
        ResponseEntity<ResultModel> responseEntity = null;
        logger.info("保存签字，生成甲方签字合同");
        try {
            //调用签署账户接口使用参数实体
            Map map;
            //1、调用会员认证查询，获取认证信息
            ResponseEntity<JSONObject> resultResponseEntity=this.restTemplate.exchange(
                    RequestEntity
                            .post(URI.create(getAuthInfoByUserId_URL))
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(user.getId()),JSONObject.class);
            JSONObject serviceResult = resultResponseEntity.getBody();
            if(resultResponseEntity.getStatusCode()== HttpStatus.OK
                    && serviceResult.get("success").toString() == "true") {
                JSONObject object = serviceResult.getJSONObject("result");
                if (object != null) {
                    map = new HashMap();
                    map.put("userName", object.getString("realName"));
                    map.put("userIdNo", object.getString("identityNumber"));
                    map.put("userArea", "0");

                    //2、调用e签宝创建用户账号
                    resultResponseEntity = this.restTemplate.exchange(
                            RequestEntity
                                    .post(URI.create(createSignUser_URL))
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .body(map, Map.class), JSONObject.class);
                    serviceResult = resultResponseEntity.getBody();
                    logger.info(serviceResult.toJSONString());
                    if (resultResponseEntity.getStatusCode() == HttpStatus.OK) {
                        String accountId = null;
                        if ("0".equals(serviceResult.get("errcode").toString())) {
                            accountId = serviceResult.getString("accountId");
                        } else {
                            //调用E签宝查询账号接口，根据身份证号获取账户id
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("idNo", object.getString("identityNumber"));
                            ResponseEntity<JSONObject> objectResponseEntity = this.restTemplate.exchange(
                                    RequestEntity
                                            .post(URI.create(createSignUser_URL))
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .body(jsonObject), JSONObject.class);
                            if ("0".equals(objectResponseEntity.getBody().get("errcode").toString())) {
                                accountId = objectResponseEntity.getBody().getString("accountId");
                            }
                        }
                        if (accountId != null) {
                            //3、调用e签宝生成签字版合同
                            map = new HashMap();
                            map.put("account_user", accountId);
                            map.put("user", object.get("realName"));
                            map.put("cus_key", Constants.SIGNATURE_CUS_KEY);
                            map.put("path_src", Constants.SIGNATURE_TEMP_BASEPDF_URL);
                            map.put("path_seal", signatureImg);
                            resultResponseEntity = this.restTemplate.exchange(
                                    RequestEntity
                                            .post(URI.create(createSignature_URL))
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .body(map, Map.class), JSONObject.class);
                            serviceResult = resultResponseEntity.getBody();
                            logger.info(serviceResult.toJSONString());
                            if (resultResponseEntity.getStatusCode() == HttpStatus.OK) {
                                if ("0".equals(serviceResult.get("errcode").toString())) {
                                    String picture = serviceResult.getString("picture");
                                    String pdf = serviceResult.getString("pdf");
                                    //4、保存签约成功的信息
                                    HashMap saveSignMap = new HashMap();
                                    saveSignMap.put("pdfUrl", pdf);
                                    saveSignMap.put("picUrl", picture);
                                    saveSignMap.put("log", serviceResult.toJSONString());
                                    saveSignMap.put("customerId", user.getId());
                                    resultResponseEntity = this.restTemplate.exchange(
                                            RequestEntity
                                                    .post(URI.create(saveSignature_URL))
                                                    .contentType(MediaType.APPLICATION_JSON)
                                                    .body(saveSignMap), JSONObject.class);
                                    serviceResult = resultResponseEntity.getBody();
                                    if (resultResponseEntity.getStatusCode() == HttpStatus.OK) {
                                        if (serviceResult.get("success").toString() == "true") {
                                            JSONObject object1 = resultResponseEntity.getBody().getJSONObject("result");
                                            //签约成功，返回结果
                                            Map mp = new HashMap();
                                            mp.put("pdf_url", pdf);
                                            mp.put("pic_url", picture);
                                            if (object1 != null && object1.get("userStatus") != null && AppUserStatusEnum.SIG_ING.equals(object1.getString("userStatus"))) {
                                                map.put("userStatus", AppUserStatusEnum.SIG_ING);
                                                //修改redis中的用户状态
                                                appUserManager.updateUserStatus(user.getId() + "", AppUserStatusEnum.SIG_ING.value());
                                            } else {
                                                map.put("userStatus", AppUserStatusEnum.SIG_SUCCESS);
                                                //修改redis中的用户状态
                                                appUserManager.updateUserStatus(user.getId() + "", AppUserStatusEnum.SIG_SUCCESS.value());
                                            }
                                            resultModel = ResultModel.ok(mp);
                                            responseEntity = new ResponseEntity<>(resultModel, HttpStatus.OK);
                                        }
                                    }
                                }
                            }

                        }
                    }else {
                        responseEntity = new ResponseEntity<>(ResultModel.error(ResultStatus.PERSON_CARDNO_ERROR), HttpStatus.OK);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            resultModel = ResultModel.error(ResultStatus.SERVICE_ERROR);
            responseEntity = new ResponseEntity<>(resultModel,HttpStatus.INTERNAL_SERVER_ERROR);
        }finally {
            if (resultModel==null || responseEntity == null){
                resultModel = ResultModel.error(ResultStatus.SERVICE_ERROR);
                responseEntity = new ResponseEntity<>(resultModel, HttpStatus.OK);
            }
            return responseEntity;
        }
    }
}