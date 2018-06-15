package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultStatus;
import com.wintop.ms.carauction.core.model.ResultModel;
import io.swagger.annotations.ApiOperation;
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
 * 获取客户端相关协议接口
 */
@RestController
@RequestMapping("accord")
public class CarAppAccordApi {

    private final RestTemplate restTemplate;
    private ResultModel resultModel;

    private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(CarAppAccordApi.class);

    CarAppAccordApi(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    private final String getAccordByCodeUrl = Constants.ROOT + "/service/accordApi/getAccordByCode";

    /***
     * 根据编号查询协议内容
     * @param code
     * @return
     */
    @ApiOperation(value = "调用获取协议详情接口")
    @RequestMapping(value = "/getAccordByCode",/*指定请求的实际地址，指定的地址可以是URI Template 模式（后面将会说明）；*/
            method=RequestMethod.POST)/*指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回；*/
    public ResponseEntity<ResultModel> findUserList(@RequestParam String code) {
        Logger.info("查询协议内容");
        ResponseEntity<ResultModel> responseEntity = null;
        try {
            Map map = new HashMap();
            map.put("code",code);
            ResponseEntity<JSONObject> resultResponseEntity = this.restTemplate.exchange(
                    RequestEntity.post(URI.create(getAccordByCodeUrl))
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
                    //如果业务处理有问题
                    resultModel = new ResultModel(false,ResultStatus.SERVICE_ERROR);
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