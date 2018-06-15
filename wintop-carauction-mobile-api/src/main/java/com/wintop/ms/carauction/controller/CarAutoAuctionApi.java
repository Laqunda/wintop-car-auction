package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AppUserRequestAuth;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.AppUserStatusEnum;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.entity.JPushAutoData;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import com.wintop.ms.carauction.util.utils.JPushUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/carAutoAuction")
public class CarAutoAuctionApi {
    private ResultModel resultModel;
    private final RestTemplate restTemplate;
    CarAutoAuctionApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private final static Logger logger = LoggerFactory.getLogger(CarAutoAuctionApi.class);

    /**
     * 获取最高价信息
     * @param map
     * @return
     */
    @RequestMapping(value = "/selectMaxAuctionPrice",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AppUserRequestAuth(AppUserStatusEnum.SIG_OK)
    public ResponseEntity<ResultModel> selectMaxAuctionPrice(@RequestBody Map<String,Object> map,@RequestHeader Map<String,String> headers) {
        String authorization = headers.get(Constants.HEADER_AUTHORIZATION);
        if(StringUtils.isNotEmpty(authorization)){
            map.put("customerId",authorization.split("_")[0]);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carAutoAuction/selectMaxAuctionPrice"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.OBJECT);
    }

    /**
     * 我要竞价
     * @return
     */
    @RequestMapping(value = "/selectMyFareList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    @AppUserRequestAuth(AppUserStatusEnum.SIG_OK)
    public ResponseEntity<ResultModel> selectMyFareList(@CurrentUserId Long userId) {
        Map<String,Object> map = new HashMap<>();
        map.put("customerId",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carAutoAuction/selectMyFareList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.LIST);
    }

    /**
     * 提交我的竞价
     * @param map
     * @return
     */
    @RequestMapping(value = "/saveMyBidPrice",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    @AppUserRequestAuth(AppUserStatusEnum.SIG_OK)
    public ResponseEntity<ResultModel> saveMyBidPrice(@RequestBody Map<String,Object> map,@CurrentUserId Long userId) {
        synchronized(this){
            map.put("customerId",userId);
            ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                    RequestEntity
                            .post(URI.create(Constants.ROOT+"/service/carAutoAuction/saveMyBidPrice"))
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(map),JSONObject.class);
            ResponseEntity<ResultModel> responseEntity = ApiUtil.getResponseEntity(response,resultModel,ApiUtil.OBJECT);
            return responseEntity;
        }
    }

}
