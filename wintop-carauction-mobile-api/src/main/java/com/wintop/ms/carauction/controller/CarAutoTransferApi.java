package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUser;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.config.ResultStatus;
import com.wintop.ms.carauction.core.config.TransferStatusEnum;
import com.wintop.ms.carauction.core.entity.AppUser;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import com.wintop.ms.carauction.util.utils.IdWorker;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
 * Created by 12991 on 2018/3/10.
 */
@RestController
@RequestMapping("/carAutoTransfer")
public class CarAutoTransferApi {
    private IdWorker idWorker = new IdWorker(10);
    private ResultModel resultModel;
    private final RestTemplate restTemplate;
    CarAutoTransferApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @ApiOperation(value = "确认过户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "transferId",value = "过户id",required = true,dataType = "long"),
            @ApiImplicitParam(name = "moveToWhere",value = "2只能外迁，3只能本市",required = true,dataType = "string"),
            @ApiImplicitParam(name = "moveAddress",value = "迁往具体地址",required = true,dataType = "string"),
            @ApiImplicitParam(name = "fileType",value = "附件类型 1 图片 2附件",required = false,dataType = "string"),
            @ApiImplicitParam(name = "fileUrl",value = "附件路径",required = false,dataType = "string"),
            @ApiImplicitParam(name = "handleTime",value = "办理时间 格式：2018-02-26 12:00:00",required = true,dataType = "string"),
            @ApiImplicitParam(name = "remark",value = "备注",required = false,dataType = "string"),
    })
    @PostMapping(value = "/saveDetermine",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResponseEntity confirmTransfer(@CurrentUserId Long managerId, @RequestBody Map<String,Object> map) {
        map.put("managerId",managerId);
        if(map.get("transferId")==null  || map.get("managerId")==null  || map.get("handleTime")==null
                || map.get("moveToWhere")==null || map.get("moveAddress")==null){
            return new ResponseEntity(ResultModel.error(ResultStatus.PARAMETERS_ERROR),HttpStatus.OK);
        }
        map.put("status", TransferStatusEnum.PLATE_HANLDING.value());
        map.put("type","1");
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/transferBoss/confirmTransfer"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.OBJECT);
    }


    /**
     * 出牌
     */
    @RequestMapping(value = "/savePlay",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResponseEntity<ResultModel> savePlay(@RequestBody Map<String,Object> map, @CurrentUserId Long userId) throws MalformedURLException {
        map.put("managerId",userId);
        map.put("type","1");
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/transferBoss/outLicensePlate"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.OBJECT);
    }


    /***
     * 确认交档
     * @param map
     * @return
     */
    @ApiOperation(value = "确认交档")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "transferId",value = "过户id",required = true,dataType = "long"),
            @ApiImplicitParam(name = "fileType",value = "附件类型 1 图片 2附件",required = false,dataType = "string"),
            @ApiImplicitParam(name = "fileUrl",value = "附件路径",required = false,dataType = "string"),
            @ApiImplicitParam(name = "handleTime",value = "办理时间",required = true,dataType = "string"),
            @ApiImplicitParam(name = "remark",value = "备注",required = false,dataType = "string"),
    })
    @PostMapping(value = "/saveToStop",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResponseEntity submitFiles(@CurrentUserId Long userId, @RequestBody Map<String,Object> map) {
        if(map.get("transferId")==null || map.get("handleTime")==null){
            return new ResponseEntity(ResultModel.error(ResultStatus.PARAMETERS_ERROR),HttpStatus.OK);
        }
        map.put("type","1");
        map.put("managerId",userId);
        map.put("status",TransferStatusEnum.EXTRACT_FILE.value());
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/transferBoss/submitFiles"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.OBJECT);
    }


    /***
     * 确认提档
     * @param map
     * @return
     */
    @ApiOperation(value = "确认提档")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "transferId",value = "过户id",required = true,dataType = "long"),
            @ApiImplicitParam(name = "fileType",value = "附件类型 1 图片 2附件",required = false,dataType = "string"),
            @ApiImplicitParam(name = "fileUrl",value = "附件路径",required = false,dataType = "string"),
            @ApiImplicitParam(name = "handleTime",value = "办理时间",required = true,dataType = "string"),
            @ApiImplicitParam(name = "remark",value = "备注",required = false,dataType = "string"),
    })
    @PostMapping(value = "/saveLiftBlock",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResponseEntity takeFiles(@CurrentUserId Long userId, @RequestBody Map<String,Object> map) {
        if(map.get("transferId")==null || map.get("handleTime")==null){
            return new ResponseEntity(ResultModel.error(ResultStatus.PARAMETERS_ERROR),HttpStatus.OK);
        }
        map.put("managerId",userId);
        map.put("type","1");
        map.put("status",TransferStatusEnum.PROCEDURE_UPLOADING.value());
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/transferBoss/takeFiles"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.OBJECT);
    }




    /***
     * 上传手续
     * @param map
     * @return
     */
    @ApiOperation(value = "上传手续")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "transferId",value = "过户id",required = true,dataType = "long"),
            @ApiImplicitParam(name = "fileType",value = "附件类型 1 图片 2附件",required = false,dataType = "string"),
            @ApiImplicitParam(name = "fileUrl",value = "附件路径",required = true,dataType = "string"),
            @ApiImplicitParam(name = "remark",value = "备注",required = true,dataType = "string"),
    })
    @PostMapping(value = "/saveCarAutoTransfer",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel saveCarAutoTransfer(@CurrentUserId Long managerId, @RequestBody Map<String,Object> map) {
        map.put("managerId",managerId);
        if(map.get("transferId")==null  || map.get("managerId")==null || map.get("fileUrl")==null || map.get("fileType")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("status",TransferStatusEnum.PROCEDURE_HANLDING.value());
        ///用户类型是买家自行办理
        map.put("type","1");
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/transferBoss/uploadProcedures"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.LIST);
    }



    /**
     * 交接完成
     */
    @RequestMapping(value = "/saveHandOver",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResponseEntity<ResultModel> saveHandOver(@RequestBody Map<String,Object> map, @CurrentUser AppUser appUser) throws MalformedURLException {
        map.put("createUser",appUser.getUserName());
        map.put("userId",appUser.getId());
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carAutoTransfer/saveHandOver"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.OBJECT);
    }
    /**
     * 根据条件查询过户流程信息
     * @param map
     * @return
     */
    @RequestMapping(value = "/queryTransferList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResponseEntity<ResultModel> queryTransferList(@RequestBody Map<String,Object> map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carAutoTransfer/queryTransferList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel,ApiUtil.OBJECT);
    }
}
