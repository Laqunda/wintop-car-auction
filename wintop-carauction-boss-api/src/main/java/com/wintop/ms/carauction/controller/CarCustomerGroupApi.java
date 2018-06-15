package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
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
 * @Description:会员分组
 * @date 2018-03-16
 */
@RestController
@RequestMapping(value = "/group")
public class CarCustomerGroupApi {
    private final RestTemplate restTemplate;
    CarCustomerGroupApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    /**
     * 查询用户分组及对应的用户数量
     *@Author:zhangzijuan
     *@date 2018-03-16
     *@param:map
     */
    @ApiOperation(value = "查询用户分组及对应的用户数量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "searchName",value = "姓名、手机号模糊搜索",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "status",value = "会员状态",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "storeId",value = "店铺ID",required = false,paramType = "query",dataType = "long"),
    })
    @PostMapping(value = "/getGroupAndNum",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel getGroupAndNum(@RequestBody Map<String,Object> map) {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/group/selectGroupAndNum"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.LIST);
    }

    /**
     * 查询用户分组及对应的用户数量
     *@Author:zhangzijuan
     *@date 2018-03-16
     *@param:map
     */
    @ApiOperation(value = "新建会员分组")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupName",value = "客户分组名称",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "remark",value = "会员状态",required = false,paramType = "query",dataType = "string"),
    })
    @PostMapping(value = "/saveGroup",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel saveGroup(@CurrentUserId Long managerId,@RequestBody Map<String,Object> map) {
        if(map.get("groupName")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        map.put("createManager",managerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/group/saveGroup"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.OBJECT);
    }

    /**
     * 查询所有可以选择的用户分组
     *@Author:zhangzijuan
     *@date 2018-03-17
     *@param:map
     */
    @ApiOperation(value = "查询所有可以选择的用户分组")
    @PostMapping(value = "/getGroupForSelect",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel getGroupForSelect() {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/group/selectGroupForSelect"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(null),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.LIST);
    }


    /**
     * 删除会员分组
     *@Author:zhangzijuan
     *@date 2018/3/17
     *@param:map
     */
    @ApiOperation(value = "删除会员分组")
    @ApiImplicitParam(dataType = "Long", name = "groupId", value = "会员级别Id", required = true)
    @PostMapping(value = "/deleteGroupById",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel deleteGroupById(@RequestBody Map<String,Object> map) {
        if(map.get("groupId")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/group/deleteGroupById"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }
}
