package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthPublic;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.RequestAuth;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/paimai")
public class ElectronAuctionApi {

    private final RestTemplate restTemplate;
    ElectronAuctionApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 电子拍牌访问接口
     * @return
     */
    @AuthPublic
    @RequestMapping(value = "/bidding",produces="application/json; charset=UTF-8")
    public synchronized Integer bidding(HttpServletRequest request,String jz,String pp,String mm) {
        if(StringUtils.isBlank(jz) || StringUtils.isBlank(pp) || StringUtils.isBlank(mm)){
            return 0;
        }
        Map<String,Object> map = new HashMap();
        map.put("jz",jz);
        map.put("pp",pp);
        map.put("mm",mm);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/electronAuction/bidding"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        ResultModel model = ApiUtil.getResultModel(response, ApiUtil.OBJECT);
        if(model.getResultCode()==100){
            return 1;
        }
        return 0;
    }

    @RequestMapping(value = "/selectLogList",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthPublic
    public synchronized ResultModel selectLogList(@RequestBody Map<String,Object> map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/electronAuction/selectLogList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 电子拍牌上传访问接口
     * @return
     */
    @AuthPublic
    @RequestMapping(value = "/tongji",produces="application/json; charset=UTF-8")
    public synchronized Integer tongji(HttpServletRequest request,String jz,String pps,String mm,String rq) {
        if(StringUtils.isBlank(jz) || StringUtils.isBlank(pps) || StringUtils.isBlank(mm)){
            return 3;
        }
        Map<String,Object> map = new HashMap();
        map.put("jz",jz);
        map.put("pps",pps);
        map.put("mm",mm);
        map.put("rq",rq);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/localeBoardRecord/saveLocaleBoard"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        ResultModel model = ApiUtil.getResultModel(response, ApiUtil.OBJECT);
        if(model.getResultCode()==100){
            return 2;
        }
        return 3;
    }
}
