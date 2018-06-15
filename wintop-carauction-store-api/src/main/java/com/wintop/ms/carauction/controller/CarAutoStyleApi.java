package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultStatus;
import com.wintop.ms.carauction.core.model.ResultModel;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * 车型接口
 */
@RestController
@RequestMapping("/carStyle")
public class CarAutoStyleApi {
    private static final Logger logger = LoggerFactory.getLogger(CarAutoStyleApi.class);
    private final RestTemplate restTemplate;

    CarAutoStyleApi(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }


    @ApiOperation(value = "根据车系查询车型")
    @PostMapping(value = "/getBySeries",
            produces="application/json; charset=UTF-8")
    public ResponseEntity<ResultModel> getBySeries(@RequestParam String seriesId){
        logger.info("根据车系查询车型");
        ResponseEntity<ResultModel> responseEntity = null;
        try {
            JSONObject object = new JSONObject();
            object.put("seriesId",seriesId);
            ResponseEntity<JSONArray> response = this.restTemplate.exchange(
                    RequestEntity.post(URI.create(Constants.GET_VEHICLE_URL))
                            .body(object)
                    ,JSONArray.class);
            responseEntity = new ResponseEntity<>(ResultModel.ok(response.getBody()),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("根据车系查询车型-失败",e);
            responseEntity = new ResponseEntity<>(ResultModel.error(ResultStatus.SERVICE_ERROR),HttpStatus.INTERNAL_SERVER_ERROR);
        }finally {
            return responseEntity;
        }
    }
}
