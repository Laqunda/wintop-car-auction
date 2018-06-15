package com.wintop.ms.carauction.controller;

import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.alibaba.fastjson.JSONObject;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.Map;

/**
 * Created by 12991 on 2018/3/6.
 */
@RestController
@RequestMapping("/carAppSlideshow")
public class CarAppSlideshowApi {
    private ResultModel resultModel;
    private final RestTemplate restTemplate;
    CarAppSlideshowApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 轮播图,现场播报
     */
    @PostMapping(value = "/selectCarAppSlideshow",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ResponseEntity<ResultModel> selectCarAppSlideshow(@RequestBody Map<String,Object> map) {
        if(map.get("type")==null){
            return new ResponseEntity<>(new ResultModel(false,101,"缺少参数",null), HttpStatus.OK);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carAppSlideshow/selectCarAppSlideshow"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.LIST);
    }

}
