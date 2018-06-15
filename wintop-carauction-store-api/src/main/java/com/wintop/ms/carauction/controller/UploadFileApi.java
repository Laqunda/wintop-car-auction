package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultStatus;
import com.wintop.ms.carauction.core.model.ResultModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/***
 * 上传文件使用
 */
@Api(value = "文件上传操作类", description ="图片、附件上传使用")
@RestController
@RequestMapping("upload")
public class UploadFileApi {

    Logger logger = LoggerFactory.getLogger(UploadFileApi.class);
    private final RestTemplate restTemplate;

    UploadFileApi(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    /***
     * 图片质量压缩上传
     * @param file
     * @param quality
     * @return
     */
    @ApiOperation(value = "图片质量压缩上传", notes = "根据压缩比例参数上传图片并压缩处理")
    @RequestMapping(value = "uploadImageForQuality",
            method = RequestMethod.POST,
            consumes = "multipart/form-data")
    public ResponseEntity<ResultModel> uploadImageForQuality(
            @ApiParam(name="file",value="图片字段",required = true)@RequestParam MultipartFile file,
            @ApiParam(name="quality",value="质量压缩比例",required = false,defaultValue = "1")@RequestParam(defaultValue = "1") float quality) {
        logger.info("图片质量压缩上传");
        ResponseEntity<ResultModel> responseEntity = null;
        ResultModel resultModel;
        File tempFile = null;
        try {
            //将post提交过来的图片保存为服务器临时文件
            tempFile = File.createTempFile(UUID.randomUUID().toString(),file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
            file.transferTo(tempFile);

            //将生成的临时文件封装为文件api参数
            FileSystemResource resource = new FileSystemResource(tempFile);
            MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
            param.add("file", resource);
            param.add("quality", quality);

            //以restTemplate方式将文件post到文件服务器
            HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(param);
            ResponseEntity<JSONObject> resultResponseEntity = this.restTemplate.exchange(Constants.UPLOADIMAGEFORQUALITY_URL, HttpMethod.POST,httpEntity, JSONObject.class);
            if (resultResponseEntity.getStatusCode()== HttpStatus.OK){
                JSONObject serviceResult = resultResponseEntity.getBody();
                //公共文件上传服务-返回参数成功
                if (serviceResult.getBoolean("success")){
                    Map fileMap = new HashMap();
                    fileMap.put("url",serviceResult.getString("result"));
                    resultModel = new ResultModel(true, ResultStatus.SUCCESS,fileMap);
                    responseEntity = new ResponseEntity<>(resultModel,HttpStatus.OK);
                }else {
                    resultModel = new ResultModel(false, ResultStatus.SERVICE_ERROR);
                    responseEntity = new ResponseEntity<>(resultModel,HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }else {
                resultModel = new ResultModel(false, ResultStatus.SERVICE_ERROR);
                responseEntity = new ResponseEntity<>(resultModel,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultModel = new ResultModel(false, ResultStatus.SERVICE_ERROR);
            responseEntity = new ResponseEntity<>(resultModel,HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            if (tempFile !=null && tempFile.exists()) {
                tempFile.deleteOnExit();
            }
            return responseEntity;
        }
    }


    /***
     * 上传附件
     * @param file
     * @return
     */
    @ApiOperation(value = "上传附件", notes = "附件上传")
    @RequestMapping(value = "uploadFile",
            method = RequestMethod.POST,
            consumes = "multipart/form-data")
    public ResponseEntity<ResultModel> uploadFile(
            @ApiParam(name="file",value="文件",required = true)@RequestParam MultipartFile file) {
        logger.info("上传附件");
        ResponseEntity<ResultModel> responseEntity = null;
        ResultModel resultModel;
        File tempFile = null;
        try {
            //将post提交过来的图片保存为服务器临时文件
            tempFile = File.createTempFile(UUID.randomUUID().toString(),file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
            file.transferTo(tempFile);

            //将生成的临时文件封装为文件api参数
            FileSystemResource resource = new FileSystemResource(tempFile);
            MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
            param.add("file", resource);

            //以restTemplate方式将文件post到文件服务器
            HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(param);
            ResponseEntity<JSONObject> resultResponseEntity = this.restTemplate.exchange(Constants.UPLOADFILE_URL, HttpMethod.POST,httpEntity, JSONObject.class);
            if (resultResponseEntity.getStatusCode()== HttpStatus.OK){
                JSONObject serviceResult = resultResponseEntity.getBody();
                //公共文件上传服务-返回参数成功
                if (serviceResult.getBoolean("success")){
                    Map fileMap = new HashMap();
                    fileMap.put("url",serviceResult.getString("result"));
                    resultModel = new ResultModel(true, ResultStatus.SUCCESS,fileMap);
                    responseEntity = new ResponseEntity<>(resultModel,HttpStatus.OK);
                }else {
                    resultModel = new ResultModel(false, ResultStatus.SERVICE_ERROR);
                    responseEntity = new ResponseEntity<>(resultModel,HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }else {
                resultModel = new ResultModel(false, ResultStatus.SERVICE_ERROR);
                responseEntity = new ResponseEntity<>(resultModel,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultModel = new ResultModel(false, ResultStatus.SERVICE_ERROR);
            responseEntity = new ResponseEntity<>(resultModel,HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            if (tempFile !=null && tempFile.exists()) {
                tempFile.deleteOnExit();
            }
            return responseEntity;
        }
    }
}