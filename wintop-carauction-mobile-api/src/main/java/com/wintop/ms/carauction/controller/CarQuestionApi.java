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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
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
    //据问题分类编码查询该分类下面的问题集
    String getQuestionByCode="/service/question/getQuestionByCode";
    //查询问题集
    String selectQuestionList="/service/question/selectQuestionList";
    //根据问题Id获取问题详情
    String selectById="/service/question/selectQuestionById";
    CarQuestionApi(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

    /**
     * 根据问题分类编码查询该分类下面的问题集
     * @return
     */
    @PostMapping(value = "getQuestionByCode", consumes="application/json; charset=UTF-8",produces="application/json; charset=UTF-8")
    @ApiOperation(value = "查询常见问题")
    public ResponseEntity<ResultModel> getQuestionByCode(@RequestBody Map<String,Object> map){
        if(map.get("code")==null){
            return new ResponseEntity<>(new ResultModel(false,101,"缺少参数",null), HttpStatus.OK);
        }
        ResponseEntity<JSONObject> response=this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+getQuestionByCode))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.LIST);
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

    /**
     * 根据问题Id获取问题详情
     * @return
     */
    @PostMapping(value = "getQuestionById", consumes="application/json; charset=UTF-8",produces="application/json; charset=UTF-8")
    @ApiOperation(value = "根据问题Id获取问题详情")
    public ResponseEntity<ResultModel> getQuestionById(@RequestBody Map<String,Object> map){
        if(map.get("questionId")==null){
            return new ResponseEntity<>(new ResultModel(false,101,"缺少参数",null), HttpStatus.OK);
        }
        ResponseEntity<JSONObject> response=this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+selectById))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }

    /**
     * 首页常见问题查询
     * @return
     */
    @PostMapping(value = "selectIndexQuestion", consumes="application/json; charset=UTF-8",produces="application/json; charset=UTF-8")
    @ApiOperation(value = "首页常见问题查询")
    public ResponseEntity<ResultModel> selectIndexQuestion(@RequestBody Map<String,Object> map){
        if(map.get("limit")==null){
            map.put("limit",4);
        }
        ResponseEntity<JSONObject> response=this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/question/selectIndexQuestion"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.LIST);
    }
}
