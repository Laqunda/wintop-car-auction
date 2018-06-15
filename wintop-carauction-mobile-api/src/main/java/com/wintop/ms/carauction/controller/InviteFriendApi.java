package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentPossibleUserId;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/inviteFriend")
public class InviteFriendApi {
    private ResultModel resultModel;

    private final RestTemplate restTemplate;

    InviteFriendApi(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }
    @RequestMapping(value = "/selectInviteFriend",
            method= RequestMethod.POST,
            produces="application/json; charset=UTF-8")
 //  @AuthUserToken
    public ResponseEntity<ResultModel> selectInviteFriend(@RequestBody Map<String,Object> map, @CurrentPossibleUserId Long userId) throws MalformedURLException {
       /* String authorization = headers.get(Constants.HEADER_AUTHORIZATION);
        if(StringUtils.isNotEmpty(authorization)){
            map.put("userId",authorization.split("_")[0]);
        }*/
       map.put("userId",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/inviteFriend/selectInviteFriend"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);

    }
}
