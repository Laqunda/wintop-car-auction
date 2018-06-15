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
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 付陈林.
 * @Description: 定金暴露的外部接口
 * @Date: 20:38 on 2018/3/6.
 * @Modified by:
 */
@RestController
@RequestMapping("/carCustomerDeposit")
public class CarCustomerDepositApi {
    private IdWorker idWorker = new IdWorker(10);
    private ResultModel resultModel;
    private final RestTemplate restTemplate;
    CarCustomerDepositApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 获取保证金常见问题接口
     * @return
     */
    @RequestMapping(value = "getCarQuestionForDeposit",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ResponseEntity<ResultModel> getCarQuestionForDeposit(@RequestBody Map<String,Object> map){
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carCustomerDeposit/getCarQuestionForDeposit"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.LIST);
    }


    /**
     * 查询保证金余额
     * @Autor 付陈林
     * @Time 2018-3-5
     * @param userId
     * @return
     */
    @PostMapping(value = "getDepositAmount",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResponseEntity<ResultModel> getDepositAmountByUserId(@RequestBody Map<String,Object> map, @CurrentUserId Long userId){
        map.put("userId",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carCustomerDeposit/getDepositAmountByUserId"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.OBJECT);
    }

    /**
     * 根据获取等级查询该等级因交保证金金额
     * @Autor 付陈林
     * @Time  2018-3-7
     * @param appUser
     * @return
     */
    @PostMapping(value = "getDepositAmountByCustomerLevelId",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AppUserRequestAuth({
            AppUserStatusEnum.SIG_SUCCESS,AppUserStatusEnum.SIG_ING,AppUserStatusEnum.SIG_ERROR,AppUserStatusEnum.SIG_OK})
    @AuthUserToken
    public ResponseEntity<ResultModel> getDepositAmountByCustomerLevelId(@CurrentUser AppUser appUser){
        Map map = new HashMap();
        map.put("customerLevelId",appUser.getUserRankId());
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carCustomerDeposit/getDepositAmountByCustomerLevelId"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.OBJECT);
    }

    /**
     * 设置支付成功后，回调保存成功
     * @Autor 付陈林
     * @Time  2018-3-13
     * @param map
     * @return
     */
    @PostMapping(value = "payDepositAmountCallback",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<ResultModel> payDepositAmountCallback(@RequestBody Map<String,Object> map){
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carCustomerDeposit/payDepositAmountCallback"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.OBJECT);
    }
}
