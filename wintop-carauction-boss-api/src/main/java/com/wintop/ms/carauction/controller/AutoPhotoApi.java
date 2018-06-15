package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 车辆-----------照片管理
 */
@RestController
@RequestMapping("/autoPhoto")
public class AutoPhotoApi {
    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(AutoPhotoApi.class);

    public static final String getAutoPhoto_URL = Constants.ROOT+"/autoPhoto/getByAutoId";
    public static final String saveAutoPhoto_URL = Constants.ROOT+"/autoPhoto/savePhoto";
    public static final String delAutoPhoto_URL = Constants.ROOT+"/autoPhoto/delPhoto";


    AutoPhotoApi(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }


    @PostMapping(value = "getPhoto")
    @ApiOperation(value = "获取车辆照片",notes = "根据车辆id获取车辆的照片信息")
    public ResultModel getPhoto(@ApiParam(value = "车辆id",required = true) @RequestParam Long autoId){
        logger.info("获取车辆照片");

        Map map = new HashMap();
        try {
            ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                    RequestEntity
                            .post(URI.create(getAutoPhoto_URL))
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(autoId), JSONObject.class);
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                JSONObject object = response.getBody();
                if (object != null && object.getJSONArray("result") != null) {
                    //解析车辆照片，重新按类型封装
                    JSONArray jsonArray = object.getJSONArray("result");
                    List otherPhoto = new ArrayList();
                    for (Object photo : jsonArray) {
                        JSONObject photoObj = (JSONObject) photo;
                        if (photoObj.get("photoType") != null) {
                            if ("1".equals(photoObj.getString("photoType"))) {
                                map.put("frontLeft", photoObj.getString("photoUrl"));
                            } else if ("2".equals(photoObj.getString("photoType"))) {
                                map.put("left", photoObj.getString("photoUrl"));
                            } else if ("3".equals(photoObj.getString("photoType"))) {
                                map.put("rightRear", photoObj.getString("photoUrl"));
                            } else if ("4".equals(photoObj.getString("photoType"))) {
                                map.put("frontSeat", photoObj.getString("photoUrl"));
                            } else if ("5".equals(photoObj.getString("photoType"))) {
                                map.put("instrumentPanel", photoObj.getString("photoUrl"));
                            } else if ("6".equals(photoObj.getString("photoType"))) {
                                map.put("backSeat", photoObj.getString("photoUrl"));
                            } else if ("7".equals(photoObj.getString("photoType"))) {
                                map.put("controlBoard", photoObj.getString("photoUrl"));
                            } else if ("8".equals(photoObj.getString("photoType"))) {
                                map.put("carTail", photoObj.getString("photoUrl"));
                            } else if ("9".equals(photoObj.getString("photoType"))) {
                                map.put("trunkFloor", photoObj.getString("photoUrl"));
                            } else if ("10".equals(photoObj.getString("photoType"))) {
                                map.put("engineNacelle", photoObj.getString("photoUrl"));
                            } else if ("11".equals(photoObj.getString("photoType"))) {
                                map.put("theFactoryBrand", photoObj.getString("photoUrl"));
                            } else if ("12".equals(photoObj.getString("photoType"))) {
                                map.put("drivingLicense", photoObj.getString("photoUrl"));
                            } else if ("13".equals(photoObj.getString("photoType"))) {
                                otherPhoto.add(photoObj.getString("photoUrl"));
                            }
                        }
                    }
                    map.put("otherPhoto", otherPhoto);
                    return new ResultModel(true, ResultCode.SUCCESS.value(),ResultCode.SUCCESS.getRemark(),map);
                } else {
                    return new ResultModel(true, ResultCode.SUCCESS.value(),ResultCode.SUCCESS.getRemark(),map);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return new ResultModel(true, ResultCode.SUCCESS.value(),ResultCode.SUCCESS.getRemark(),map);
        }
    }

    @PostMapping(value = "savePhoto")
    @ApiOperation(value = "保存车辆照片信息",notes = "将上传的照片保存到车辆照片中")
    @AuthUserToken
    public ResultModel savePhoto(@CurrentUserId Long userId,
            @ApiParam(value = "车辆照片json，车辆id+照片数组",required = true) @RequestBody JSONObject object){
        logger.info("保存车辆照片信息");
        object.put("userId",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(saveAutoPhoto_URL))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(object),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }


}
