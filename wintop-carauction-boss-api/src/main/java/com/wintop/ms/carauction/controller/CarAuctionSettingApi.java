package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

/**
 * @author admin
 * @Description:线上配置信息
 * @date 2018-03-15
 */
@RestController
@RequestMapping("/auctionSetting")
public class CarAuctionSettingApi {
    private final RestTemplate restTemplate;
    CarAuctionSettingApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private String rootUrl = Constants.ROOT+"/service/auctionSetting/";

    /**
     * 查询线上拍卖设置列表
     *@Author:admin
     *@date 2018/3/21
     *@param:map
     */
    @ApiOperation(value = "查询线上拍卖设置列表")
    @RequestMapping(value = "/selectAuctionSettingList",
            consumes = "application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel selectAuctionSettingList(@RequestBody Map<String,Object> map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(rootUrl+"selectAuctionSettingList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 保存线上拍卖设置
     *@Author:admin
     *@date 2018/3/21
     *@param:map
     */
    @ApiOperation(value = "保存线上拍卖设置")
    @RequestMapping(value = "/saveAuctionSetting",
            consumes = "application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel saveAuctionSetting(@RequestBody Map<String,Object> map, @CurrentUserId Long userId) {
        map.put("createPerson",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(rootUrl+"saveAuctionSetting"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 修改线上拍卖设置
     *@Author:admin
     *@date 2018/3/21
     *@param:map
     */
    @ApiOperation(value = "修改线上拍卖设置")
    @RequestMapping(value = "/updateAuctionSetting",
            consumes = "application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel updateAuctionSetting(@RequestBody Map<String,Object> map, @CurrentUserId Long userId) {
        map.put("updatePerson",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(rootUrl+"updateAuctionSetting"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 查看线上拍卖设置
     *@Author:admin
     *@date 2018/3/21
     *@param:map
     */
    @ApiOperation(value = "查看线上拍卖设置")
    @RequestMapping(value = "/selectAuctionSetting",
            consumes = "application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel selectAuctionSetting(@RequestBody Map<String,Object> map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(rootUrl+"selectAuctionSetting"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

}
