package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.config.TransferStatusEnum;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
 * @Description:PC端过户手续
 * @date 2018-03-26
 */
@RestController
@RequestMapping("/autoTransfer")
public class CarAutoTransferApi {
    private final RestTemplate restTemplate;
    CarAutoTransferApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    /***
     * 确认过户
     * @return
     */
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
    @PostMapping(value = "/confirmTransfer",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel confirmTransfer(@CurrentUserId Long managerId, @RequestBody Map<String,Object> map) {
        map.put("managerId",managerId);
        if(map.get("transferId")==null  || map.get("managerId")==null  || map.get("handleTime")==null
            || map.get("moveToWhere")==null || map.get("moveAddress")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("type","3");
        map.put("status", TransferStatusEnum.PLATE_HANLDING.value());
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/transferBoss/confirmTransfer"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.LIST);
    }



    /***
     * 确认出牌
     * @param map
     * @return
     */
    @ApiOperation(value = "确认出牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "transferId",value = "过户id",required = true,dataType = "long"),
            @ApiImplicitParam(name = "fileType",value = "附件类型 1 图片 2附件",required = false,dataType = "string"),
            @ApiImplicitParam(name = "fileUrl",value = "附件路径",required = false,dataType = "string"),
            @ApiImplicitParam(name = "handleTime",value = "办理时间",required = true,dataType = "string"),
            @ApiImplicitParam(name = "remark",value = "备注",required = false,dataType = "string"),
    })
    @PostMapping(value = "/outLicensePlate",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel outLicensePlate(@CurrentUserId Long managerId, @RequestBody Map<String,Object> map) {
        map.put("managerId",managerId);
        if(map.get("transferId")==null || map.get("managerId")==null || map.get("handleTime")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("type","3");
        map.put("status",TransferStatusEnum.HANDOVER_FILE.value());
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/transferBoss/outLicensePlate"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.LIST);
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
    @PostMapping(value = "/submitFiles",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel submitFiles(@CurrentUserId Long managerId, @RequestBody Map<String,Object> map) {
        map.put("managerId",managerId);
        if(map.get("transferId")==null  || map.get("managerId")==null || map.get("handleTime")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("type","3");
        map.put("status",TransferStatusEnum.EXTRACT_FILE.value());
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/transferBoss/submitFiles"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.LIST);
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
    @PostMapping(value = "/takeFiles",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel takeFiles(@CurrentUserId Long managerId, @RequestBody Map<String,Object> map) {
        map.put("managerId",managerId);
        if(map.get("transferId")==null  || map.get("managerId")==null || map.get("handleTime")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("type","3");
        map.put("status",TransferStatusEnum.PROCEDURE_UPLOADING.value());
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/transferBoss/takeFiles"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.LIST);
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
    @PostMapping(value = "/uploadProcedures",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel uploadProcedures(@CurrentUserId Long managerId, @RequestBody Map<String,Object> map) {
        map.put("managerId",managerId);
        if(map.get("transferId")==null  || map.get("managerId")==null || map.get("fileUrl")==null || map.get("fileType")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("type","3");
        map.put("status",TransferStatusEnum.PROCEDURE_HANLDING.value());
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/transferBoss/uploadProcedures"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.LIST);
    }
    @ApiImplicitParam(name = "transferId",value = "过户id",required = true,dataType = "long")
    @ApiOperation(value = "查看审批信息")
    @PostMapping(value = "/getApproveTransfer",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel getApproveTransfer(@RequestBody Map<String,Object> map) {
        if(map.get("transferId")==null ){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/transferBoss/getApproveTransfer"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    @ApiImplicitParam(name = "transferId",value = "过户id",required = true,dataType = "long")
    @ApiOperation(value = "确认过户时查看过户信息")
    @PostMapping(value = "/getTransferById",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel getTransferById(@RequestBody Map<String,Object> map) {
        if(map.get("transferId")==null ){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/transferBoss/getTransferById"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }
}
