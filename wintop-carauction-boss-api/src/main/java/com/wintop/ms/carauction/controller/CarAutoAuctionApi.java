package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * 竞拍信息接口
 */
@RestController
@RequestMapping("/carAutoAuction")
public class CarAutoAuctionApi {
    private final RestTemplate restTemplate;

    //获取车辆竞拍信息
    private static String getByAutoId_URL = Constants.ROOT+"/service/carAutoAuction/getByAutoId";
    //保存车辆竞拍信息
    private static String saveInfo_URL = Constants.ROOT+"/service/carAutoAuction/saveInfo";
    //最近的开拍时间
    private static String selectForToday_URL = Constants.ROOT + "/service/carAutoAuction/selectForToday";
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
    public ResultModel selectAuctionInformation(@RequestBody JSONObject object) {

        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(getByAutoId_URL))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(object),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    @ApiOperation(value = "保存车辆竞拍信息")
    @RequestMapping(value = "/saveInfo",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel saveInfo(@RequestBody JSONObject object, @CurrentUserId Long userId) {
        logger.info("保存车辆竞拍信息");
        object.put("createUser",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(saveInfo_URL))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(object),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 查询-填充使用,最近的开拍时间
     */
    @ApiOperation(value = "最近的开拍时间")
    @RequestMapping(value = "/selectForToday",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel selectForToday(@RequestBody JSONObject object,@CurrentUserId Long userId) {
        logger.info("最近的开拍时间");
        object.put("userId",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(selectForToday_URL))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(object),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }
}
