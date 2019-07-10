package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

/**
 * @author zhangzijuan
 * @Description:常见问题
 * @date 2018-03-06
 */
@RestController
@RequestMapping("/question")
public class CarQuestionApi {
    private ResultModel resultModel;

    private final RestTemplate restTemplate;
    //查询问题集
    String selectQuestionList="/service/question/selectQuestionList";
    CarQuestionApi(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }


    /**
     * 查询问题集
     * @return
     */
    @PostMapping(value = "getAllQuestion", consumes="application/json; charset=UTF-8",produces="application/json; charset=UTF-8")
    @ApiOperation(value = "查询常见问题")
    public ResponseEntity<ResultModel> getAllQuestion(@RequestBody Map<String,Object> map){
        map.put("isOpen","1");
        ResponseEntity<JSONObject> response=this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+selectQuestionList))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }

}
