package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.annotation.InfoNotify;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Map;

/**
 * 议价审核接口
 */
@RestController
@RequestMapping("/bargainingAudit")
public class BargainingAuditApi {
    private ResultModel resultModel;

    private final RestTemplate restTemplate;

    BargainingAuditApi(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

    /**
     *议价审核(成功/失败)接口
     */
    @PostMapping(value = "/insertBargainingAuditSure",
            produces="application/json; charset=UTF-8")
    @ApiOperation(value = "议价审核")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "carId",value = "车辆id",required = true,paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "status",value = "议价状态 1成功 2失败",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "bargainFee",value = "议价价格",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "msg",value = "备注",required = false,paramType = "query",dataType = "string"),
    })
    @AuthUserToken
    @InfoNotify
    public ResultModel insertBargainingAuditSure(@RequestBody Map<String,Object> map, @CurrentUserId Long userId) throws MalformedURLException {
        map.put("managerId",userId);
        if (map.get("carId")==null || map.get("status")==null || map.get("bargainFee")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/service/bargainingAudit/insertBargainingAuditSure"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        return ApiUtil.getResultModel(response, ApiUtil.OBJECT);
    }

}
