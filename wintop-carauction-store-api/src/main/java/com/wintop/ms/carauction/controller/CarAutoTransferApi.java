package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUser;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultStatus;
import com.wintop.ms.carauction.core.entity.AppUser;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import com.wintop.ms.carauction.util.utils.IdWorker;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Map;

/**
 * Created by 12991 on 2018/3/10.
 */
@RestController
@RequestMapping("/carAutoTransfer")
public class CarAutoTransferApi {
    private IdWorker idWorker = new IdWorker(10);
    private ResultModel resultModel;
    private final RestTemplate restTemplate;
    CarAutoTransferApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 根据条件查询过户流程信息
     * @param map
     * @return
     */
    @RequestMapping(value = "/queryTransferList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResponseEntity<ResultModel> queryTransferList(@RequestBody Map<String,Object> map) {
        if (map.get("orderId")==null){
            return new ResponseEntity<>(ResultModel.error(ResultStatus.PARAMETERS_ERROR), HttpStatus.OK);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carOrder/queryTransferInfo"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.OBJECT);
    }

    /**
     * 确定过户
     */
    @RequestMapping(value = "/saveDetermine",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResponseEntity<ResultModel> saveDetermine(@RequestBody Map<String,Object> map, @CurrentUser AppUser appUser) throws MalformedURLException {
        map.put("createUser",appUser.getUserName());
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carAutoTransfer/saveDetermine"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.OBJECT);
    }

    /**
     * 确定收款
     */
    @RequestMapping(value = "/saveGathering",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResponseEntity<ResultModel> saveGathering(@RequestBody Map<String,Object> map, @CurrentUser AppUser appUser) throws MalformedURLException {
        if (map.get("orderId")==null){
            return new ResponseEntity<>(ResultModel.error(ResultStatus.PARAMETERS_ERROR), HttpStatus.OK);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carOrderBoss/saveOfflineOrderPay"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.OBJECT);
    }

    /**
     * 交接完成
     */
//    @RequestMapping(value = "/saveHandOver",
//            method= RequestMethod.POST,
//            consumes="application/json; charset=UTF-8",
//            produces="application/json; charset=UTF-8")
//    @AuthUserToken
//    public ResponseEntity<ResultModel> saveHandOver(@RequestBody Map<String,Object> map, @CurrentUser AppUser appUser) throws MalformedURLException {
//        map.put("createUser",appUser.getUserName());
//        map.put("userId",appUser.getId());
//        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
//                RequestEntity
//                        .post(URI.create(Constants.ROOT+"/service/carAutoTransfer/saveHandOver"))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .body(map),JSONObject.class);
//        return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.OBJECT);
//    }


    /**
     * 确认手续交接
     *@Author:zhangzijuan
     *@date 2018/4/18
     *@param:
     */
    @PostMapping(value = "/saveHandOver",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResponseEntity<ResultModel> saveTransfer(@RequestBody Map<String,Object> map, @CurrentUserId Long managerId) {
        if (map.get("orderId")==null || map.get("commissionPhoto")==null){
            return new ResponseEntity<>(ResultModel.error(ResultStatus.PARAMETERS_ERROR), HttpStatus.OK);
        }
        map.put("managerId",managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carAutoTransfer/saveTransfer"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.OBJECT);
    }

}
