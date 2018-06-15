package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 12991 on 2018/3/7.
 */
@RestController
@RequestMapping("/homePage")
public class HomePageApi {
    private ResultModel resultModel;

    private final RestTemplate restTemplate;
//    本月场次数量
    private int auctionCount = 20;
    //开拍时间
    private String startTime = "2018-03-07 23:59:59";
    //城市数量
    private int cityCount = 5;
    //今日上新数量
    private int newCarSum = 200;
    //成交率
    private String successRate = "85%";
    //置换比
    private String replaceRate = "95%";
    HomePageApi(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }
    /**
     * 查询首页信息
     * @return
     */
/*    @RequestMapping(value = "/getHomePage",
            method= RequestMethod.POST,
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "查询首页信息")
    public ResponseEntity<ResultModel> getHomePage(@RequestParam Long cityId){
        Map<String,Object> map=new HashMap<>();
        map.put("cityCount",cityCount);
        map.put("auctionCount",auctionCount);
        map.put("newCarSum",newCarSum);
        map.put("successRate",successRate);
        map.put("replaceRate",replaceRate);
        map.put("startTime",startTime);
        return new ResponseEntity<>(new ResultModel(true,100,"成功",map), HttpStatus.OK);
    }*/
    /**
     * 查询首页信息
     */
    @RequestMapping(value = "/getHomePage",
            method= RequestMethod.POST)
    public ResponseEntity<ResultModel> getHomePage(@RequestBody Map<String,Object> map) throws MalformedURLException {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/homePage/selectHomePage"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }
}
