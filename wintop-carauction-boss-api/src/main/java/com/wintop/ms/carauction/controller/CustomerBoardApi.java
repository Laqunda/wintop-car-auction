package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.annotation.RequestAuth;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import com.wintop.ms.carauction.util.utils.IdWorker;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/customerBoard")
public class CustomerBoardApi {

    private final RestTemplate restTemplate;
    CustomerBoardApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 绑定拍牌
     * @param managerId
     * @param map
     * @return
     */
    @RequestMapping(value = "/saveCustomerBoard",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    @RequestAuth(false)
    public ResultModel saveAuctionBoard(@CurrentUserId Long managerId, @RequestBody Map<String,Object> map) {
        if(map.get("customerId")==null && map.get("boardRealId")==null ){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("createPerson",managerId);
        map.put("createTime",new Date());
        map.put("bindStatus","1");
        map.put("id", IdWorker.getInstance().nextId());
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/customerBoard/saveCustomerBoard"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 接触绑定
     * @param managerId
     * @param map
     * @return
     */
    @RequestMapping(value = "/deleteCustomerBoard",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    @RequestAuth(false)
    public ResultModel deleteAuctionBoard(@CurrentUserId Long managerId, @RequestBody Map<String,Object> map) {
        if(map.get("customerId")==null && map.get("boardRealId")==null ){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("modifyPerson",managerId);
        map.put("modifyTime",new Date());
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/customerBoard/deleteCustomerBoard"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }
}
