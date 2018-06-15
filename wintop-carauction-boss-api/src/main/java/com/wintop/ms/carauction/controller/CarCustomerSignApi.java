package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthPublic;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.CurrentUser;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.config.ResultStatus;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.util.utils.ApiUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangzijuan
 * @Description:
 * @date 2018-03-20
 */
@RestController
@RequestMapping(value = "/sign")
@Api(tags = "签约信息相关",value = "签约信息相关接口")
public class CarCustomerSignApi {

    private Logger logger = LoggerFactory.getLogger(CarCustomerSignApi.class);
    private ResultModel resultModel;
    private final RestTemplate restTemplate;
    CarCustomerSignApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    /**
     * 根据签约id查询合同信息
     *@Author:zhangzijuan
     *@date 2018/3/20
     *@param:map
     */
    @ApiOperation(value = "根据签约id查询合同信息")
    @ApiImplicitParam(name = "signId",value = "签约Id",required = true,paramType = "query",dataType = "long")
    @PostMapping(value = "/getSignInfoSignId",produces="application/json; charset=UTF-8")
    @AuthUserToken
    public ResultModel getSignInfoSignId(@RequestBody Map<String,Object> map) {
        if(map.get("signId")==null){
            return new ResultModel(false, ResultCode.NO_PARAM.value(),ResultCode.NO_PARAM.getRemark(),null);
        }
        ResponseEntity<JSONObject> response = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(Constants.ROOT+"/sign/selectSignLogBySignId"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map.get("signId")),JSONObject.class);
        return ApiUtil.getResultModel(response,ApiUtil.LIST);
    }

    /**
     * 签约审核
     */
    @PostMapping(value = "/authSignature")
    @AuthPublic
    public ResponseEntity<ResultModel> authSignature(@RequestBody JSONObject object) {
        ResponseEntity<ResultModel> responseEntity = null;
        logger.info("保存签字，生成甲方签字合同");
        try {
            //1、获取用户数据
            Long customerId = object.getLong("customerId");
            String authStatus = object.getString("authStatus");
            if (authStatus != null && "2".equals(authStatus)) {
                //审核通过
                //a、获取当前用户的签名合同，调用签约服务接口生成签章合同

                //3、调用e签宝生成签字版合同
                Map map = new HashMap();
                map.put("user", object.get("realName"));
                map.put("cus_key", Constants.SIGNATURE_BSE_KEY);
                map.put("path_src", "甲方签字的合同pdf_url");// TODO: 2018/3/29 动态获取
                ResponseEntity<JSONObject> resultResponseEntity = this.restTemplate.exchange(
                        RequestEntity
                                .post(URI.create(Constants.PLATFORM_SIGNATURE_URL))
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(map, Map.class), JSONObject.class);
                JSONObject serviceResult = resultResponseEntity.getBody();
                logger.info(serviceResult.toJSONString());
                if (resultResponseEntity.getStatusCode() == HttpStatus.OK) {
                    if ("0".equals(serviceResult.get("errcode").toString())) {
                        String picture = serviceResult.getString("picture");
                        String pdf = serviceResult.getString("pdf");
                        //4、保存签约成功的信息
                        HashMap saveSignMap = new HashMap();
                        saveSignMap.put("pdfUrl", pdf);
                        saveSignMap.put("picUrl", picture);
                        saveSignMap.put("log", serviceResult.toJSONString());
                        saveSignMap.put("customerId", customerId);


                        //5、保存签约表状态 + 保存签约日志 + 修改用户签约状态
                    } else {
                        //签约失败
                        //5、保存签约表状态 + 保存签约日志 + 修改用户签约状态
                    }

                } else {
                    //审核失败
                    //5、保存签约表状态 + 修改用户签约状态
                }
            } else {
                //审核失败
                //5、保存签约表状态 + 修改用户签约状态
            }
        }catch (Exception e){
            e.printStackTrace();
            resultModel = ResultModel.error(ResultStatus.SERVICE_ERROR);
            responseEntity = new ResponseEntity<>(resultModel,HttpStatus.INTERNAL_SERVER_ERROR);
        }finally {
            if (resultModel==null || responseEntity == null){
                resultModel = ResultModel.error(ResultStatus.SERVICE_ERROR);
                responseEntity = new ResponseEntity<>(resultModel, HttpStatus.OK);
            }
            return responseEntity;
        }
    }

}
