package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.primitives.Longs;
import com.wintop.ms.carauction.core.annotation.AppApiVersion;
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
    private ResultModel resultModel;
    private static final Logger logger = LoggerFactory.getLogger(AutoPhotoApi.class);

    public static final String getAutoPhoto_URL = Constants.ROOT+"/autoPhoto/getByAutoId";
    public static final String saveAutoPhoto_URL = Constants.ROOT+"/autoPhoto/savePhoto";
    public static final String delAutoPhoto_URL = Constants.ROOT+"/autoPhoto/delPhoto";
    private static Map<String, String> autoTitleMap = getAutoTitlePhotoMap();

    private static Map<String, String> getAutoTitlePhotoMap() {
        return new HashMap<String, String>() {{
            put("1", "左前方");
            put("2", "左侧");
            put("3", "右后方");
            put("4", "前排座椅");
            put("5", "仪表盘");
            put("6", "后排座椅");
            put("7", "中控板");
            put("8", "车尾");
            put("9", "后备箱底板");
            put("10", "发动机舱");
            put("11", "出厂铭牌");
            put("12", "行驶证");
            put("13", "其它");
        }};
    }



    AutoPhotoApi(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }


    @PostMapping(value = "getPhoto")
    @ApiOperation(value = "获取车辆照片",notes = "根据车辆id获取车辆的照片信息")
    public ResponseEntity<ResultModel> getPhoto(@ApiParam(value = "车辆id",required = true) @RequestParam Long autoId){
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
                    return new ResponseEntity<>(ResultModel.ok(map), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(ResultModel.ok(map), HttpStatus.OK);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return new ResponseEntity<>(ResultModel.ok(map), HttpStatus.OK);
        }
    }

    @PostMapping(value = "savePhoto")
    @ApiOperation(value = "保存车辆照片信息",notes = "将上传的照片保存到车辆照片中")
    @AuthUserToken
    public ResponseEntity<ResultModel> savePhoto(@CurrentUserId Long userId,
            @ApiParam(value = "车辆照片json，车辆id+照片数组",required = true) @RequestBody JSONObject object){
        logger.info("保存车辆照片信息");
        object.put("userId",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(saveAutoPhoto_URL))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(object),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }

    @PostMapping(value = "delPhoto")
    @ApiOperation(value = "删除车辆照片",notes = "根据照片id删除车辆照片")
    @AuthUserToken
    public ResponseEntity<ResultModel> delPhoto(@ApiParam(value = "照片id",required = true) @RequestParam Long photoId){
        logger.info("删除车辆照片");
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(delAutoPhoto_URL))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(photoId),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }

    @RequestMapping(value = "/queryPhoto",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "获取车辆照片",notes = "根据车辆id获取车辆的照片信息")
    @AppApiVersion(value = "2.0")
    public ResponseEntity getPhoto(@RequestBody Map<String,Object> requestMap) {
        Long carId;
        String type = "";
        if (requestMap.get("carId") == null && requestMap.get("type") == null) {
            return new ResponseEntity<>(new ResultModel(false, 101, "缺少参数", null), HttpStatus.OK);
        } else {
            carId = Longs.tryParse(requestMap.get("carId").toString());
            type = requestMap.get("type").toString();
        }
        requestMap.get("carId");
        logger.info("获取车辆照片");
        if ("auto_photo".equals(type)) {
            return getCarAutoByCarId(carId);
        } else if ("auto_detection_data_photo".equals(type)) {
            ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                    RequestEntity
                            .post(URI.create(Constants.ROOT + "/service/carAutoDetectionDataPhotoApi/getCarDetectionPhoto"))
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(requestMap), JSONObject.class);
            return ApiUtil.getResponseEntity(response, resultModel, ApiUtil.LIST);
        }
        return getCarAutoByCarId(carId);
    }
    /**
     * 车辆
     * @param carId
     * @return
     */
    private ResponseEntity getCarAutoByCarId(Long carId) {
        Map<String,Object> map = new HashMap<>();
        List<Map<String, Object>> list = Lists.newArrayList();
        try {
            ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                    RequestEntity
                            .post(URI.create(getAutoPhoto_URL))
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(carId), JSONObject.class);
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                JSONObject object = response.getBody();
                if (object != null && object.getJSONArray("result") != null) {
                    //解析车辆照片，重新按类型封装
                    JSONArray jsonArray = object.getJSONArray("result");
                    List otherPhoto = new ArrayList();
                    for (Object photo : jsonArray) {
                        JSONObject photoObj = (JSONObject) photo;
                        if (photoObj.get("photoType") != null) {
                            map = new HashMap<>();
                            map.put("title", autoTitleMap.get(photoObj.getString("photoType")));
                            map.put("url", photoObj.getString("photoUrl"));
                            list.add(map);
                        }
                    }
                    return new ResponseEntity<>(new ResultModel(true, ResultCode.SUCCESS.value(),ResultCode.SUCCESS.getRemark(),list),HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(new ResultModel(true, ResultCode.SUCCESS.value(),ResultCode.SUCCESS.getRemark(),list),HttpStatus.OK);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return new ResponseEntity<>(new ResultModel(true, ResultCode.SUCCESS.value(),ResultCode.SUCCESS.getRemark(),list),HttpStatus.OK);
        }
    }
}
