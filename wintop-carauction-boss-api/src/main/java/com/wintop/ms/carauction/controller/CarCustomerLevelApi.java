package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.TokenManager;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.core.model.TokenModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

/**
 * @author zhangzijuan
 * @Description:会员级别信息
 * @date 2018-03-15
 */
@RestController
@RequestMapping("/level")
public class CarCustomerLevelApi {
    private final RestTemplate restTemplate;
    @Autowired
    CarCustomerLevelApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    /**
     * 查询会员级别
     *@Author:zhangzijuan
     *@date 2018/3/14
     *@param:map
     */
    @ApiOperation(value = "查询会员级别")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "levelName",value = "客户等级名称",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "page",value = "当前页数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "limit",value = "每页显示的条数",required = true,paramType = "query",dataType = "int")
    })
    @PostMapping(value = "/getCustomerLevelList",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel getCustomerLevelList(@RequestBody Map<String,Object> map) {
        if(map.get("page")==null || map.get("limit")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/level/selectListByParam"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 删除会员级别
     *@Author:zhangzijuan
     *@date 2018/3/14
     *@param:map
     */
    @ApiOperation(value = "删除会员级别")
    @ApiImplicitParam(dataType = "Long", name = "levelId", value = "会员级别Id", required = true)
    @PostMapping(value = "/deleteLevel",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel deleteLevel(@RequestBody Map<String,Object> map) {
        if(map.get("levelId")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/level/deleteLevel"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 设置会员级别开启
     *@Author:zhangzijuan
     *@date 2018/3/15
     *@param:map
     */
    @ApiOperation(value = "设置会员级别开启")
    @ApiImplicitParam(dataType = "Long", name = "levelId", value = "会员级别Id", required = true)
    @PostMapping(value = "/setIsOpenLevel",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel isOpenLevel(@RequestBody Map<String,Object> map) {
        if(map.get("levelId")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/level/isOpenLevel"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 修改会员级别
     *@Author:zhangzijuan
     *@date 2018/3/15
     *@param:map
     */
    @ApiOperation(value = "修改会员级别")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "客户级别Id",required = false,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "code",value = "用户等级编号",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "levelName",value = "客户等级名称",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "depositMoney",value = "该等级所需缴纳保证金金额",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "sort",value = "排序",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "isDefault",value = "'是否默认",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "isOpen",value = "是否开启",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "fareIds",value = "竞拍加价金额（加价金额id用逗号拼接）",required = false,paramType = "query",dataType = "string")
    })
    @PostMapping(value = "/editLevel",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel updateLevel(@CurrentUserId Long updatePerson,@RequestBody Map<String,Object> map) {
        map.put("updatePerson",updatePerson);
        if(map.get("id")==null || map.get("levelName")==null || map.get("updatePerson")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/level/updateLevel"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }
    /**
     * 保存会员级别
     *@Author:zhangzijuan
     *@date 2018/3/15
     *@param:map
     */
    @ApiOperation(value = "保存会员级别")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code",value = "用户等级编号",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "levelName",value = "客户等级名称",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "depositMoney",value = "该等级所需缴纳保证金金额",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "sort",value = "排序",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "isDefault",value = "'是否默认",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "isOpen",value = "是否开启",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "fareIds",value = "竞拍加价金额（加价金额id用逗号拼接）",required = false,paramType = "query",dataType = "string")
    })
    @PostMapping(value = "/saveLevel",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel saveLevel(@CurrentUserId Long createPerson, @RequestBody Map<String,Object> map) {
        map.put("createPerson",createPerson);
        if(map.get("levelName")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/level/saveLevel"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 设置会员级别开启
     *@Author:zhangzijuan
     *@date 2018/3/15
     *@param:map
     */
    @ApiOperation(value = "设置默认级别")
    @ApiImplicitParam(dataType = "Long", name = "levelId", value = "会员级别Id", required = true)
    @PostMapping(value = "/setDefaultLevel",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel setDefaultLevel(@RequestBody Map<String,Object> map) {
        if(map.get("levelId")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/level/isDefaultLevel"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 查询所有开启的会员级别
     *@Author:zhangzijuan
     *@date 2018/3/15
     *@param:map
     */
    @ApiOperation(value = "查询所有开启的会员级别")
    @PostMapping(value = "/getAllLevel",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel selectAllLevel(){
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/level/selectAllLevel"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(null),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.LIST);
    }

    /**
     * 设置会员级别开启
     *@Author:zhangzijuan
     *@date 2018/3/15
     *@param:map
     */
    @ApiOperation(value = "查询会员级别详情")
    @ApiImplicitParam(dataType = "Long", name = "levelId", value = "级别Id", required = true)
    @RequestMapping(value = "/getLevelById",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel getLevelById(@RequestBody Map<String,Object> map) {
        if(map.get("levelId")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/level/selectLevelById"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }
}
