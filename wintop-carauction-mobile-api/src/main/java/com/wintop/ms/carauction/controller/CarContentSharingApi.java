package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
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
 * 获取分享车辆内容[分享前调用]接口
 */
@RestController
@RequestMapping("/carContentSharing")
public class CarContentSharingApi {
    private ResultModel resultModel;

    private final RestTemplate restTemplate;

    CarContentSharingApi(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }
    @RequestMapping(value = "/selectCarContentSharing",
            method= RequestMethod.POST,
            produces="application/json; charset=UTF-8")
  // @AuthUserToken
    public ResponseEntity<ResultModel> selectCarContentSharing(@RequestBody Map<String,Object> map) throws MalformedURLException {
        if(map.get("carId")==null){
            return new ResponseEntity<>(new ResultModel(false,101,"缺少参数",null), HttpStatus.OK);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carContentSharing/selectCarContentSharing"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);

    }
}
