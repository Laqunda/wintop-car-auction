package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.annotation.RequestAuth;
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
 * @Description:请求接口地址Url
 * @date 2018-04-11
 */
@RestController
@RequestMapping("/managerPage")
public class CarManagerPageApi {
    private final RestTemplate restTemplate;
    CarManagerPageApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 根据角色id查询可见页面
     *@Author:zhangzijuan
     *@date 2018-04-11
     */
    @ApiOperation(value = "根据角色id查询可见页面")
    @ApiImplicitParam(name = "roleId",value = "角色id",required = true,dataType = "long")
    @PostMapping(value = "/getPageTreeList",
            consumes = "application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel getPageTreeList(@RequestBody Map<String,Object> map) {
        if(map.get("roleId")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carManagerPage/getPageTreeList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.LIST);
    }

    /**
     * 根据角色id查询可见页面
     *@Author:zhangzijuan
     *@date 2018-04-11
     */
    @ApiOperation(value = "根据角色id查询可见页面")
    @PostMapping(value = "/getPageTreeForTwoNode",
            consumes = "application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel getPageTreeForTwoNode( @CurrentUserId Long manaagerId) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("managerId", manaagerId);
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carManagerPage/getPageTreeForTwoNode"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.LIST);
    }
    /**
     * 修改角色权限
     *@Author:zhangzijuan
     *@date 2018/4/11
     *@param:
     */
    @ApiOperation(value = "修改角色权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId",value = "角色id",required = true,dataType = "long"),
            @ApiImplicitParam(name = "pageIds",value = "页面id 多个用逗号拼接",required = true,dataType = "long")
    })
    @PostMapping(value = "/editRolePage",
            consumes = "application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel editRolePage(@RequestBody Map<String,Object> map) {
        if(map.get("pageIds")==null || map.get("roleId")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/rolePage/editRolePage"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

    /**
     * 根据父节点查询
     *@Author:zhangzijuan
     *@date 2018-04-13
     */
    @ApiOperation(value = "根据父节点查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId",value = "角色id",required = true,dataType = "long"),
            @ApiImplicitParam(name = "parentId",value = "父节点id",required = true,dataType = "long")
    })
    @PostMapping(value = "/getPageTreeByPId",
            consumes = "application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel getPageTreeByPId(@RequestBody Map<String,Object> map) {
        if(map.get("roleId")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        if (map.get("parentId")==null){
            map.put("parentId",0l);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/carManagerPage/getPageTreeByPId"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.LIST);
    }
}
