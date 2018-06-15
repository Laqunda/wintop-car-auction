package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 12991 on 2018/3/8.
 */
@RestController
@RequestMapping("/carBrand")
public class CarBrandApi {
    private ResultModel resultModel;
    private static final Logger logger = LoggerFactory.getLogger(CarBrandApi.class);
    private final RestTemplate restTemplate;

    CarBrandApi(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }
    @RequestMapping(value = "/selectCarBrand",
            method= RequestMethod.GET,
            produces="application/json; charset=UTF-8")
//    @AuthUserToken
    public ResponseEntity<ResultModel> selectCarBrand(){

        try {
            ResponseEntity<JSONArray> response = this.restTemplate.exchange(
                    RequestEntity.get(URI.create(Constants.GET_BRANDS_URL)).header("Content-Type",new String[]{MediaType.APPLICATION_JSON_UTF8.toString()})
                            .accept(MediaType.APPLICATION_JSON_UTF8).build()
                    ,JSONArray.class);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code",0);
            jsonObject.put("success",true);
            jsonObject.put("result",response.getBody());
            ResponseEntity<JSONObject> entity = new ResponseEntity<JSONObject>(jsonObject,HttpStatus.OK);
            return ApiUtil.getResponseEntity(entity,resultModel, ApiUtil.LIST);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询品牌失败",e);
        }
        return null;
    }
}
