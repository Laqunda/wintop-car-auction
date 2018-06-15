package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
 * Created by 12991 on 2018/3/9.
 */
@RestController
@RequestMapping("/serviceNetwork")
public class ServiceNetworkApi {
    private ResultModel resultModel;

    private final RestTemplate restTemplate;

    ServiceNetworkApi(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }


    /**
     * 获取店铺详情
     */
    @RequestMapping(value = "/selectServiceNetwork",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ResponseEntity<ResultModel> selectServiceNetwork(@RequestBody Map<String,Object> map) throws MalformedURLException {
        if(map.get("serviceNetworkId")==null){
            return new ResponseEntity<>(new ResultModel(false,101,"缺少参数",null), HttpStatus.OK);
        }

        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/serviceNetwork/selectServiceNetwork"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }
}
