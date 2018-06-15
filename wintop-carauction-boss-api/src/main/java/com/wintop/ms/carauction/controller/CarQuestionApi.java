package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthPublic;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.TokenManager;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.core.model.TokenModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
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
    @Autowired
    private TokenManager tokenManager;
    private final RestTemplate restTemplate;

    private String rootUrl = Constants.ROOT+"/service/question/";
    CarQuestionApi(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

    /**
     * 获取常见问题分类列表
     */
    @RequestMapping(value = "/selectQuestionClassifyList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel selectQuestionClassifyList(@RequestBody Map<String,Object> map){
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(rootUrl+"selectQuestionClassifyList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 获取所有常见问题分类列表
     */
    @RequestMapping(value = "/selectAllQuestionClassify",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel selectAllQuestionClassify(){
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(rootUrl+"selectAllQuestionClassify"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(null),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.LIST);
    }


    /**
     * 保存问题分类
     * @return
     */
    @PostMapping(value = "saveQuestionClassify", consumes="application/json; charset=UTF-8",produces="application/json; charset=UTF-8")
    @ApiOperation(value = "保存问题分类")
    @AuthUserToken
    public ResultModel saveQuestionClassify(@RequestBody Map<String,Object> map, @CurrentUserId Long userId){
        map.put("createPerson",userId);
        ResponseEntity<JSONObject> response=this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(rootUrl+"saveQuestionClassify"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 修改问题分类
     * @return
     */
    @PostMapping(value = "updateQuestionClassify", consumes="application/json; charset=UTF-8",produces="application/json; charset=UTF-8")
    @ApiOperation(value = "修改问题分类")
    @AuthUserToken
    public ResultModel updateQuestionClassify(@RequestBody Map<String,Object> map){
        ResponseEntity<JSONObject> response=this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(rootUrl+"updateQuestionClassify"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 查询问题列表
     * @return
     */
    @PostMapping(value = "selectQuestionList", consumes="application/json; charset=UTF-8",produces="application/json; charset=UTF-8")
    @ApiOperation(value = "查询常见问题")
    @AuthUserToken
    public ResultModel getAllQuestion(@RequestBody Map<String,Object> map){
        ResponseEntity<JSONObject> response=this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(rootUrl+"selectQuestionList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 根据问题Id获取问题详情
     * @return
     */
    @PostMapping(value = "selectQuestionById", consumes="application/json; charset=UTF-8",produces="application/json; charset=UTF-8")
    @ApiOperation(value = "根据问题Id获取问题详情")
    @AuthUserToken
    public ResultModel selectQuestionById(@RequestBody Map<String,Object> map){
        ResponseEntity<JSONObject> response=this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(rootUrl+"selectQuestionById"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 保存问题
     * @return
     */
    @PostMapping(value = "saveQuestion", consumes="application/json; charset=UTF-8",produces="application/json; charset=UTF-8")
    @ApiOperation(value = "保存问题")
    @AuthUserToken
    public ResultModel saveQuestion(@RequestBody Map<String,Object> map, @CurrentUserId Long userId){
        map.put("createPerson",userId);
        ResponseEntity<JSONObject> response=this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(rootUrl+"saveQuestion"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 修改问题
     * @return
     */
    @PostMapping(value = "updateQuestion", consumes="application/json; charset=UTF-8",produces="application/json; charset=UTF-8")
    @ApiOperation(value = "修改问题")
    @AuthUserToken
    public ResultModel saveQuestion(@RequestBody Map<String,Object> map){
        ResponseEntity<JSONObject> response=this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(rootUrl+"updateQuestion"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 删除问题分类
     * @return
     */
    @PostMapping(value = "deleteQuestionClassify", consumes="application/json; charset=UTF-8",produces="application/json; charset=UTF-8")
    @ApiOperation(value = "删除问题分类")
    @AuthUserToken
    public ResultModel deleteQuestionClassify(@RequestBody Map<String,Object> map){
        ResponseEntity<JSONObject> response=this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(rootUrl+"deleteQuestionClassify"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 删除问题
     * @return
     */
    @PostMapping(value = "deleteQuestion", consumes="application/json; charset=UTF-8",produces="application/json; charset=UTF-8")
    @ApiOperation(value = "删除问题")
    @AuthUserToken
    public ResultModel deleteQuestion(@RequestBody Map<String,Object> map){
        ResponseEntity<JSONObject> response=this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(rootUrl+"deleteQuestion"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

}
