package com.wintop.ms.carauction.controller;
import com.wintop.ms.carauction.core.annotation.AuthPublic;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.alibaba.fastjson.JSONObject;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.Map;
/**
 * Created by 12991 on 2018/3/7.
 */
@RestController
@RequestMapping("/carQuestionClassify")
public class CarQuestionClassifyApi {
    private ResultModel resultModel;
    private final RestTemplate restTemplate;
    CarQuestionClassifyApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private String rootUrl = Constants.ROOT+"/service/question/";

    /**
     * 获取常见问题列表
     */
    @RequestMapping(value = "/selectCarQuestionClassify",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthPublic
    public ResponseEntity<ResultModel> selectCarQuestionClassify(@RequestBody Map<String,Object> map){
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(rootUrl+"selectAllQuestionClassify"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(null),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.LIST);
    }

    /**
     * 根据分类获取常见问题列表
     */
    @RequestMapping(value = "/selectCarQuestion",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthPublic
    public ResponseEntity<ResultModel> selectCarQuestion(@RequestBody Map<String,Object> map) throws MalformedURLException {

        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(rootUrl+"selectQuestionListByClassify"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.LIST);
    }

}
