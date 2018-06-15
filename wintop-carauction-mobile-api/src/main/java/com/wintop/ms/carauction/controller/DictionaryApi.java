package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.CurrentPossibleUserId;
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
 * Created by liangtingsen on 2018/3/5.
 * 字典数据接口
 */
@RestController
@RequestMapping("dictionary")
public class DictionaryApi {
    private final RestTemplate restTemplate;
    private ResultModel resultModel;

    private static final Logger logger = LoggerFactory.getLogger(DictionaryApi.class);
    /**根据编号查询字典项*/
    String getDicByCodeUrl = Constants.ROOT + "/dictionary/getByCode";

    /**根据编号查询字典项*/
    String getDicByPCodeUrl = Constants.ROOT + "/dictionary/getByPCode";
    DictionaryApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /***
     * 根据字典编号获取字典项
     * @param code
     * @return
     */
    @RequestMapping(value = "/getByCode",method = RequestMethod.POST)
    @ApiOperation(value = "根据编号获取字典项")
    public ResponseEntity<ResultModel> getByCode(@RequestParam String code) {
        logger.info("根据编号获取字典项");
        //1、通过http调用基础api获取当前登陆人对象
        ResponseEntity<JSONObject> resultResponseEntity = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(getDicByCodeUrl))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(code),JSONObject.class);
        JSONObject serviceResult = resultResponseEntity.getBody();
        ResultModel resultModel = null;
        //1、先判断返回网络状态码是否成功
        if(resultResponseEntity.getStatusCode()== HttpStatus.OK){
            //2、判断业务处理结果是否成功
            if (serviceResult.get("success").toString()=="true") {
                JSONObject object = serviceResult.getJSONObject("result");
                if (object!=null){//已存在
                    resultModel = new ResultModel(true, ResultStatus.SUCCESS,object);
                }else {//不存在
                    resultModel = new ResultModel(true,ResultStatus.OBJECT_NOT_EXIST);
                }
                return new ResponseEntity<>(resultModel, HttpStatus.OK);
            }else {//异常情况
                resultModel = new ResultModel(true,ResultStatus.ERROR);
                return new ResponseEntity<>(resultModel,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else {
            resultModel = new ResultModel(false,ResultStatus.ERROR);
            return new ResponseEntity<>(resultModel,resultResponseEntity.getStatusCode());
        }
    }

    /***
     * 根据字典编号获取子类集合
     * @param code
     * @return
     */
    @RequestMapping(value = "/getByPCode",method = RequestMethod.POST)
    @ApiOperation(value = "根据上级编号获取子集字典项集合")
    public ResponseEntity<ResultModel> getByPCode(@RequestParam String code) {
        logger.info("根据上级编号获取子集字典项集合");

        Map map = new HashMap();
        map.put("code",code);
        map.put("status","1");
        //1、通过http调用基础api获取当前登陆人对象
        ResponseEntity<JSONObject> resultResponseEntity = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(getDicByPCodeUrl))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        JSONObject serviceResult = resultResponseEntity.getBody();
        ResultModel resultModel = null;
        //1、先判断返回网络状态码是否成功
        if(resultResponseEntity.getStatusCode()== HttpStatus.OK){
            //2、判断业务处理结果是否成功
            if (serviceResult.get("success").toString()=="true") {
                JSONArray object = serviceResult.getJSONArray("result");
                if (object!=null){//已存在
                    resultModel = new ResultModel(true, ResultStatus.SUCCESS,object);
                }else {//不存在
                    resultModel = new ResultModel(true,ResultStatus.OBJECT_NOT_EXIST);
                }
                return new ResponseEntity<>(resultModel, HttpStatus.OK);
            }else {//异常情况
                resultModel = new ResultModel(true,ResultStatus.ERROR);
                return new ResponseEntity<>(resultModel,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else {
            resultModel = new ResultModel(false,ResultStatus.ERROR);
            return new ResponseEntity<>(resultModel,resultResponseEntity.getStatusCode());
        }
    }
}
