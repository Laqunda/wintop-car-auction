package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AppUserRequestAuth;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUser;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.AppUserStatusEnum;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.entity.AppUser;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import com.wintop.ms.carauction.util.utils.IdWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/carEntrust")
public class CarCustomerEntrustApi {
    private IdWorker idWorker = new IdWorker(10);
    private ResultModel resultModel;
    private final RestTemplate restTemplate;
    CarCustomerEntrustApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 查询是否设置委托价
     * @param userId
     * @param map
     * @return
     * @throws MalformedURLException
     * {"carId":1}
     */
    @RequestMapping(value = "/queryEntrustPrice",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResponseEntity<ResultModel> queryEntrustPrice( @RequestBody Map<String,Object> map,@CurrentUserId Long userId) throws MalformedURLException {
        map.put("customerId",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carEntrust/queryEntrustPrice"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.OBJECT);
    }

    /**
     * 设置委托价
     * @param map
     * @return
     * @throws MalformedURLException
     * {"carId":1,entrustPrice:1000}
     */
    @RequestMapping(value = "/saveEntrustPrice",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    @AppUserRequestAuth(AppUserStatusEnum.SIG_OK)
    public ResponseEntity<ResultModel> saveEntrustPrice( @RequestBody Map<String,Object> map, @CurrentUser AppUser appUser) throws MalformedURLException {
        if(map.get("carId")==null || map.get("entrustPrice")==null){
            return new ResponseEntity<>(new ResultModel(false,101,"缺少参数",null), HttpStatus.OK);
        }
        map.put("id",idWorker.nextId());
        map.put("customerId",appUser.getId());
        map.put("createManager",appUser.getName());
        if(map.get("status")==null){
            map.put("status","1");
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carEntrust/saveEntrustPrice"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.OBJECT);
    }

    /**
     * 修改委托价
     * @param userId
     * @param map
     * @return
     * @throws MalformedURLException
     * {"carId":1,entrustPrice:1000}
     */
    @RequestMapping(value = "/updateEntrustPrice",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    @AppUserRequestAuth(AppUserStatusEnum.SIG_OK)
    public ResponseEntity<ResultModel> updateEntrustPrice(@RequestBody Map<String,Object> map,@CurrentUserId Long userId) throws MalformedURLException {
        if(map.get("carId")==null || map.get("entrustPrice")==null){
            return new ResponseEntity<>(new ResultModel(false,101,"缺少参数",null), HttpStatus.OK);
        }
        map.put("customerId",userId);
        if(map.get("status")==null){
            map.put("status","1");
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carEntrust/updateEntrustPrice"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.OBJECT);
    }

    /**
     * 删除委托价
     * @param userId
     * @param map
     * @return
     * @throws MalformedURLException
     * {"carId":1,entrustPrice:1000}
     */
    @RequestMapping(value = "/deleteEntrustPrice",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    @AppUserRequestAuth(AppUserStatusEnum.SIG_OK)
    public ResponseEntity<ResultModel> deleteEntrustPrice(@RequestBody Map<String,Object> map,@CurrentUserId Long userId) throws MalformedURLException {
        if(map.get("carId")==null){
            return new ResponseEntity<>(new ResultModel(false,101,"缺少参数",null), HttpStatus.OK);
        }
        map.put("customerId",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carEntrust/deleteEntrustPrice"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.OBJECT);
    }

    /**
     * 是否启用委托价
     * @param headers
     * @param map
     * @return
     * @throws MalformedURLException
     * {"carId":1,entrustPrice:1000}
     */
    @RequestMapping(value = "/updateEntrustPriceStatus",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    @AppUserRequestAuth(AppUserStatusEnum.SIG_OK)
    public ResponseEntity<ResultModel> updateEntrustPriceStatus(@RequestHeader Map<String,String> headers, @RequestBody Map<String,Object> map, @CurrentUserId Long userId) throws MalformedURLException {
        if(map.get("carId")==null || map.get("status")==null){
            return new ResponseEntity<>(new ResultModel(false,101,"缺少参数",null), HttpStatus.OK);
        }
        map.put("customerId",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carEntrust/updateEntrustPriceStatus"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.OBJECT);
    }
}
