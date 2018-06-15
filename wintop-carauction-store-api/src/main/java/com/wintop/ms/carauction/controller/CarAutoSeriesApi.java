package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultStatus;
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
import java.util.HashMap;
import java.util.Map;

/**
 * 车系接口
 */
@RestController
@RequestMapping("/carSeries")
public class CarAutoSeriesApi {
    private static final Logger logger = LoggerFactory.getLogger(CarAutoSeriesApi.class);
    private final RestTemplate restTemplate;

    CarAutoSeriesApi(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }


    @ApiOperation(value = "根据品牌获取车系数据")
    @PostMapping(value = "/getByBrand",
            produces="application/json; charset=UTF-8")
    public ResponseEntity<ResultModel> getByBrand(@RequestBody JSONObject object){
        logger.info("根据品牌获取车系数据");
        ResponseEntity<ResultModel> responseEntity = null;
        try {
            Map map = new HashMap();
            map.put("brandId",object.get("brandId"));
            ResponseEntity<JSONArray> response = this.restTemplate.exchange(
                    RequestEntity.post(URI.create(Constants.GET_SERIES_URL))
                            .header("Content-Type",new String[]{MediaType.APPLICATION_JSON_UTF8.toString()})
                            .accept(MediaType.APPLICATION_JSON_UTF8)
                            .body(map)
                    ,JSONArray.class);
            responseEntity = new ResponseEntity<>(ResultModel.ok(response.getBody()),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("根据品牌获取车系数据-失败",e);
            responseEntity = new ResponseEntity<>(ResultModel.error(ResultStatus.SERVICE_ERROR),HttpStatus.INTERNAL_SERVER_ERROR);
        }finally {
            return responseEntity;
        }
    }
}
