package com.wintop.ms.carauction.controller;


import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AppApiVersion;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Map;

/**
 * 车辆出价记录
 *
 * @author mazg
 * @date 2019-05-13
 */
@RestController
@RequestMapping("/carAuctionBidRecord")
public class CarAuctionBidRecordApi {
    private static final Logger logger = LoggerFactory.getLogger(CarAuctionBidRecordApi.class);

    private ResultModel resultModel;
    private final RestTemplate restTemplate;

    public CarAuctionBidRecordApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    /**
     * 获取浏览的车辆列表
     */
    @RequestMapping(value = "/getBidPriceList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    @AppApiVersion(value = "2.0")
    public ResponseEntity<ResultModel> stockInfo(@RequestBody Map<String,Object> map) throws MalformedURLException {
        if (map.get("carId") == null) {
            return new ResponseEntity<>(new ResultModel(false, 101, "缺少参数", null), HttpStatus.OK);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT + "/service/carAuctionBidRecord/getBidPriceList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map), JSONObject.class);
        return ApiUtil.getResponseEntity(response, resultModel, ApiUtil.LIST);
    }
}

