package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import io.swagger.annotations.ApiImplicitParam;
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
 * @Description:
 * @date 2018-03-26
 */
@RestController
@RequestMapping("/transferLog")
public class CarAutoTransferLogApi {
    private final RestTemplate restTemplate;
    CarAutoTransferLogApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    /**
     * 查询车辆轨迹
     *@Author:zhangzijuan
     *@date 2018/3/23
     *@param:map
     */
    @ApiOperation(value = "查询过户轨迹")
    @ApiImplicitParam(name = "transferId",value = "过户Id",required = true,paramType = "query",dataType = "Long")
    @PostMapping(value = "/getTransferList",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel getTransferList(@RequestBody Map<String,Object> map) {
        if(map.get("transferId")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/transferLog/queryTransferList"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.LIST);
    }

}
