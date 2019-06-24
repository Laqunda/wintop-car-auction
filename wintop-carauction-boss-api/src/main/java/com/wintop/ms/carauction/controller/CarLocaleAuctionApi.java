package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthPublic;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.annotation.RequestAuth;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.config.ResultStatus;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: 付陈林.
 * @Description: 现场拍--竞拍场次PC端接口
 * @Date: 16:04 on 2018/3/15.
 * @Modified by:
 */
@Api(tags = "现场拍--竞拍场次PC端接口",value = "根据页面进行划分，对所有的现场拍相关的接口在此进行管控!")
@RestController
@RequestMapping("/localeAuction")
public class CarLocaleAuctionApi {
    private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(CarLocaleAuctionApi.class);
    private ResultModel resultModel;
    private final RestTemplate restTemplate;
    CarLocaleAuctionApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /***********************************场次列表相关接口************************************/
    @PostMapping(value = "/getLocaleAuctionList",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "根据条件查询竞拍场次列表",notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status",value = "竞拍状态",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "title",value = "场次主题",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "beginTime",value = "开拍开始时间",required = false,paramType = "query",dataType = "object"),
            @ApiImplicitParam(name = "endTime",value = "开拍结束时间",required = false,paramType = "query",dataType = "object"),
            @ApiImplicitParam(name = "page",value = "当前页数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "limit",value = "每页显示的条数",required = true,paramType = "query",dataType = "int")
    })
    @AuthUserToken
    public ResultModel getLocaleAuctionList(@RequestBody Map<String,Object> map,@CurrentUserId Long maanagerId){
        if(map.get("page")==null || map.get("limit")==null){
            return new ResultModel(false,101,"缺少page或者limit参数",null);
        }
        map.put("managerId", maanagerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carLocaleAuction/getLocaleAuctionList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    @PostMapping(value = "/setUpAuction",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "上拍操作",notes = "")
    @ApiImplicitParam(name = "id",value = "场次主键",required = false,paramType = "query",dataType = "string")
    @AuthUserToken
    public ResultModel setUpAuction(@RequestBody Map<String,Object> map){
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carLocaleAuction/setUpAuction"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }
    @PostMapping(value = "/setDownAuction",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "下拍操作",notes = "")
    @ApiImplicitParam(name = "id",value = "场次主键",required = false,paramType = "query",dataType = "string")
    @AuthUserToken
    public ResultModel setDownAuction(@RequestBody Map<String,Object> map){
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carLocaleAuction/setDownAuction"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    @PostMapping(value = "/deleteLocaleAuction",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "删除竞拍场次",notes = "")
    @ApiImplicitParam(name = "id",value = "场次主键",required = false,paramType = "query",dataType = "string")
    @AuthUserToken
    public ResultModel deleteLocaleAuction(@CurrentUserId Long userId,@RequestBody Map<String,Object> map){
        if(map.get("id")==null){
            return new ResultModel(false,101,"缺少参数",null);
        }
        map.put("userId",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carLocaleAuction/deleteLocaleAuction"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /************************************************场次新增界面相关接口********************************************/
    /**
     * @Author 付陈林
     * @Date 2018-3-15
     * @Abuot 获取现场竞拍的系统编号
     * */
    @PostMapping(value = "/getLocaleAuctionSessionNo",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "获取现场竞拍的系统编号(系统自动生成)",notes = "")
    @AuthUserToken
    @RequestAuth(false)
    public ResultModel getLocaleAuctionSessionNo(@RequestBody Map<String,Object> map){
        Date date = new Date();
        String s = new SimpleDateFormat("yyyyMMdd").format(date)+date.getTime();
        String rand = "";
        for(int i=0;i<5;i++){
            int randomInt = new Random().nextInt(9);
            rand += randomInt;
        }
        Map<String,String> resultMap=new HashMap<>();
        resultMap.put("sessionNo",s+rand);
        return new ResultModel(true,0,"获取成功！",resultMap);
    }

    @PostMapping(value = "/getCustomerGroupList",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "获取有效可用的用户组",notes = "")
    @AuthUserToken
    @RequestAuth(false)
    public ResultModel getCustomerGroupList(){
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/group/selectGroupForSelect"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(null),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.LIST);
    }

    @PostMapping(value = "/uploadImage",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "上传封面图片",notes = "")
    @ApiImplicitParam(name = "file",value = "上传的文件",required = true,paramType = "body",dataType = "file")
    @AuthPublic
    @RequestAuth(false)
    public ResponseEntity<ResultModel> uploadImage(MultipartFile file){
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
    @PostMapping(value = "/getAgencyCompany",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "获取所有可用的代办公司",notes = "")
    @AuthUserToken
    @RequestAuth(false)
    public ResponseEntity<ResultModel> getAgencyCompany(@RequestBody Map<String,Object> map){
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/agentCompany/selectAllAgentCompany"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.LIST);
    }

    @PostMapping(value = "/getCity",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "获取所有的城市信息",notes = "")
    @AuthUserToken
    @RequestAuth(false)
    public ResponseEntity<ResultModel> getCity(){
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/wtRegion/getAll"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("1"),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.LIST);
    }
    @PostMapping(value = "/saveLocaleAuction",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "保存竞拍场次",notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title",value = "场次主题",required = true,paramType = "body",dataType = "string"),
            @ApiImplicitParam(name = "regionId",value = "可见范围，客户组id",required = true,paramType = "body",dataType = "long"),
            @ApiImplicitParam(name = "cityId",value = "场次所在城市",required = true,paramType = "body",dataType = "long"),
            @ApiImplicitParam(name = "poster",value = "封面图片",required = false,paramType = "body",dataType = "string"),
            @ApiImplicitParam(name = "startTime",value = "开拍时间",required = true,paramType = "body",dataType = "object"),
            @ApiImplicitParam(name = "corporateAgent",value = "代办公司",required = true,paramType = "body",dataType = "long"),
            @ApiImplicitParam(name = "address",value = "拍卖地址",required = true,paramType = "body",dataType = "string"),
            @ApiImplicitParam(name = "seeCarMan",value = "看车联系人",required = true,paramType = "body",dataType = "string"),
            @ApiImplicitParam(name = "seeCarPhone",value = "看车联系电话",required = true,paramType = "body",dataType = "string"),
            @ApiImplicitParam(name = "seeCarTime",value = "看车时间",required = true,paramType = "body",dataType = "string")
    })
    @AuthUserToken
    public ResponseEntity<ResultModel> saveLocaleAuction(@CurrentUserId Long userId, @RequestBody Map<String,Object> map){
        map.put("createPerson",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carLocaleAuction/saveLocaleAuction"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }

    /***********************************************竞拍场次编辑界面相关接口**********************************************/

    @PostMapping(value = "/getLocaleAuctionById",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "根据Id获取到场次的信息",notes = "")
    @ApiImplicitParam(name = "id",value = "场次主键",required = true,paramType = "body",dataType = "long")
    @AuthUserToken
    public ResponseEntity<ResultModel> getLocaleAuctionById(@RequestBody Map<String,Object> map){
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carLocaleAuction/getLocaleAuctionById"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }


    @PostMapping(value = "/updateLocaleAuction",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "更新竞拍场次",notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "场次主键",required = true,paramType = "body",dataType = "long"),
            @ApiImplicitParam(name = "code",value = "场次编号",required = true,paramType = "body",dataType = "string"),
            @ApiImplicitParam(name = "title",value = "场次主题",required = true,paramType = "body",dataType = "string"),
            @ApiImplicitParam(name = "regionId",value = "可见范围，客户组id",required = true,paramType = "body",dataType = "long"),
            @ApiImplicitParam(name = "cityId",value = "场次所在城市",required = true,paramType = "body",dataType = "long"),
            @ApiImplicitParam(name = "poster",value = "封面图片",required = false,paramType = "body",dataType = "string"),
            @ApiImplicitParam(name = "startTime",value = "开拍时间",required = true,paramType = "body",dataType = "object"),
            @ApiImplicitParam(name = "corporateAgent",value = "代办公司",required = true,paramType = "body",dataType = "long"),
            @ApiImplicitParam(name = "address",value = "拍卖地址",required = true,paramType = "body",dataType = "string"),
            @ApiImplicitParam(name = "seeCarMan",value = "看车联系人",required = true,paramType = "body",dataType = "string"),
            @ApiImplicitParam(name = "seeCarPhone",value = "看车联系电话",required = true,paramType = "body",dataType = "string"),
            @ApiImplicitParam(name = "seeCarTime",value = "看车时间",required = true,paramType = "body",dataType = "string")
    })
    @AuthUserToken
    public ResponseEntity<ResultModel> updateLocaleAuction(@CurrentUserId Long userId,@RequestBody Map<String,Object> map){
        map.put("modifyPerson",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carLocaleAuction/updateLocaleAuction"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }

    /****************************************场次-车辆管理相关接口********************************************/



    @PostMapping(value = "/getLocaleAuctionCar",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "获取参加现场竞拍的车辆",notes = "")
    @ApiImplicitParam(name = "searchParam",value = "车辆标题/车辆编号",required = true,paramType = "body",dataType = "string")
    @AuthUserToken
    public ResponseEntity<ResultModel> getLocaleAuctionCar(@RequestBody Map<String,Object> map){
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carLocaleAuction/getLocaleAuctionCar"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.LIST);

    }

    @PostMapping(value = "/saveLocaleAuctionCar",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "保存参加竞拍的车辆",notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "auctionId",value = "场次主键",required = true,paramType = "body",dataType = "long"),
            @ApiImplicitParam(name = "carIds",value = "车辆编号",required = true,paramType = "body",dataType = "string")
    })
    @AuthUserToken
    public ResponseEntity<ResultModel> saveLocaleAuctionCar(@CurrentUserId Long userId,@RequestBody Map<String,Object> map){
        map.put("userId",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carLocaleAuction/saveLocaleAuctionCar"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }

    /************************************************场次管理--竞价详情相关接口****************************************************/

    @PostMapping(value = "/getLocaleAuctionDetail",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "查询场次信息及车辆列表",notes = "")
    @ApiImplicitParam(name = "id",value = "场次主键",required = true,paramType = "query",dataType = "long")
    @AuthUserToken
    public ResponseEntity<ResultModel> getLocaleAuctionDetail(@RequestBody Map<String,Object> map){
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carLocaleAuction/getLocaleAuctionDetail"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }

    @PostMapping(value = "/adjustAuctionCarSort",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "调整车辆顺序",notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "auctionCarId",value = "车辆关联表id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "sort",value = "顺序",required = true,paramType = "query",dataType = "int")
    })
    @AuthUserToken
    public ResponseEntity<ResultModel> adjustAuctionCarSort(@RequestBody Map<String,Object> map){
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carLocaleAuction/adjustAuctionCarSort"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }

    @PostMapping(value = "/getAuctionCarList",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "根据场次ID查询该场次的所有车辆",notes = "")
    @ApiImplicitParam(name = "auctionId",value = "场次ID",required = true,paramType = "query",dataType = "long")
    @AuthUserToken
    public ResponseEntity<ResultModel> getAuctionCarList(@RequestBody Map<String,Object> map){
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carLocaleAuction/getAuctionCarList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.LIST);
    }



    @PostMapping(value = "/deleteAuctionCar",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "删除竞拍场次中的车辆",notes = "")
    @ApiImplicitParam(name = "auctionCarId",value = "竞拍场次车辆关联Id",required = true,paramType = "query",dataType = "long")
    @AuthUserToken
    public ResponseEntity<ResultModel> deleteAuctionCar(@CurrentUserId Long userId, @RequestBody Map<String,Object> map){
        map.put("userId",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carLocaleAuction/deleteAuctionCar"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }

    @PostMapping(value = "/getAuctionCarInfo",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "获取关联车辆信息",notes = "")
    @ApiImplicitParam(name = "auctionCarId",value = "竞拍场次车辆关联Id",required = true,paramType = "query",dataType = "long")
    @AuthUserToken
    public ResponseEntity<ResultModel> getAuctionCarInfo(@RequestBody Map<String,Object> map){
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carLocaleAuction/getAuctionCarInfo"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }

    @PostMapping(value = "/getCustomerInfo",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "查询车商信息",notes = "")
    @AuthUserToken
    public ResponseEntity<ResultModel> getCustomerInfo(){
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/appuser/selectAllUserForSelect"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(null),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.LIST);
    }

    @PostMapping(value = "/saveBiddenInfo",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "保存出价信息",notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "auctionId",value = "竞拍场次Id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "auctionCarId",value = "竞拍场次车辆Id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "carId",value = "车辆Id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "customerId",value = "车商id",required = true,paramType = "body",dataType = "long"),
            @ApiImplicitParam(name = "lastAmount",value = "最高出价",required = true,paramType = "query",dataType = "double")
    })
    @AuthUserToken
    public ResponseEntity<ResultModel> saveBiddenInfo(@RequestBody Map<String,Object> map){
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carLocaleAuction/saveBiddenInfo"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }

    @PostMapping(value = "/saveBiddenInfoByCode",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "根据车商编号保存出价信息",notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "auctionId",value = "竞拍场次Id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "auctionCarId",value = "竞拍场次车辆Id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "carId",value = "车辆Id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "customerCode",value = "车商编号",required = true,paramType = "body",dataType = "string"),
            @ApiImplicitParam(name = "lastAmount",value = "最高出价",required = true,paramType = "query",dataType = "double")
    })
    @AuthUserToken
    public ResponseEntity<ResultModel> saveBiddenInfoByCode(@RequestBody Map<String,Object> map){
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carLocaleAuction/saveBiddenInfoByCode"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }

    @PostMapping(value = "/cleanBiddenInfo",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "清除车辆的出价记录",notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "auctionId",value = "竞拍场次Id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "auctionCarId",value = "竞拍场次车辆Id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "carId",value = "车辆Id",required = true,paramType = "query",dataType = "long")
    })
    @AuthUserToken
    public ResponseEntity<ResultModel> cleanBiddenInfo(@RequestBody Map<String,Object> map){
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carLocaleAuction/cleanBiddenInfo"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }

    @PostMapping(value = "/cleanLastBiddenInfo",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "清除车辆的最后一条出价记录",notes = "")
    @ApiImplicitParam(name = "auctionCarId",value = "竞拍场次车辆Id",required = true,paramType = "query",dataType = "long")
    //@AuthUserToken
    public ResponseEntity<ResultModel> cleanLastBiddenInfo(@RequestBody Map<String,Object> map){
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carLocaleAuction/cleanLastBiddenInfo"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }

    @PostMapping(value = "/getCustomerBidPriceList",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "查询该车辆的出价记录列表",notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "auctionId",value = "竞拍场次Id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "auctionCarId",value = "竞拍场次车辆Id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "carId",value = "车辆Id",required = true,paramType = "query",dataType = "long"),
    })
    @AuthUserToken
    public ResponseEntity<ResultModel> getCustomerBidPriceList(@RequestBody Map<String,Object> map){
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carLocaleAuction/getCustomerBidPriceList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.LIST);
    }

    @PostMapping(value = "/setCustomerBidNumber",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "车商号补全",notes = "")
    @ApiImplicitParam(name = "auctionBidRecordIds",value = "出价记录Ids 规则，auctionBidRecordId1_customerId1,auctionBidRecordId2_customerId2",required = true,paramType = "query",dataType = "long")
    @AuthUserToken
    public ResponseEntity<ResultModel> setCustomerBidNumber(@RequestBody Map<String,Object> map){
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carLocaleAuction/setCustomerBidNumber"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }

    @PostMapping(value = "/setCustomerBidNumberByCode",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "根据车商编码车商号补全",notes = "")
    @ApiImplicitParam(name = "auctionBidRecordCodes",value = "出价记录Ids 规则，auctionBidRecordId1_amount_customerCode1,auctionBidRecordId1_amount_customerCode1",required = true,paramType = "query",dataType = "long")
    @AuthUserToken
    public ResponseEntity<ResultModel> setCustomerBidNumberByCode(@RequestBody Map<String,Object> map){
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carLocaleAuction/setCustomerBidNumberByCode"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }


    @PostMapping(value = "/setAgainAuction",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "设置车辆二拍",notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "auctionCarId",value = "场次车辆ID",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "carId",value = "车辆信息Id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "startAmount",value = "起步价",required = true,paramType = "query",dataType = "double"),
            @ApiImplicitParam(name = "reserveAmount",value = "保留价",required = true,paramType = "body",dataType = "double")
    })
    @AuthUserToken
    public ResponseEntity<ResultModel> setAgainAuction(@CurrentUserId Long userId, @RequestBody Map<String,Object> map){
        map.put("userId",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carLocaleAuction/setAgainAuction"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResponseEntity(response,resultModel, ApiUtil.OBJECT);
    }


    /****************************************************大屏显示相关接口**********************************************************/




    @PostMapping(value = "/largeScreenDisplay",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "大屏显示列表",notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status",value = "竞拍状态",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "title",value = "场次主题",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "beginTime",value = "开拍开始时间",required = false,paramType = "query",dataType = "object"),
            @ApiImplicitParam(name = "endTime",value = "开拍结束时间",required = false,paramType = "query",dataType = "object"),
            @ApiImplicitParam(name = "page",value = "当前页数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "limit",value = "每页显示的条数",required = true,paramType = "query",dataType = "int")
    })
    @AuthUserToken
    public ResultModel largeScreenDisplay(@RequestBody Map<String,Object> map){
        if(map.get("page")==null || map.get("limit")==null){
            return new ResultModel(false,101,"缺少page或者limit参数",null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carLocaleAuction/largeScreenDisplay"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }


    @PostMapping(value = "/largeScreenAuctionInfo",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "获取大屏场次信息",notes = "")
    @ApiImplicitParam(name = "auctionId",value = "场次id",required = false,paramType = "body",dataType = "long")
    @AuthUserToken
    public ResultModel largeScreenAuctionInfo(@RequestBody Map<String,Object> map){
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carLocaleAuction/largeScreenAuctionInfo"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    @PostMapping(value = "/clearLargeScreenAuctionCar",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "清除车辆缓存操作",notes = "")
    @ApiImplicitParam(name = "auctionId",value = "场次id",required = false,paramType = "body",dataType = "long")
    @AuthUserToken
    public ResultModel clearLargeScreenAuctionCar(@RequestBody Map<String,Object> map){
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carLocaleAuction/clearLargeScreenAuctionCar"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }



    @PostMapping(value = "/largeScreenShowCar",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "大屏显示--单条记录",notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "auctionId",value = "场次id",required = false,paramType = "body",dataType = "long"),
            @ApiImplicitParam(name = "auctionCarId",value = "车辆场次id",required = false,paramType = "query",dataType = "object")
    })
    @AuthUserToken
    public ResultModel largeScreenShowCar(@RequestBody Map<String,Object> map){
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carLocaleAuction/largeScreenShowCar"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    @PostMapping(value = "/largeScreenStartAuction",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "大屏显示--开拍操作",notes = "")
    @ApiImplicitParam(name = "auctionId",value = "场次id",required = false,paramType = "body",dataType = "long")
    @AuthUserToken
    public ResultModel largeScreenStartAuction(@RequestBody Map<String,Object> map){
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carLocaleAuction/largeScreenStartAuction"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    @PostMapping(value = "/largeScreenBidPrice",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "大屏显示--获取当前车辆的最高出价信息",notes = "")
    @ApiImplicitParam(name = "auctionCarId",value = "场次车辆id",required = false,paramType = "body",dataType = "long")
    @AuthUserToken
    public ResultModel largeScreenBidPrice(@RequestBody Map<String,Object> map){
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carLocaleAuction/largeScreenBidPrice"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    @PostMapping(value = "/largeScreenAuctionResult",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "大屏显示--确定当前车的竞拍结果",notes = "")
    @ApiImplicitParam(name = "auctionCarId",value = "场次车辆id",required = false,paramType = "body",dataType = "long")
    @AuthUserToken
    public ResultModel largeScreenAuctionResult(@CurrentUserId Long userId,@RequestBody Map<String,Object> map){
        map.put("userId",userId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carLocaleAuction/largeScreenAuctionResult"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }
    @PostMapping(value = "/largeScreenAuctionFinish",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "大屏显示--本次竞拍结束",notes = "")
    @ApiImplicitParam(name = "auctionId",value = "场次id",required = false,paramType = "body",dataType = "long")
    @AuthUserToken
    public ResultModel largeScreenAuctionFinish(@RequestBody Map<String,Object> map){
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carLocaleAuction/largeScreenAuctionFinish"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /************************************************************参拍车辆列表*******************************************************************/

    @PostMapping(value = "/hasAuctionCarList",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "参拍车辆列表",notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "beginTime",value = "开拍开始时间",required = false,paramType = "query",dataType = "object"),
            @ApiImplicitParam(name = "endTime",value = "开拍结束时间",required = false,paramType = "query",dataType = "object"),
            @ApiImplicitParam(name = "title",value = "场次主题",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "cityId",value = "城市Id",required = false,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "page",value = "当前页数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "limit",value = "每页显示的条数",required = true,paramType = "query",dataType = "int")
    })
    public ResultModel hasAuctionCarList(@RequestBody Map<String,Object> map){
        if(map.get("page")==null || map.get("limit")==null){
            return new ResultModel(false,101,"缺少page或者limit参数",null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carLocaleAuction/hasAuctionCarList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }
    /**
     * 获取场次车辆列表
     * @param map
     * @return
     */
    @PostMapping(value = "/selectAuctionCarList",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthPublic
    public ResultModel selectAuctionCarList(@RequestBody Map<String,Object> map) {
      if (map.get("auctionId")==null){
          return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
      }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carLocaleAuction/selectShareAuctionList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 查询单个竞拍详情
     * @return
     */
    @RequestMapping(value = "/selectLocaleAuctionCar",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel selectLocaleAuctionCar(@RequestBody Map<String,Object> map) {
        if(map.get("auctionCarId")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carLocaleAuction/selectLocaleAuctionCar"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }
}
