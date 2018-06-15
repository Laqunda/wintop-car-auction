package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultStatus;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

/**
 * @author zhangzijuan
 * @Description:车辆手续信息
 * @date 2018-03-07
 */
@RestController
@RequestMapping("/procedures")
public class CarAutoProceduresApi {
    private ResultModel resultModel;

    private final RestTemplate restTemplate;
    //根据车辆d查询车辆手续信息
    String getAutoProceduresByCarId="/service/procedures/getAutoProceduresByCarId";

    CarAutoProceduresApi(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }


    /**
     * 根据车辆d查询车辆手续信息
     * @return
     */
    @PostMapping(value = "getAutoProceduresByCarId",produces="application/json; charset=UTF-8")
    @ApiOperation(value = "查询车辆手续信息")
    public ResponseEntity<ResultModel> getAutoProceduresByCarId(@RequestBody Map<String,Object> map){
        if(map.get("carId")==null){
            return new ResponseEntity<>(new ResultModel(false,101,"缺少参数",null), HttpStatus.OK);
        }
        ResponseEntity<JSONObject> response=this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+getAutoProceduresByCarId))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        resultModel=new ResultModel(true, ResultStatus.SUCCESS);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }
}
