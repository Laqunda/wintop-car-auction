package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.primitives.Longs;
import com.wintop.ms.carauction.core.annotation.AppApiVersion;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import io.swagger.annotations.ApiOperation;
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

    private ResultModel resultModel;
    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(AutoPhotoApi.class);
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

    public AutoPhotoApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public static final String getAutoPhoto_URL = Constants.ROOT+"/autoPhoto/getByAutoId";

    @RequestMapping(value = "/getPhoto",
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
