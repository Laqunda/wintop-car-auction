package com.wintop.ms.carauction.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liangtingsen on 2018/3/9.
 * 车辆质检报告
 */
@RestController
@RequestMapping("autoDetection")
public class CarAutoDetectionApi {

    private final RestTemplate restTemplate;
    private ResultModel resultModel;

    private int port = 8185;
    private static final Logger logger = LoggerFactory.getLogger(CarAutoDetectionApi.class);
    /**根据车辆查询全部质检报告结果*/
    String getByIdUrl = Constants.ROOT + "/autoDetection/getAutoDetection";

    CarAutoDetectionApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    /***
     * 获取车辆质检报告
     * @param autoId
     * @return
     */
    @RequestMapping(value = "/getAutoDetection",method = RequestMethod.POST)
    @ApiOperation(value = "根据车辆id获取车辆质检报告")
    public ResponseEntity<ResultModel> getAutoDetection(@RequestParam Long autoId){
        logger.info("查询协议内容");
        ResponseEntity<ResultModel> responseEntity = null;
        try {
            Map map = new HashMap();
            map.put("autoId",autoId);
            ResponseEntity<JSONObject> resultResponseEntity = this.restTemplate.exchange(
                    RequestEntity.post(URI.create(getByIdUrl))
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(map),JSONObject.class);
            JSONObject serviceResult = resultResponseEntity.getBody();
            ResultModel resultModel;
            //1、先判断返回网络状态码是否成功
            if(resultResponseEntity.getStatusCode()== HttpStatus.OK){
                //2、判断业务处理结果是否成功
                if (serviceResult.get("success").toString()=="true") {
                    JSONObject object = serviceResult.getJSONObject("result");
                    resultModel = new ResultModel(true, ResultStatus.SUCCESS, object);
                    responseEntity = new ResponseEntity<>(resultModel, HttpStatus.OK);
                }else {
                    //如果业务处理有问题，返回错误编码
                    resultModel = new ResultModel(false,ResultStatus.ERROR);
                    responseEntity = new ResponseEntity<>(resultModel,HttpStatus.OK);
                }
            }else {
                resultModel = new ResultModel(false,ResultStatus.SERVICE_ERROR);
                responseEntity = new ResponseEntity<>(resultModel,resultResponseEntity.getStatusCode());
            }
        }catch (Exception e){
            e.printStackTrace();
            resultModel = new ResultModel(false,ResultStatus.ERROR);
            responseEntity = new ResponseEntity<>(resultModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }finally {
            return responseEntity;
        }

    }
}
