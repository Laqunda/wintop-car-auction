package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import com.wintop.ms.carauction.util.utils.IdWorker;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Map;

/**
 * @Author: 付陈林.
 * @Description: 文件上传api
 * @Date: 20:00 on 2018/3/6.
 * @Modified by:
 */
@RestController
@RequestMapping("/imageUpload")
public class ImageUploadApi {
    private IdWorker idWorker = new IdWorker(10);
    private ResultModel resultModel;
    private final RestTemplate restTemplate;
    ImageUploadApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 进行图片上传操作
     * @param imageFile
     * @param map
     * @return
     * @throws MalformedURLException
     */
    @RequestMapping(value = "/uploadImage",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResponseEntity<ResultModel> uploadImage(@RequestBody Map<String,Object> map, MultipartFile imageFile) throws MalformedURLException {
        map.put("imageFile", imageFile);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT + "/service/imageUpload/uploadImage"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map), JSONObject.class);
        return ApiUtil.getResponseEntity(response, resultModel, ApiUtil.OBJECT);
    }
}
