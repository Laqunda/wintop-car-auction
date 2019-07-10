package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AppApiVersion;
import com.wintop.ms.carauction.core.annotation.AppUserRequestAuth;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.AppUserStatusEnum;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import io.swagger.annotations.ApiOperation;
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

/**
 * 竞拍信息接口
 */
@RestController
@RequestMapping("/carAutoAuction")
public class CarAutoAuctionApi {
    private ResultModel resultModel;
    private final RestTemplate restTemplate;

    //获取车辆竞拍信息
    private static String getByAutoId_URL = Constants.ROOT+"/service/carAutoAuction/getByAutoId";
    //保存车辆竞拍信息
    private static String saveInfo_URL = Constants.ROOT+"/service/carAutoAuction/saveInfo";
    CarAutoAuctionApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private final static Logger logger = LoggerFactory.getLogger(CarAutoAuctionApi.class);

    /**
     * 获取竞拍信息接口
     */
    @RequestMapping(value = "/selectAuctionInformation",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResponseEntity<ResultModel> selectAuctionInformation(@RequestBody JSONObject object) {

        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(getByAutoId_URL))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(object),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.OBJECT);
    }

    @ApiOperation(value = "保存车辆竞拍信息")
    @RequestMapping(value = "/saveInfo",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResponseEntity<ResultModel> saveInfo(@RequestBody JSONObject object, @CurrentUserId Long userId) {
        logger.info("保存车辆竞拍信息");
        object.put("createUser",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(saveInfo_URL))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(object),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.OBJECT);
    }

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

}
