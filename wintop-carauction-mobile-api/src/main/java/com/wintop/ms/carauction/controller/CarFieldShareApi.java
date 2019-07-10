package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUser;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultStatus;
import com.wintop.ms.carauction.core.entity.AppUser;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.bcel.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 12991 on 2018/4/4.
 */
@RestController
@RequestMapping("share")
public class CarFieldShareApi {
    private ResultModel resultModel;
    private static final Logger logger = LoggerFactory.getLogger(CarFieldShareApi.class);
    private final RestTemplate restTemplate;
    CarFieldShareApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 分享车辆
     */
    @PostMapping(value = "/auto")
    public ResponseEntity<ResultModel> auto(@RequestParam Long autoId){
        logger.info("分享车辆");

        JSONObject object = new JSONObject();
        object.put("autoId",autoId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carAuto/getAutoInfo"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(object),JSONObject.class);
        JSONObject auto = response.getBody().getJSONObject("result");
        Map<String,Object> map = new HashMap<>();
        map.put("shareTitle","柠檬竞价-让二手车买卖更轻松");
        map.put("sharePhoto",auto.getString("mainPhoto"));
        map.put("subtitle","精选车源、现场竞拍场次、车辆抢鲜看");
        map.put("shareUrl", Constants.CAR_SHARE_URL+"?carId="+autoId);
        return new ResponseEntity<ResultModel>(ResultModel.ok(map),HttpStatus.OK);
    }

    /**
     * 场次分享接口
     */
    @PostMapping(value = "/auction")
    public ResponseEntity<ResultModel> auction(@RequestParam Long auctionId){
        logger.info("场次分享接口");
      /*  Map<String,Object> map = new HashMap<>();
        map.put("shareTitle","柠檬二手车-领先的二手车经销商集团");
        map.put("sharePhoto","http://static.yuntongauto.com/web/lay-ui/images/share/logo.png");
        map.put("subtitle","精选车源、现场竞拍场次、车辆抢鲜看");
        map.put("shareUrl","http://2sc.wintop2sc.com/ht/share/match.html?auctionId="+auctionId);
       return  new ResponseEntity<ResultModel>(ResultModel.ok(map),HttpStatus.OK);*/

        JSONObject object = new JSONObject();
        object.put("id",auctionId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carLocaleAuction/getLocaleAuctionDetailById"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(object),JSONObject.class);
        JSONObject carLocaleAuction = response.getBody().getJSONObject("result");
        Map<String,Object> map = new HashMap<>();
        if(carLocaleAuction != null){
            map.put("sharePhoto",carLocaleAuction.getString("poster"));
        }else{
            map.put("sharePhoto","");
        }
        map.put("shareTitle","柠檬竞价-让二手车买卖更轻松");
        map.put("subtitle","精选车源、现场竞拍场次、车辆抢鲜看");
        map.put("shareUrl",Constants.AUCTION_SHARE_URL+"?auctionId="+auctionId);
        return new ResponseEntity<ResultModel>(ResultModel.ok(map),HttpStatus.OK);
    }
}
