package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthPublic;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.TokenManager;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.core.model.TokenModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import com.wintop.ms.carauction.util.utils.ParamValidUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

/**
 * @author admin
 * @Description:角色信息
 * @date 2018-03-15
 */
@RestController
@RequestMapping("/managerRole")
public class CarManagerRoleApi {
    private final RestTemplate restTemplate;
    CarManagerRoleApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    /**
     * 查询所有角色
     *@Author:admin
     *@date 2018/3/14
     *@param:map
     */
    @ApiOperation(value = "查询所有角色")
    @RequestMapping(value = "/selectAllRole",
            consumes = "application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel selectAllRole() {
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/managerRole/selectAllRole"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(null),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.LIST);
    }

    /**
     * 查询单个角色信息
     *@Author:admin
     *@date 2018/3/14
     *@param:map
     */
    @ApiOperation(value = "查询单个角色信息")
    @RequestMapping(value = "/selectRoleById",
            consumes = "application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel selectRoleById(@RequestBody Map<String,Object> map) {
        if(ParamValidUtil.invalidMapParam(map,"roleId")){
            new ResultModel(true, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/managerRole/selectRoleById"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }
}
